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
        for(RectangleSprite s : test.getSquares()){
            addSprite(s);
        }
        
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
