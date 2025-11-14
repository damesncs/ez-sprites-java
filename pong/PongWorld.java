package pong;

import java.awt.Color;

import core.CircleSprite;
import core.World;

public class PongWorld extends World {

    private CircleSprite ball;

    public PongWorld(int width, int height){
        super(width, height);
        
        ball = new CircleSprite(getWorldWidth() / 2, getWorldHeight() / 2, 20, Color.RED);
        ball.dx = 2;
        ball.dy = 3;

        addSprite(ball);
    }

    public void updateSprites(){
        // do game logic here
        detectWallCollisions();
        
        super.updateSprites(); // this advances all sprite positions one frame
    }

    private void detectWallCollisions(){
        if(ball.x + ball.radius > getWorldWidth()){
            ball.dx = -ball.dx;
        } else if(ball.x - ball.radius < 0){
            ball.dx = -ball.dx;
        }
        
        if(ball.y + ball.radius > getWorldHeight()){
            ball.dy = -ball.dy;
        } else if(ball.y - ball.radius < 0){
            ball.dy = -ball.dy;
        }
    }

}
