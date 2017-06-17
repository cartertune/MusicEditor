package cs3500.music;

import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicOperations;

import cs3500.music.util.MusicReader;



import cs3500.music.view.ViewFactory;
import cs3500.music.view.ViewInterface;
import java.io.FileReader;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;



public class MusicEditor {

  /**
   * Initializes various view for various models.
   * @param args arguments
   * @throws IOException if cant happen.
   * @throws InvalidMidiDataException if midi cant be played.
   */
  public static void main(String[] args) throws IOException, InvalidMidiDataException {



    MusicOperations model = MusicReader.parseFile(new FileReader("mary-little-lamb.txt"),
            new MusicModel.compBuilder());
    MusicOperations model2 = MusicReader.parseFile(new FileReader("mystery-1.txt"),
        new MusicModel.compBuilder());
    MusicOperations model3 = MusicReader.parseFile(new FileReader("mystery-2.txt"),
        new MusicModel.compBuilder());
    ViewFactory factory = new ViewFactory();
    ViewInterface guiView = factory.makeView(model3, "gui");
    ViewInterface midiView = factory.makeView(model2, "midi");
    ViewInterface consoleView = factory.makeView(model, "console");

    consoleView.initialize();
    guiView.initialize();
    midiView.initialize();
  }
}
