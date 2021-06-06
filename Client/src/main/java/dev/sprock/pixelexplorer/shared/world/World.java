package dev.sprock.pixelexplorer.shared.world;

import dev.sprock.pixelexplorer.client.PixelExplorerScreen;
import dev.sprock.pixelexplorer.client.engine.graphics.Screen;
import dev.sprock.pixelexplorer.client.engine.inputs.InputListener;
import dev.sprock.pixelexplorer.server.Tux;
import dev.sprock.pixelexplorer.shared.entity.Entity;
import dev.sprock.pixelexplorer.shared.entity.EntityManager;
import dev.sprock.pixelexplorer.shared.network.packet.play.world.SendWorldChunkPacket;
import dev.sprock.pixelexplorer.shared.utils.ChunkUtils;
import dev.sprock.pixelexplorer.shared.utils.Location;
import dev.sprock.pixelexplorer.shared.world.chunk.Chunk;
import dev.sprock.pixelexplorer.shared.world.generator.BasicChunkGenerator;
import dev.sprock.pixelexplorer.shared.world.generator.ChunkGenerator;
import dev.sprock.pixelexplorer.shared.world.tile.Tile;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class World
{
    @Getter
    private int id;
    private EntityManager entityManager = new EntityManager();
    private HashMap<Long, Chunk> chunkMap = new HashMap<>();
    private ChunkGenerator chunkGenerator = new BasicChunkGenerator();

    public World(int worldId)
    {
        this.id = worldId;
    }

    public void addEntity(Entity entity)
    {
        this.entityManager.addEntity(entity);
    }

    public void tick()
    {
        this.entityManager.tick();

        for (Entity entity : this.entityManager.getEntities())
        {
            int chunkX = (int) Math.floor(((double) entity.getLocation().getX() / Tile.TILE_SIZE) / Chunk.CHUNK_SIZE);
            int chunkY = (int) Math.floor(((double) entity.getLocation().getY() / Tile.TILE_SIZE) / Chunk.CHUNK_SIZE);
            if (!this.isChunkLoaded(chunkX, chunkY))
            {

                Chunk chunk = this.generateChunk(chunkX, chunkY);
                Tux.getPacketProcessor().broadcastPacket(new SendWorldChunkPacket(this.getId(), chunk));
            }
        }
    }

    public Tile getTile(Location location)
    {
        Chunk chunk = location.getChunkAndLoad();
        Location chunkMinLocation = chunk.getLocation(location.getWorld());
        int subChunkX = location.getX() - chunkMinLocation.getX();
        int subChunkY = location.getY() - chunkMinLocation.getY();
        return chunk.getTile(subChunkX / Tile.TILE_SIZE, subChunkY / Tile.TILE_SIZE);
    }

    public void render(Screen screen)
    {
        for (Map.Entry<Long, Chunk> chunkEntry : this.chunkMap.entrySet())
        {
            Chunk chunk = chunkEntry.getValue();
            chunk.render(screen, 0, 0);
        }

        this.entityManager.render(screen);
    }

    public void removeEntity(int entityId)
    {
        this.entityManager.removeEntity(entityId);
    }

    public Entity getEntity(int entityId)
    {
        return this.entityManager.getEntity(entityId);
    }

    public Collection<Entity> getEntities()
    {
        return this.entityManager.getEntities();
    }

    public void loadChunk(Chunk chunk)
    {
        this.chunkMap.put(chunk.getLongId(), chunk);
    }

    public boolean isChunkLoaded(int chunkX, int chunkY)
    {
        return this.chunkMap.containsKey(ChunkUtils.toLong(chunkX, chunkY));
    }

    public Chunk getChunk(int chunkX, int chunkY)
    {
        return this.chunkMap.get(ChunkUtils.toLong(chunkX, chunkY));
    }

    public Chunk generateChunk(int chunkX, int chunkY)
    {
        Chunk chunk = this.chunkGenerator.generateChunk(chunkX, chunkY);
        this.loadChunk(chunk);
        return chunk;
    }

    public Collection<Chunk> getLoadedChunks()
    {
        return this.chunkMap.values();
    }
}
