import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.ShortMessage;
import javax.swing.*;
import java.awt.*;

/**
 * Created by Mark on 29/07/2016
 */
public class MyDrawPanel extends JPanel implements ControllerEventListener{

    boolean msg = false;

    public void paintComponent(Graphics g){
        if(msg) {
            g.setColor(randomColor());
            g.fillRect(0, 0, this.getWidth(), this.getHeight());

            int ht = (int) ((Math.random() * 120) + 10);
            int width = (int) ((Math.random() * 120) + 10);
            int x = (int) ((Math.random() * 40) + 10);
            int y = (int) ((Math.random() * 40) + 10);
            g.fillRect(x,y,width,ht);
            msg = false;
        }
    }
    private Color randomColor(){
        int red = (int)(Math.random() * 256);
        int green = (int)(Math.random() * 256);
        int blue = (int)(Math.random() * 256);
        return new Color(red,green,blue);
    }

    @Override
    public void controlChange(ShortMessage event) {
        msg = true;
        repaint();
    }
}
