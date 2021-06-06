package dev.sprock.pixelexplorer.shared.assets;

import dev.sprock.pixelexplorer.client.engine.assets.Asset;
import dev.sprock.pixelexplorer.client.engine.graphics.Bitmap;
import dev.sprock.pixelexplorer.client.engine.graphics.Screen;

import java.io.IOException;

public class EntityAssets
{
    private static final int ENTITY_WIDTH = 41;
    private static final int ENTITY_HEIGHT = 42;
    private static Bitmap bitmap;

    public static void init()
    {
        try {
            bitmap = Asset.loadBitmap("Entities.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void render(Screen screen, int ix, int iy, int x, int y)
    {
        screen.drawSegment(bitmap, ENTITY_WIDTH * ix, ENTITY_HEIGHT *  iy, ENTITY_WIDTH, ENTITY_HEIGHT, x, y, 0xFFFFFF);
    }
}
