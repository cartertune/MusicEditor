package cs3500.music.view;

import cs3500.music.controller.GUIController;
import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicOperations;
import java.awt.Dimension;

/**
 * Created by cartertune on 6/15/17.
 */
public class MainGui {

  public static void main(String[] args) {

    MusicOperations model = new MusicModel();
    model.addNote(4, 3,1, "CSHARP");
    model.addNote(2, 5,0, "CSHARP");
    model.addNote(8, 4,4, "F");
    model.addNote(2, 4,2, "E");
    GuiViewFrame gui = new GuiViewFrame(model, null);
    GUIController controller = new GUIController(gui, model);
    gui.setController(controller);
    gui.initialize();
  }
}
