import javax.sound.midi.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.io.FileOutputStream;

/**
 * Created by Mark on 29/07/2016.
 */
public class MiniMiniMusicApp{

    static JFrame frame = new JFrame("My first music video");
    static MyDrawPanel panel;

    public static void main(String[] args){
        MiniMiniMusicApp mini = new MiniMiniMusicApp();
        mini.go();
    }
    public void setupGui(){
        panel = new MyDrawPanel();
        frame.setContentPane(panel);
        frame.setBounds(30,30,300,300);
        frame.setVisible(true);
    }
    public void go(){
        setupGui();
        try{
            Sequencer player = MidiSystem.getSequencer(); //retrive a sequencer object - can throw an exception
            player.addControllerEventListener(panel, new int[]{127});
            player.open(); //sequence opens to be used
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

            int r = 0;
            for (int i = 0; i < 60; i+= 4) {
                r = (int) ((Math.random() *50) +1);
                track.add(makeEvent(144,1,r,100,i));
                track.add(makeEvent(176,1,127,0,i));
                track.add(makeEvent(128,1,r,100,i+2));
            }

            player.setSequence(seq);
            player.start(); //like pressing play on a tape player
            player.setTempoInBPM(120);

        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public static MidiEvent makeEvent(int comd,int chan, int one, int two, int tick){
        MidiEvent event = null;
        try{
            ShortMessage a = new ShortMessage();
            a.setMessage(comd, chan, one, two);
            event = new MidiEvent(a, tick);
        }catch(Exception ex){

        }
        return event;
    }

    /**
     * Created by Mark on 29/07/2016
     */
    class MyDrawPanel extends JPanel implements ControllerEventListener{

        boolean msg = false;

        public void paintComponent(Graphics g){
            if(msg) {
                g.setColor(randomColor());
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

}
