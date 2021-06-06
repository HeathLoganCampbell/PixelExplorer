package dev.sprock.pixelexplorer.shared.world.generator;

import dev.sprock.pixelexplorer.shared.world.chunk.Chunk;

import java.util.Arrays;

public class BasicChunkGenerator extends ChunkGenerator
{
    @Override
    public Chunk generateChunk(int chunkX, int chunkY)
    {
        Chunk chunk = new Chunk(chunkX, chunkY);

        int[] tiles = new int[Chunk.CHUNK_SIZE * Chunk.CHUNK_SIZE];
        Arrays.fill(tiles, 1);

        for (int i = 0; i < 16; i++) {
            tiles[i + 16 * 4] = 2;
        }

        chunk.setTiles(tiles);

        return chunk;
    }
}
