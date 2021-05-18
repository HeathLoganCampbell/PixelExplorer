package dev.sprock.pixelexplorer.shared.network;

import dev.sprock.pixelexplorer.shared.entity.Player;
import dev.sprock.pixelexplorer.shared.network.packet.Packet;

@FunctionalInterface
public interface ReturnConsumer<T extends Packet>
{
    T accept();
}