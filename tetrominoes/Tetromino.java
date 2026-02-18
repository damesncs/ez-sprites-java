package tetrominoes;

import java.awt.Color;
import java.awt.Rectangle;

public class Tetromino {
    private int dx;
    private int dy;

    public static final int D = 20;

    // 7 possible configurations
    public static final int[] I = { 0, 0, 1, 0, 2, 0, 3, 0 };
    public static final int[] O = { 0, 0, 1, 0, 0, 1, 1, 1 };
    public static final int[] T = { 0, 0, 1, 0, 2, 0, 1, 1 };
    // TODO J, L, S, Z
    public static final int[] J = { 1, 0, 1, 1, 1, 2, 0, 2 };
    public static final int[] L = { 0, 0, 0, 1, 0, 2, 1, 2 };
    public static final int[] S = { 0, 1, 1, 1, 1, 0, 2, 0 };
    public static final int[] Z = { 0, 0, 1, 0, 1, 1, 2, 1 };

    public static final int[] I2 = { 1, 1, 2, 1, 3, 1, 0, 1};
    public static final int[] I3 = { 0, 0, 0 ,1 , 0, 2, 0, 3};
    public static final int[] T2 = { 0, 0, 0, 1, 0, 2, 1, 1 };
    public static final int[] T3 = { 0, 0, 0, 1, 0, 2, 1, 1 };

    private SquareSprite[] squares;

    public Tetromino(int[] configuration, int x, int y, Color color){
        squares = new SquareSprite[4];

       /*  if(mirrored){
            for(int i = 0; i < configuration.length; i += 2){
                SquareSprite s = new SquareSprite(
                    x - (configuration[i] * D),
                    y - (configuration[i + 1] * D),
                    D,
                    color);
                squares[i / 2] = s;
                }
        }else{*/
            for(int i = 0; i < configuration.length; i += 2){
                SquareSprite s = new SquareSprite(
                    x + (configuration[i] * D),
                    y + (configuration[i + 1] * D),
                    D,
                    color);
                squares[i / 2] = s;
            }
       // }
    }

    public SquareSprite[] getSquares(){
        return squares;
    }

    /* set DX for all square sprites */
    public void setDX(Tetromino block, int dx){
       
       SquareSprite[] squares = block.getSquares();
       for(int i = 0; i < squares.length; i ++){
            squares[0].setDX(dx);
       }
    }
    


    /* set DY for all square sprites */
    public void setDY(Tetromino block, int dy){
       
        SquareSprite[] squares = block.getSquares();
        for(int i = 0; i < squares.length; i ++){
             squares[0].setDY(dy);
        }
     }


    /* rotate the tetromino 90 degrees to the right such that all squares are transposed */
    public void rotateRight(SquareSprite[] squares){
        // TODO implement
        squares[0].getX();
    }

    public void mirror(int[] configuration, int x, int y){
        for(int i = 0; i < squares.length; i ++){
            SquareSprite s = new SquareSprite(
                x - (configuration[i] * D),
                y - (configuration[i + 1] * D),
                D,
                squares[i].getColor());
            squares[i] = s;
        }
    }

}
