package dev.sprock.pixelexplorer.shared.network.netty.codec;

import com.sun.istack.internal.NotNull;
import dev.sprock.pixelexplorer.shared.entity.Player;
import dev.sprock.pixelexplorer.shared.network.PacketProcessor;
import dev.sprock.pixelexplorer.shared.network.packet.TransferPacket;
import dev.sprock.pixelexplorer.shared.network.player.PlayerConnection;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerProcessHandler extends SimpleChannelInboundHandler<TransferPacket>
{
    private PacketProcessor packetProcessor;

    public ServerProcessHandler(PacketProcessor packetProcessor)
    {
        this.packetProcessor = packetProcessor;
    }

    @Override
    public void channelActive(@NotNull ChannelHandlerContext ctx)
    {
        packetProcessor.registerPlayerConnection(ctx);
    }

    @Override
    public void channelInactive(@NotNull ChannelHandlerContext ctx)
    {
        PlayerConnection playerConnection = packetProcessor.unregisterPlayerConnection(ctx);
        if (playerConnection != null) {
            Player player = playerConnection.getPlayer();
            if (player != null)
            {
                player.remove();
            }
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TransferPacket transferPacket) throws Exception
    {
        this.packetProcessor.process(ctx, transferPacket);
    }
}
