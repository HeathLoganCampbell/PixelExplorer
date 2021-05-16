package dev.sprock.pixelexplorer.shared.network.netty.codec;

import dev.sprock.pixelexplorer.shared.network.PacketProcessor;
import dev.sprock.pixelexplorer.shared.network.packet.Packet;
import dev.sprock.pixelexplorer.shared.network.packet.TransferPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ProcessHandler extends SimpleChannelInboundHandler<TransferPacket>
{
    private PacketProcessor packetProcessor;

    public ProcessHandler(PacketProcessor packetProcessor)
    {
        this.packetProcessor = packetProcessor;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TransferPacket transferPacket) throws Exception
    {
        this.packetProcessor.process(ctx, transferPacket);
    }
}
