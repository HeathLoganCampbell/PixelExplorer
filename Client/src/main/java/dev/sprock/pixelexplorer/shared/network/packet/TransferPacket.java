package dev.sprock.pixelexplorer.shared.network.packet;

import io.netty.buffer.ByteBuf;

public class TransferPacket
{
    private int packetId;
    private ByteBuf byteBuf;

    public TransferPacket(int packetId, ByteBuf byteBuf)
    {
        this.packetId = packetId;
        this.byteBuf = byteBuf;
    }

    public int getPacketId() {
        return packetId;
    }

    public void setPacketId(int packetId) {
        this.packetId = packetId;
    }

    public ByteBuf getByteBuf() {
        return byteBuf;
    }

    public void setByteBuf(ByteBuf byteBuf) {
        this.byteBuf = byteBuf;
    }
}
