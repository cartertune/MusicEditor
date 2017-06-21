package cs3500.music;


import cs3500.music.model.MusicModel.CompBuilder;
import cs3500.music.model.MusicOperations;

import cs3500.music.util.MusicReader;


import cs3500.music.view.ViewFactory;
import cs3500.music.view.ViewInterface;
import java.io.FileReader;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;



public class MusicEditor {

  /**
   * Initializes view with given file name and view type.
   * @param args args[0] is the file name to read from and args[1] is the View type
   * @throws IOException if cant happen.
   * @throws InvalidMidiDataException if midi cant be played.
   */
  public static void main(String[] args) throws IOException, InvalidMidiDataException {


    MusicOperations model = MusicReader.parseFile(new FileReader(args[0]),
        new CompBuilder());
    ViewFactory factory = new ViewFactory();
    ViewInterface view = factory.makeView(model, args[1]);
    view.initialize();

  }
}
