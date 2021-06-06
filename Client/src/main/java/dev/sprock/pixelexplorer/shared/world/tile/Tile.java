package dev.sprock.pixelexplorer.shared.world.tile;

import dev.sprock.pixelexplorer.client.engine.graphics.Screen;
import lombok.Getter;
import lombok.Setter;

public class Tile
{
    public static int TILE_SIZE = 32;

    @Getter
    private int id;
    @Getter
    @Setter
    private boolean solid = false;

    public Tile(int id)
    {
        this.id = id;
    }

    public void render(Screen screen, int x, int y)
    {

    }

    public static Tile[] TILE_REGISTER = new Tile[40];

    static
    {
        registerTile(new GrassTile(1));
        registerTile(new WallTile(2));
    }

    public static void registerTile(Tile tile)
    {
        TILE_REGISTER[tile.getId()] = tile;
    }
}
