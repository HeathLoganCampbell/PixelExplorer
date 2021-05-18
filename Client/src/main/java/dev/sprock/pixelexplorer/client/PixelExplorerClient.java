package dev.sprock.pixelexplorer.client;

import dev.sprock.pixelexplorer.client.network.ClientPacketListener;
import dev.sprock.pixelexplorer.client.network.NettyClient;
import dev.sprock.pixelexplorer.shared.network.PacketProcessor;
import dev.sprock.pixelexplorer.shared.network.common.RunMode;
import dev.sprock.pixelexplorer.shared.network.packet.login.LoginPacket;

public class PixelExplorerClient implements Runnable
{
    private Thread thread;
    private NettyClient client;

    public synchronized void start()
    {
        this.thread = new Thread(this, "PixelExplorer : Main Client Thread");
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
        client = new NettyClient();
        client.init(new PacketProcessor(new ClientPacketListener(), RunMode.CLIENT));
        client.start("localhost", 8000);

        client.sendPacket(new LoginPacket("Sprock"));
        System.out.println("Packet sent");
    }

    public void tick()
    {
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
