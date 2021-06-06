package dev.sprock.pixelexplorer.shared.network.packet.play.world;

import dev.sprock.pixelexplorer.shared.network.packet.Packet;
import dev.sprock.pixelexplorer.shared.network.packet.PacketConstants;
import dev.sprock.pixelexplorer.shared.world.chunk.Chunk;
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
public class SendWorldChunkPacket extends Packet
{
    public int worldId = 0;
    public int chunkX, chunkY;
    public int[] tilesData;

    public SendWorldChunkPacket(int worldId, Chunk chunk)
    {
        this.worldId = worldId;
        this.chunkX = chunk.getChunkX();
        this.chunkY = chunk.getChunkY();
        this.tilesData = chunk.getTiles();
    }

    @Override
    public int getPacketId() {
        return PacketConstants.SEND_WORLD_CHUNK_PACKET_ID;
    }

    @Override
    public void read(ByteBuf in)
    {
        this.worldId = in.readInt();
        this.chunkX = in.readInt();
        this.chunkY = in.readInt();
        this.tilesData = this.readVarIntArray(in);
    }

    @Override
    public void write(ByteBuf out)
    {
        out.writeInt(this.worldId);
        out.writeInt(this.chunkX);
        out.writeInt(this.chunkY);
        this.writeVarIntArray(out, this.tilesData);
    }
}
