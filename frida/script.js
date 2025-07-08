// By root7325
// Part of https://gitlab.com/root-7325/JavaBS / https://github.com/root-7325/JavaBS

const Libg = {
    init() {
        let module = Process.findModuleByName('libg.so');
        if (!module) {
            return false;
        }

        Libg.begin = module.base;
        Libg.size = module.size;
        Libg.end = Libg.begin.add(Libg.size);
        return true;
    },
    offset(off) {
        return Libg.begin.add(off);
    }
};

const Armceptor = {
    nop(addr) {
        Memory.patchCode(addr, Process.pageSize, function (code) {
            var writer = new ArmWriter(code, {
                pc: addr
            });

            writer.putNop();
            writer.flush();
        });
    },
    ret(addr) {
        Memory.patchCode(addr, Process.pageSize, function (code) {
            var writer = new ArmWriter(code, {
                pc: addr
            });

            writer.putRet();
            writer.flush();
        });
    },
    jumpout(addr, target) {
        Memory.patchCode(addr, Process.pageSize, function (code) {
            var writer = new ArmWriter(code, {
                pc: addr
            });
            writer.putBranchAddress(target);
            writer.flush();
        });
    }
}

// Incompatible with Frida 17; triggers TypeError: not a function
// Use gadget from Frida 16 if needed
const JavaUtils = {
    toast(text) {
        Java.perform(() => {
            Java.scheduleOnMainThread(() => {
                Java.use("android.widget.Toast")
                    .makeText(Java.use('android.app.ActivityThread').currentApplication().getApplicationContext(), Java.use("java.lang.String").$new(text), 1)
                    .show();
            })
        });
    },

    exit(text) {
        if (text) {
            this.toast(text);
        }

        Java.scheduleOnMainThread(() => {
            Java.use("java.lang.System").exit(0);
        });
    }
}

const Config = {
    HOST_PATCH: {
        "original": "game.brawlstarsgame.com",
        "target": "SET_YOUR_IP"
    },
    LOGIC_VERSION: {
        "isProd": 0,
        "isDev": 1,
    },
    ENABLE_OFFLINE_BATTLES: true,
    CLIENT_SECRET: [0xa3, 0x18, 0x3e, 0x2d, 0xfc, 0x28, 0xd4, 0x8a, 0x4e, 0x83, 0x37, 0xf5, 0xbd, 0x97, 0xb0, 0xd5, 0x30, 0x98, 0xb3, 0x2a, 0xed, 0xe9, 0x60, 0x60, 0x0c, 0xcd, 0xef, 0xb5, 0x1d, 0xce, 0x8e, 0x1c]
}

const Addresses = {
    createGameJump: 0x32FAF0,
    createGameClean: 0x32FBEC,
    unk: 0x10F038, // I dunno what 'sub_1F71E0' purpose is (except of crashing game), but noping it makes things work.
    unk2Jump: 0x3BD214,
    unk2Clean: 0x3BE398,
    loginJump: 0x07633C,
    loginClean: 0x0763E4,
    ultButtonJump: 0x180180,
    ultButtonClean: 0x1804C0,
    serverConnection: 0x060EF8,
    randomBytes: 0x171540,
    homePage: 0x271114,
    isProd: 0x22B384,
    isDev: 0x1E3AFC,
    debugger: 0x1D7DA8
}

const ArxanPatcher = {
    init() {
        Armceptor.jumpout(Libg.offset(Addresses.createGameJump), Libg.offset(Addresses.createGameClean));
        Armceptor.nop(Libg.offset(Addresses.unk));
        Armceptor.jumpout(Libg.offset(Addresses.loginJump), Libg.offset(Addresses.loginClean));
        Armceptor.jumpout(Libg.offset(Addresses.ultButtonJump), Libg.offset(Addresses.ultButtonClean));
        Armceptor.jumpout(Libg.offset(Addresses.unk2Jump), Libg.offset(Addresses.unk2Clean));

        console.log("[*] Patched Arxan prots")
    }
}

const GlobalPatcher = {
    init() {
        this.patchServerConnection();
        this.patchSecretKey();
        this.patchLogicVersion();
        if (Config.ENABLE_OFFLINE_BATTLES) {
            this.patchOfflineBattles();
        }
    },

    patchServerConnection() {
        Interceptor.attach(Libg.offset(Addresses.serverConnection), {
            onEnter(args) {
                args[1].add(8).readPointer().writeUtf8String(Config.HOST_PATCH.target);
                console.log(`[*] Redirected to ${Config.HOST_PATCH.target}`);
            }
        });
    },

    patchSecretKey() {
        Interceptor.attach(Libg.offset(Addresses.randomBytes), {
            onEnter(args) {
                this.buffer = args[0];
            },
            onLeave() {
                this.buffer.writeByteArray(Config.CLIENT_SECRET);
                console.log("[*] Patched client secret key");
            }
        })
    },

    patchOfflineBattles() {
        Interceptor.attach(Libg.offset(Addresses.homePage), {
            onEnter(args) {
                args[2] = ptr(3);

                console.log("[*] Enabled offline battles");
            }
        })
    },

    patchLogicVersion() {
        Interceptor.replace(Libg.offset(Addresses.isProd), new NativeCallback(() => Config.LOGIC_VERSION.isProd, 'int', []));
        Interceptor.replace(Libg.offset(Addresses.isDev), new NativeCallback(() => Config.LOGIC_VERSION.isDev, 'int', []));

        console.log("[*] Patched LogicVersion calls");
    }
}

rpc.exports.init = function () {
    if (!Libg.init()) {
        setTimeout(rpc.exports.init, 100);
        return;
    }
    ArxanPatcher.init();
    GlobalPatcher.init();
    JavaUtils.toast("Powered by JavaBS (and root7325..)");
}