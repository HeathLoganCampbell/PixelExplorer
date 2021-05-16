package dev.sprock.pixelexplorer.shared.network.packet;

import io.netty.buffer.ByteBuf;

public abstract class Packet
{
    public abstract int getPacketId();

    public abstract void read(ByteBuf in);

    public abstract void write(ByteBuf out);
}
