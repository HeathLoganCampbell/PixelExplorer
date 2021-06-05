package dev.sprock.pixelexplorer.shared.world.tile;

public enum TileType
{
    GRASS(new GrassTile(1));

    private Tile tile;

    TileType(Tile tile)
    {
        this.tile = tile;
    }
}
