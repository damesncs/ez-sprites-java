import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class DrawingTester {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    private static void createAndShowGUI() {
        JFrame f = new JFrame("Drawing Tester");
        EZCanvas canvas = new EZCanvas(250, 250);
        f.add(canvas);
        f.pack();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(250, 250);
        f.setVisible(true);
    }
}
