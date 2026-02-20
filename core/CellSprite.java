package core;
import java.awt.Color;
import java.awt.Graphics;

public class CellSprite extends RectangleSprite{
    public CellSprite(int x, int y, int d, Color color){
        super(x,y, d, d, color);
    }
    
    /* draw with outline */
    public void draw(Graphics g){
        g.setColor(getColor());
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        g.setColor(Color.BLACK);
        g.drawRect(getX(), getY(), getWidth(), getHeight());
    }

}


