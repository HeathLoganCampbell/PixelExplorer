package dev.sprock.pixelexplorer.client;

import dev.sprock.pixelexplorer.client.engine.Game;
import dev.sprock.pixelexplorer.client.engine.inputs.InputListener;
import dev.sprock.pixelexplorer.client.network.ClientPacketListener;
import dev.sprock.pixelexplorer.client.network.NettyClient;
import dev.sprock.pixelexplorer.shared.network.PacketProcessor;
import dev.sprock.pixelexplorer.shared.network.common.RunMode;
import dev.sprock.pixelexplorer.shared.network.packet.login.LoginPacket;
import lombok.Getter;

public class PixelExplorerGame extends Game
{
    @Getter
    private int width;
    @Getter
    private int height;

    private NettyClient client;

    public PixelExplorerGame(int width, int height)
    {
        this.width = width;
        this.height = height;

        client = new NettyClient();
        client.init(new PacketProcessor(new ClientPacketListener(), RunMode.CLIENT));
        client.start("localhost", 8000);

        client.sendPacket(new LoginPacket("Sprock"));
        System.out.println("Packet sent");
    }

    @Override
    public void tick(InputListener inputListener)
    {
    }
}