package dev.sprock.pixelexplorer.shared.entity;

import dev.sprock.pixelexplorer.client.network.NettyClient;
import dev.sprock.pixelexplorer.shared.network.player.PlayerConnection;
import lombok.Getter;
import lombok.Setter;

public class Player extends Entity
{
    @Getter
    private String username;

    public Player(String username)

    {
        this.username = username;
    }

    public void updateUsername(String username)
    {
        this.username = username;
    }
}
