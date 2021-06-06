package dev.sprock.pixelexplorer.shared.utils;

import dev.sprock.pixelexplorer.shared.world.World;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location
{
    private World world;
    private int x, y;

    public Location(World world, int x, int y)
    {
        this.world = world;
        this.x = x;
        this.y = y;
    }

    public Location clone()
    {
        return new Location(this.world, this.x, this.y);
    }

    public Location add(Vector vector)
    {
        Location clone = this.clone();
        clone.setX((int) (clone.getX() + vector.getX()));
        clone.setY((int) (clone.getY() + vector.getY()));
        return clone;
    }

    public Location subtract(Vector vector)
    {
        Location clone = this.clone();
        clone.setX((int) (clone.getX() - vector.getX()));
        clone.setY((int) (clone.getY() - vector.getY()));
        return clone;
    }
}
