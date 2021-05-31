package dev.sprock.pixelexplorer.shared.entity;

import dev.sprock.pixelexplorer.client.engine.graphics.Screen;

import java.util.ArrayList;
import java.util.List;

public class EntityManager
{
    private List<Entity> entities = new ArrayList<Entity>();

    public void addEntity(Entity entity)
    {
        this.entities.add(entity);
    }

    public void tick()
    {
        for (Entity entity : entities)
        {
            entity.tick();
        }
    }

    public void render(Screen screen)
    {
        for (Entity entity : entities)
        {
            entity.render(screen);
        }
    }
}
