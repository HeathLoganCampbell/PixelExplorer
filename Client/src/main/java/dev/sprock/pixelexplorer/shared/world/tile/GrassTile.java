package dev.sprock.pixelexplorer.shared.world.tile;

import dev.sprock.pixelexplorer.client.engine.graphics.Screen;
import dev.sprock.pixelexplorer.shared.assets.TileAssets;

public class GrassTile extends Tile
{
    public GrassTile(int id)
    {
        super(id);
    }

    @Override
    public void render(Screen screen, int x, int y)
    {
        if(x * y% 3 == 0)
        {
            TileAssets.render(screen, 7, 2, x, y);
        }
        else
        {
            TileAssets.render(screen, 1, 1, x, y);
        }
    }
}
