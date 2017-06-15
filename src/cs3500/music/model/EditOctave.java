package cs3500.music.model;

/**
 * Class to edit the octave of a class.
 */
public class EditOctave implements INoteEditor {

  private int octave;

  /**
   * Creates an EditOctave with given octave.
   * @param octave octave to set given  notes to.
   */
  public EditOctave(int octave) {

    this.octave = octave;
  }

  @Override
  public void edit(INote note) {
    note.setOctave(this.octave);
  }
}
