package minesweeper;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import core.CellSprite;
import core.World;
//import java.util.Random;

public class mineSweeperWorld extends World implements MouseListener{
    private static final int NUM_MINES = 40;
    int numCellsPerAxis = 15;
    int D = getWorldWidth()/numCellsPerAxis;
    int headerPixels;
    boolean firstMouseClick = true;

   CellSprite[][] cells = new CellSprite[numCellsPerAxis][numCellsPerAxis];
    public mineSweeperWorld(int height, int width, int offsetY){
        super(height, width);
        setUpWorld();
        headerPixels = offsetY;
       
    }

    public void setUpWorld(){
        
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[i].length; j++){
                CellSprite individualCell = new CellSprite(D*i, D*j, D, Color.GRAY, "");
                cells[i][j] = individualCell;
                addSprite(individualCell);
            }
        }
        assignMines();
        
    }

    public void assignMines(){
        //Random randomCell;
       

        for(int i = 0; i <= NUM_MINES; i ++){
            int rowMine = (int) (Math.random()*numCellsPerAxis);
            int colMine = (int) (Math.random()*numCellsPerAxis);
           System.out.println(cells[rowMine][colMine]); 
           cells[rowMine][colMine].changeToMine();
           System.out.println(rowMine);
           System.out.println(colMine);
        }

        showMineValues();
    }
    public void showMineValues(){
        for(int i = 0; i < cells.length; i ++){
            for(int j = 0; j < cells[i].length; j++){
                cells[i][j].showValue();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
        if(firstMouseClick){
            firstMouseClick = false;
        }
        double xMouse = e.getX();
        double yMouse = e.getY();
        int xIndex = (int)xMouse/D;
        int yIndex = (int)yMouse/D;
        System.out.println(xIndex);
        System.out.println(yIndex);

        handleCellClick(xIndex, yIndex);

    }

    private void handleCellClick(int row, int col){
        CellSprite clickedCell = cells[row][col];

        if(clickedCell.getValue() == "M"){
            cells[row][col].setColor(Color.RED);
            System.out.println("Game over");
        }else{
            cells[row][col].setColor(Color.GREEN);
            System.out.println("empty cell");
            uncoverCells(row, col);
        }
    }

    private void uncoverCells(int row, int col){
        if()
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
       // throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
      //  throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
      //  throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
       // throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }
}
