package snake;

import java.awt.Color;

import core.CircleSprite;

public class SnakeSegment extends CircleSprite {

    private SnakeSegment next;

    public SnakeSegment(int x, int y, int radius, Color color) {
        super(x, y, radius, color);
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
