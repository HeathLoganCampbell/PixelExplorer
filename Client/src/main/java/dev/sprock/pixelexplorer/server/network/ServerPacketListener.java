package dev.sprock.pixelexplorer.server.network;

import dev.sprock.pixelexplorer.shared.entity.OnlinePlayer;
import dev.sprock.pixelexplorer.shared.network.listener.PacketListener;
import dev.sprock.pixelexplorer.shared.network.packet.login.LoginPacket;
import dev.sprock.pixelexplorer.shared.network.packet.play.EntityDestroyPacket;
import dev.sprock.pixelexplorer.shared.network.packet.play.EntitySpawnPacket;
import dev.sprock.pixelexplorer.shared.network.packet.play.EntityTeleportPacket;

public class ServerPacketListener extends PacketListener
{
    public ServerPacketListener()
    {
        setListener(LoginPacket.class, (packet, player)-> {
            String username = packet.getUsername();
            player.updateUsername(username);

            System.out.println("Login Username: " + username);

            player.getPlayerConnection().sendPacket(new EntitySpawnPacket(0, 0, 0, 0));
            player.getPlayerConnection().sendPacket(new EntityTeleportPacket(0, 0, 0));
            player.getPlayerConnection().sendPacket(new EntityDestroyPacket(0));
        });
    }
}
