package cs3500.music;

import cs3500.music.model.MusicModel;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;

import java.io.FileReader;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;


public class MusicEditor {
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    //GuiViewFrame view = new GuiViewFrame();


    MusicModel model = MusicReader.parseFile(new FileReader("mary-little-lamb.txt"),
        new CompositionBuilder<MusicModel>() {
        });




    MidiViewImpl midiView = new MidiViewImpl();
    // You probably need to connect these views to your model, too...
  }
}
