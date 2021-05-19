package dev.sprock.pixelexplorer.shared.network;

import com.sun.istack.internal.NotNull;
import dev.sprock.pixelexplorer.shared.common.ServerOnly;
import dev.sprock.pixelexplorer.shared.entity.OnlinePlayer;
import dev.sprock.pixelexplorer.shared.network.common.RunMode;
import dev.sprock.pixelexplorer.shared.network.listener.PacketListener;
import dev.sprock.pixelexplorer.shared.network.packet.Packet;
import dev.sprock.pixelexplorer.shared.network.packet.TransferPacket;
import dev.sprock.pixelexplorer.shared.network.packet.login.LoginPacket;
import dev.sprock.pixelexplorer.shared.network.player.PlayerConnection;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.SocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PacketProcessor
{
    private PacketFactory packetFactory = new PacketFactory();
    private PacketListener packetListener;
    private RunMode runMode;

    private final Map<ChannelHandlerContext, PlayerConnection> connectedPlayerMap = new ConcurrentHashMap<>();

    public PacketProcessor(PacketListener packetListener, RunMode runMode) {
        this.packetListener = packetListener;
        this.runMode = runMode;
    }

    @ServerOnly
    public PlayerConnection registerPlayerConnection(ChannelHandlerContext ctx)
    {
        PlayerConnection playerConnection = new PlayerConnection((SocketChannel) ctx.channel());
        playerConnection.setPacketProcessor(this);
        this.connectedPlayerMap.put(ctx, playerConnection);
        return playerConnection;
    }

    @ServerOnly
    public PlayerConnection unregisterPlayerConnection(ChannelHandlerContext ctx)
    {
        return this.connectedPlayerMap.remove(ctx);
    }

    public void process(ChannelHandlerContext ctx, TransferPacket transferPacket)
    {
        final int packetId = transferPacket.getPacketId();
        ByteBuf byteBuf = transferPacket.getByteBuf();

        Packet packet = packetFactory.getPacketInstance(packetId);
        if(packet == null) return; // drop
        packet.read(byteBuf);

        // Only Servers need to keep track of the player that sent in the packet
        // As clients will only ever recieve packets from the server.
        OnlinePlayer player = null;
        if(runMode == RunMode.SERVER)
        {
            PlayerConnection playerConnection = this.connectedPlayerMap.get(ctx);

            player = playerConnection.getPlayer();

            // Player is not created yet
            if(player == null)
            {
                if(packet instanceof LoginPacket)
                {
                    LoginPacket loginPacket = (LoginPacket) packet;
                    player = new OnlinePlayer(loginPacket.getUsername(), playerConnection);
                }
                else
                {
                    throw new RuntimeException("Player has sent a " + packet.toString() + " and hasn't got a player instance");
                }
            }
        }

        this.processPacket(packet, player);
    }

    public void processPacket(@NotNull Packet packet, @NotNull OnlinePlayer player)
    {
       this.forceProcessPacket(packet, player);
    }

    public void forceProcessPacket(@NotNull Packet packet, @NotNull OnlinePlayer player)
    {
        packetListener.processPacket(packet, player);
    }

    // TEMP
    public void update()
    {
        if (this.connectedPlayerMap.size() == 0)
        {
            return;
        }

        // Tick all players
        this.connectedPlayerMap.forEach((key, value) -> {
            OnlinePlayer player = value.getPlayer();
            if(player == null) return;
            player.update();
        });
    }
}
