package dev.sprock.pixelexplorer.shared.network.packet.play;

import dev.sprock.pixelexplorer.shared.entity.EntityType;
import dev.sprock.pixelexplorer.shared.network.packet.Packet;
import dev.sprock.pixelexplorer.shared.network.packet.PacketConstants;
import io.netty.buffer.ByteBuf;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/*
Sent from the Server => Client to say that entity can be spawn into the world
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EntitySpawnPacket extends Packet
{
    public int entityId;
    public int entityType;
    public int x, y;

    public EntitySpawnPacket(int entityId, EntityType entityType, int x, int y)
    {
        this(entityId, entityType.getId(), x, y);
    }

    @Override
    public int getPacketId() {
        return PacketConstants.ENTITY_SPAWN_PACKET_ID;
    }

    @Override
    public void read(ByteBuf in)
    {
        this.entityId = in.readInt();
        this.entityType = in.readInt();
        this.x = in.readInt();
        this.y = in.readInt();
    }

    @Override
    public void write(ByteBuf out)
    {
        out.writeInt(this.entityId);
        out.writeInt(this.entityType);
        out.writeInt(this.x);
        out.writeInt(this.y);
    }
}
