package com.root7325.javabs.utils;

import com.root7325.javabs.laser.logic.common.GlobalId;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.nio.charset.StandardCharsets;

/**
 * @author root7325 on 17.06.2025
 */
@Getter
@RequiredArgsConstructor
public class LaserByteBuf {
    private final ByteBuf buffer;

    public byte[] readBytes(int size) {
        byte[] result = new byte[size];
        buffer.readBytes(result);
        return result;
    }

    public boolean readBoolean() {
        return buffer.readBoolean();
    }

    public int readInt() {
        return buffer.readInt();
    }

    public long readLong() {
        return ((long) buffer.readInt() << 32) | (buffer.readInt() & 0xFFFFFFFFL);
    }

    public String readString() {
        int length = readInt();
        if (length <= 0 || length > 900000) {
            return "";
        }

        byte[] bytes = new byte[length];
        buffer.readBytes(bytes);
        return new String(bytes);
    }

    public int readVInt() {
        int b, sign = (b = buffer.readByte()) >> 6 & 0x1, i = b & 0x3F, offset = 6;
        for (int j = 0; j < 4 && (b & 0x80) != 0; j++, offset += 7)
            i |= ((b = buffer.readByte()) & 0x7F) << offset;
        return ((b & 0x80) != 0) ? -1 : (i | ((sign == 1 && offset < 32) ? (i | -1 << offset) : i));
    }

    public long readVLong() {
        return ((long) readVInt() << 32) | (readVInt());
    }

    public GlobalId readDataReference() {
        return new GlobalId(readVInt(), readVInt());
    }

    public void writeBoolean(boolean value) {
        buffer.writeBoolean(value);
    }

    public void writeBoolean(boolean... values) {
        int result = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i]) result |= 1 << i;
        }
        writeByte(result);
    }

    public void writeByte(byte value) {
        buffer.writeByte(value);
    }

    public void writeByte(int value) {
        writeByte((byte) value);
    }

    public void writeBytes(byte[] value) {
        buffer.writeInt(value.length);
        buffer.writeBytes(value);
    }

    public void writeInt(int value) {
        buffer.writeInt(value);
    }

    public void writeShort(int value) {
        buffer.writeShort(value);
    }

    public void writeString(String value) {
        if (value == null) {
            writeInt(-1);
            return;
        }
        ;

        byte[] bytes = value.getBytes(StandardCharsets.UTF_8);
        writeInt(bytes.length);
        buffer.writeBytes(bytes);
    }

    public void writeString() {
        writeString(null);
    }

    public void writeVInt(int v) {
        int temp = v >> 25 & 0x40;
        int flipped = v ^ v >> 31;
        temp |= v & 0x3F;
        v >>= 6;
        if ((flipped >>= 6) == 0) {
            buffer.writeByte(temp);
            return;
        }
        buffer.writeByte(temp | 0x80);
        do {
            buffer.writeByte(v & 0x7F | (((flipped >>= 7) != 0) ? 128 : 0));
            v >>= 7;
        } while (flipped != 0);
    }

    public void writeArrayVInt(int... arr) {
        writeVInt(arr.length);
        for (int i : arr)
            writeVInt(i);
    }

    public void writeLong(long l) {
        buffer.writeLong(l);
    }

    public void writeVLong(long l) {
        writeVInt((int) (l >> 32));
        writeVInt((int) (l & 0xFFFFFFFFL));
    }

    public void writeDataReference(int classId, int instanceId) {
        writeVInt(classId);
        writeVInt(instanceId);
    }

    public void writeDataReference(GlobalId globalId) {
        writeDataReference(globalId.getClassId(), globalId.getInstanceId());
    }

    public void writeHex(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        String[] strBytes = new String[hex.length() / 2];
        int k = 0;
        for (int i = 0; i < hex.length(); i = i + 2) {
            int j = i + 2;
            strBytes[k] = hex.substring(i, j);
            bytes[k] = (byte) Integer.parseInt(strBytes[k], 16);
            k++;
        }
        buffer.writeBytes(bytes);
    }
}
