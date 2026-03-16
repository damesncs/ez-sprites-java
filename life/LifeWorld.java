package life;

import core.World;
import core.RectangleSprite;

import java.awt.Color;
import java.util.ArrayList;

public class LifeWorld extends World {

    int D;
    int ROWS;
    int COLS;

    int RIGHT_EDGE;
    int BOTTOM_EDGE;

    boolean[][] cells;

    ArrayList<RectangleSprite> gridSprites;

    public LifeWorld(int width, int height, int d) {
        super(width, height);
        D = d;
        ROWS = height / D;
        COLS = width / D;
        RIGHT_EDGE = COLS - 1;
        BOTTOM_EDGE = ROWS - 1;

        gridSprites = new ArrayList<RectangleSprite>(ROWS * COLS);

        cells = new boolean[ROWS][COLS];

        // initial setup - the "R-pentomino"
        cells[49][50] = true;
        cells[50][50] = true;
        cells[50][51] = true;
        cells[51][50] = true;
        cells[51][49] = true;

        initSprites();

    }

    public void updateSprites(){
        // super.updateSprites(); // not using Sprite's dx and dy,fields so this is not necessary
        getNextGeneration();
        setSpritesFromCells();
    }

    private void getNextGeneration(){
        boolean[][] newGrid = new boolean[ROWS][COLS];
      
          for(int row = 0; row < ROWS; row++){
              for(int col = 0; col < COLS; col++){
                // for each cell
                // count neighbors
                int neighbors = 0;
                //  NW N NE
                //  W  C E
                //  SW S SE
                
                // NW
                if(row > 0 && col > 0 && cells[row - 1][col - 1]) neighbors++;
                // N
                if(row > 0 && cells[row - 1][col]) neighbors++;
                // NE
                if(row > 0 && col < RIGHT_EDGE && cells[row - 1][col + 1]) neighbors++;
                // E
                if(col < RIGHT_EDGE && cells[row][col + 1]) neighbors++;
                // SE
                if(row < BOTTOM_EDGE && col < RIGHT_EDGE && cells[row + 1][col + 1]) neighbors++;
                // S
                if(row < BOTTOM_EDGE && cells[row + 1][col]) neighbors++;
                // SW
                if(row < BOTTOM_EDGE && col > 0 && cells[row + 1][col - 1]) neighbors++;
                // W
                if(col > 0 && cells[row][col - 1]) neighbors++;
                
                //Any live cell with fewer than two live neighbours dies, as if by underpopulation.
                //Any live cell with two or three live neighbours lives on to the next generation.
                //Any live cell with more than three live neighbours dies, as if by overpopulation.
                //Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

                if(neighbors == 2 && cells[row][col]) newGrid[row][col] = true;
                else if(neighbors == 3) newGrid[row][col] = true;
              }
          }
          // replace the old grid
          cells = newGrid;
    }

    private void setSpritesFromCells(){
        for(int r = 0; r < cells.length; r++){
            for(int c = 0; c < cells[r].length; c++){
                Color color = Color.white;
                if(cells[r][c]) color = Color.black;
                RectangleSprite rs = gridSprites.get(r * cells[r].length + c);
                rs.setColor(color);
            }
        }
    }

    private void initSprites(){
        for(int r = 0; r < cells.length; r++){
            for(int c = 0; c < cells[r].length; c++){
                RectangleSprite s = new RectangleSprite(r * D, c * D, D, D, Color.WHITE);
                gridSprites.add(s);
                addSprite(s);
            }
        }
    }
    
}
