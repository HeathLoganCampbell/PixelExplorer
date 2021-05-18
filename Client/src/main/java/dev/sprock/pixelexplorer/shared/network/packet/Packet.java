package dev.sprock.pixelexplorer.shared.network.packet;

import com.sun.tools.javac.comp.Check;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

import java.nio.charset.StandardCharsets;

public abstract class Packet
{
    public abstract int getPacketId();

    public abstract void read(ByteBuf in);

    public abstract void write(ByteBuf out);

    public void writeString(ByteBuf out, String text)
    {
        final int utf8Bytes = ByteBufUtil.utf8Bytes(text);
        writeVarInt(out, utf8Bytes);
        out.writeCharSequence(text, StandardCharsets.UTF_8);
    }

    // Should check for max chars
    public String readString(ByteBuf in)
    {
        final int length = readVarInt(in);
        final String str = in.toString(in.readerIndex(), length, StandardCharsets.UTF_8);
        in.skipBytes(length);
        return str;
    }

    public void writeVarIntArray(ByteBuf out, int[] array)
    {
        if (array == null)
        {
            writeVarInt(out,0);
            return;
        }
        writeVarInt(out, array.length);
        for (int element : array)
        {
            writeVarInt(out, element);
        }
    }

    public int[] readVarIntArray(ByteBuf in)
    {
        final int size = readVarInt(in);
        int[] array = new int[size];
        for (int i = 0; i < size; i++)
        {
            array[i] = readVarInt(in);
        }
        return array;
    }

    public void writeVarInt(ByteBuf out, int value)
    {
        do {
            byte temp = (byte) (value & 0b01111111);
            value >>>= 7;
            if (value != 0) {
                temp |= 0b10000000;
            }
            out.writeByte(temp);
        } while (value != 0);
    }

    public int readVarInt(ByteBuf in)
    {
        int numRead = 0;
        int result = 0;
        byte read;
        do {
            read = in.readByte();
            int value = (read & 0b01111111);
            result |= (value << (7 * numRead));

            numRead++;
            if (numRead > 5) {
                throw new RuntimeException("VarInt is too big");
            }
        } while ((read & 0b10000000) != 0);

        return result;
    }
}
