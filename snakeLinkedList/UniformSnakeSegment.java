package snakeLinkedList;
import java.awt.Color;
import java.awt.Graphics;

import core.CircleSprite;
import core.Sprite;


public class UniformSnakeSegment extends Sprite {

    private static int radius = 5;
    private static Color color = Color.green;
    
    private UniformSnakeSegment next;

    public UniformSnakeSegment(int x, int y){
        super(x, y);
    }

    public void draw(Graphics g){
        g.setColor(color);
        g.fillOval(getX() - radius, getY() - radius, radius * 2, radius * 2);
    }

    public static void setColor(Color c){
        color = c;
    }

    public static void setRadius(int r){
        radius = r;
    }

    /**
     * If this is the last segment (its next is null), returns this segment.
     * If this is not the last segment, it calls this method for the next segment.
     * @return the last segment in the linked-list
     */
    public UniformSnakeSegment getTail(){
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
    public UniformSnakeSegment addSegmentToTail(int offsetX, int offsetY, int dx, int dy){
        if(next == null) {
            next = new UniformSnakeSegment(getX() + offsetX, getY() + offsetY);
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

    public int getRadius(){
        return radius;
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

    public boolean isColliding(CircleSprite other){
        return Math.hypot(this.getX() - other.getX(), this.getY() - other.getY()) <= this.getRadius() + other.getRadius();
    }
}
