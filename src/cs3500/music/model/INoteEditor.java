package cs3500.music.model;

/**
 * An interface for classes that edit notes to implement.
 * Never gets used but did not want to delete it, just in case.
 */
public interface INoteEditor {


  /**
   * Edits the given note according to the class.
   * @param note note to be edited.
   */
  void edit(INote note);
}
