package dev.sprock.pixelexplorer.shared.network;

import com.sun.istack.internal.NotNull;
import dev.sprock.pixelexplorer.shared.entity.Player;
import dev.sprock.pixelexplorer.shared.network.packet.Packet;
import dev.sprock.pixelexplorer.shared.network.packet.login.LoginPacket;
import dev.sprock.pixelexplorer.shared.network.packet.play.DummyPacket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PacketListener
{
    private final Map<Class<? extends Packet>, PacketListenerConsumer> listeners = new ConcurrentHashMap<>();

    public PacketListener()
    {
        setListener(DummyPacket.class, (packet, player)-> {
            System.out.println("Recieved " + packet.toString());
        });

        setListener(LoginPacket.class, (packet, player)-> {
            String username = packet.getUsername();
            player.updateUsername(username);

            System.out.println("Login Username: " + username);
        });
    }

    public <T extends Packet> void processPacket(@NotNull T packet, @NotNull Player player ) {

        final Class clazz = packet.getClass();

        PacketListenerConsumer<T> packetListenerConsumer = listeners.get(clazz);

        packetListenerConsumer.accept(packet, player);
    }

    public <T extends Packet> void setListener(@NotNull Class<T> packetClass, @NotNull PacketListenerConsumer<T> consumer) {
        this.listeners.put(packetClass, consumer);
    }
}
