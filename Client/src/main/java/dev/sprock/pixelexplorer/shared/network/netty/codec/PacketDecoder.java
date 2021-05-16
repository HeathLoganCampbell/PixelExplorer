package dev.sprock.pixelexplorer.shared.network.netty.codec;

import dev.sprock.pixelexplorer.shared.network.packet.TransferPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder
{
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> out) throws Exception
    {
        if (byteBuf.readableBytes() > 0)
        {
            int packetId = byteBuf.readInt();
            out.add(new TransferPacket(packetId, byteBuf));
        }
    }
}
