package pong;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import core.Canvas;
import core.TimerListener;

public class Pong {
    public static final int CANVAS_WIDTH = 500;
    public static final int CANVAS_HEIGHT = 500;

    private static final int FRAME_DELAY_MS = 15;

    private static JFrame frame;
    private static Canvas canvas;
    private static Timer timer;

    private static PongWorld world;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
                world = new PongWorld(canvas);
                startAnimationLoop();
            }
        });
    }
    
    private static void createAndShowGUI() {
        frame = new JFrame("Pong");
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        frame.add(canvas);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        frame.setVisible(true);
    }

    private static void startAnimationLoop(){
        TimerListener tl = new TimerListener(world);
        timer = new Timer(FRAME_DELAY_MS, tl);
        timer.start();
    }

}
