package dev.sprock.pixelexplorer.shared.world.generator;

import dev.sprock.pixelexplorer.shared.world.chunk.Chunk;

import java.util.Arrays;

public class BasicChunkGenerator extends ChunkGenerator
{
    @Override
    public Chunk generateChunk(int chunkX, int chunkY)
    {
        Chunk chunk = new Chunk(chunkX, chunkY);

        short[] tiles = new short[Chunk.CHUNK_SIZE * Chunk.CHUNK_SIZE];
        Arrays.fill(tiles, (short) 1);

        chunk.setTiles(tiles);

        return chunk;
    }
}
