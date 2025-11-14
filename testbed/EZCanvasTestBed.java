package testbed;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import core.CircleSprite;
import core.Canvas;
import core.TimerListener;

public class EZCanvasTestBed {

    private static final int CANVAS_WIDTH = 500;
    private static final int CANVAS_HEIGHT = 500;

    private static final int FRAME_DELAY_MS = 15;
    

    private static JFrame frame;
    private static Canvas canvas;
    private static Timer timer;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
                createTestSprites();
                startAnimationLoop();
            }
        });
    }
    
    private static void createAndShowGUI() {
        frame = new JFrame("Drawing Tester");
        canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
        frame.add(canvas);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        frame.setVisible(true);
    }

    private static void startAnimationLoop(){
        // TimerListener tl = new TimerListener(canvas);
        // timer = new Timer(FRAME_DELAY_MS, tl);
        // TODO update this to use World
        timer.start();
    }

    private static void createTestSprites(){
        CircleSprite circle = new CircleSprite(CANVAS_WIDTH / 2, CANVAS_HEIGHT / 2, 20, Color.RED);
        circle.dx = 1;
        circle.dy = 1;

        canvas.addSprite(circle);

    }

    
}


