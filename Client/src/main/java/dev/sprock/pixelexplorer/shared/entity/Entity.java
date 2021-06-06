package dev.sprock.pixelexplorer.shared.entity;

import dev.sprock.pixelexplorer.client.engine.graphics.Font;
import dev.sprock.pixelexplorer.client.engine.graphics.Screen;
import dev.sprock.pixelexplorer.shared.utils.Direction;
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
    private int x, y;
    @Getter
    private World world;
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
        this.updatePosition(x, y, null);
    }

    public void updatePosition(int x, int y, Direction direction)
    {
        this.x = x;
        this.y = y;
        this.direction = direction;
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

            int newX = (int) (this.x + this.velocity.getX() * 2);
            int newY = (int) (this.y + this.velocity.getY() * 2);

// Collision Detection
//            int chunkX = newX / Tile.TILE_SIZE / Chunk.CHUNK_SIZE;
//            int chunkY = newY / Tile.TILE_SIZE / Chunk.CHUNK_SIZE;
//
//            Chunk chunk = this.world.getChunk(chunkX, chunkY);
//            if(chunk != null)
//            {
//                if (chunk.isSolid(newX, newY))
//                {
//
//                }
//            }

            this.x = newX;
            this.y = newY;

            this.movedLastTick = true;
        }
    }

    public void render(Screen screen)
    {
        Font.text("" + ((char) ((char) entityId + 'A')), screen, this.x, this.y);
    }
}
