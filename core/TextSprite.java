package core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/** A sprite whose main purpose is to display text (e.g., a scoreboard) */
public class TextSprite extends RectangleSprite {
    
    private String text;
    private Font font;
    private boolean drawBox;
    private Color bkgd;

    public TextSprite(int x, int y, int width, int height, String text){
        super(x, y, width, height, null);
        this.text = text;
        this.drawBox = false;
        this.bkgd = Color.WHITE;
        this.font = new Font(Font.SANS_SERIF, Font.PLAIN, 30);
    }

    public void setText(String text){
        this.text = text;
    }

    public void setFont(Font font){
        this.font = font;
    }

    /** Controls whether the bounding box of the sprite should be filled or transparent */
    public void setDrawBkgd(boolean drawBkgd) {
        this.drawBox = drawBkgd;
    }

    /** Sets the color of the filled bounding box, if it is drawn */
    public void setBkgd(Color bkgd) {
        this.bkgd = bkgd;
    }

    public void draw(Graphics g){
        if (drawBox) {
            g.setColor(bkgd);
            g.fillRect(getX(), getY(), getWidth(), getHeight());
        }
        g.setFont(font);
        g.drawString(text, getLeftEdge(), getBottomEdge());
    }


}
