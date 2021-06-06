package dev.sprock.pixelexplorer.shared.utils;

import dev.sprock.pixelexplorer.shared.world.World;
import dev.sprock.pixelexplorer.shared.world.chunk.Chunk;
import dev.sprock.pixelexplorer.shared.world.tile.Tile;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Location
{
    private World world;
    private int x, y;

    public Location(World world, int x, int y)
    {
        this.world = world;
        this.x = x;
        this.y = y;
    }

    public Location clone()
    {
        return new Location(this.world, this.x, this.y);
    }

    public Location add(Vector vector)
    {
        Location clone = this.clone();
        clone.setX((int) (clone.getX() + vector.getX()));
        clone.setY((int) (clone.getY() + vector.getY()));
        return clone;
    }

    public Location subtract(Vector vector)
    {
        Location clone = this.clone();
        clone.setX((int) (clone.getX() - vector.getX()));
        clone.setY((int) (clone.getY() - vector.getY()));
        return clone;
    }

    public Chunk getChunk()
    {
        int chunkX = this.x / Tile.TILE_SIZE / Chunk.CHUNK_SIZE;
        int chunkY = this.y / Tile.TILE_SIZE / Chunk.CHUNK_SIZE;
        return this.world.getChunk(chunkX, chunkY);
    }
}
