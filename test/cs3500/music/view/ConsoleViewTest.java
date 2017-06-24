package cs3500.music.view;

import cs3500.music.model.MusicModel.CompBuilder;
import cs3500.music.model.MusicOperations;
import cs3500.music.util.MusicReader;
import java.io.FileReader;

/**
 * Test for Console View.
 */
public class ConsoleViewTest {

  /**
   * tests initialize of consoleView.
   * @throws Exception if unrenderable.
   */
  public void initializeTest() throws Exception {
    MusicOperations model = MusicReader.parseFile(new FileReader("mystery-1.txt"),
        new CompBuilder());

    ConsoleView view = new ConsoleView(model);
    view.initialize();
  }

}