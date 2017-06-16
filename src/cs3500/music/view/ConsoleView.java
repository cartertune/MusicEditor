package cs3500.music.view;

import cs3500.music.model.MusicOperations;

/**
 * Created by cartertune on 6/16/17.
 */
public class ConsoleView implements ViewInterface {

  private final MusicOperations model;

  public ConsoleView(MusicOperations model) {
    this.model = model;
  }

  @Override
  public void initialize() {
    System.out.print(model.getMIDINotation());
  }
}
