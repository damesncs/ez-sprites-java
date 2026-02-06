package snakeLinkedList;

import java.awt.Color;

import core.CircleSprite;

public class SnakeSegment extends CircleSprite {

    private SnakeSegment next;

    public SnakeSegment(int x, int y, int radius, Color color) {
        super(x, y, radius, color);
    }

    /**
     * If this is the last segment (its next is null), returns this segment.
     * If this is not the last segment, it calls this method for the next segment.
     * @return the last segment in the linked-list
     */
    public SnakeSegment getTail(){
        if(next == null) return this;
        return next.getTail();
    }

    /**
     * If this is the last segment (its next is null), add a new segment as its next and return the new one.
     * If it is not the last, call this method on the next
     * @param offsetX the number of pixels to offset the new segment from the tail on X axis
     * @param offsetY the number of pixels to offset the new segment from the tail on Y axis
     * @param dx the new segment's DX
     * @param dy the new segment's DY
     * @return a reference to the new tail segment
     */
    public SnakeSegment addSegmentToTail(int offsetX, int offsetY, int dx, int dy){
        if(next == null) {
            next = new SnakeSegment(getX() + offsetX, getY() + offsetY, getRadius(), getColor());
            next.setDX(dx);
            next.setDY(dy);
            return next;
        }
        return next.addSegmentToTail(offsetX, offsetY, dx, dy);
    }
    
    /**
     * Sets the DY of the next segment to this segment's current DY,
     * then sets the current segment's DY to the given value.
    */
    public void setDYCascade(int dy){
        if (next != null){
            next.setDYCascade(this.getDY());
        } 
        this.setDY(dy);
    }

    /**
     * Sets the DX of the next segment to this segment's current DX,
     * then sets the current segment's DX to the given value.
    */
    public void setDXCascade(int dx){
        if (next != null){
            next.setDXCascade(this.getDX());
        }
        this.setDX(dx);
    }

}
