package dev.sprock.pixelexplorer.client.network;

import dev.sprock.pixelexplorer.client.Explorer;
import dev.sprock.pixelexplorer.shared.entity.Entity;
import dev.sprock.pixelexplorer.shared.entity.EntityType;
import dev.sprock.pixelexplorer.shared.entity.Player;
import dev.sprock.pixelexplorer.shared.network.listener.PacketListener;
import dev.sprock.pixelexplorer.shared.network.packet.play.EntityDestroyPacket;
import dev.sprock.pixelexplorer.shared.network.packet.play.EntitySpawnPacket;
import dev.sprock.pixelexplorer.shared.network.packet.play.EntityTeleportPacket;
import dev.sprock.pixelexplorer.shared.network.packet.play.InitWorldPacket;
import dev.sprock.pixelexplorer.shared.world.World;

public class ClientPacketListener extends PacketListener
{
    public ClientPacketListener()
    {
        this.setListener(EntitySpawnPacket.class, (packet, nullPlayer) -> {
            System.out.println("Spawned new Entity " + packet.getEntityId() + " at " + packet.getX() + ", " + packet.getY());
            if(packet.getEntityId() == 0)
            {
                return;
            }

            World world = Explorer.thePlayer.getWorld();

            Player player = new Player(packet.getEntityId(), world, packet.x, packet.y);
            world.addEntity(player);
        });

        this.setListener(InitWorldPacket.class, (packet, nullPlayer) -> {
            World world = new World(packet.getWorldId());

            Explorer.thePlayer = new Player("Sprock");
            Explorer.thePlayer.setWorld(world);
            Explorer.thePlayer.updatePosition(15, 15);
            world.addEntity(Explorer.thePlayer);
        });

        this.setListener(EntityDestroyPacket.class, (packet, nullPlayer) -> {
            Explorer.thePlayer.getWorld().removeEntity(packet.entityId);
        });

        this.setListener(EntityTeleportPacket.class, (packet, nullPlayer) -> {
//            System.out.println("Entity " + packet.getEntityId() + " is now at " + packet.getX() + ", " + packet.getY());
            if(packet.entityId == 0)
            {
                Explorer.thePlayer.updatePosition(packet.getX(), packet.getY(), packet.getDirection());
                return;
            }

            Entity entity = Explorer.thePlayer.getWorld().getEntity(packet.entityId);
            if(entity != null)
            {
                entity.updatePosition(packet.getX(), packet.getY(), packet.getDirection());
            }
        });
    }


}
