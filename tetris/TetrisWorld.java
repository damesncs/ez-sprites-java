package tetris;

import java.util.ArrayList;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import core.RectangleSprite;
import core.TextSprite;
import core.World;



public class TetrisWorld extends World implements KeyListener{

    public TetrisWorld(int height, int width){
        super(height, width);
        setUpWorld();
    }

    public void setUpWorld(){
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
    }
}
