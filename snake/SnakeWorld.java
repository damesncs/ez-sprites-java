package snake;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import core.RectangleSprite;
import core.CircleSprite;
import core.TextSprite;
import core.World;

public class SnakeWorld extends World implements KeyListener {

    static final int D = 20; // snake segment diameter
    static final int SPEED = 1;

    static final int INITIAL_SEGMENTS = 10;

    static int nextHeadDX = 0;
    static int nextHeadDY = -SPEED;
    
    static int frameCounter = 0;

    SnakeSegment head; // i.e., first segment
    SnakeSegment tail; // i.e., last segment

    CircleSprite food;

    TextSprite frames;

    public SnakeWorld(int width, int height){
        super(width, height);

        // uncomment this (and the line in updateSprites) to see the frame counter on the canvas:

        // frames = new TextSprite(50, 50, 100, 30, Integer.toString(frameCounter));
        // addSprite(frames);

        head = new SnakeSegment(getWorldWidth() / 2, getWorldHeight() / 2, D / 2, Color.BLUE);
        head.setDX(nextHeadDX);
        head.setDY(nextHeadDY);
        addSprite(head);

        SnakeSegment prevSegment = head;
        SnakeSegment thisSegment;

        for(int i = 0; i < INITIAL_SEGMENTS; i++){
            thisSegment = new SnakeSegment(head.getX(), head.getY() + (D * i) + D, D / 2, Color.ORANGE);
            thisSegment.setDX(head.getDX());
            thisSegment.setDY(head.getDY());
            addSprite(thisSegment);

            prevSegment.setNextSegment(thisSegment);
            prevSegment = thisSegment;
            tail = thisSegment;
        }
        tail.setColor(Color.CYAN); // for debugging

        food = new CircleSprite(0, 0, D / 2, Color.RED);
        setFoodToRandomPosition();
        addSprite(food);

    }

    public void updateSprites(){
        
        if(frameCounter % (D / SPEED) == 0){
            head.setDXCascade(nextHeadDX);
            head.setDYCascade(nextHeadDY);
        }
        frameCounter++;
        // frames.setText(Integer.toString(frameCounter));
        
        if(head.isColliding(food)){
            System.out.println("food get!");
            setFoodToRandomPosition();
            // TODO add a snake segment:
            // 1. initialize a new SnakeSegment
            // 2. set the tail's `next` to the new segment
            // 3. set `tail` to the new segment 
        }

        // TODO bonus: check if head colliding with any other segment

        super.updateSprites(); // this advances all sprite positions one frame
    }

    private void setFoodToRandomPosition(){
        food.setX(getRandomInt(0, getWorldWidth() - D));
        food.setY(getRandomInt(0, getWorldHeight() - D));
    }

    private int getRandomInt(int min, int max){
        return (int) (Math.random() * (max - min + 1) + min);
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

