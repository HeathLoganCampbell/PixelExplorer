package dev.sprock.pixelexplorer.shared.assets;

import dev.sprock.pixelexplorer.client.engine.assets.Asset;
import dev.sprock.pixelexplorer.client.engine.graphics.Bitmap;
import dev.sprock.pixelexplorer.client.engine.graphics.Screen;

import java.io.IOException;

public class TileAssets
{
    private static final int TILE_SIZE = 32;
    private static Bitmap bitmap;

    public static void init()
    {
        try {
            bitmap = Asset.loadBitmap("Tiles.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void render(Screen screen, int ix, int iy, int x, int y)
    {
        screen.drawSegment(bitmap, TILE_SIZE * ix, TILE_SIZE *  iy, TILE_SIZE, TILE_SIZE, x, y, 0xFFFFFF);
    }
}
