package snake;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import core.CircleSprite;
import core.RectangleSprite;
import core.TextSprite;
import core.World;

public class SnakeWorld extends World implements KeyListener {

    
    // linked list


    public SnakeWorld(int width, int height){
        super(width, height);
        
       
    }

    /** This method is called by the `TimerListener` for every frame.
     *  It updates the game state by:
     *  1. checking for collisions and handling them.
     *  2. moving all sprites by applying their dx and dy (this is done by the superclass's method)
     */
    public void updateSprites(){
      

        super.updateSprites(); // this advances all sprite positions one frame
    }
    
    public void keyTyped(KeyEvent e) {
        // do nothing
    }

    /** called when any key is pressed */
    public void keyPressed(KeyEvent e) {
       
    }

    /** called when any key is released after being pressed */
    public void keyReleased(KeyEvent e) {
      
    }

}

