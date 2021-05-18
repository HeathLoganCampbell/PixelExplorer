package dev.sprock.pixelexplorer.shared.network.player;

import com.sun.istack.internal.NotNull;
import dev.sprock.pixelexplorer.shared.entity.Player;
import dev.sprock.pixelexplorer.shared.network.packet.Packet;
import io.netty.channel.socket.SocketChannel;

import java.net.SocketAddress;

public class PlayerConnection
{
    private final SocketChannel channel;
    private SocketAddress remoteAddress;

    private Player player;

    public PlayerConnection(@NotNull SocketChannel channel)
    {
        this.channel = channel;
        this.remoteAddress = channel.remoteAddress();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void sendPacket(Packet packet)
    {

    }
}
