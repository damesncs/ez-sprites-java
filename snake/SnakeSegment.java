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

    // if this is the last segment (its next is null), returns this segment.
    // if this is not the last segment, it calls this method for the next segment.
    public SnakeSegment getTail(){
        if(next == null) return this;
        return next.getTail();
    }


    
    public SnakeSegment addSegmentToTail(int offsetX, int offsetY){
        if(next == null) {
            // TODO fix
            next = new SnakeSegment(getX() + offsetX, getY() + offsetY, getRadius(), getColor());
            next.setDX(getDX());
            next.setDY(getDY());
            return next;
        }
        return next.addSegmentToTail(offsetX, offsetY);
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
