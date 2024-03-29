package dev.sprock.pixelexplorer.shared.network.packet.play;

import dev.sprock.pixelexplorer.shared.network.packet.Packet;
import dev.sprock.pixelexplorer.shared.network.packet.PacketConstants;
import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class DummyPacket extends Packet
{
    private int magicNumber;
    @Setter
    private int hopCount = 0;

    public DummyPacket(int magicNumber) {
        this.magicNumber = magicNumber;
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
}
