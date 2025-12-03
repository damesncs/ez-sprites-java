package breakout;
import java.util.ArrayList;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import core.CircleSprite;
import core.RectangleSprite;
import core.TextSprite;
import core.World;

public class BreakoutWorld extends World implements KeyListener {
    private static final int BALL_SPEED = 4;
    private static final int PADDLE_SPEED = 6;

    private static final int COLS_OF_BOXES = 6;
    private static final int ROWS_OF_BOXES = 4;
    private static final int BOXWIDTH = 100;
    private static final int BOXHEIGHT = 20;
    private int boxHeight = getWorldHeight()/2 -30;
    ArrayList<RectangleSprite> boxes = new ArrayList<>();

    private CircleSprite ball;

    private RectangleSprite bottomPaddle;

     

    // TODO you might want these to store player scores
    // private int leftPlayerScore = 0;
    // private int rightPlayerScore = 0;

    public BreakoutWorld(int width, int height){
        super(width, height);
        
        int ballInitialX = getWorldWidth() / 2;
        int ballInitialY = getWorldHeight() / 2;
        ball = new CircleSprite(ballInitialX, ballInitialY, 15, Color.RED);
        ball.setDX(BALL_SPEED);
        ball.setDY(BALL_SPEED);
        addSprite(ball);

        bottomPaddle = new RectangleSprite(getWorldWidth()/2, getWorldHeight() - 20, 100, 20, Color.BLUE);
        addSprite(bottomPaddle);



        for(int i = 0; i < ROWS_OF_BOXES; i ++){

            for(int e = 0; e < COLS_OF_BOXES; e++){
                RectangleSprite box = new RectangleSprite((getWorldWidth()/COLS_OF_BOXES * e) + (getWorldHeight()/COLS_OF_BOXES)/COLS_OF_BOXES ,boxHeight, BOXWIDTH, BOXHEIGHT, Color.BLACK);
                addSprite(box);
                boxes.add(box);
            }
            boxHeight -= 30;
            
        }

      
    }

    /** This method is called by the `TimerListener` for every frame.
     *  It updates the game state by:
     *  1. checking for collisions and handling them.
     *  2. moving all sprites by applying their dx and dy (this is done by the superclass's method)
     */

    

    public void updateSprites(){
        detectWallCollisions();
        detectPaddleCollisions();
        boxes.forEach(box -> detectBoxCollisions(ball, box));
        

        super.updateSprites(); // this advances all sprite positions one frame
    }

    /** Checks if the ball is colliding with any of the four walls, and if it is,
     *  updates the ball's DX and DY to "bounce"
     */
    private void detectWallCollisions(){
         if(ball.getRightEdge() > getWorldWidth()){
            bounceBallX();
            // TODO instead of bouncing, give a point to left player and update scoreboard
        } else if(ball.getLeftEdge() < 0){
            bounceBallX();
            // TODO instead of bouncing, give a point to right player and update scoreboard
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
       // System.out.println(ball.getRightEdge());
        
        
        
        if(ball.getRightEdge() > bottomPaddle.getLeftEdge() && ball.getLeftEdge() < bottomPaddle.getRightEdge() && ball.getBottomEdge() > getWorldHeight() ){
            
            System.out.println("hit paddle");
            bounceBallY();
        } 

    }

    private void detectBoxCollisions(CircleSprite ball, RectangleSprite box){
            System.out.println("boxHit");
            if(circleRectangleTopEdgeAreColliding( ball,  box) ||
            circleRectangleBottomEdgeAreColliding(ball, box)){
        boxes.remove(box);
        ball.getDY() = -ball.getDY();
    } else if(circleRectangleRightEdgeAreColliding(ball, brick) || 
                circleRectangleLeftEdgeAreColliding(ball, brick)){
        deleteBrick(brick);
        ball.dx = -ball.dx;
    } 
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
        // TODO add handling for left paddle controls here (W and S keys)
    }

    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            bottomPaddle.setDX(0);
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            bottomPaddle.setDX(0);
        }
        // TODO add handling for left paddle controls here (W and S keys)
    }

}
