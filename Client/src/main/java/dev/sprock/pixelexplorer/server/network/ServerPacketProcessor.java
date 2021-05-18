package dev.sprock.pixelexplorer.server.network;

import dev.sprock.pixelexplorer.shared.entity.OnlinePlayer;
import dev.sprock.pixelexplorer.shared.network.PacketProcessor;
import dev.sprock.pixelexplorer.shared.network.common.RunMode;
import dev.sprock.pixelexplorer.shared.network.listener.PacketListener;
import dev.sprock.pixelexplorer.shared.network.packet.Packet;

public class ServerPacketProcessor extends PacketProcessor
{
    public ServerPacketProcessor(PacketListener packetListener, RunMode runMode)
    {
        super(packetListener, runMode);
    }

    @Override
    public void processPacket(Packet packet, OnlinePlayer player)
    {
        player.addPacketToQueue(packet);
    }
}
