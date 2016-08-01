import javax.swing.*;
import java.awt.*;

/**
 * Created by Mark on 01/08/2016.
 */
public class SimpleAnimation {
    int x = 70, y = 70;

    public static void main(String[] args) {
        SimpleAnimation anim = new SimpleAnimation();
        anim.go();
    }
    public void go(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MyDrawPanel panel = new MyDrawPanel();
        frame.getContentPane().add(panel);
        frame.setSize(300,300);
        frame.setVisible(true);

        for (int i = 0; i < 130; i++) {
            x = x + 1;
            y = y + 1;
            panel.repaint();
            try{
                Thread.sleep(50);
            }catch (Exception ex){

            }
        }

    }
    class MyDrawPanel extends JPanel{
        public void paintComponent(Graphics g){
            g.setColor(Color.white);
            g.fillRect(0,0, this.getWidth(), this.getHeight());
            g.setColor(Color.green);
            g.fillOval(x,y,40,40);
        }
    }
}
