package cs3500.music.model;

/**
 * Edits the duration of the given note to the given duration.
 */
public class EditDuration implements INoteEditor {

  int duration;

  /**
   * Creates a duration editor that edits notes to the given duration.
   * @param duration duration to set note to.
   */
  public EditDuration(int  duration) {
    this.duration = duration;
  }

  @Override
  public void edit(INote note) {
    note.setDuration(this.duration);
  }
}
