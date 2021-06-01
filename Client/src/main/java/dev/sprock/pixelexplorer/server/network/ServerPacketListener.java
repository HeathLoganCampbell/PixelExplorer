package dev.sprock.pixelexplorer.server.network;

import dev.sprock.pixelexplorer.server.Tux;
import dev.sprock.pixelexplorer.shared.entity.Entity;
import dev.sprock.pixelexplorer.shared.entity.OnlinePlayer;
import dev.sprock.pixelexplorer.shared.entity.Player;
import dev.sprock.pixelexplorer.shared.network.listener.PacketListener;
import dev.sprock.pixelexplorer.shared.network.packet.Packet;
import dev.sprock.pixelexplorer.shared.network.packet.login.LoginPacket;
import dev.sprock.pixelexplorer.shared.network.packet.play.EntityDestroyPacket;
import dev.sprock.pixelexplorer.shared.network.packet.play.EntitySpawnPacket;
import dev.sprock.pixelexplorer.shared.network.packet.play.EntityTeleportPacket;
import dev.sprock.pixelexplorer.shared.network.packet.play.InitWorldPacket;
import dev.sprock.pixelexplorer.shared.world.World;

public class ServerPacketListener extends PacketListener
{
    public static int ENTITY_ID_COUNTER = 1;

    public ServerPacketListener()
    {
        setListener(LoginPacket.class, (packet, player)-> {
            String username = packet.getUsername();
            player.updateUsername(username);

            System.out.println("Login Username: " + username);
        });

        setListener(InitWorldPacket.class, (packet, player)-> {
            // Get all Player entitys in the world and send them
            player.getPlayerConnection().sendPacket(new InitWorldPacket(0));

            World world = Tux.getWorldManager().getWorld(0);

            for (Entity entity : world.getEntities())
            {
                player.getPlayerConnection().sendPacket(
                        new EntitySpawnPacket(
                            entity.getEntityId(),
                            1,
                            entity.getX(),
                            entity.getY()
                        )
                );
            }

            player.setEntityId(ENTITY_ID_COUNTER++);
            world.addEntity(player);

            broadcastIgnoreSelf(new EntitySpawnPacket(player.getEntityId(), 1, player.getX(), player.getY()), player);
        });

//        setListener(EntitySpawnPacket.class, (packet, player)-> {
//            // Get all Player entitys in the world and send them
//            player.getPlayerConnection().sendPacket(new EntitySpawnPacket(0, 0, 0, 0));
//        });

        setListener(EntityTeleportPacket.class, (packet, player)-> {
            if(packet.entityId == 0)
            {
                player.updatePosition(packet.getX(), packet.getY());
            }

            EntityTeleportPacket newPacket = new EntityTeleportPacket(player.getEntityId(), packet.getX(), packet.getY());
            this.broadcastIgnoreSelf(newPacket, player);
        });

    }

    public void broadcastIgnoreSelf(Packet packet, OnlinePlayer player)
    {
        Tux.getPacketProcessor().broadcastPacketIgnoreSelf(packet, player);
    }
}
