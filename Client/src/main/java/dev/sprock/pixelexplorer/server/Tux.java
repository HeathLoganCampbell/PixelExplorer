package dev.sprock.pixelexplorer.server;

import dev.sprock.pixelexplorer.server.network.ServerPacketListener;
import dev.sprock.pixelexplorer.server.network.ServerPacketProcessor;
import dev.sprock.pixelexplorer.server.world.WorldManager;
import dev.sprock.pixelexplorer.shared.network.PacketProcessor;
import dev.sprock.pixelexplorer.shared.network.common.RunMode;
import dev.sprock.pixelexplorer.shared.network.listener.PacketListener;
import io.netty.channel.ChannelHandler;

/*
Server side Api
 */
public class Tux
{
    private static WorldManager worldManager;
    private static PacketProcessor packetProcessor;

    public static void init()
    {
        worldManager = new WorldManager();
        packetProcessor = new ServerPacketProcessor(new ServerPacketListener(), RunMode.SERVER);
    }

    public static WorldManager getWorldManager()
    {
        return worldManager;
    }

    public static PacketProcessor getPacketProcessor() {
        return packetProcessor;
    }
}
