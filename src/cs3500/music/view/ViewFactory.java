package cs3500.music.view;

import cs3500.music.model.MusicOperations;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

/**
 * Created by cartertune on 6/16/17.
 */
public class ViewFactory {
    /**
     * Creates of the specified class
     *
     * @param type the type of vie to return
     * @return a new view of the specified type
     */
    public static ViewInterface makeView(MusicOperations model, String type) {
      switch (type) {
        case "console":
          return new ConsoleView(model);
        case "gui":
          return new GuiViewFrame(model);
        case "midi":
          return new MidiViewImpl(model);
        default:
          throw new IllegalArgumentException("Invalid class given.");
      }
    }
}
