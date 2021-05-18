package dev.sprock.pixelexplorer.shared.entity;

import lombok.Getter;

public class Entity
{
    @Getter
    private int entityId;
    private int x, y;

    public void updatePosition(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void remove()
    {

    }

    public void update()
    {

    }
}
