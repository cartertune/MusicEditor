package cs3500.music.view.mocks;

import java.util.List;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Transmitter;

/**
 * A mock MidiDevice. Emulates the default MidiSynthesizer.
 *  Primary purpose is to pass the view a Mock receiver, which logs any sent MidiMessages.
 */
public class MockMidiDevice implements MidiDevice{

  /**
   * Returns a mock receiver, used to log the sending of MidiMessages.
   */
  @Override
  public Receiver getReceiver() {
    return new MockReceiver();
  }


  @Override
  public Info getDeviceInfo() {
    return null;
  }

  @Override
  public void open() throws MidiUnavailableException {
    //does nothing, because this is a mock
  }

  @Override
  public void close() {
    //does nothing, because this is a mock
  }

  @Override
  public boolean isOpen() {
    return false;
  }

  @Override
  public long getMicrosecondPosition() {
    return 0;
  }

  @Override
  public int getMaxReceivers() {
    return 0;
  }

  @Override
  public int getMaxTransmitters() {
    return 0;
  }

  @Override
  public List<Receiver> getReceivers() {
    return null;
  }

  @Override
  public Transmitter getTransmitter() throws MidiUnavailableException {
    return null;
  }

  @Override
  public List<Transmitter> getTransmitters() {
    return null;
  }
}
