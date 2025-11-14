package core;
import java.awt.Color;
import java.awt.Graphics;

/** A rectangle sprite whose top left corner is at (x,y) with given width and height */
public class RectangleSprite extends Sprite {
    
    private int width;
    private int height;
    private Color color;

    public RectangleSprite(int x, int y, int width, int height, Color color){
        super(x, y);
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillRect(getX(), getY(), width, height);
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getRightEdge(){
        return getX() + width;
    }

    public int getLeftEdge(){
        return getX();
    }

    public int getTopEdge(){
        return getY();
    }

    public int getBottomEdge(){
        return getY() + height;
    }

}
