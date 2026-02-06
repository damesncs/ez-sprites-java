package core;
import java.awt.Color;
import java.awt.Graphics;

public class LineSprite extends Sprite {
    private int length;
    private Color color;

    public LineSprite(int x, int y, int length, Color color){
        super(x,y);
        this.length = length;
        this.color = color;
    }

public void draw(Graphics g){
    g.setColor(color);
    g.fillRect(getX(), getY(), length)
}
}
