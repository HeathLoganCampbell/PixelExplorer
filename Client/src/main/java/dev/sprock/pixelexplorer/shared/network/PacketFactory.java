package dev.sprock.pixelexplorer.shared.network;

import dev.sprock.pixelexplorer.shared.network.common.ReturnConsumer;
import dev.sprock.pixelexplorer.shared.network.packet.Packet;
import dev.sprock.pixelexplorer.shared.network.packet.PacketConstants;
import dev.sprock.pixelexplorer.shared.network.packet.login.LoginPacket;
import dev.sprock.pixelexplorer.shared.network.packet.play.DummyPacket;
import dev.sprock.pixelexplorer.shared.network.packet.play.EntityDestroyPacket;
import dev.sprock.pixelexplorer.shared.network.packet.play.EntitySpawnPacket;
import dev.sprock.pixelexplorer.shared.network.packet.play.EntityTeleportPacket;

public class PacketFactory
{
    public ReturnConsumer<Packet>[] packetCreators = new ReturnConsumer[100];

    public PacketFactory()
    {
        this.register(PacketConstants.DUMMY_PACKET_ID, DummyPacket::new);

        this.register(PacketConstants.LOGIN_PACKET_ID, LoginPacket::new);

        this.register(PacketConstants.ENTITY_SPAWN_PACKET_ID, EntitySpawnPacket::new);
        this.register(PacketConstants.ENTITY_TELEPORT_PACKET_ID, EntityTeleportPacket::new);
        this.register(PacketConstants.ENTITY_DESTROY_PACKET_ID, EntityDestroyPacket::new);
    }

    public void register(int packetId, ReturnConsumer<Packet> packerConsumer)
    {
        packetCreators[packetId] = packerConsumer;
    }

    public Packet getPacketInstance(int packetId)
    {
        ReturnConsumer<Packet> packetCreator = packetCreators[packetId];
        if(packetCreator == null)
        {
            System.out.println("No packet Id " + packetId);
            return null;
        }
        return packetCreator.accept();
    }
}
