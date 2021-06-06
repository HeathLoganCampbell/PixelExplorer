package dev.sprock.pixelexplorer.shared.utils;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class Vector
{
    private double x, y;

    public Vector(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public Vector()
    {
        this(0, 0);
    }

    public void setXY(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public void scale(double scalar)
    {
        this.x *= scalar;
        this.y *= scalar;
    }

    public Direction getDirection()
    {
        if(this.getX() > 0)
        {
            if(this.getY() > 0)
            {
                return Direction.NORTH_EAST;
            }

            if(this.getY() < 0)
            {
                return Direction.SOUTH_EAST;
            }

            return Direction.EAST;
        }

        if(this.getX() < 0)
        {

            if(this.getY() > 0)
            {
                return Direction.NORTH_WEST;
            }

            if(this.getY() < 0)
            {
                return Direction.SOUTH_WEST;
            }

            return Direction.WEST;
        }

        if(this.getY() > 0)
        {
            if(this.getX() > 0)
            {
                return Direction.NORTH_EAST;
            }

            if(this.getX() < 0)
            {
                return Direction.NORTH_EAST;
            }

            return Direction.NORTH;
        }

        if(this.getY() < 0)
        {
            if(this.getX() > 0)
            {
                return Direction.SOUTH_EAST;
            }

            if(this.getX() < 0)
            {
                return Direction.SOUTH_WEST;
            }

            return Direction.SOUTH;
        }

        return Direction.NORTH;
    }
}
