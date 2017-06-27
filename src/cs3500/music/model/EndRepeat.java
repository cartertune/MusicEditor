package cs3500.music.model;

/**
 * Created by cartertune on 6/27/17.
 */
public class EndRepeat implements Repeat {


  private int beat;
  private int goToBeat;
  private boolean hasPassed;

  EndRepeat(int beat, int goToBeat) {

    this.beat = beat;
    this.goToBeat = goToBeat;
    this.hasPassed = false;
  }

  /**
   * The beat that this repeat delegates the song to.
   *
   * @return the integer of the beat number
   */
  @Override
  public int goTo() {

    if (hasPassed) {
      return beat + 1;
    } else {
      return goToBeat;
    }
  }

  @Override
  public int getBeat() {
    return beat;
  }

  /**
   * The type of the Repeat. Either a begin repeat, an end repeat, or an ending.
   *
   * @return the type.
   */
  @Override
  public RepeatType type() {
    return RepeatType.EndRepeat;
  }

  @Override
  public void setToPassed() {
    hasPassed = true;
  }
}
