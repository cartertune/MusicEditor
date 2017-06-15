package cs3500.music.model;

/**
 * An interface for classes that edit notes to implement.
 */
public interface INoteEditor {


  /**
   * Edits the given note according to the class.
   * @param note note to be edited.
   */
  void edit(INote note);
}
