package core;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** Listens for a Timer action and paints the World on the Canvas */
public class TimerListener implements ActionListener{
    private World world;
    private Canvas canvas;

    public TimerListener(World world, Canvas canvas){
        this.world = world;
        this.canvas = canvas;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        world.updateSprites();
        canvas.setSprites(world.getSprites());
        canvas.repaint();
    }
}

