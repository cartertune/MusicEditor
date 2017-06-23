package cs3500.music.view;

import cs3500.music.controller.Controller;
import cs3500.music.controller.MusicController;
import cs3500.music.model.MusicOperations;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Combines a MidiView and a GUIView into a synchronized music editor.
 */
public class CompositeView implements IGUIView {

  private final MusicOperations model;
  private final MidiViewImpl midi;
  private final GuiViewFrame gui;

  private long tempoInMilliSeconds;
  private int currentBeat;
  private boolean isPlaying;
  private Timer timer = new Timer();

  CompositeView(MusicOperations model) {
    this.model = model;
    this.midi = new MidiViewImpl(model);
    this.gui = new GuiViewFrame(model);
    this.currentBeat = 0;
    this.isPlaying = false;
    tempoInMilliSeconds = model.getTempo() / 1000;
    timer.scheduleAtFixedRate(new MusicTimer(), 0, tempoInMilliSeconds);
    new Controller(this, model);
  }


  /**
   * Allows the view to be viewed, such as by exposing an invisible panel, or playing a song.
   */
  @Override
  public void initialize() {

    gui.initialize();
  }

  // TODO: 6/21/17 This will be simple but take time to write.
  @Override
  public void playPause() {


    isPlaying = !isPlaying;
    midi.playPause();
    gui.playPause();
  }

  /**
   * Scrolls the current beat to the left if possible.
   */
  @Override
  public void scrollLeft() {
    if (currentBeat > 0) {
      currentBeat--;
      midi.scrollLeft();
      gui.scrollLeft();
    }
  }

  /**
   * Scrolls the current beat to the right if possible.
   */
  @Override
  public void scrollRight() {

    if (currentBeat < model.maxBeatNum()) {
      currentBeat++;
      midi.scrollRight();
      gui.scrollRight();
    }
  }

  /**
   * Jumps ot the end of the song.
   */
  @Override
  public void jumpToEnd() {

    currentBeat = model.maxBeatNum();
    midi.jumpToEnd();
    gui.jumpToEnd();
  }

  /**
   * Jumps to the beginning of the song.
   */
  @Override
  public void jumpToBeginning() {
    currentBeat = 0;
    midi.jumpToBeginning();
    gui.jumpToBeginning();
  }

  /**
   * Adds a quarter note at current beat with a default volume, and instrument at current beat.
   *
   * @param me the mouse event to use to find the key pressed.
   */
  @Override
  public void addNoteAt(MouseEvent me, MusicController controller){

    gui.addNoteAt(me, controller);
  }

  /**
   * Adds the given keyListener to the view.
   *
   * @param kl the keylistener to add.
   */
  @Override
  public void addKeyListener(KeyListener kl) {

    gui.addKeyListener(kl);
  }

  @Override
  public void addMouseListener(MouseListener ml) {
    gui.addMouseListener(ml);
  }

  public class MusicTimer extends TimerTask {

    MusicTimer() {}
    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
      if (isPlaying) {
        scrollRight();
      }
    }
  }
}
