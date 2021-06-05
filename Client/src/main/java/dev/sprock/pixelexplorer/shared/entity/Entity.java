package dev.sprock.pixelexplorer.shared.entity;

import dev.sprock.pixelexplorer.client.engine.graphics.Font;
import dev.sprock.pixelexplorer.client.engine.graphics.Screen;
import dev.sprock.pixelexplorer.shared.utils.Vector;
import dev.sprock.pixelexplorer.shared.world.World;
import lombok.Getter;
import lombok.Setter;

public class Entity
{
    @Getter
    @Setter
    private int entityId;
    @Getter
    private int x, y;
    @Getter
    private World world;
    @Getter
    private Vector velocity = new Vector();
    @Getter
    @Setter
    private boolean movedLastTick = false;

    public Entity()
    {
    }

    public Entity(int entityId, World world, int x, int y)
    {
        this.entityId = entityId;
        this.world = world;
        this.x = x;
        this.y = y;
    }


    public void updatePosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void setWorld(World world)
    {
        this.world = world;
    }

    public void remove()
    {

    }

    public void tick()
    {
        this.movedLastTick = false;
        if(this.velocity.getX() != 0.0 || this.velocity.getY() != 0.0) {
            double FRICTION = 0.3;

            this.velocity.scale(FRICTION);

            if (Math.abs(this.velocity.getY()) < 0.03) this.velocity.setY(0);
            if (Math.abs(this.velocity.getX()) < 0.03) this.velocity.setX(0);

            this.x += this.velocity.getX() * 2;
            this.y += this.velocity.getY() * 2;

            this.movedLastTick = true;
        }
    }

    public void render(Screen screen)
    {
        Font.text("" + ((char) ((char) entityId + 'A')), screen, this.x, this.y);
    }
}
