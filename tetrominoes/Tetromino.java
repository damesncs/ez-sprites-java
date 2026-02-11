package tetrominoes;

import java.awt.Color;

public class Tetromino {
    
    public static final int D = 20;

    public static final int[] I = { 0, 0, 1, 0, 2, 0, 3, 0 };
    public static final int[] O = { 0, 0, 1, 0, 0, 1, 1, 1 };
    public static final int[] T = { 0, 0, 1, 0, 2, 0, 1, 1 };
    // TODO J, L, S, Z

    private SquareSprite[] squares;

    public Tetromino(int[] configuration, int x, int y, Color color){
        squares = new SquareSprite[4];

        for(int i = 0; i < configuration.length; i += 2){
            SquareSprite s = new SquareSprite(
                x + (configuration[i] * D),
                y + (configuration[i + 1] * D),
                D,
                color);
            squares[i / 2] = s;
        }

    }

    public SquareSprite[] getSquares(){
        return squares;
    }

}
