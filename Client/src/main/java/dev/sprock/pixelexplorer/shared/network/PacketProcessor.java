package dev.sprock.pixelexplorer.shared.network;

import dev.sprock.pixelexplorer.shared.common.ServerOnly;
import dev.sprock.pixelexplorer.shared.entity.Player;
import dev.sprock.pixelexplorer.shared.network.packet.Packet;
import dev.sprock.pixelexplorer.shared.network.packet.TransferPacket;
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
        packet.read(byteBuf);

        // Only Servers need to keep track of the player that sent in the packet
        // As clients will only ever recieve packets from the server.
        Player player = null;
        if(runMode == RunMode.SERVER)
        {
            PlayerConnection playerConnection = this.connectedPlayerMap.get(ctx);
            player = playerConnection.getPlayer();
        }

        packetListener.processPacket(packet, player);
    }
}
