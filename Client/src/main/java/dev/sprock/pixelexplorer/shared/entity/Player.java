package dev.sprock.pixelexplorer.shared.entity;

import dev.sprock.pixelexplorer.client.network.NettyClient;
import dev.sprock.pixelexplorer.shared.network.player.PlayerConnection;
import dev.sprock.pixelexplorer.shared.world.World;
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

    public Player(int entityId, World world, int x, int y)
    {
        super(entityId, world, x, y);
    }

    public void updateUsername(String username)
    {
        this.username = username;
    }
}
