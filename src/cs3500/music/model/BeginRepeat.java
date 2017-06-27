package cs3500.music.model;

/**
 * Created by cartertune on 6/25/17.
 */
public class BeginRepeat implements Repeat {

  private int startBeat;

  BeginRepeat(int startBeat) {

    this.startBeat = startBeat;
  }

  @Override
  public int goTo() {
    return startBeat + 1;
  }

  @Override
  public int getBeat() {
    return startBeat;
  }

  @Override
  public RepeatType type() {
    return RepeatType.StartRepeat;
  }

  @Override
  public void setToPassed() {
    //do nothing.. does not matter.
  }
}
