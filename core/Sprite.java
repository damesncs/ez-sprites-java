package core;
import java.awt.Graphics;

public class Sprite {
    
    private int x;
    private int y;

    private int dx;
    private int dy;

    public Sprite(){
        this.x = 0;
        this.y = 0;
        this.dx = 0;
        this.dy = 0;
    }

    public Sprite(int x, int y){
        this.x = x;
        this.y = y;
        this.dx = 0;
        this.dy = 0;
    }

    public void draw(Graphics g){
        return;
    }

    public int getRightEdge(){
        return 0;
    }

    public int getLeftEdge(){
        return 0;
    }

    public int getTopEdge(){
        return 0;
    }

    public int getBottomEdge(){
        return 0;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getDX(){
        return dx;
    }

    public int getDY(){
        return dy;
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setDX(int dx){
        this.dx = dx;
    }

    public void setDY(int dy){
        this.dy = dy;
    }

}
