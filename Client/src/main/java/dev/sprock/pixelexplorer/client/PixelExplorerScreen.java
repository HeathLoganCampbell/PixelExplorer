package dev.sprock.pixelexplorer.client;

import dev.sprock.pixelexplorer.client.engine.assets.Asset;
import dev.sprock.pixelexplorer.client.engine.graphics.Font;
import dev.sprock.pixelexplorer.client.engine.graphics.Screen;
import dev.sprock.pixelexplorer.shared.assets.EntityAssets;
import dev.sprock.pixelexplorer.shared.assets.TileAssets;

import java.io.IOException;

public class PixelExplorerScreen extends Screen<PixelExplorerGame>
{
    public PixelExplorerScreen(int width, int height)
    {
        super(width, height);

        try {
            Font.init(Asset.loadBitmap("fonts.png"));
            EntityAssets.init();
            TileAssets.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(PixelExplorerGame game)
    {
        if(Explorer.thePlayer == null) return;

        this.clearScreen();

        Explorer.thePlayer.getWorld().render(this);
    }
}
