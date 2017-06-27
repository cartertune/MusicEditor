package cs3500.music.model;

/**
 * Created by cartertune on 6/25/17.
 */
public interface Repeat {


  /**
   * The beat that this repeat delegates the song to.
   * @return the integer of the beat number
   */
  int goTo();

  int getBeat();

  /**
   * The type of the Repeat. Either a begin repeat, an end repeat, or an ending.
   * @return the type.
   */
  RepeatType type();

  /**
   * Lets the repeatNotation know it has been passed.
   */
  void setToPassed();
}
