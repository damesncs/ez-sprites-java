package core;

public class World {
    
    protected Canvas canvas;

    public World(Canvas canvas){
        this.canvas = canvas;
    }

    public void update(){
        canvas.repaint();
        return;
    }

}
