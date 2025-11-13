import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class EZCanvas extends JPanel {
    
    private int width;
    private int height;

    public EZCanvas(int width, int height) {
        this.width = width;
        this.height = height;
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);       

        // do drawing here with g
        
    }  
}
