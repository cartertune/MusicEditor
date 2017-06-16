package cs3500.music.view;

import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicOperations;
import java.awt.Dimension;

/**
 * Created by cartertune on 6/15/17.
 */
public class MainGui {

  public static void main(String[] args) {

    MusicOperations model = new MusicModel();
    model.addNote(4, 3,0, "CSHARP");
    model.addNote(2, 5,0, "CSHARP");

    GuiViewFrame gui = new GuiViewFrame(model);
    gui.initialize();
  }
}
