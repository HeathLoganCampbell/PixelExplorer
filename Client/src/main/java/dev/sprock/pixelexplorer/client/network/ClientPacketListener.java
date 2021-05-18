package dev.sprock.pixelexplorer.client.network;

import dev.sprock.pixelexplorer.shared.network.listener.PacketListener;
import dev.sprock.pixelexplorer.shared.network.packet.play.EntityDestroyPacket;
import dev.sprock.pixelexplorer.shared.network.packet.play.EntitySpawnPacket;
import dev.sprock.pixelexplorer.shared.network.packet.play.EntityTeleportPacket;

public class ClientPacketListener extends PacketListener
{
    public ClientPacketListener()
    {
        this.setListener(EntitySpawnPacket.class, (packet, nullPlayer) -> {
            System.out.println("Spawned Entity " + packet.getEntityId());
        });

        this.setListener(EntityDestroyPacket.class, (packet, nullPlayer) -> {
            System.out.println("Destroyed Entity " + packet.getEntityId());
        });

        this.setListener(EntityTeleportPacket.class, (packet, nullPlayer) -> {
            System.out.println("Teleported Entity " + packet.getEntityId());
        });
    }


}
