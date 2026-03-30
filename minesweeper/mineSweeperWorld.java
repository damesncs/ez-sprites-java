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
    int numOfUserFlags = 0;
    

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
        //showCellValues(); //uncomment to show all cell values at the start
        
    }

    public void assignMines(){









        //Comment
        for(int i = 0; i < NUM_MINES; i ++){
            int rowMine = (int) (Math.random()*numCellsPerAxis);
            int colMine = (int) (Math.random()*numCellsPerAxis);
            if(cells[rowMine][colMine].getValue().equals("M")){
                i--;
            }else{
                cells[rowMine][colMine].changeToMine();
            }
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
    private boolean checkGameWin(){
        int numCorrect = 0;
        for(int i = 0; i < cells.length; i++){
            for(int j = 0; j < cells[i].length; j ++){
                if(cells[i][j].getCellText().equals("F") && cells[i][j].getValue().equals("M")){
                    numCorrect++;
                    System.out.println("correct flag");
                }
            }
        }
        if(numCorrect == NUM_MINES){
            return true;
        }
        return false;
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
        }
    }

    private void handleRightMouseClick(int row, int col){
        CellSprite clickedCell = cells[row][col];

        if(clickedCell.getCellText().equals("F")){
            clickedCell.changeCellText(" ");
            clickedCell.setColor(Color.gray);
            int flagsLeft = NUM_MINES-numOfUserFlags+1;
            System.out.println("Flags left:" + flagsLeft);
            numOfUserFlags--;
        }else if(numOfUserFlags >= NUM_MINES){
            System.out.println("Max number of flags reached");
        }else{
            clickedCell.changeCellText("F");
            clickedCell.setColor(Color.yellow);
            int flagsLeft = NUM_MINES-numOfUserFlags-1;
            System.out.println("Flags left:" + flagsLeft);
            numOfUserFlags++;
        } 
        if(numOfUserFlags == NUM_MINES){
            System.out.println("Checking game win");
            if(checkGameWin()){
                System.out.println("YOU WIN!");
            }
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
