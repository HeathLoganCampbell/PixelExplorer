package dev.sprock.pixelexplorer.shared.entity;

import lombok.Getter;

@Getter
public enum EntityType
{
    PLAYER(1);

    private int id;

    EntityType(int id)
    {
        this.id = id;
    }
}
