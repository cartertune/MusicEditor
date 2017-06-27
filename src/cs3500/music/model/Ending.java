package cs3500.music.model;

/**
 * Created by cartertune on 6/27/17.
 */
public class Ending implements Repeat {

  private boolean isFirstEnding; // if this is the first ending.
  private int hasPassed; // the number of times the beat has passed this point.
  private int startBeat; // the position of the repeat notation.
  private int endBeat; // the beat the ending ends on/
  private int gotoBeat; // the beat this repeat delegates to.(last StartRepeat)

  Ending(boolean isFirstEnding, int startBeat, int gotoBeat) {
    this.isFirstEnding = isFirstEnding;
    this.startBeat = startBeat;
    this.endBeat = startBeat  + 4;
    this.gotoBeat = gotoBeat;
    this.hasPassed = 0;
  }


  /**
   * The beat that this repeat delegates the song to.
   *
   * @return the integer of the beat number
   */
  @Override
  public int goTo() {
    if (isFirstEnding) {
      if (hasPassed > 0) {
        return endBeat;
      } else {
        return startBeat + 1;
      }
    }
    if (hasPassed == 0) {
      return gotoBeat;
    } else if (hasPassed == 1) {
      return  startBeat + 1;
    } else {
      return endBeat;
    }
  }

  @Override
  public int getBeat() {
    return startBeat;
  }

  /**
   * The type of the Repeat. Either a begin repeat, an end repeat, or an ending.
   *
   * @return the type.
   */
  @Override
  public RepeatType type() {
    return RepeatType.EndingVariation;
  }

  @Override
  public void setToPassed() {
    hasPassed++;
  }
}
