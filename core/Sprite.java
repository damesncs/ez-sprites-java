package core;
import java.awt.Graphics;

public class Sprite {
    
    public int x;
    public int y;

    public int dx;
    public int dy;

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

}
