package core;
import java.awt.Color;

public class CellSprite extends RectangleSprite{
    private static int d;
    public CellSprite(int x, int y, int d, Color color){
        super(x,y, d, d, color);
    }
}
