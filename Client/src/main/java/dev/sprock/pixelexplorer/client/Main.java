package dev.sprock.pixelexplorer.client;

import dev.sprock.pixelexplorer.client.engine.Engine;

public class Main
{
    public static void main(String[] args)
    {
        Engine<PixelExplorerGame> engine = new Engine<>(700, 400, 2);
        engine.setScreen(new PixelExplorerScreen(engine.getScreenWidth(), engine.getScreenHeight()));
        engine.setGame(new PixelExplorerGame(engine.getWidth(), engine.getHeight()));
        engine.setTitle("Pixel Explorer");
        engine.start();
    }
}
