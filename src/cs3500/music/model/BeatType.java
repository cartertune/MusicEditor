package cs3500.music.model;

/**
 * An enumeration to represent the two types of beats.
 * */
public enum BeatType {

  NOTEHEAD, NOTESUSTAINED;


  /**
   * Returns the name of this enum constant, as contained in the
   * declaration.  This method may be overridden, though it typically
   * isn't necessary or desirable.  An enum type should override this
   * method when a more "programmer-friendly" string form exists.
   *
   * @return the name of this enum constant
   */
  @Override
  public String toString() {

    if (this.equals(NOTEHEAD)) {
      return "  X  ";
    }
    return "  |  ";
  }
}
