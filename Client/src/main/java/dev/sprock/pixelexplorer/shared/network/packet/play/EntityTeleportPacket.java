package dev.sprock.pixelexplorer.shared.network.packet.play;

import dev.sprock.pixelexplorer.shared.network.packet.Packet;
import dev.sprock.pixelexplorer.shared.network.packet.PacketConstants;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
Sent from the Server => Client to say that entity has been teleported
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EntityTeleportPacket extends Packet
{
    public int entityId;
    public int x, y;

    @Override
    public int getPacketId() {
        return PacketConstants.ENTITY_TELEPORT_PACKET_ID;
    }

    @Override
    public void read(ByteBuf in)
    {
        this.entityId = in.readInt();
        this.x = in.readInt();
        this.y = in.readInt();
    }

    @Override
    public void write(ByteBuf out)
    {
        out.writeInt(this.entityId);
        out.writeInt(this.x);
        out.writeInt(this.y);
    }
}
