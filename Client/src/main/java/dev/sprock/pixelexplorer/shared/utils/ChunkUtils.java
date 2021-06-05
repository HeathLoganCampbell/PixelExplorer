package dev.sprock.pixelexplorer.shared.utils;

public class ChunkUtils
{
    public static long toLong(int chunkX, int chunkY)
    {
        return (long) chunkX & 0xffffffffL | ((long) chunkY & 0xffffffffL) << 32;
    }
}
