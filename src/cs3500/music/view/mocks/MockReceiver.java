package cs3500.music.view.mocks;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

/**
 * A mock Receiver. Emulates the synthesizer's receiver.
 */
public class MockReceiver implements Receiver{


  @Override
  public void send(MidiMessage message, long timeStamp) {

  }

  @Override
  public void close() {

  }
}
