package dev.sprock.pixelexplorer.shared.network.netty.codec;

import dev.sprock.pixelexplorer.shared.network.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet>
{
    @Override
    protected void encode(ChannelHandlerContext ctx, Packet packet, ByteBuf in) throws Exception
    {
        in.writeInt(packet.getPacketId());
        packet.write(in);
    }
}
