package pong;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import core.CircleSprite;
import core.RectangleSprite;
import core.TextSprite;
import core.World;

public class PongWorld extends World implements KeyListener {

    private static final int BALL_SPEED = 4;
    private static final int PADDLE_SPEED = 6;

    private CircleSprite ball;

    private RectangleSprite leftPaddle;
    private RectangleSprite rightPaddle;

    private TextSprite leftScore;
    private TextSprite rightScore;

    // TODO you might want these to store player scores
    // private int leftPlayerScore = 0;
    // private int rightPlayerScore = 0;

    public PongWorld(int width, int height){
        super(width, height);
        
        int ballInitialX = getWorldWidth() / 2;
        int ballInitialY = getWorldHeight() / 2;
        ball = new CircleSprite(ballInitialX, ballInitialY, 15, Color.RED);
        ball.setDX(BALL_SPEED);
        ball.setDY(BALL_SPEED);
        addSprite(ball);

        leftPaddle = new RectangleSprite(0, 0, 20, 100, Color.BLUE);
        addSprite(leftPaddle);

        int rightPaddleInitialX = getWorldWidth() - 20;
        rightPaddle = new RectangleSprite(rightPaddleInitialX, 0, 20, 100, Color.BLUE);
        addSprite(rightPaddle);
        
        int scoresY = getWorldHeight() / 4;
        int scoreXInset = getWorldWidth() / 4;

        leftScore = new TextSprite(scoreXInset, scoresY, 100, 30, "test");
        addSprite(leftScore);

        rightScore = new TextSprite(getWorldWidth() - scoreXInset, scoresY, 100, 30, "test");
        addSprite(rightScore);
    }

    /** This method is called by the `TimerListener` for every frame.
     *  It updates the game state by:
     *  1. checking for collisions and handling them.
     *  2. moving all sprites by applying their dx and dy (this is done by the superclass's method)
     */
    public void updateSprites(){
        detectWallCollisions();
        detectPaddleCollisions();

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

        if(ball.getBottomEdge() > getWorldHeight()){
            bounceBallY();
        } else if(ball.getTopEdge() < 0){
            bounceBallY();
        }
    }

    /** Checks the positions of the paddles and the ball to determine if the ball
     * is colliding with either paddle, and updates the ball's DX accordingly
     * by calling `bounceBallX`
     */
    private void detectPaddleCollisions(){
        // TODO implement this method
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

    /** called when any key is pressed */
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            rightPaddle.setDY(-PADDLE_SPEED);
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            rightPaddle.setDY(PADDLE_SPEED);
        }
        // TODO add handling for left paddle controls here (W and S keys)
    }

    /** called when any key is released after being pressed */
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            rightPaddle.setDY(0);
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            rightPaddle.setDY(0);
        }
        // TODO add handling for left paddle controls here (W and S keys)
    }

}
