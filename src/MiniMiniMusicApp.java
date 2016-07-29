import javax.sound.midi.*;

/**
 * Created by Mark on 29/07/2016.
 */
public class MiniMiniMusicApp {
    public static void main(String[] args){
        MiniMiniMusicApp mini = new MiniMiniMusicApp();
        mini.play();
    }
    public void play(){
        try{
            Sequencer player = MidiSystem.getSequencer(); //retrive a sequencer object - can throw an exception
            player.open(); //sequence opens to be used
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

            //Change instrument message
            ShortMessage first = new ShortMessage();
            first.setMessage(192, 1, 102, 0);
            MidiEvent firstMsg = new MidiEvent(first,1);
            track.add(firstMsg);
            //Following code adds 2 events to the track which will be played as a single piano note
            ShortMessage a = new ShortMessage();
            a.setMessage(144,1,44,100);
            MidiEvent noteOn = new MidiEvent(a,1);
            track.add(noteOn);

            ShortMessage b = new ShortMessage();
            b.setMessage(128,1,44,100);
            MidiEvent noteOff = new MidiEvent(b,3);
            track.add(noteOff);

            player.setSequence(seq);
            player.start(); //like pressing play on a tape player
        }
        catch(Exception ex){

        }
    }
}
