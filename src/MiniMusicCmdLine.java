import javax.sound.midi.*;

/**
 * Created by Mark on 29/07/2016.
 */
public class MiniMusicCmdLine {
    public static void main(String[] args) {
        MiniMusicCmdLine mini = new MiniMusicCmdLine();
        if(args.length < 2){
            System.out.println("Dont forget the instrument and note args");
        }
        else{
            int instrument = Integer.parseInt(args[0]);
            int note = Integer.parseInt(args[1]);
            mini.play(instrument, note);
        }
    }

    public void play(int instrument, int note){
        try{
            Sequencer player = MidiSystem.getSequencer(); //retrive a sequencer object - can throw an exception
            player.open(); //sequence opens to be used
            Sequence seq = new Sequence(Sequence.PPQ, 4);
            Track track = seq.createTrack();

            //Change instrument message
            ShortMessage first = new ShortMessage();
            first.setMessage(192, 1, instrument, 0);
            MidiEvent changeInstrument = new MidiEvent(first,1);
            track.add(changeInstrument);
            //Following code adds 2 events to the track which will be played as a single piano note
            ShortMessage a = new ShortMessage();
            a.setMessage(144,1,note,100);
            MidiEvent noteOn = new MidiEvent(a,1);
            track.add(noteOn);

            ShortMessage b = new ShortMessage();
            b.setMessage(128,1,note,100);
            MidiEvent noteOff = new MidiEvent(b,3);
            track.add(noteOff);

            player.setSequence(seq);
            player.start(); //like pressing play on a tape player
        }
        catch(Exception ex){

        }
    }
}
