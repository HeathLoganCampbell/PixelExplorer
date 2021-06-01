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
}
