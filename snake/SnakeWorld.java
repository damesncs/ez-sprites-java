package snake;

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
    private static final int SNAKE_HEAD_START_X = (int)(GRID_COLS / 2) * D; // snake starts with head in the middle
    private static final int SNAKE_HEAD_START_Y = (int)(GRID_ROWS / 2) * D;
    private static final Color SNAKE_COLOR = Color.green;
    private static final Color FOOD_COLOR = Color.red;
    private static final int SNAKE_SPEED = D; // move one grid space each frame

    private RectangleSprite foodSprite;
    ArrayList<RectangleSprite> snakeSprites = new ArrayList<>();

    private static final int textX = 1;
    private static final int textY = 1;
    public int numScore = 0;
    private TextSprite score;

    public void updateSprites(){
        moveAndDrawSprites();
        updateSnakeSpritesMovement();
        checkSpriteCollisions();
        
    }

    public SnakeWorld(int height, int width){
        super(height, width);

        foodSprite = new RectangleSprite(getRandomGridSpaceX(), getRandomGridSpaceY(), D, D, FOOD_COLOR);
        addSprite(foodSprite);

        for(int i = 0; i < SNAKE_START_LENGTH; i++){
            // all snake sprite segments are initially moving up (towards the top edge of the canvas)
            RectangleSprite s = new RectangleSprite(SNAKE_HEAD_START_X, SNAKE_HEAD_START_Y + (i * D), D, D, SNAKE_COLOR);
            s.setDX(0);
            s.setDY(-SNAKE_SPEED);
            snakeSprites.add(s);
            addSprite(s);
        }

        score = new TextSprite(GRID_COLS, GRID_ROWS, textX , textY, "0");
        score.setDrawBkgd(true);
        score.setBkgd(Color.GREEN);
        addSprite(score);
    }

    public void moveAndDrawSprites(){
        for(RectangleSprite piece:snakeSprites){
            piece.setX(piece.getX()+piece.getDX());
            piece.setY(piece.getY()+piece.getDY());
        };
    }

    public void updateSnakeSpritesMovement(){
        for(int i = snakeSprites.size() - 1; i > 0; i--){
            int snakeDX = snakeSprites.get(i-1).getDX();
            snakeSprites.get(i).setDX(snakeDX);        
            int snakeDY = snakeSprites.get(i-1).getDY();
            snakeSprites.get(i).setDY(snakeDY);
        }
    }

    public void checkSpriteCollisions(){
        if(foodSprite.getX() == snakeSprites.get(0).getX() && foodSprite.getY() == snakeSprites.get(0).getY() ){

            removeSprite(foodSprite);
            foodSprite = new RectangleSprite(getRandomGridSpaceX(), getRandomGridSpaceY(), D, D, FOOD_COLOR);
            addSprite(foodSprite);

            RectangleSprite lastSnakePiece = snakeSprites.get(snakeSprites.size()-1);
            int newSpriteX = lastSnakePiece.getX() - lastSnakePiece.getDX();
            int newSpriteY = lastSnakePiece.getY() - lastSnakePiece.getDY();
            RectangleSprite s = new RectangleSprite(newSpriteX, newSpriteY, D, D, SNAKE_COLOR);

            s.setDX(snakeSprites.get(snakeSprites.size()-1).getDX());
            s.setDY(snakeSprites.get(snakeSprites.size()-1).getDY());
            snakeSprites.add(s);
            addSprite(s);

            numScore++;
            score.setText(String.valueOf(numScore));
            
        }

        if(snakeSprites.get(0).getX() < 0 || snakeSprites.get(0).getX() >= GRID_COLS *10 ){
            resetWorld();
            moveAndDrawSprites();
        }

        if(snakeSprites.get(0).getY() < 0 || snakeSprites.get(0).getY() >= GRID_ROWS *10 ){
            resetWorld();
            moveAndDrawSprites();
        }
    }

    public void resetWorld(){
        removeSprite(foodSprite);
        removeSprite(score);
        removeSprites(snakeSprites);
        snakeSprites.clear();

        foodSprite = new RectangleSprite(getRandomGridSpaceX(), getRandomGridSpaceY(), D, D, FOOD_COLOR);
        addSprite(foodSprite);

        numScore = 0;
        score = new TextSprite(GRID_COLS, GRID_ROWS, textX , textY, String.valueOf(numScore));
        score.setDrawBkgd(true);
        score.setBkgd(Color.GREEN);
        addSprite(score);
        
        for(int i = 0; i < SNAKE_START_LENGTH; i++){
            // all snake sprite segments are initially moving up (towards the top edge of the canvas)
            RectangleSprite s = new RectangleSprite(SNAKE_HEAD_START_X, SNAKE_HEAD_START_Y + (i * D), D, D, SNAKE_COLOR);
            s.setDX(0);
            s.setDY(-SNAKE_SPEED);
            snakeSprites.add(s);
            addSprite(s);
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
 

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    private int getRandomGridSpaceX(){
        return (int)(Math.random() * GRID_COLS) * D;
    }

    private int getRandomGridSpaceY(){
        return (int)(Math.random() * GRID_ROWS) * D;
    }
}
