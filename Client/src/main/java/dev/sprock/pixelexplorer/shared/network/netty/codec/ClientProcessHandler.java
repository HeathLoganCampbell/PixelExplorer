package dev.sprock.pixelexplorer.shared.network.netty.codec;

import com.sun.istack.internal.NotNull;
import dev.sprock.pixelexplorer.shared.entity.Player;
import dev.sprock.pixelexplorer.shared.network.PacketProcessor;
import dev.sprock.pixelexplorer.shared.network.packet.TransferPacket;
import dev.sprock.pixelexplorer.shared.network.player.PlayerConnection;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientProcessHandler extends SimpleChannelInboundHandler<TransferPacket>
{
    private PacketProcessor packetProcessor;

    public ClientProcessHandler(PacketProcessor packetProcessor)
    {
        this.packetProcessor = packetProcessor;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TransferPacket transferPacket) throws Exception
    {
        this.packetProcessor.process(ctx, transferPacket);
    }
}
