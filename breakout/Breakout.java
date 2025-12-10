package breakout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import core.Canvas;
import core.TimerListener;
import core.World;

public class Breakout {
    public static final int CANVAS_WIDTH = 700;
    public static final int CANVAS_HEIGHT = 500;

    private static final int FRAME_DELAY_MS = 15;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Breakout");
        Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        
        frame.add(canvas);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        frame.setVisible(true);

        BreakoutWorld world = new BreakoutWorld(CANVAS_WIDTH, CANVAS_HEIGHT);
        frame.addKeyListener(world);

        startAnimationLoop(world, canvas);
    }

    private static void startAnimationLoop(World w, Canvas c){
        TimerListener tl = new TimerListener(w, c);
        Timer timer = new Timer(FRAME_DELAY_MS, tl);
        timer.start();
    }

}