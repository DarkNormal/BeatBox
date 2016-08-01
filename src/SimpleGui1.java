import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Mark on 29/07/2016.
 */
public class SimpleGui1{
    JButton labelButton, colorButton;
    JLabel label;
    JFrame frame;
    public static void main(String[] args) {
        SimpleGui1 gui = new SimpleGui1();
        gui.go();

    }
    public void go(){
        frame = new JFrame();
        MyDrawPanel rect = new MyDrawPanel();
        frame.getContentPane().add(BorderLayout.CENTER,rect);
        label = new JLabel("I'm a label");
        labelButton = new JButton("Change Label");
        labelButton.addActionListener(new LabelListener());
        colorButton = new JButton("Change Circle");
        colorButton.addActionListener(new ColorListener());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(BorderLayout.CENTER,rect);
        frame.getContentPane().add(BorderLayout.WEST, label);
        frame.getContentPane().add(BorderLayout.EAST, labelButton);
        frame.getContentPane().add(BorderLayout.SOUTH, colorButton);
        frame.setSize(300,300);
        frame.setVisible(true);
    }

    class LabelListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            label.setText("ouch!");
        }
    }
    class ColorListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            frame.repaint();
        }
    }
}
