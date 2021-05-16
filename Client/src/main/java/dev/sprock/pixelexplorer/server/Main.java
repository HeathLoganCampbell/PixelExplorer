package dev.sprock.pixelexplorer.server;

import dev.sprock.pixelexplorer.client.PixelExplorerClient;

public class Main
{
    public static void main(String[] args)
    {
        PixelExplorerServer server = new PixelExplorerServer();

        server.start();
    }
}
