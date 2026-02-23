package snakeArray;

import java.util.ArrayList;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import core.RectangleSprite;
import core.TextSprite;
import core.World;

public class SnakeWorld extends World implements KeyListener {
    private static final int D = 10; // grid square dimension in pixels

    private static final int GRID_ROWS = 400 / D;
    private static final int GRID_COLS = 400 / D;

    private static final int SNAKE_START_LENGTH = 10;
    private static final int SNAKE_HEAD_START_X = (int) (GRID_COLS / 2) * D; // snake starts with head in the middle
    private static final int SNAKE_HEAD_START_Y = (int) (GRID_ROWS / 2) * D;
    private static final Color SNAKE_COLOR = Color.green;
    private static final Color FOOD_COLOR = Color.red;
    private static final int SNAKE_SPEED = D; // move one grid space each frame

    private RectangleSprite foodSprite;
    RectangleSprite[] snakeSprites;

    private static final int textX = 1;
    private static final int textY = 1;
    public int numScore = 0;
    private TextSprite score;

    public void updateSprites(){
        super.updateSprites();
        updateSnakeSpritesMovement();
        checkSpriteCollisions();
    }

    public SnakeWorld(int height, int width){
        super(height, width);
        setupWorld();
    }

    public void updateSnakeSpritesMovement(){
        for(int i = snakeSprites.length - 1; i > 0; i--){
            int dx = snakeSprites[i - 1].getDX();
            snakeSprites[i].setDX(dx);
            int dy = snakeSprites[i - 1].getDY();
            snakeSprites[i].setDY(dy);
        }
    }

    public void checkSpriteCollisions(){
        // TODO if snake head is colliding with the food:
        //      1. move the food somewhere else randomly (use getRandomGridSpaceX() etc.)
        //      2. add a segment to the snake
        //      3. update the score
        
        if(snakeSprites[0].getX() == foodSprite.getX() &&
            snakeSprites[0].getY() == foodSprite.getY()){
                addSegmentToTail();
                numScore++;
                score.setText(Integer.toString(numScore));
                foodSprite.setX(getRandomGridSpaceX());
                foodSprite.setY(getRandomGridSpaceY());
        }

        // TODO if the snake is going off of the canvas, call resetWorld()
    }

    public void resetWorld(){
        removeSprite(foodSprite);
        removeSprite(score);
        // TODO reset snake to original length and position

        setupWorld();
    }

    public void setupWorld(){
        foodSprite = new RectangleSprite(getRandomGridSpaceX(), getRandomGridSpaceY(), D, D, FOOD_COLOR);
        addSprite(foodSprite);

        numScore = 0;
        score = new TextSprite(GRID_COLS, GRID_ROWS, textX, textY, String.valueOf(numScore));
        score.setDrawBkgd(true);
        score.setBkgdColor(Color.GREEN);
        addSprite(score);
        
       // TODO create snake segments (n = SNAKE_START_LENGTH) and add them to the snakeSprites array
    
       snakeSprites = new RectangleSprite[SNAKE_START_LENGTH];
       for(int i = 0; i < snakeSprites.length; i++){
            int segmentX = SNAKE_HEAD_START_X;
            int segmentY = SNAKE_HEAD_START_Y + (i * D);
            RectangleSprite newSegment = new RectangleSprite(segmentX, segmentY, D, D, SNAKE_COLOR);
            snakeSprites[i] = newSegment;
            addSprite(newSegment);
            newSegment.setDY(-SNAKE_SPEED);
       }


    }

    // 1. initialize a new array (newSegments) that's one bigger than the old one
    // 2. copy all of the references to the sprites to the new array (for loop)
    // 3. set snakeSprites = newArray
    // 4. create a new segment and assign snakeSprites[snakeSprites.length - 1]

    private void addSegmentToTail(){
        RectangleSprite[] newSnake = new RectangleSprite[snakeSprites.length + 1];
        for(int i = 0; i < snakeSprites.length; i++){
            newSnake[i] = snakeSprites[i];
        }
        // int i = 0;
        // for(RectangleSprite s : snakeSprites){
        //     newSnake[i] = s;
        //     i++;
        // }
        snakeSprites = newSnake;
        RectangleSprite tail = snakeSprites[snakeSprites.length - 2];
        RectangleSprite newSegment = new RectangleSprite(tail.getX(), tail.getY(), D, D, Color.orange);
        addSprite(newSegment);
        snakeSprites[snakeSprites.length - 1] = newSegment;
    }

    public void keyPressed(KeyEvent e){
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_RIGHT){
            onKeyEventArrowRight();
        } else if (keyCode == KeyEvent.VK_LEFT){
            onKeyEventArrowLeft();
        } else if(keyCode == KeyEvent.VK_UP){
            onKeyEventArrowUp();
        } else if(keyCode == KeyEvent.VK_DOWN){
            onKeyEventArrowDown();
        }
    }
    
    // on arrow key input, change the direction of movement of the first snake segment sprite (the "head")
    public void onKeyEventArrowLeft(){
        snakeSprites[0].setDX(-SNAKE_SPEED);
        snakeSprites[0].setDY(0);
    }
    
    public void onKeyEventArrowRight(){
        snakeSprites[0].setDX(SNAKE_SPEED);
        snakeSprites[0].setDY(0);
    }
    
    public void onKeyEventArrowUp(){
        snakeSprites[0].setDX(0);
        snakeSprites[0].setDY(-SNAKE_SPEED);
    }
    
    public void onKeyEventArrowDown(){
        snakeSprites[0].setDX(0);
        snakeSprites[0].setDY(SNAKE_SPEED);
    }
    
    public void keyTyped(KeyEvent e) {
       // do nothing
    }

    public void keyReleased(KeyEvent e) {
        // do nothing
    }

    private int getRandomGridSpaceX(){
        return (int)(Math.random() * GRID_COLS) * D;
    }

    private int getRandomGridSpaceY(){
        return (int)(Math.random() * GRID_ROWS) * D;
    }
}
