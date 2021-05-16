package dev.sprock.pixelexplorer.server.network;

import com.sun.istack.internal.NotNull;
import dev.sprock.pixelexplorer.shared.network.PacketProcessor;
import dev.sprock.pixelexplorer.shared.network.netty.codec.PacketDecoder;
import dev.sprock.pixelexplorer.shared.network.netty.codec.PacketEncoder;
import dev.sprock.pixelexplorer.shared.network.netty.codec.ProcessHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer
{
    private ServerBootstrap bootstrap;
    private EventLoopGroup boss, worker;
    private ServerSocketChannel channel;

    public void init(PacketProcessor packetProcessor)
    {
        boss = new NioEventLoopGroup(2);
        worker = new NioEventLoopGroup(2);

        Class<? extends ServerChannel> channel = NioServerSocketChannel.class;

        this.bootstrap = new ServerBootstrap()
                .group(boss, worker)
                .channel(channel);

        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(@NotNull SocketChannel ch) {
                ChannelConfig config = ch.config();
                config.setOption(ChannelOption.TCP_NODELAY, true);
                config.setOption(ChannelOption.SO_KEEPALIVE, true);
                config.setOption(ChannelOption.SO_SNDBUF, 262_144);
                config.setAllocator(ByteBufAllocator.DEFAULT);

                ChannelPipeline pipeline = ch.pipeline();

                pipeline.addLast("Decoder", new PacketDecoder());
                pipeline.addLast("Encoder", new PacketEncoder());
                pipeline.addLast("ServerChannel", new ProcessHandler(packetProcessor));
            }
        });
    }

    public void start( int port)
    {
        try {
            ChannelFuture cf = bootstrap.bind(port).sync();

            if (!cf.isSuccess()) {
                throw new IllegalStateException("Unable to bind server at Port: " + port);
            }

            this.channel = (ServerSocketChannel) cf.channel();
        } catch (InterruptedException ex) {
            this.worker.shutdownGracefully();
            this.boss.shutdownGracefully();
        }
    }
}
