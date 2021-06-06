package dev.sprock.pixelexplorer.shared.world.tile;

import dev.sprock.pixelexplorer.client.engine.graphics.Screen;
import dev.sprock.pixelexplorer.shared.assets.TileAssets;

public class WallTile extends Tile
{
    public WallTile(int id)
    {
        super(id);
        this.setSolid(true);
    }

    @Override
    public void render(Screen screen, int x, int y)
    {
        TileAssets.render(screen, 1, 3, x, y);
    }
}
