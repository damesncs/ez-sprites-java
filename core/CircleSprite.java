package core;
import java.awt.Color;
import java.awt.Graphics;

/** A circle sprite whose center is at (x,y) with given radius */
public class CircleSprite extends Sprite {
    
    private int radius;
    private Color color;

    public CircleSprite(int x, int y, int radius, Color color){
        super(x, y);
        this.radius = radius;
        this.color = color;
    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillOval(getX() - radius, getY() - radius, radius * 2, radius * 2);
    }

    public int getRadius(){
        return radius;
    }

    public void setRadius(int radius){
        this.radius = radius;
    }

    public void setColor(Color color){
        this.color = color;
    }

    public int getRightEdge(){
        return getX() + radius;
    }

    public int getLeftEdge(){
        return getX() - radius;
    }

    public int getTopEdge(){
        return getY() - radius;
    }

    public int getBottomEdge(){
        return getY() + radius;
    }

}
