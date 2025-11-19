package core;
import java.awt.Graphics;

public class Sprite {
    
    /** x-coordinate for this sprite. 
     * Subclasses determine how the actual shape is drawn
     * in relation to this point.
     *  */
    private int x;

    /** y-coordinate for this sprite.
     * Subclasses determine how the actual shape is drawn
     * in relation to this point.
    */
    private int y; 

    /** The number of pixels this Sprite will move each frame on the x-axis.
     *  Positive dx values will move the sprite to the right,
     *  while negative values will move the sprite to the left. */
    private int dx;

    /** The number of pixels this Sprite will move each frame on the y-axis.
     *  Positive dy values will move the sprite down (towards the bottom of the canvas),
     *  while negative values will move the sprite up (towards the top of the canvas).
    */
    private int dy;

    /** Creates a sprite with x, y, dx, and dy set to zero */
    public Sprite(){
        this.x = 0;
        this.y = 0;
        this.dx = 0;
        this.dy = 0;
    }

    /** Creates a sprite with the given initial values for x and y, and with dx and dy set to 0 */
    public Sprite(int x, int y){
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
    }

    /** Creates a sprite with the given initial values for x, y, dx, and dy */
    public Sprite(int x, int y, int dx, int dy){
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
    }

    /** Draws the sprite using the given Graphics object. 
     * This is called by Canvas to draw the sprite on each iteration of the animation loop (each "frame").
     * Subclasses should override this. */
    public void draw(Graphics g){
        return;
    }

    /** Subclasses should override this to return the rightmost extent of the sprite for basic collision checking */
    public int getRightEdge(){
        return 0;
    }

    /** Subclasses should override this to return the leftmost extent of the sprite for basic collision checking */
    public int getLeftEdge(){
        return 0;
    }

    /** Subclasses should override this to return the topmost extent of the sprite for basic collision checking */
    public int getTopEdge(){
        return 0;
    }

    /** Subclasses should override this to return the bottommost extent of the sprite for basic collision checking */
    public int getBottomEdge(){
        return 0;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getDX(){
        return dx;
    }

    public int getDY(){
        return dy;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setDX(int dx){
        this.dx = dx;
    }

    public void setDY(int dy){
        this.dy = dy;
    }

}
