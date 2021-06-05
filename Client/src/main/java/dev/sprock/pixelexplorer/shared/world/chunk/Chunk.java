package dev.sprock.pixelexplorer.shared.world.chunk;

import dev.sprock.pixelexplorer.client.engine.graphics.Screen;
import dev.sprock.pixelexplorer.shared.world.tile.Tile;
import lombok.Getter;
/*
 32 pixels = a tile
32 * 32 tiles = Chunk
 */
public class Chunk
{
    public static int CHUNK_SIZE = 16;

    @Getter
    private int chunkX, chunkY;
    @Getter
    private short[] tiles;

    public Chunk(int chunkX, int chunkY)
    {
        this.chunkX = chunkX;
        this.chunkY = chunkY;
        this.tiles = new short[CHUNK_SIZE * CHUNK_SIZE];
    }

    public void setTiles(short[] tiles)
    {
        this.tiles = tiles;
    }

    public void render(Screen screen, int x, int y)
    {
        int realX = this.chunkX * Tile.TILE_SIZE * Chunk.CHUNK_SIZE;
        int realY = this.chunkY * Tile.TILE_SIZE * Chunk.CHUNK_SIZE;

        int tileCountX = 0;
        int xOffset = 0;
        int yOffset = 0;
        for (short tileId : tiles)
        {
            Tile tile = Tile.TILE_REGISTER[tileId];
            tile.render(screen, realX + xOffset, realY + yOffset);

            xOffset += Tile.TILE_SIZE;
            tileCountX++;

            if(tileCountX >= CHUNK_SIZE)
            {
                tileCountX = 0;
                xOffset = 0;
                yOffset += Tile.TILE_SIZE;
            }
        }
    }

    public Long getLongId()
    {
        int chunkX = this.chunkX;
        int chunkZ = this.chunkY;
        return (long) chunkX & 0xffffffffL | ((long) chunkZ & 0xffffffffL) << 32;
    }
}
