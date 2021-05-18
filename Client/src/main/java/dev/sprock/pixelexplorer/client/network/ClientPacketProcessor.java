package dev.sprock.pixelexplorer.client.network;

import dev.sprock.pixelexplorer.shared.entity.OnlinePlayer;
import dev.sprock.pixelexplorer.shared.network.PacketProcessor;
import dev.sprock.pixelexplorer.shared.network.common.RunMode;
import dev.sprock.pixelexplorer.shared.network.listener.PacketListener;
import dev.sprock.pixelexplorer.shared.network.packet.Packet;

public class ClientPacketProcessor extends PacketProcessor
{
    public ClientPacketProcessor(PacketListener packetListener, RunMode runMode)
    {
        super(packetListener, runMode);
    }
}
