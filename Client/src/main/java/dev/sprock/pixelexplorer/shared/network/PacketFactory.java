package dev.sprock.pixelexplorer.shared.network;

import dev.sprock.pixelexplorer.shared.network.packet.Packet;
import dev.sprock.pixelexplorer.shared.network.packet.PacketConstants;
import dev.sprock.pixelexplorer.shared.network.packet.login.LoginPacket;
import dev.sprock.pixelexplorer.shared.network.packet.play.DummyPacket;

import java.util.function.Consumer;

public class PacketFactory
{
    public ReturnConsumer<Packet>[] packetCreators = new ReturnConsumer[100];

    public PacketFactory()
    {
        this.register(PacketConstants.DUMMY_PACKET_ID, DummyPacket::new);

        this.register(PacketConstants.LOGIN_PACKET_ID, LoginPacket::new);
    }

    public void register(int packetId, ReturnConsumer<Packet> packerConsumer)
    {
        packetCreators[packetId] = packerConsumer;
    }

    public Packet getPacketInstance(int packetId)
    {
        return packetCreators[packetId].accept();
    }
}
