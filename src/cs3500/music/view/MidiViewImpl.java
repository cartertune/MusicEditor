package cs3500.music.view;

import cs3500.music.model.MusicOperations;
import javax.sound.midi.*;

/** todo: update javadoc
 * A skeleton for MIDI playback
 */
public class MidiViewImpl implements ViewInterface {
  private Receiver receiver;
  private Sequencer sequencer = null;

  //todo: mod, overload constructor? - one with model and [one empty?]
  public MidiViewImpl() throws MidiUnavailableException {
    //null check
  }


  // todo:delete this method, currently serving as reference
//  public void playNote() throws InvalidMidiDataException {
//    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, 0, 60, 64);
//    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, 0, 60, 64);
//    this.receiver.send(start, -1);
//    //this.receiver.send(stop, this.synth.getMicrosecondPosition() + 200000);
//
//    /*
//    The receiver does not "block", i.e. this method
//    immediately moves to the next line and closes the
//    receiver without waiting for the synthesizer to
//    finish playing.
//
//    You can make the program artificially "wait" using
//    Thread.sleep. A better solution will be forthcoming
//    in the subsequent assignments.
//    */
//    this.receiver.close(); // Only call this once you're done playing *all* notes
//  }


  //todo: delete, move internals to initialize or private load music method
  public void testSequencer(MusicOperations model) throws InvalidMidiDataException, MidiUnavailableException {
    sequencer = MidiSystem.getSequencer(); //default sequencer
    sequencer.open();

    this.receiver = sequencer.getReceiver(); //todo: Move to constructor

        //test receiver
    //System.out.print(sequencer.getReceiver()); //todo: delete



    //create sequence
    Sequence sequence = null;
    sequence = new Sequence(Sequence.PPQ, 10); //10 pulses per quarter note
    sequence.createTrack();
    //System.out.print(sequencer.getTracks()); //todo:delete


    sequencer.setSequence(sequence);

    //Only when about to record
    sequencer.recordEnable(sequence.getTracks()[0], 0);

    //---------------------------------------------------------------------------------------------
    // ^cruff above is setup.   V Cruff below is actual music recording, Midi messages


    //Breakdown: For each note, send 2 ShortMessages, A start and stop.
    this.receiver.send(new ShortMessage(ShortMessage.NOTE_ON, 0, 64, 64), 0);
    this.receiver.send(new ShortMessage(ShortMessage.NOTE_OFF, 0, 64, 64), 20);


    //Ideally, Iterate through a list of notes.

    //Alternatively, get last note time, and iterate based on time from 0 to that.

    sequencer.close(); //when done recording, makes sequencer available for use
    sequencer.start(); //start play
  } //todo: divide into private helpers.


  //todo: implement, save converted sequence. Should the method be split into private load and play?
  private void loadPiece(MusicOperations piece) { //todo: should this be initialize?


  }


  @Override //todo: whatever this does
  public void initialize() { //todo: note: using a sequencer makes starting and stopping easier and cleaner
    //sqr.start()
  }
}
