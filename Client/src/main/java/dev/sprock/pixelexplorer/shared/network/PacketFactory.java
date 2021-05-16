package dev.sprock.pixelexplorer.shared.network;

import dev.sprock.pixelexplorer.shared.network.packet.Packet;
import dev.sprock.pixelexplorer.shared.network.packet.play.DummyPacket;

public class PacketFactory
{
    public Packet getPacketInstance(int packetId)
    {
        return new DummyPacket();
    }
}
