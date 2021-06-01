package dev.sprock.pixelexplorer.client;

import dev.sprock.pixelexplorer.client.engine.Game;
import dev.sprock.pixelexplorer.client.engine.inputs.InputListener;
import dev.sprock.pixelexplorer.client.network.ClientPacketListener;
import dev.sprock.pixelexplorer.client.network.NettyClient;
import dev.sprock.pixelexplorer.shared.entity.Player;
import dev.sprock.pixelexplorer.shared.network.PacketProcessor;
import dev.sprock.pixelexplorer.shared.network.common.RunMode;
import dev.sprock.pixelexplorer.shared.network.packet.login.LoginPacket;
import dev.sprock.pixelexplorer.shared.world.World;
import javafx.scene.input.KeyCode;
import lombok.Getter;

import java.awt.event.KeyEvent;

public class PixelExplorerGame extends Game
{
    @Getter
    private int width;
    @Getter
    private int height;

    private NettyClient client;
    private World world;

    public PixelExplorerGame(int width, int height)
    {
        this.width = width;
        this.height = height;

//        client = new NettyClient();
//        client.init(new PacketProcessor(new ClientPacketListener(), RunMode.CLIENT));
//        client.start("localhost", 8000);
//
//        client.sendPacket(new LoginPacket("Sprock"));
//        System.out.println("Packet sent");

        Explorer.currentWorld = new World();
        Explorer.thePlayer = new Player("Sprock");
        Explorer.thePlayer.updatePosition(15, 15);
        Explorer.currentWorld.addEntity(Explorer.thePlayer);
    }

    @Override
    public void tick(InputListener inputListener)
    {
        double SPEED = 3.3;
        if (inputListener.isPressed(KeyEvent.VK_W)) {
            Explorer.thePlayer.getVelocity().setY(-SPEED);
        }

        if (inputListener.isPressed(KeyEvent.VK_S)) {
            Explorer.thePlayer.getVelocity().setY(SPEED);
        }

        if (inputListener.isPressed(KeyEvent.VK_A)) {
            Explorer.thePlayer.getVelocity().setX(-SPEED);
        }

        if (inputListener.isPressed(KeyEvent.VK_D)) {
            Explorer.thePlayer.getVelocity().setX(SPEED);
        }

        Explorer.currentWorld.tick(inputListener);
    }
}