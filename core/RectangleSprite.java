package core;
import java.awt.Color;
import java.awt.Graphics;

/** A rectangle sprite whose top left corner is at (x,y) with given width and height */
public class RectangleSprite extends Sprite {
    
    public int width;
    public int height;
    public Color color;

    public RectangleSprite(int x, int y, int width, int height, Color color){
        super(x, y);
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

}
