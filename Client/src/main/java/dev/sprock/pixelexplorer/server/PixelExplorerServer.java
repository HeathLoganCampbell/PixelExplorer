package dev.sprock.pixelexplorer.server;

import dev.sprock.pixelexplorer.server.network.NettyServer;
import dev.sprock.pixelexplorer.server.network.ServerPacketListener;
import dev.sprock.pixelexplorer.server.network.ServerPacketProcessor;
import dev.sprock.pixelexplorer.shared.network.PacketProcessor;
import dev.sprock.pixelexplorer.shared.network.common.RunMode;

public class PixelExplorerServer implements Runnable
{
    private Thread thread;
    private NettyServer nettyServer;
    private ServerPacketProcessor packetProcessor;

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
        this.packetProcessor = new ServerPacketProcessor(new ServerPacketListener(), RunMode.SERVER);

        this.nettyServer = new NettyServer();
        this.nettyServer.init(packetProcessor);
        this.nettyServer.start(8000);
    }

    public void tick()
    {
        this.packetProcessor.update();
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
