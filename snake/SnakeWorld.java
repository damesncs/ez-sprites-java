package snake;

import java.util.ArrayList;
import java.util.Random;
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

    public SnakeWorld(int height, int width){
        super(width, height);

        RectangleSprite foodSprite = new RectangleSprite(getRandomGridSpaceX(), getRandomGridSpaceY(), D, D, FOOD_COLOR);
        addSprite(foodSprite);

        for(int i = 0; i < SNAKE_START_LENGTH; i++){
            // all snake sprite segments are initially moving up (towards the top edge of the canvas)
            RectangleSprite s = new RectangleSprite(SNAKE_HEAD_START_X, SNAKE_HEAD_START_Y + (i * D), D, D, SNAKE_COLOR);
            snakeSprites.add(s);
            s.setDX();
        }
     //   addEventListener("keydown", onKeyEvent);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
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
