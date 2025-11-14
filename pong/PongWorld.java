package pong;

import java.awt.Color;

import core.Canvas;
import core.CircleSprite;
import core.World;

public class PongWorld extends World {

    private CircleSprite ball;

    public PongWorld(Canvas canvas){
        super(canvas);
        
        ball = new CircleSprite(canvas.getWidth() / 2, canvas.getHeight() / 2, 20, Color.RED);
        ball.dx = 1;
        ball.dy = 1;

        canvas.addSprite(ball);
    }

    public void update(){
        // do game logic here



        super.update();

    }

}
