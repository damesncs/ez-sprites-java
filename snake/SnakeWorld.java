package snake;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import breakout.BreakoutWorld;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import core.CircleSprite;
import core.RectangleSprite;
import core.World;





public class SnakeWorld extends World implements KeyListener {
    private static final int D = 10; // grid square dimension in pixels

    private static final int GRID_ROWS = 400 / D;
    private static final int GRID_COLS = 400 / D;

    private static final String EVENT_KEY_PRESSED = "keydown";
    private static final String EVENT_KEY_RELEASED= "keyup";

    private static final int SNAKE_START_LENGTH = 10;
    private static final int SNAKE_HEAD_START_X = (int)(GRID_COLS / 2) * D; // snake starts with head in the middle
    private static final int SNAKE_HEAD_START_Y = (int)(GRID_ROWS / 2) * D;
    private static final Color SNAKE_COLOR = Color.green;
    private static final Color FOOD_COLOR = Color.red;

    private static final int SNAKE_SPEED = D; // move one grid space each frame

    ArrayList<RectangleSprite> snakeSprites = new ArrayList<>();

    public void updateSprites(){
        moveAndDrawSprites();
        updateSnakeSpritesMovement();
        
    }

    public SnakeWorld(int height, int width){
        super(width, height);

        RectangleSprite foodSprite = new RectangleSprite(getRandomGridSpaceX(), getRandomGridSpaceY(), D, D, FOOD_COLOR);
        addSprite(foodSprite);

        for(int i = 0; i < SNAKE_START_LENGTH; i++){
            // all snake sprite segments are initially moving up (towards the top edge of the canvas)
            RectangleSprite s = new RectangleSprite(SNAKE_HEAD_START_X, SNAKE_HEAD_START_Y + (i * D), D, D, SNAKE_COLOR);
            s.setDX(0);
            s.setDY(-SNAKE_SPEED);
            snakeSprites.add(s);
            addSprite(s);
        }
        
       // addEventListener("keydown", onKeyEvent);
    }

    public void moveAndDrawSprites(){
        for(RectangleSprite piece:snakeSprites){
            piece.setX(piece.getX()+piece.getDX());
            piece.setY(piece.getY()+piece.getDY());
        };
    }

    public void updateSnakeSpritesMovement(){
        for(int i = snakeSprites.size() - 1; i > 0; i--){
            snakeSprites.get(i) = snakeSprites.get[i-1].dx;
            snakeSprites[i].dy = snakeSprites[i-1].dy;
        }
    }

    function updateSnakeSpritesMovement(){
        for(let i = snakeSprites.length - 1; i > 0; i--){
            snakeSprites[i].dx = snakeSprites[i-1].dx;
            snakeSprites[i].dy = snakeSprites[i-1].dy;
        }
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
            snakeSprites.get(0).setDX(-SNAKE_SPEED);
            snakeSprites.get(0).setDY(0);
    }
    
    public void onKeyEventArrowRight(){
            snakeSprites.get(0).setDX(SNAKE_SPEED);
            snakeSprites.get(0).setDY(0);
        
    }
    
    public void onKeyEventArrowUp(){
        snakeSprites.get(0).setDX(0);
        snakeSprites.get(0).setDY(-SNAKE_SPEED);
    }
    
    public void onKeyEventArrowDown(){
        snakeSprites.get(0).setDX(0);
        snakeSprites.get(0).setDY(SNAKE_SPEED);
    }
    
    
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
/* 
    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
    }
*/
    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    private int getRandomGridSpaceX(){
        Random random = new Random();
        return random.nextInt(Snake.CANVAS_WIDTH);
    }

    private int getRandomGridSpaceY(){
        Random random = new Random();
        return random.nextInt(Snake.CANVAS_HEIGHT);
    }
}
