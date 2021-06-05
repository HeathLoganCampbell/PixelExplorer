package dev.sprock.pixelexplorer.shared.world.generator;

import dev.sprock.pixelexplorer.shared.world.chunk.Chunk;

public class ChunkGenerator
{
    public Chunk generateChunk(int chunkX, int chunkY)
    {
        return new Chunk(chunkX, chunkY);
    }
}
