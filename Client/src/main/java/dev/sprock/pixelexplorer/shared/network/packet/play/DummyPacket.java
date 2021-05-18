package dev.sprock.pixelexplorer.shared.network.packet.play;

import dev.sprock.pixelexplorer.shared.network.packet.Packet;
import dev.sprock.pixelexplorer.shared.network.packet.PacketConstants;
import io.netty.buffer.ByteBuf;

public class DummyPacket extends Packet
{
    private int magicNumber;
    private int hopCount = 0;

    public DummyPacket(int magicNumber) {
        this.magicNumber = magicNumber;
    }

    public DummyPacket() { }

    public int getHopCount() {
        return hopCount;
    }

    public void setHopCount(int hopCount) {
        this.hopCount = hopCount;
    }

    public int getMagicNumber() {
        return magicNumber;
    }

    @Override
    public int getPacketId() {
        return PacketConstants.DUMMY_PACKET_ID;
    }

    @Override
    public void read(ByteBuf in)
    {
        this.magicNumber = in.readInt();
        this.hopCount = in.readInt();
    }

    @Override
    public void write(ByteBuf out)
    {
        out.writeInt(this.magicNumber);
        out.writeInt(this.hopCount);
    }

    @Override
    public String toString() {
        return "DummyPacket{" +
                "magicNumber=" + magicNumber +
                ", hopCount=" + hopCount +
                '}';
    }
}
