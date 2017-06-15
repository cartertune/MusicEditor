package cs3500.music.model;


/**
 * An enumeration to represent the 14 possible pitches.
 */
public enum Pitch {

  C, CSHARP, D, DSHARP, E, F, FSHARP, G, GSHARP, A, ASHARP, B;

  public int value() {

    return this.ordinal();
  }


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

    switch (this) {
      case C:
        return "  C";
      case CSHARP:
        return " C#";
      case D:
        return "  D";
      case DSHARP:
        return " D#";
      case E:
        return "  E";
      case F:
        return "  F";
      case FSHARP:
        return " F#";
      case G:
        return "  G";
      case GSHARP:
        return " G#";
      case A:
        return "  A";
      case ASHARP:
        return " A#";
      case B:
        return "  B";
      default:
        return "";
    }
  }
}
