package cs3500.music;

import cs3500.music.controller.GUIController;
import cs3500.music.model.MusicModel;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;

import java.io.FileReader;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;


public class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException {



    MusicModel model = MusicReader.parseFile(new FileReader("mary-little-lamb.txt"),
            new MusicModel.compBuilder());


      MusicModel mod1 = new MusicModel();
      mod1.addNote(500000, 4, 5,  5, 64, "C");
      //GuiViewFrame view = new GuiViewFrame(model, new GUIController());

      try {
          MidiViewImpl midiView = new MidiViewImpl(mod1);
          midiView.initialize();
      } catch (MidiUnavailableException e) {
          e.printStackTrace();
      }
      // You probably need to connect these views to your model, too...
  }
}
