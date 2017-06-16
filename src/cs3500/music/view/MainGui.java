package cs3500.music.view;

import cs3500.music.controller.GUIController;
import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicOperations;
import cs3500.music.util.MusicReader;

import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by cartertune on 6/15/17.
 */
public class MainGui {

  public static void main(String[] args) {

    MusicModel model = null;
    try {
      model = MusicReader.parseFile(new FileReader("mary-little-lamb.txt"),
              new MusicModel.compBuilder());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    GuiViewFrame gui = new GuiViewFrame(model, null);
    GUIController controller = new GUIController(gui, model);
    gui.setController(controller);
    gui.initialize();
  }
}
