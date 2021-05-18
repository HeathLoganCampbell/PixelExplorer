package dev.sprock.pixelexplorer.shared.network.listener;

import dev.sprock.pixelexplorer.shared.entity.OnlinePlayer;
import dev.sprock.pixelexplorer.shared.entity.Player;
import dev.sprock.pixelexplorer.shared.network.packet.Packet;

@FunctionalInterface
public interface PacketListenerConsumer<T extends Packet> {
    void accept(T packet , OnlinePlayer player);
}