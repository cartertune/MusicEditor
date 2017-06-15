package cs3500.music.model;

/**
 * A class to edit the pitch of a given note.
 */
public class EditPitch implements INoteEditor {

  Pitch pitch;

  public EditPitch(Pitch pitch) {
    this.pitch = pitch;
  }


  @Override
  public void edit(INote note) {
    note.setPitch(this.pitch);
  }
}
