package dev.sprock.pixelexplorer.shared.world;

import dev.sprock.pixelexplorer.client.PixelExplorerScreen;
import dev.sprock.pixelexplorer.client.engine.graphics.Screen;
import dev.sprock.pixelexplorer.client.engine.inputs.InputListener;
import dev.sprock.pixelexplorer.shared.entity.Entity;
import dev.sprock.pixelexplorer.shared.entity.EntityManager;
import lombok.Getter;

import java.util.Collection;

public class World
{
    @Getter
    private int id;
    private EntityManager entityManager = new EntityManager();

    public World(int worldId)
    {
        this.id = worldId;
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
}
