package pong;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import core.CircleSprite;
import core.RectangleSprite;
import core.World;

public class PongWorld extends World implements KeyListener {

    private static final int BALL_SPEED = 4;
    private static final int PADDLE_SPEED = 6;

    private CircleSprite ball;

    private RectangleSprite leftPaddle;
    private RectangleSprite rightPaddle;

    public PongWorld(int width, int height){
        super(width, height);
        
        ball = new CircleSprite(getWorldWidth() / 2, getWorldHeight() / 2, 15, Color.RED);
        ball.setDX(BALL_SPEED);
        ball.setDY(BALL_SPEED);
        addSprite(ball);

        leftPaddle = new RectangleSprite(0, 0, 20, 100, Color.BLUE);
        addSprite(leftPaddle);

        rightPaddle = new RectangleSprite(getWorldWidth() - 20, 0, 20, 100, Color.BLUE);
        addSprite(rightPaddle);
        
    }

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
        } else if(ball.getLeftEdge() < 0){
            bounceBallX();
        }

        if(ball.getBottomEdge() > getWorldHeight()){
            bounceBallY();
        } else if(ball.getTopEdge() < 0){
            bounceBallY();
        }
    }

    /** Checks the positions of the paddles and the ball to determine if the ball
     * is colliding with either paddle, and updates the ball's DX and DY 
     */
    private void detectPaddleCollisions(){
        // TODO implement
    }

    private void bounceBallX(){
        ball.setDX(-ball.getDX());
    }

    private void bounceBallY(){
        ball.setDY(-ball.getDY());
    }

    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            rightPaddle.setDY(-PADDLE_SPEED);
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            rightPaddle.setDY(PADDLE_SPEED);
        }
        // TODO add handling for left paddle controls here
    }

    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP){
            rightPaddle.setDY(0);
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN){
            rightPaddle.setDY(0);
        }
        // TODO add handling for left paddle controls here
    }

}
