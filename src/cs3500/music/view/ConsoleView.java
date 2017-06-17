package cs3500.music.view;

import cs3500.music.model.MusicOperations;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * todo: javadoc
 */
public class ConsoleView implements ViewInterface {

  private final MusicOperations model;

  public ConsoleView(MusicOperations model) {
    this.model = model;
  }

  @Override
  public void initialize() {

    //Used to output console file
    /*
    FileWriter fw = null;
    try {
      fw = new FileWriter("console-transcript.txt");

    BufferedWriter bw = new BufferedWriter(fw);
    bw.write(model.getMIDINotation());

    bw.close();
    fw.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    */
    System.out.print(model.getMIDINotation());
  }
}
