package minesweeper;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.SwingUtilities;

import core.CellSprite;
import core.World;

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
        assignCellNumber();
       // showCellValues(); //uncomment to show all cell values at the start
        
    }

    public void assignMines(){

        for(int i = 0; i <= NUM_MINES; i ++){
            int rowMine = (int) (Math.random()*numCellsPerAxis);
            int colMine = (int) (Math.random()*numCellsPerAxis);
           System.out.println(cells[rowMine][colMine]); 
           cells[rowMine][colMine].changeToMine();
           System.out.println(rowMine);
           System.out.println(colMine);
        }

    }
    public void showCellValues(){
        for(int i = 0; i < cells.length; i ++){
            for(int j = 0; j < cells[i].length; j++){
                cells[i][j].showValue();
                cells[i][j].showCellNumber();
            }
        }
    }

    public void assignCellNumber(){
        
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[i].length; j ++){
                if(cells[i][j].isMine()){
                    cells[i][j].changeCellNumber(8);//all mines are changed to zero
                    continue;
                }

                int mineCounter = 0;
                for(int r = -1; r <= 1; r++){
                    for(int c = -1; c <= 1; c++){
                        int adjacentRow = r + i;
                        int adjacentCol = c + j;

                        if(adjacentRow >= 0 && adjacentRow < cells.length && adjacentCol >= 0 && adjacentCol < cells[i].length){
                            if(cells[adjacentRow][adjacentCol].getValue() == "M"){
                                mineCounter++;
                            }
                        }
                    }
                }
                cells[i][j].changeCellNumber(mineCounter);
            }
        }
    }

    public void unveilSurroundingCells(int row, int col){
        for(int r = -1; r <= 1; r++){
            for(int c = -1; c <= 1; c++){
                int adjacentRow = r + row;
                int adjacentCol = c + col;

                if(adjacentRow >= 0 && adjacentRow < cells.length && adjacentCol >= 0 && adjacentCol < cells[row].length ){
                    handleLeftCellClick(adjacentRow, adjacentCol);
                    }
                }
            }
    }
    

    public void unveilZeroCells(int row, int col){
                for(int r = -1; r <= 1; r++){
                    for(int c = -1; c <= 1; c++){
                        int adjacentRow = r + row;
                        int adjacentCol = c + col;

                        if(adjacentRow >= 0 && adjacentRow < cells.length && adjacentCol >= 0 && adjacentCol < cells[row].length ){
                            if(cells[adjacentRow][adjacentCol].getCellNumber() == 0 && cells[adjacentRow][adjacentCol].getValue() != "Mine"){
                                System.out.println(cells[adjacentRow][adjacentCol].getCellNumber());
                                cells[adjacentRow][adjacentCol].changeCellNumber(9);// changes the number to 9 in order to prevent the recursive method from visting the same cell twice
                               unveilSurroundingCells(adjacentRow, adjacentCol);
                               handleLeftCellClick(adjacentRow, adjacentCol);
                               unveilZeroCells(adjacentRow, adjacentCol);
                            
                            }
                        }
                    }
                }
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        double xMouse = e.getX();
        double yMouse = e.getY();
        int xIndex = (int)xMouse/D;
        int yIndex = (int)yMouse/D;
    
        if(SwingUtilities.isRightMouseButton(e)){
            handleRightMouseClick(xIndex, yIndex);
        }else{
            handleLeftCellClick(xIndex, yIndex);
            unveilZeroCells(xIndex, yIndex);
        }
        
  
    }

    private void handleLeftCellClick(int row, int col){
        CellSprite clickedCell = cells[row][col];
        clickedCell.showValue();
        clickedCell.showCellNumber();
        if(clickedCell.isMine()){
            cells[row][col].setColor(Color.RED);
            System.out.println("Game over");
            showCellValues();
        }else{
            cells[row][col].setColor(Color.GREEN);
            System.out.println("empty cell");
        }
    }

    private void handleRightMouseClick(int row, int col){
        CellSprite clickedCell = cells[row][col];
        if(clickedCell.getCellText().equals("F")){
            clickedCell.changeCellText(" ");
        }else{
            clickedCell.changeCellText("F");
        }
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
