package cs3500.music;

import cs3500.music.controller.GUIController;
import cs3500.music.model.MusicModel;
import cs3500.music.model.MusicOperations;

import cs3500.music.util.MusicReader;
import cs3500.music.view.GuiViewFrame;
import cs3500.music.view.MidiViewImpl;


import cs3500.music.view.ViewFactory;
import cs3500.music.view.ViewInterface;
import java.io.FileReader;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;



public class MusicEditor {
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
