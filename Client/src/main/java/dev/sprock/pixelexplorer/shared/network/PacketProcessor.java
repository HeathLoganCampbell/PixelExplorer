package dev.sprock.pixelexplorer.shared.network;

import dev.sprock.pixelexplorer.shared.network.packet.Packet;
import dev.sprock.pixelexplorer.shared.network.packet.TransferPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class PacketProcessor
{
    private PacketFactory packetFactory = new PacketFactory();
    private PacketListener packetListener;

    public PacketProcessor(PacketListener packetListener)
    {
        this.packetListener = packetListener;
    }

    public void process(ChannelHandlerContext ctx, TransferPacket transferPacket)
    {
        final int packetId = transferPacket.getPacketId();
        ByteBuf byteBuf = transferPacket.getByteBuf();

        Packet packet = packetFactory.getPacketInstance(packetId);
        packet.read(byteBuf);

        packetListener.processPacket(packet);
    }
}
