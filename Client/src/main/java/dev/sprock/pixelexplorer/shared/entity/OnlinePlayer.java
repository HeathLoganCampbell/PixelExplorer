package dev.sprock.pixelexplorer.shared.entity;

import dev.sprock.pixelexplorer.shared.network.player.PlayerConnection;
import lombok.Getter;

public class OnlinePlayer extends Player
{
    @Getter
    private PlayerConnection playerConnection;

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
}
