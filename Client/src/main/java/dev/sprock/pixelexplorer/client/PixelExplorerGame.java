package dev.sprock.pixelexplorer.client;

import dev.sprock.pixelexplorer.client.engine.Game;
import dev.sprock.pixelexplorer.client.engine.inputs.InputListener;
import dev.sprock.pixelexplorer.client.network.ClientPacketListener;
import dev.sprock.pixelexplorer.client.network.NettyClient;
import dev.sprock.pixelexplorer.shared.network.PacketProcessor;
import dev.sprock.pixelexplorer.shared.network.common.RunMode;
import dev.sprock.pixelexplorer.shared.network.packet.login.LoginPacket;
import dev.sprock.pixelexplorer.shared.network.packet.play.EntityTeleportPacket;
import dev.sprock.pixelexplorer.shared.network.packet.play.world.InitWorldPacket;
import dev.sprock.pixelexplorer.shared.world.World;
import lombok.Getter;

import java.awt.event.KeyEvent;

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
        client.sendPacket(new InitWorldPacket(0));
    }

    @Override
    public void tick(InputListener inputListener)
    {
        if(Explorer.thePlayer == null) return;
        if(!Explorer.chatMode)
        {
            double SPEED = 12;
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

            if (inputListener.isPressed(KeyEvent.VK_T))
            {
                Explorer.chatMode = true;
                inputListener.setKeyRecorder(true);
                inputListener.resetKeyBuffer();
                Explorer.currentMessage = "";
            }
        }
        else
        {
            Explorer.currentMessage = inputListener.keyBuffer;
            if (inputListener.isPressed(KeyEvent.VK_ENTER))
            {
                Explorer.chatMode = false;
                inputListener.setKeyRecorder(false);
            }
        }

        Explorer.thePlayer.tick();

        if (Explorer.thePlayer.isMovedLastTick())
        {
            client.sendPacket(new EntityTeleportPacket(0, Explorer.thePlayer.getLocation().getX(), Explorer.thePlayer.getLocation().getY(), Explorer.thePlayer.getVelocity().getDirection()));
        }
    }
}