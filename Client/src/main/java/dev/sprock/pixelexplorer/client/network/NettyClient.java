package dev.sprock.pixelexplorer.client.network;

import com.sun.istack.internal.NotNull;
import dev.sprock.pixelexplorer.shared.network.PacketProcessor;
import dev.sprock.pixelexplorer.shared.network.netty.codec.PacketDecoder;
import dev.sprock.pixelexplorer.shared.network.netty.codec.PacketEncoder;
import dev.sprock.pixelexplorer.shared.network.netty.codec.ProcessHandler;
import dev.sprock.pixelexplorer.shared.network.packet.Packet;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class NettyClient
{
    private String address = "localhost";
    private int port = 8000;

    private Bootstrap bootstrap;
    private EventLoopGroup workerGroup;

    private Channel channel;

    public NettyClient()
    {

    }

    public void init(PacketProcessor packetProcessor)
    {

        this.workerGroup = new NioEventLoopGroup();

        this.bootstrap = new Bootstrap()
                .group(this.workerGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true);

        bootstrap.handler(new ChannelInitializer<SocketChannel>()
        {
            @Override
            public void initChannel(SocketChannel ch) throws Exception
            {
                ChannelPipeline pipeline = ch.pipeline();

                pipeline.addLast("Decoder", new PacketDecoder());
                pipeline.addLast("Encoder", new PacketEncoder());
                pipeline.addLast("ServerChannel", new ProcessHandler(packetProcessor));
            }
        });
    }

    public void start(@NotNull String address, int port)
    {
        this.address = address;
        this.port = port;

        try {
            ChannelFuture cf = bootstrap.connect(new InetSocketAddress(address, port)).sync();
            if (!cf.isSuccess()) {
                throw new IllegalStateException("Unable to bind server at " + address + ":" + port);
            }
            this.channel = cf.channel();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            workerGroup.shutdownGracefully();
        }
    }

    public void sendPacket(Packet packet)
    {
        this.channel.writeAndFlush(packet);
    }
}
