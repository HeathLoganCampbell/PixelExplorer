package dev.sprock.pixelexplorer.shared.entity;

import dev.sprock.pixelexplorer.client.engine.graphics.Font;
import dev.sprock.pixelexplorer.client.engine.graphics.Screen;
import dev.sprock.pixelexplorer.shared.assets.EntityAssets;
import dev.sprock.pixelexplorer.shared.utils.Direction;
import dev.sprock.pixelexplorer.shared.utils.Location;
import dev.sprock.pixelexplorer.shared.utils.Vector;
import dev.sprock.pixelexplorer.shared.world.World;
import dev.sprock.pixelexplorer.shared.world.chunk.Chunk;
import dev.sprock.pixelexplorer.shared.world.tile.Tile;
import lombok.Getter;
import lombok.Setter;

public class Entity
{
    @Getter
    @Setter
    private int entityId;
    @Getter
    private Location location;
    @Getter
    private Vector velocity = new Vector();
    @Getter
    @Setter
    private Direction direction = null;
    @Getter
    @Setter
    private boolean movedLastTick = false;

    public Entity()
    {
        this.location = new Location(null, 0, 0);
    }

    public Entity(int entityId, World world, int x, int y)
    {
        this.entityId = entityId;
        this.location = new Location(world, x, y);
    }

    public void updatePosition(int x, int y)
    {
        this.updatePosition(x, y, null);
    }

    public void updatePosition(int x, int y, Direction direction)
    {
        this.location.setX(x);
        this.location.setY(y);
        this.direction = direction;
    }

    public World getWorld()
    {
        return this.getLocation().getWorld();
    }

    public void setWorld(World world)
    {
        this.location.setWorld(world);
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

            int newX = (int) (this.location.getX() + this.velocity.getX() * 2);
            int newY = (int) (this.location.getY() + this.velocity.getY() * 2);

            // Collision Detection
            Tile tileMinUpperX = getWorld().getTile(new Location(getWorld(), newX, this.getLocation().getY()));
            Tile tileMaxUpperX = getWorld().getTile(new Location(getWorld(), newX + 30, this.getLocation().getY()));

            Tile tileMinLowerX = getWorld().getTile(new Location(getWorld(), newX, this.getLocation().getY() + 30));
            Tile tileMaxLowerX = getWorld().getTile(new Location(getWorld(), newX + 30, this.getLocation().getY() + 30));

            Tile tileMinUpperY = getWorld().getTile(new Location(getWorld(), this.getLocation().getX(), newY));
            Tile tileMaxUpperY = getWorld().getTile(new Location(getWorld(), this.getLocation().getX(), newY + 30));

            Tile tileMinLowerY = getWorld().getTile(new Location(getWorld(), this.getLocation().getX() + 30, newY));
            Tile tileMaxLowerY = getWorld().getTile(new Location(getWorld(), this.getLocation().getX() + 30, newY + 30));

            if(!tileMinUpperX.isSolid() && !tileMaxUpperX.isSolid() && !tileMinLowerX.isSolid() && !tileMaxLowerX.isSolid())
            {
                this.location.setX(newX);

                this.movedLastTick = true;
            }

            if(!tileMinUpperY.isSolid() && !tileMaxUpperY.isSolid() && !tileMinLowerY.isSolid() && !tileMaxLowerY.isSolid())
            {
                this.location.setY(newY);

                this.movedLastTick = true;
            }
        }
    }

    public void render(Screen screen)
    {
        Font.text("" + ((char) ((char) entityId + 'A')), screen, this.location.getX(), this.location.getY());
    }
}
