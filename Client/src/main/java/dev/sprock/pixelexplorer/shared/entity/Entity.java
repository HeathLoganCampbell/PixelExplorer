package dev.sprock.pixelexplorer.shared.entity;

import dev.sprock.pixelexplorer.client.engine.graphics.Font;
import dev.sprock.pixelexplorer.client.engine.graphics.Screen;
import dev.sprock.pixelexplorer.shared.utils.Vector;
import lombok.Getter;

public class Entity
{
    @Getter
    private int entityId;
    private int x, y;
    @Getter
    private Vector velocity = new Vector();

    public void updatePosition(int x, int y)
    {
        this.x = x;
        this.y = y;

    }

    public void remove()
    {

    }

    public void tick()
    {
        double FRICTION = 0.6;

        this.velocity.scale(FRICTION);

        if(Math.abs(this.velocity.getY()) < 0.03) this.velocity.setY(0);
        if(Math.abs(this.velocity.getX()) < 0.03) this.velocity.setX(0);

        this.x += this.velocity.getX() * 2;
        this.y += this.velocity.getY() * 2;
    }

    public void render(Screen screen)
    {
        Font.text("O", screen, this.x, this.y);
    }
}
