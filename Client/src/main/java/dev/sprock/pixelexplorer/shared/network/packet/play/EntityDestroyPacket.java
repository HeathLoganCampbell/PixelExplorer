package dev.sprock.pixelexplorer.shared.network.packet.play;

import dev.sprock.pixelexplorer.shared.network.packet.Packet;
import dev.sprock.pixelexplorer.shared.network.packet.PacketConstants;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
Sent from the Server => Client to say that entity can be removed from the world
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EntityDestroyPacket extends Packet
{
    public int entityId;

    @Override
    public int getPacketId() {
        return PacketConstants.ENTITY_DESTROY_PACKET_ID;
    }

    @Override
    public void read(ByteBuf in)
    {
        this.entityId = in.readInt();
    }

    @Override
    public void write(ByteBuf out)
    {
        out.writeInt(this.entityId);
    }
}
