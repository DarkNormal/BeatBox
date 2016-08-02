import javax.sound.midi.*;

/**
 * Created by Mark on 29/07/2016.
 */
public class MiniMiniMusicApp implements ControllerEventListener{
    public static void main(String[] args){
        MiniMiniMusicApp mini = new MiniMiniMusicApp();
        mini.play();
    }
    public void play(){
        try{
            Sequencer player = MidiSystem.getSequencer(); //retrive a sequencer object - can throw an exception
            int[] eventsIWant = {127};
            player.addControllerEventListener(this, eventsIWant);
            player.open(); //sequence opens to be used
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

            for (int i = 5; i < 61; i+= 4) {
                track.add(makeEvent(144,1,i,100,i));
                track.add(makeEvent(176,1,127,0,i));
                track.add(makeEvent(128,1,i,100,i+2));
            }

            player.setSequence(seq);
            player.setTempoInBPM(220);
            player.start(); //like pressing play on a tape player
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

    @Override
    public void controlChange(ShortMessage shortMessage) {

    }
}
