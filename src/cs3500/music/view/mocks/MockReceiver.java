package cs3500.music.view.mocks;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

/**
 * A mock Receiver. Emulates the synthesizer's receiver.
 *  Primary purpose is to log any MidiMessages sent.
 */
public class MockReceiver implements Receiver {
  private StringBuilder sendLog = new StringBuilder();

  /**
   * Logs the given message and timeStamp
   * Log Format:
   * "[timeStamp] [Message]\n"
   *        ... repeat
   *
   * @param message the message to be logged
   * @param timeStamp the timeStamp to be logged
   */
  @Override
  public void send(MidiMessage message, long timeStamp) {
    sendLog.append(Long.toString(timeStamp) + " " + message.getMessage() + "\n");
  }

  /**
   * The log of all messages sent to this receiver.
   * @return String, formatted as described above, containing all messages received.
   */
  public String getLog() {
    return sendLog.toString();
  }

  @Override
  public void close() {
    //Does nothing because this is a mock
  }
}
