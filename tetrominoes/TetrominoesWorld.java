package tetrominoes;

import core.RectangleSprite;
import core.World;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;

public class TetrominoesWorld extends World implements KeyListener {

    public TetrominoesWorld(int width, int height) {
        super(width, height);
        
        Tetromino test = new Tetromino(Tetromino.I, 100, 100, Color.red);
        Tetromino testo = new Tetromino(Tetromino.O, 200, 100, Color.red);
        Tetromino testt = new Tetromino(Tetromino.T, 300, 100, Color.red);
        Tetromino testJ = new Tetromino(Tetromino.J, 400, 100, Color.red);
        Tetromino testL = new Tetromino(Tetromino.L, 100, 200, Color.red);
        Tetromino testS = new Tetromino(Tetromino.S, 200, 200, Color.red);
        Tetromino testZ = new Tetromino(Tetromino.Z, 300, 200, Color.red);

        Tetromino testRotate = new Tetromino(Tetromino.I2, 400, 200, Color.red);

        for(RectangleSprite s : test.getSquares()){
            addSprite(s);
        }

        for(RectangleSprite s : testo.getSquares()){
            addSprite(s);
        }

        for(RectangleSprite s : testt.getSquares()){
            addSprite(s);
        }

        for(RectangleSprite s : testJ.getSquares()){
            addSprite(s);
        }

        for(RectangleSprite s : testL.getSquares()){
            addSprite(s);
        }

        for(RectangleSprite s : testS.getSquares()){
            addSprite(s);
        }

        for(RectangleSprite s : testZ.getSquares()){
            addSprite(s);
        }
        for(RectangleSprite s : testRotate.getSquares()){
            addSprite(s);
        }

        // TODO test all configurations

        // TODO setDY() to ensure the squares move together
        
        // TODO check collisions
        
        // TODO keyboard input to rotate tetrominoes

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
    
}
