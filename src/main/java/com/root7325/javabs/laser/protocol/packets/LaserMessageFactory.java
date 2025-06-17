package com.root7325.javabs.laser.protocol.packets;

import com.root7325.javabs.laser.protocol.packets.client.ClientHelloMessage;
import com.root7325.javabs.laser.protocol.packets.client.LoginMessage;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author root7325 on 17.06.2025
 */
public class LaserMessageFactory {
    private static final Map<MessageType, Supplier<PiranhaMessage>> defaultSuppliers = new HashMap<>() {{
        put(MessageType.ClientHello, ClientHelloMessage::new);
        put(MessageType.Login, LoginMessage::new);
    }};

    private final Map<MessageType, Supplier<PiranhaMessage>> suppliers;

    public LaserMessageFactory(Map<MessageType, Supplier<PiranhaMessage>> suppliers) {
        this.suppliers = suppliers;
    }

    public LaserMessageFactory() {
        this.suppliers = defaultSuppliers;
    }

    public PiranhaMessage create(MessageType messageType) {
        Supplier<PiranhaMessage> supplier = suppliers.get(messageType);
        if (supplier == null) {
            return null;
        }

        PiranhaMessage piranhaMessage = supplier.get();
        piranhaMessage.setMessageType(messageType);
        return piranhaMessage;
    }
}
