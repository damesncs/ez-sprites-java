package core;
import java.awt.Color;
import java.awt.Graphics;

public class CircleSprite extends Sprite {
    
    public int radius;
    public Color color;

    public CircleSprite(int x, int y, int radius, Color color){
        super(x, y);
        this.radius = radius;
        this.color = color;
    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillArc(x, y, radius, radius, 0, 360);
    }
}
