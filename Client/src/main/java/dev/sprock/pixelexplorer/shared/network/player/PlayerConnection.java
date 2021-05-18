package dev.sprock.pixelexplorer.shared.network.player;

import com.sun.istack.internal.NotNull;
import dev.sprock.pixelexplorer.shared.entity.OnlinePlayer;
import dev.sprock.pixelexplorer.shared.entity.Player;
import dev.sprock.pixelexplorer.shared.network.common.ConnectionState;
import dev.sprock.pixelexplorer.shared.network.packet.Packet;
import io.netty.channel.socket.SocketChannel;
import lombok.Getter;
import lombok.Setter;

import java.net.SocketAddress;

@Getter
public class PlayerConnection
{
    private final SocketChannel channel;
    private SocketAddress remoteAddress;

    private OnlinePlayer player;
    @Setter
    private ConnectionState state;

    public PlayerConnection(@NotNull SocketChannel channel)
    {
        this.channel = channel;
        this.remoteAddress = channel.remoteAddress();
    }

    public OnlinePlayer getPlayer() {
        return player;
    }

    public void setPlayer(OnlinePlayer player) {
        this.player = player;
    }

    public void sendPacket(Packet packet)
    {
        this.channel.writeAndFlush(packet);
    }
}
