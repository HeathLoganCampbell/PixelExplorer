package dev.sprock.pixelexplorer.client;

import dev.sprock.pixelexplorer.client.engine.graphics.Screen;

public class PixelExplorerScreen extends Screen<PixelExplorerGame>
{
    public PixelExplorerScreen(int width, int height)
    {
        super(width, height);
    }

    @Override
    public void render(PixelExplorerGame game)
    {
        this.clearScreen();

        for (int i = 0; i < 100; i++)
        {
            this.setPixel(i, i, 0xFF00FF);
        }
    }
}
