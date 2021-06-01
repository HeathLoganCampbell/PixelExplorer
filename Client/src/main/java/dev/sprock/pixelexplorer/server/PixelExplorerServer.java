package dev.sprock.pixelexplorer.server;

import dev.sprock.pixelexplorer.server.network.NettyServer;
import dev.sprock.pixelexplorer.server.network.ServerPacketListener;
import dev.sprock.pixelexplorer.server.network.ServerPacketProcessor;
import dev.sprock.pixelexplorer.server.world.WorldManager;
import dev.sprock.pixelexplorer.shared.network.PacketProcessor;
import dev.sprock.pixelexplorer.shared.network.common.RunMode;
import dev.sprock.pixelexplorer.shared.world.World;

public class PixelExplorerServer implements Runnable
{
    private Thread thread;
    private NettyServer nettyServer;

    public synchronized void start()
    {
        this.thread = new Thread(this, "PixelExplorer : Main Server Thread");
        this.thread.start();
    }

    public synchronized void stop()
    {
        try {
            this.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void init()
    {
        Tux.init();
        Tux.getWorldManager().registerWorld(new World(0));

        this.nettyServer = new NettyServer();
        this.nettyServer.init();
        this.nettyServer.start(8000);

        while(true)
        {
            tick();

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void tick()
    {
        Tux.getPacketProcessor().update();
//        System.out.println("Tick");
    }

    public void render()
    {

    }

    @Override
    public void run()
    {
        init();
        while(true)
        {
            this.tick();
            this.render();

            try {
                Thread.sleep(50L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
