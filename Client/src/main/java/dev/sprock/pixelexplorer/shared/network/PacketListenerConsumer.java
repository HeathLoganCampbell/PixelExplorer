package dev.sprock.pixelexplorer.shared.network;

import dev.sprock.pixelexplorer.shared.network.packet.Packet;

@FunctionalInterface
public interface PacketListenerConsumer<T extends Packet> {
    void accept(T packet /*, Player player */);
}