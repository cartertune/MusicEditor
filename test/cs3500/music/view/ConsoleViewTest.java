package cs3500.music.view;

import static org.junit.Assert.*;

import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicOperations;
import cs3500.music.util.MusicReader;
import java.io.FileReader;
import org.junit.Test;

/**
 * Created by fshikara on 6/16/17.
 */
public class ConsoleViewTest {

  @Test
  public void initializeTest() throws Exception {
    MusicOperations model = MusicReader.parseFile(new FileReader("mystery-2.txt"),
        new MusicModel.compBuilder());

    ConsoleView view = new ConsoleView(model);
    view.initialize();
  }

}