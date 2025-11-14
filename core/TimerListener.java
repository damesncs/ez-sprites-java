package core;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerListener implements ActionListener{
    private World world;

    public TimerListener(World world){
        this.world = world;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        world.update();
    }
}

