package dev.sprock.pixelexplorer.shared.world;

import dev.sprock.pixelexplorer.client.PixelExplorerScreen;
import dev.sprock.pixelexplorer.client.engine.graphics.Screen;
import dev.sprock.pixelexplorer.client.engine.inputs.InputListener;
import dev.sprock.pixelexplorer.shared.entity.Entity;
import dev.sprock.pixelexplorer.shared.entity.EntityManager;

public class World
{
    private EntityManager entityManager = new EntityManager();

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
}
