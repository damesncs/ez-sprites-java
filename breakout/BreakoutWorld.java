package breakout;
import java.util.ArrayList;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import core.CircleSprite;
import core.RectangleSprite;
import core.World;

public class BreakoutWorld extends World implements KeyListener {
    private static final int BALL_SPEED = 4;
    private static final int PADDLE_SPEED = 6;

    private static final int COLS_OF_BRICKS = 6;
    private static final int ROWS_OF_BRICKS = 4;
    private static final int BRICK_WIDTH = 100;
    private static final int BRICK_HEIGHT = 20;
    private static final int COL_MARGIN = 15;
    
    ArrayList<RectangleSprite> boxes = new ArrayList<>();

    private CircleSprite ball;

    private RectangleSprite bottomPaddle;

    public BreakoutWorld(int width, int height){
        super(width, height);

        int ballInitialX = getWorldWidth() / 2;
        int ballInitialY = getWorldHeight() / 2;
        ball = new CircleSprite(ballInitialX, ballInitialY + 10, 15, Color.RED);
        ball.setDX(BALL_SPEED );
        ball.setDY(BALL_SPEED);
        addSprite(ball);

        bottomPaddle = new RectangleSprite(getWorldWidth()/2, getWorldHeight() - 20, 100, 20, Color.BLUE);
        addSprite(bottomPaddle);

        // TODO create bricks in a grid here:

        // REQUIREMENTS:
        // 1. Your code must use the constants above for the number of rows and columns of bricks.
        // 2. The bricks must all have the same dimensions (use the constants above).
        // 3. The columns of bricks should be separated by a constant margin (COL_MARGIN)
        // 4. The rows of bricks should be separated by a margin of one brick-height (BRICK_HEIGHT)

    }

    /** This method is called by the `TimerListener` for every frame.
     *  It updates the game state by:
     *  1. checking for collisions and handling them.
     *  2. moving all sprites by applying their dx and dy (this is done by the superclass's method)
     */
    public void updateSprites(){
        ArrayList<RectangleSprite> toRemove = new ArrayList<RectangleSprite>();

        detectWallCollisions();
        detectPaddleCollisions();

        for(RectangleSprite brick:boxes){
           boolean shouldRemove = detectBoxCollisions(ball, brick);
           if(shouldRemove){
            toRemove.add(brick);
           }
        }
        boxes.removeAll(toRemove);
        removeSprites(toRemove);
        super.updateSprites(); // this advances all sprite positions one frame
    }

    private void detectWallCollisions(){
         if(ball.getRightEdge() > getWorldWidth()){
            bounceBallX();
        } else if(ball.getLeftEdge() < 0){
            bounceBallX();
        }

        if(ball.getBottomEdge()-5 > getWorldHeight()){
            System.out.println("gameOver");
            ball.setX(getWorldWidth() / 2);
            ball.setY(getWorldHeight() / 2);
        } else if(ball.getTopEdge() < 0){
            bounceBallY();
        }
    }

    /** Checks the positions of the paddles and the ball to determine if the ball
     * is colliding with either paddle, and updates the ball's DX accordingly
     * by calling `bounceBallX`
     */
    private void detectPaddleCollisions(){
        if(ball.getRightEdge() > bottomPaddle.getLeftEdge() 
        && ball.getLeftEdge() < bottomPaddle.getRightEdge() 
        && ball.getBottomEdge() > getWorldHeight() - bottomPaddle.getHeight()){
            bounceBallY();
        } 
    }

    private boolean detectBoxCollisions(CircleSprite ball, RectangleSprite box){
        if(circleRectangleTopEdgeAreColliding( ball,  box) ||
            circleRectangleBottomEdgeAreColliding(ball, box)){
            ball.setDY(-ball.getDY());
            return true;

        } else if(circleRectangleRightEdgeAreColliding(ball, box) || 
            circleRectangleLeftEdgeAreColliding(ball, box)){
            ball.setDX(-ball.getDX());
            return true;

    } 

    return false;

    }

    private boolean checkDistanceToPointLessThanRadius(CircleSprite c, int testX, int testY){
        int distX = c.getX() - testX;
        int distY = c.getY() - testY;
        final int distance = (int) Math.sqrt( (distX*distX) + (distY*distY) );
        return distance <= c.getRadius();
    }

    private boolean circleRectangleTopEdgeAreColliding(CircleSprite c, RectangleSprite r){
        if (c.getY() <= r.getTopEdge() && c.getRightEdge() >= r.getLeftEdge() && c.getLeftEdge() <= r.getRightEdge()){
            return checkDistanceToPointLessThanRadius(c, c.getX(), r.getTopEdge());
        }
        return false;
    }
    
    private boolean circleRectangleBottomEdgeAreColliding(CircleSprite c, RectangleSprite r){
        if (c.getY() >= r.getBottomEdge() && c.getRightEdge() >= r.getLeftEdge() && c.getLeftEdge() <= r.getRightEdge()){
            return checkDistanceToPointLessThanRadius(c, c.getX(), r.getBottomEdge());
        }
        return false;
    }
    
    private boolean circleRectangleRightEdgeAreColliding(CircleSprite c, RectangleSprite r){
        if (c.getX() >= r.getRightEdge() && c.getBottomEdge() >= r.getTopEdge() && c.getTopEdge() <= r.getBottomEdge()){
            return checkDistanceToPointLessThanRadius(c, r.getRightEdge(), c.getY());
        }
        return false;
    }
    
    private boolean circleRectangleLeftEdgeAreColliding(CircleSprite c, RectangleSprite r){
        if (c.getX() <= r.getLeftEdge() && c.getBottomEdge() >= r.getTopEdge() && c.getTopEdge() <= r.getBottomEdge()){
            return checkDistanceToPointLessThanRadius(c, r.getLeftEdge(), c.getY());
        }
        return false;
    }

    /** Inverts the movement of the ball on the x axis ("flip dx")*/
    private void bounceBallX(){
        ball.setDX(-ball.getDX());
    }

    /** Inverts the movement of the ball on the y axis ("flip dy")*/
    private void bounceBallY(){
        ball.setDY(-ball.getDY());
    }

    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            bottomPaddle.setDX(-PADDLE_SPEED);
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            bottomPaddle.setDX(PADDLE_SPEED);
        }
        
    }

    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            bottomPaddle.setDX(0);
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            bottomPaddle.setDX(0);
        }
        
    }

}
