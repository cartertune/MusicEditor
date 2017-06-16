package cs3500.music;

import cs3500.music.controller.GUIController;
import cs3500.music.controller.IController;
import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicOperations;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;

import cs3500.music.view.ViewInterface;
import java.io.FileReader;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;


public class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException {



    MusicOperations model = MusicReader.parseFile(new FileReader("mystery-2.txt"),
            new MusicModel.compBuilder());
    GuiViewFrame gui = new GuiViewFrame(model, null);
    GUIController controller = new GUIController(gui, model);
    gui.setController(controller);

    gui.initialize();
    try {
      MidiViewImpl midiView = new MidiViewImpl(model);
      midiView.initialize();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }

  }
}
