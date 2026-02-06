package tetris;

import javax.swing.border.Border;

import core.Canvas;
import core.TimerListener;
import core.World;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.Line2D;

public class Tetris {
    public static final int CANVAS_WIDTH = 400;
    public static final int CANVAS_HEIGHT = 400;

    private static final int D = 20;
    public static final int GRID_ROWS = 200/D;
    public static final int GRID_COLS = 200/D;

    private static final int FRAME_DELAY_MS = 100;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
                paintComponent(Graphics g);
            }
        });
    }
    
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Tetris");
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        
        frame.add(canvas);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        frame.setVisible(true);

        JPanel panel = new JPanel(new BorderLayout());
        Border border = BorderFactory.createLineBorder(Color.BLACK, 10);
        panel.setBorder(border);
        panel.add(canvas, BorderLayout.CENTER);
        frame.add(panel);
        frame.setVisible(true);
 
        panel = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); 
                
           
            g.setColor(Color.RED);
            
            g.drawLine(10, 20, 100, 100);
                
            
            g.setColor(Color.BLUE);
                
            g.drawLine(50, 150, 250, 50);
            }

            
        };
        
        frame.add(panel);
      //  frame.pack();
        //    frame.setLocationRelativeTo(null); // Center the window
            frame.setVisible(true);

        TetrisWorld world = new TetrisWorld(CANVAS_WIDTH, CANVAS_HEIGHT);
        frame.addKeyListener(world);

        startAnimationLoop(world, canvas);
    }


    private static void startAnimationLoop(World w, Canvas c){
        TimerListener tl = new TimerListener(w, c);
        Timer timer = new Timer(FRAME_DELAY_MS, tl);
        timer.start();
    }

}
