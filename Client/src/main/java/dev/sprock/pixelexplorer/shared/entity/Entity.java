package dev.sprock.pixelexplorer.shared.entity;

import dev.sprock.pixelexplorer.client.engine.graphics.Font;
import dev.sprock.pixelexplorer.client.engine.graphics.Screen;
import lombok.Getter;

public class Entity
{
    @Getter
    private int entityId;
    private int x, y;
    private double vx = 0, vy = 0;

    public void updatePosition(int x, int y)
    {
        this.x = x;
        this.y = y;

    }

    public double getVelocityDist()
    {
        return this.vx * this.vx + this.vy * this.vy;
    }

    public double getVelocityX()
    {
        return this.vx;
    }

    public double getVelocityY()
    {
        return this.vy;
    }

    public void setVelocity(double vx, double vy)
    {
        this.vx = vx;
        this.vy = vy;
    }

    public void setVelocityX(double vx)
    {
        this.vx = vx;
    }

    public void setVelocityY(double vy)
    {
        this.vy = vy;
    }

    public void remove()
    {

    }

    public void tick()
    {
        double FRICTION = 0.6;

        this.vx *= FRICTION;
        this.vy *= FRICTION;

        if(Math.abs(vy) < 0.03) vy = 0;
        if(Math.abs(vx) < 0.03) vx = 0;

        this.x += vx * 4;
        this.y += vy * 4;
    }

    public void render(Screen screen)
    {
        Font.text("O", screen, this.x, this.y);
    }
}
