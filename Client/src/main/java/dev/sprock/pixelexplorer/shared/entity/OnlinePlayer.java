package dev.sprock.pixelexplorer.shared.entity;

import dev.sprock.pixelexplorer.shared.network.PacketProcessor;
import dev.sprock.pixelexplorer.shared.network.packet.Packet;
import dev.sprock.pixelexplorer.shared.network.player.PlayerConnection;
import lombok.Getter;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class OnlinePlayer extends Player
{
    @Getter
    private PlayerConnection playerConnection;
    private final Queue<Packet> queuedPackets = new ConcurrentLinkedQueue<Packet>();

    public OnlinePlayer(String username, PlayerConnection playerConnection)

    {
        super(username);
        this.playerConnection = playerConnection;

        this.initPlayerConnection();
    }

    public void initPlayerConnection()
    {
        this.playerConnection.setPlayer(this);
    }

    public PlayerConnection getPlayerConnection()
    {
        return this.playerConnection;
    }

    public void addPacketToQueue(Packet packet)
    {
        this.queuedPackets.add(packet);
    }

    @Override
    public void tick()
    {
        PacketProcessor packetProcessor = this.getPlayerConnection().getPacketProcessor();

        Packet packet;
        while ((packet = queuedPackets.poll()) != null) {
            System.out.println("process packet");
            packetProcessor.forceProcessPacket(packet, this);
        }

        super.tick();
    }
}
