package core;

import java.util.ArrayList;

public class World {

    private ArrayList<Sprite> sprites;

    private int width;
    private int height;

    public World(int width, int height){
        this.width = width;
        this.height = height;
        this.sprites = new ArrayList<Sprite>();
    }

    public void updateSprites(){
        for(Sprite s : sprites){
            s.x += s.dx;
            s.y += s.dy;
        }
    }

    protected void addSprite(Sprite s){
        sprites.add(s);
    }

    protected void removeSprite(Sprite s){
        sprites.remove(s);
    }

    public ArrayList<Sprite> getSprites(){
        return sprites;
    }

    protected int getWorldWidth(){
        return width;
    }

    protected int getWorldHeight(){
        return height;
    }

}
