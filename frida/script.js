// by root7325!
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
    replace(ptr, arr) {
        Memory.protect(ptr, arr.length, "rwx");
        ptr.writeByteArray(arr);
        Memory.protect(ptr, arr.length, "rx");
    },
    nop(ptr) {
        Armceptor.replace(ptr, [0x00, 0xF0, 0x20, 0xE3]);
    },
    ret(ptr) {
        Armceptor.replace(ptr, [0x1E, 0xFF, 0x2F, 0xE1]);
    },
    jumpout: function (addr, target) {
        Memory.patchCode(addr, Process.pageSize, function (code) {
            var writer = new ArmWriter(code, {
                pc: addr
            });

            writer.putBranchAddress(target);
            writer.flush();
        });
    }
}

const Config = {
    HOST_PATCH: {
        "original": "game.brawlstarsgame.com",
        "target": "SET_YOUR_IP"
    },
    ENABLE_OFFLINE_BATTLES: true,
    CLIENT_SECRET: [0xa3, 0x18, 0x3e, 0x2d, 0xfc, 0x28, 0xd4, 0x8a, 0x4e, 0x83, 0x37, 0xf5, 0xbd, 0x97, 0xb0, 0xd5, 0x30, 0x98, 0xb3, 0x2a, 0xed, 0xe9, 0x60, 0x60, 0x0c, 0xcd, 0xef, 0xb5, 0x1d, 0xce, 0x8e, 0x1c]
}

const Addresses = {
    createGameJump: 0x32FAF0,
    createGameClean: 0x32FBEC,
    unk: 0x10F038, // I dunno what 'sub_1F71E0' purpose is (except of crashing game), but noping it makes things work.
    loginJump: 0x07633C,
    loginClean: 0x0763E4,
    ultButtonJump: 0x180180,
    ultButtonClean: 0x1804C0,
    serverConnection: 0x060EF8,
    randomBytes: 0x171540,
    homePage: 0x271114
}

const ArxanPatcher = {
    init() {
        Armceptor.jumpout(Libg.offset(Addresses.createGameJump), Libg.offset(Addresses.createGameClean));
        Armceptor.nop(Libg.offset(Addresses.unk));
        Armceptor.nop(Libg.offset(0x3BD214));
        Armceptor.jumpout(Libg.offset(Addresses.loginJump), Libg.offset(Addresses.loginClean));
        Armceptor.jumpout(Libg.offset(Addresses.ultButtonJump), Libg.offset(Addresses.ultButtonClean));

        console.log("[*] Patched Arxan prots")
    }
}

const GlobalPatcher = {
    init() {
        this.patchServerConnection();
        this.patchSecretKey();
        if (Config.ENABLE_OFFLINE_BATTLES) {
            this.patchOfflineBattles();
        }
    },

    patchServerConnection() {
        Interceptor.attach(Libg.offset(Addresses.serverConnection), {
            onEnter(args) {
                if (args[1].add(8).readPointer().readUtf8String() === Config.HOST_PATCH.original) {
                    args[1].add(8).readPointer().writeUtf8String(Config.HOST_PATCH.target);
                    console.log(`[*] Redirected to ${Config.HOST_PATCH.target}`);
                }
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
    }
}

rpc.exports.init = function () {
    if (!Libg.init()) {
        setTimeout(rpc.exports.init, 500);
        return;
    }
    ArxanPatcher.init();
    GlobalPatcher.init();
}