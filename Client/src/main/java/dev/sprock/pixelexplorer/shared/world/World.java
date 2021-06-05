package dev.sprock.pixelexplorer.shared.world;

import dev.sprock.pixelexplorer.client.PixelExplorerScreen;
import dev.sprock.pixelexplorer.client.engine.graphics.Screen;
import dev.sprock.pixelexplorer.client.engine.inputs.InputListener;
import dev.sprock.pixelexplorer.shared.entity.Entity;
import dev.sprock.pixelexplorer.shared.entity.EntityManager;
import dev.sprock.pixelexplorer.shared.world.chunk.Chunk;
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

    public World(int worldId)
    {
        this.id = worldId;

        short[] tiles = new short[Chunk.CHUNK_SIZE * Chunk.CHUNK_SIZE];
        Arrays.fill(tiles, (short) 1);

        Chunk basicChunk = new Chunk(0, 0);
        Chunk basicChunk2 = new Chunk(1, 0);
        basicChunk.setTiles(tiles);
        basicChunk2.setTiles(tiles);
        this.loadChunk(basicChunk);
        this.loadChunk(basicChunk2);
    }

    public void addEntity(Entity entity)
    {
        this.entityManager.addEntity(entity);
    }

    public void tick(InputListener inputListener) {
        this.entityManager.tick();
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
}
