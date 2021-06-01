package dev.sprock.pixelexplorer.client;

import dev.sprock.pixelexplorer.client.engine.assets.Asset;
import dev.sprock.pixelexplorer.client.engine.graphics.Font;
import dev.sprock.pixelexplorer.client.engine.graphics.Screen;

import java.io.IOException;

public class PixelExplorerScreen extends Screen<PixelExplorerGame>
{
    public PixelExplorerScreen(int width, int height)
    {
        super(width, height);

        try {
            Font.init(Asset.loadBitmap("fonts.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(PixelExplorerGame game)
    {
        this.clearScreen();

        Explorer.currentWorld.render(this);

        System.out.println("Velocity" + Explorer.thePlayer.getVelocity().toString());
    }
}
