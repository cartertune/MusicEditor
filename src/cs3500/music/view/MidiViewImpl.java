package cs3500.music.view;

import cs3500.music.model.INote;
import cs3500.music.model.MusicOperations;
import cs3500.music.model.Note;

import javax.sound.midi.*;
import java.util.ArrayList;

/** todo: update javadoc
 * A skeleton for MIDI playback
 */
public class MidiViewImpl implements ViewInterface {


  private Synthesizer synth;
  private Receiver receiver;
  private Sequencer sequencer = null;

  //todo: mod, overload constructor? - one with model and [one empty?]
  public MidiViewImpl(MusicOperations model) throws MidiUnavailableException {
    this.synth = MidiSystem.getSynthesizer();
    this.receiver = synth.getReceiver();

    sequencer = MidiSystem.getSequencer(); //default sequencer
    this.synth.open();
    sequencer.open();


    this.receiver = synth.getReceiver();
    try {
      this.loadPiece(model); //Create sequence from model
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Invalid model: " + e.getMessage());
    }
  }

  //todo: javadoc
  private void loadPiece(MusicOperations piece) throws InvalidMidiDataException, MidiUnavailableException {
    //Boiler Plate
     //todo: Move to constructor
    //todo: tempo in Pulses per tick per quarter note as time resolution in new sequence
    Sequence sequence = new Sequence(Sequence.PPQ, 30); //10 pulses per quarter note
    sequence.createTrack();
    sequencer.setSequence(sequence);
    sequencer.recordEnable(sequence.getTracks()[0], 0);
    sequencer.startRecording();


    //---------------------------------------------------------------------------------------------
    // ^cruff above is setup.   V Cruff below is actual music recording, Midi messages

    //Ideally, Iterate through a list of notes.
    //Alternatively, get last note time, and iterate based on time from 0 to that.
    int c = 0;
    for (int time = 0; time < piece.maxBeatNum(); time++) {
      ArrayList<INote> notes = piece.getNotesAt(time);
      if (!notes.isEmpty()) { //there are notes here
        for (INote note: notes) { //For each note at this time,send 2 messages

          c++;
          long timeStamp = ((long)600000000 * (long)time) / (long)piece.getTempo();
          this.receiver.send(new ShortMessage(ShortMessage.NOTE_ON,
                  note.getInstrument(), //Instrument midi value
                  note.value(),
                  note.getVolume()),
                  timeStamp); // Time stamp


          this.receiver.send(new ShortMessage(ShortMessage.NOTE_OFF,
                          note.getInstrument(), //Instrument midi value
                          note.value(),
                          note.getVolume()),
                  timeStamp + ((long)600000000 * (long)note.getDuration()) / (long)piece.getTempo());
        }
      }
    }
    sequencer.stopRecording();
  }

  @Override
  public void initialize() {
    sequencer.start();// start playing loaded music
    System.out.print(sequencer.isRunning());
    System.out.print(sequencer.toString());
    System.out.print("reached close");
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.print(sequencer.getMicrosecondLength());
    sequencer.close();

    try {
      this.playNote();
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }

  public void playNote() throws InvalidMidiDataException {
    MidiMessage start = new ShortMessage(ShortMessage.NOTE_ON, 0, 60, 120);
    MidiMessage stop = new ShortMessage(ShortMessage.NOTE_OFF, 0, 60, 64);
    this.receiver.send(start, -1);
    this.receiver.send(stop, this.synth.getMicrosecondPosition() + 200000);

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    /*
    The receiver does not "block", i.e. this method
    immediately moves to the next line and closes the
    receiver without waiting for the synthesizer to
    finish playing.

    You can make the program artificially "wait" using
    Thread.sleep. A better solution will be forthcoming
    in the subsequent assignments.
    */
    this.receiver.close(); // Only call this once you're done playing *all* notes
  }
}
