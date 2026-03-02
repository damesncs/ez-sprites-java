package core;

import java.awt.Color;
import java.awt.Graphics;

public class CellSprite extends RectangleSprite{
    private String text;
    private String value;

    public static final String MINE = "M";
    public static final String EMPTY = "E";
    public static final String Opened = "O";

    public CellSprite(int x, int y, int d, Color color, String Text){
        super(x,y, d, d, color);
        this.text = Text;
        this.value = EMPTY;
    }

    public void changeToMine(){
        this.value = MINE;
    }

    public String getValue(){
        return this.value;
    }

    public void showValue(){
        text = value;
    }

    
    /* draw with outline */
    public void draw(Graphics g){
        g.setColor(getColor());
        g.fillRect(getX(), getY(), getWidth(), getHeight());
        g.setColor(Color.BLACK);
        g.drawRect(getX(), getY(), getWidth(), getHeight());
        g.drawString(text, getLeftEdge(), getBottomEdge());
    }



}


