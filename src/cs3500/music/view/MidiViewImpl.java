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
    Sequence sequence = new Sequence(Sequence.PPQ, 30, 1); //10 pulses per quarter note
    //sequence.createTrack();
    sequencer.setSequence(sequence);
    //System.out.print(sequence.getTracks().length);
    sequencer.recordEnable(sequence.getTracks()[0], 0);
    sequencer.startRecording();

    sequencer.setTempoInBPM(piece.getTempo());

    //---------------------------------------------------------------------------------------------
    // ^cruff above is setup.   V Cruff below is actual music recording, Midi messages
    //Ideally, Iterate through a list of notes.
    //Alternatively, get last note time, and iterate based on time from 0 to that.
    for (int time = 0; time < piece.maxBeatNum(); time++) {
      ArrayList<INote> notes = piece.getNotesAt(time);
      if (!notes.isEmpty()) { //there are notes here
        for (INote note: notes) { //For each note at this time,send 2 messages


          long timeStamp = (100 * (long)600000000 * (long)time) / (long)piece.getTempo();
          long duration = ((100 * (long)600000000 * (long)note.getDuration()) /
              (long)piece.getTempo());

          this.receiver.send(new ShortMessage(ShortMessage.NOTE_ON,
                  note.getInstrument(), //Instrument midi value
                  note.value(),
                  note.getVolume()),
                  timeStamp); // Time stamp

          this.receiver.send(new ShortMessage(ShortMessage.NOTE_OFF,
                          note.getInstrument(), //Instrument midi value
                          note.value(),
                          note.getVolume()),
                  timeStamp + duration);
        }
      }
    }
    sequencer.stopRecording();
  }

  @Override
  public void initialize() {
    sequencer.start();// start playing loaded music
    System.out.print("reached close");
    try {
      Thread.sleep(20000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.print(sequencer.getMicrosecondLength());
    sequencer.close();
  }

}
