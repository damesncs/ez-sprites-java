package snake;

import java.awt.Color;

import core.RectangleSprite;

public class SnakeSegment extends RectangleSprite {

    private SnakeSegment next;

    public SnakeSegment(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    public void setNextSegment(SnakeSegment seg){
        next = seg;
    }

    public SnakeSegment getNextSegment(){
        return next;
    }
    
    public void setDYCascade(int dy){
        if (next != null){
            next.setDYCascade(this.getDY());
        } 
        this.setDY(dy);
    }

    public void setDXCascade(int dx){
        if (next != null){
            next.setDXCascade(this.getDX());
        }
        this.setDX(dx);
    }


}
