package snakeLinkedList;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import core.CircleSprite;
import core.TextSprite;
import core.World;

/**
 * Linked-list 'recursive' implementation of snake.
 * Challenges:
 *  - implement checking collisions 
 */
public class SnakeWorld extends World implements KeyListener {

    static final int D = 14; // snake segment diameter
    static final int SPEED = 2;

    static final int INITIAL_SEGMENTS = 10;

    static int nextHeadDX = 0;
    static int nextHeadDY = -SPEED;
    
    static int frameCounter = 0;

    SnakeSegment head; // i.e., first segment

    CircleSprite food;

    TextSprite frames;

    boolean addSegmentOnNextInterval = false;

    public SnakeWorld(int width, int height){
        super(width, height);

        head = new SnakeSegment(getWorldWidth() / 2, getWorldHeight() / 2, D / 2, Color.GREEN);
        head.setDX(nextHeadDX);
        head.setDY(nextHeadDY);
        addSprite(head);

        for(int i = 0; i < INITIAL_SEGMENTS; i++){
            addSprite(head.addSegmentToTail(0, D, head.getDX(), head.getDY()));
        }

        // head.getTail().setColor(Color.PINK); // debugging

        food = new CircleSprite(0, 0, D / 2, Color.RED);
        setFoodToRandomPosition();
        addSprite(food);

    }

    public void updateSprites(){
        // cascade the DX and DY to the following segments each time the head has traveled one diameter
        if(frameCounter % (D / SPEED) == 0){
            head.setDXCascade(nextHeadDX);
            head.setDYCascade(nextHeadDY);
            if(addSegmentOnNextInterval) {
                addSegmentToTail();
                addSegmentOnNextInterval = false;
            }
        }
        frameCounter++;
        
        if(head.isColliding(food)){
            System.out.println("food get!");
            setFoodToRandomPosition();
            addSegmentOnNextInterval = true;   
        }
        super.updateSprites(); // this advances all sprite positions one frame
    }

    private void addSegmentToTail(){
        addSprite(head.addSegmentToTail(0, 0, 0, 0));
    }

    private void setFoodToRandomPosition(){
        food.setX(getRandomInt(D, getWorldWidth() - D));
        food.setY(getRandomInt(D, getWorldHeight() - D));
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
