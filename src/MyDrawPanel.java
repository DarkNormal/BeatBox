import javax.swing.*;
import java.awt.*;

/**
 * Created by Mark on 29/07/2016
 */
public class MyDrawPanel extends JPanel {

    public void paintComponent(Graphics g){


        g.setColor(randomColor());
        g.fillRect(0,0,this.getWidth(),this.getHeight());
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gradient = new GradientPaint(70,70, randomColor(), 150,150, randomColor());
        g2d.setPaint(gradient);
        g2d.fillOval(70,70,100,100);
//        g.setColor(randomColor());
//        g.fillOval(70, 70, 75,75);
//        Image image = new ImageIcon(getClass().getResource("lilgif.gif")).getImage();
//        g.drawImage(image, 3,4,this);
    }
    private Color randomColor(){
        int red = (int)(Math.random() * 256);
        int green = (int)(Math.random() * 256);
        int blue = (int)(Math.random() * 256);
        return new Color(red,green,blue);
    }
}
