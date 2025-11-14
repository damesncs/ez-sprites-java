package core;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

public class Canvas extends JPanel {
    
    private int width;
    private int height;

    private ArrayList<Sprite> sprites;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        this.sprites = new ArrayList<>();
        setMinimumSize(new Dimension(width, height));
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public void setSprites(ArrayList<Sprite> list){
        sprites = list;
    }

    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        for(Sprite s : sprites){
            s.draw(g);
        }
    }
}
