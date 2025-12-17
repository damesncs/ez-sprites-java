package snake;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import core.TextSprite;
import core.World;

public class SnakeWorld extends World implements KeyListener {

    static final int D = 10;
    static final int SPEED = 1;

    static final int INITIAL_SEGEMENTS = 10;

    static int nextHeadDX = 0;
    static int nextHeadDY = -SPEED;
    
    static int frameCounter = 0;

    SnakeSegment head;

    TextSprite frames;

    public SnakeWorld(int width, int height){
        super(width, height);

        // frames = new TextSprite(50, 50, 100, 30, Integer.toString(frameCounter));
        // addSprite(frames);

        head = new SnakeSegment(getWorldWidth() / 2, getWorldHeight() / 2, D, D, Color.GREEN);
        head.setDX(nextHeadDX);
        head.setDY(nextHeadDY);
        addSprite(head);

        SnakeSegment prevSegment = head;
        SnakeSegment thisSegment;
        for(int i = 1; i <= 10; i++){
            thisSegment = new SnakeSegment(head.getX(), head.getY() + D * i, D, D, Color.RED);
            thisSegment.setDX(head.getDX());
            thisSegment.setDY(head.getDY());
            prevSegment.setNextSegment(thisSegment);
            prevSegment = thisSegment;
            addSprite(thisSegment);
        }

    }

    /** This method is called by the `TimerListener` for every frame.
     *  It updates the game state by:
     *  1. checking for collisions and handling them.
     *  2. moving all sprites by applying their dx and dy (this is done by the superclass's method)
     */
    public void updateSprites(){
        if(frameCounter % D == 0){
            head.setDXCascade(nextHeadDX);
            head.setDYCascade(nextHeadDY);
        }

        // frames.setText(Integer.toString(frameCounter));
        frameCounter++;

        super.updateSprites(); // this advances all sprite positions one frame
    }
    
    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    /** called when any key is pressed */
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            // head.setDX(0);
            // head.setDY(-SPEED);
            nextHeadDX = 0;
            nextHeadDY = -SPEED;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            // head.setDX(0);
            // head.setDY(SPEED);
            nextHeadDX = 0;
            nextHeadDY = SPEED;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            // head.setDX(-SPEED);
            // head.setDY(0);
            nextHeadDX = -SPEED;
            nextHeadDY = 0;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            // head.setDX(SPEED);
            // head.setDY(0);
            nextHeadDX = SPEED;
            nextHeadDY = 0;
        }
    }

    /** called when any key is released after being pressed */
    public void keyReleased(KeyEvent e) {
        // do nothing
    }

}

