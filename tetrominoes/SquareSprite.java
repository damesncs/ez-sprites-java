package tetrominoes;

import java.awt.Color;
import java.awt.Graphics;

import core.RectangleSprite;

public class SquareSprite extends RectangleSprite{

    public SquareSprite(int x, int y, int d, Color color) {
        super(x, y, d, d, color);
    }
    
    /* draw with outline */
    public void draw(Graphics g){
        g.setColor(getColor());
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        g.setColor(Color.BLACK);
        g.drawRect(getX(), getY(), getWidth(), getHeight());
    }

}
