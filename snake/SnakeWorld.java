package snake;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import core.TextSprite;
import core.World;

public class SnakeWorld extends World implements KeyListener {

    static final int D = 20;
    static final int SPEED = 1;

    static final int INITIAL_SEGMENTS = 10;

    static int nextHeadDX = 0;
    static int nextHeadDY = -SPEED;
    
    static int frameCounter = 0;

    SnakeSegment head; // i.e., first segment
    SnakeSegment tail; // i.e., last segment

    TextSprite frames;

    public SnakeWorld(int width, int height){
        super(width, height);

        // uncomment this (and the line in updateSprites) to see the frame counter on the canvas:

        // frames = new TextSprite(50, 50, 100, 30, Integer.toString(frameCounter));
        // addSprite(frames);

        head = new SnakeSegment(getWorldWidth() / 2, getWorldHeight() / 2, D, D, Color.BLUE);
        head.setDX(nextHeadDX);
        head.setDY(nextHeadDY);
        addSprite(head);

        SnakeSegment prevSegment = head;
        SnakeSegment thisSegment;

        for(int i = 0; i < INITIAL_SEGMENTS; i++){
            thisSegment = new SnakeSegment(head.getX(), head.getY() + (D * i) + D, D, D, Color.ORANGE);
            thisSegment.setDX(head.getDX());
            thisSegment.setDY(head.getDY());
            addSprite(thisSegment);

            prevSegment.setNextSegment(thisSegment);
            prevSegment = thisSegment;
            tail = thisSegment;
        }
        tail.setColor(Color.RED); // for debugging

    }

    public void updateSprites(){
        
        if(frameCounter % (D / SPEED) == 0){
            head.setDXCascade(nextHeadDX);
            head.setDYCascade(nextHeadDY);
        }
        frameCounter++;
        // frames.setText(Integer.toString(frameCounter));
        

        super.updateSprites(); // this advances all sprite positions one frame
    }
    
    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            nextHeadDX = 0;
            nextHeadDY = -SPEED;
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            nextHeadDX = 0;
            nextHeadDY = SPEED;
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            nextHeadDX = -SPEED;
            nextHeadDY = 0;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            nextHeadDX = SPEED;
            nextHeadDY = 0;
        }
    }

    public void keyReleased(KeyEvent e) {
        // do nothing
    }

}

