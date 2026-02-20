package minesweeper;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import core.CellSprite;
import core.World;

public class mineSweeperWorld extends World implements MouseListener{
   // private static final int D = 20;
   int numCells = 10;
    public mineSweeperWorld(int height, int width){
        super(height, width);
        setUpWorld();
       // int numCells = 100;
    }

    public void setUpWorld(){
        int D = getWorldWidth()/numCells;
        CellSprite[][] cells = new CellSprite[numCells][numCells];
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[i].length; j++){
                CellSprite individualCell = new CellSprite(D*i, D*j, D, Color.MAGENTA);
                addSprite(individualCell);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }
}
