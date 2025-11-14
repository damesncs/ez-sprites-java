package core;
import java.awt.Color;
import java.awt.Graphics;

/** A circle sprite whose center is at (x,y) with given radius */
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
        g.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}
