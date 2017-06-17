package cs3500.music.view;

import cs3500.music.model.INote;
import cs3500.music.model.MusicOperations;

import javax.sound.midi.*;
import java.util.ArrayList;

/**
 * View for Music Editor. Presents music through sound, instead of visuals.
 */
public class MidiViewImpl implements ViewInterface {


  private MidiDevice synth; //Abstracted from Synthesizer to MidiDevice to enable us of mock Device.
  private Receiver receiver;
  private MusicOperations piece;

  /**
   * Constructor. Takes the music piece to be played, and prepares the necessary boiler plate.
   * @param model the music piece to be played
   * @throws IllegalArgumentException if model is null
   * @throws IllegalStateException if system midi is inaccessible
   */
  public MidiViewImpl(MusicOperations model) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null");
    }
    this.piece = model;

    try { //Boiler Plate: Synthesizer and receiver
      this.synth = MidiSystem.getSynthesizer();
      this.receiver = synth.getReceiver();
      this.synth.open();
    } catch (MidiUnavailableException e) {
      throw new IllegalStateException("System unsupported. System midi output unavailable.");
    }
  }


  /**
   * Constructor reserved for mock usage. Does not play music if used.
   * @param model the music piece to be analyzed.
   * @param mockDevice the mock Midi Device to be used instead of a Synthesizer
   * @throws IllegalArgumentException if a non-mock MidiDevice is given.
   */
  public MidiViewImpl(MusicOperations model, MidiDevice mockDevice) {
    if (mockDevice == null) {
      throw new IllegalArgumentException("Mock Midi Device cannot be null.");
    }
    this.synth = mockDevice;

    try {
      this.receiver = synth.getReceiver();//returns mock receiver
    } catch (MidiUnavailableException e) { //mock does not check for midi availabilty
      throw new IllegalArgumentException("Not a mock midi device");
    }

    this.piece = model;
  }

  /**
   * Converts the music piece into a series of messages, appropriately time stamped, and plays it.
   *
   * @throws IllegalArgumentException if the model used to construct this contained invalid midiData
   * @throws IllegalStateException if the thread sleep is interrupted.
   */
  @Override
  public void initialize() {
    for (int time = 0; time < piece.maxBeatNum(); time++) {
      ArrayList<INote> notes = piece.getNotesAt(time);
      if (!notes.isEmpty()) { //if there are notes beginning at this time
        for (INote note: notes) { //For each note at this time, send 2 messages

          long timeStamp = (100 * (long)600000000 * (long)time) / (long)piece.getTempo();
          long duration = ((100 * (long)600000000 * (long)note.getDuration()) /
              (long)piece.getTempo());

          try {
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
          } catch (InvalidMidiDataException e) {
            throw new IllegalArgumentException("Invalid model. Check format." + e.getMessage());
          }
        }
      }
    }

    try { //sleep for entire duration of piece.
      Thread.sleep(((long)600000000 * (long)piece.maxBeatNum() ) / (long)piece.getTempo());
    } catch (InterruptedException e) {
      throw new IllegalStateException("Thread sleep interrupted. " + e.getMessage());
    }
  }
}
