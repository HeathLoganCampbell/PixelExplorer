package dev.sprock.pixelexplorer.shared.network.common;

import dev.sprock.pixelexplorer.shared.entity.Player;
import dev.sprock.pixelexplorer.shared.network.packet.Packet;

@FunctionalInterface
public interface ReturnConsumer<T extends Packet>
{
    T accept();
}