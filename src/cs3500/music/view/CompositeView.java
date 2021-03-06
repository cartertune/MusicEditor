package cs3500.music.view;

import cs3500.music.controller.Controller;
import cs3500.music.controller.MusicController;
import cs3500.music.model.MusicOperations;
import cs3500.music.model.Repeat;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Combines a MidiView and a GUIView into a synchronized music editor.
 * Uses a timer to appropriately play notes and advance song.
 */
public class CompositeView implements IGUIView {

  private final MusicOperations model;
  private final MidiViewImpl midi;
  private final GuiViewFrame gui;

  private int currentBeat;
  private int nextBeat;
  private boolean isPlaying;
  private Timer timer = new Timer();

  /**
   * Creates a composite view for given model, and sets a timer that advances song as long as it is
   * not paused.
   *
   * @param model model represented by view.
   */
  CompositeView(MusicOperations model) {
    this.model = model;
    this.midi = new MidiViewImpl(model);
    this.gui = new GuiViewFrame(model);
    this.currentBeat = 0;
    this.isPlaying = false;

    long tempoInMilliSeconds = model.getTempo() / 1000;
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
      nextBeat--;
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
      midi.scrollRight();
      gui.scrollRight();
      setCurrentAndNextBeat(nextBeat);

    }
  }

  /**
   * Jumps ot the end of the song.
   */
  @Override
  public void jumpToEnd() {

    currentBeat = model.maxBeatNum();
    nextBeat = currentBeat + 1;
    midi.jumpToEnd();
    gui.jumpToEnd();
  }

  /**
   * Jumps to the beginning of the song.
   */
  @Override
  public void jumpToBeginning() {
    currentBeat = 0;
    nextBeat =1;
    midi.jumpToBeginning();
    gui.jumpToBeginning();
  }

  /**
   * Adds a quarter note at current beat with a default volume, and instrument at current beat.
   *
   * @param me the mouse event to use to find the key pressed.
   */
  @Override
  public void addNoteAt(MouseEvent me, MusicController controller, double holdTime) {

    gui.addNoteAt(me, controller, holdTime);
    scrollRight();
  }

  @Override
  public int getCurrentBeat() {
    return currentBeat;
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

  @Override
  public void increaseTempo() {
    int tempo = Math.max(1000, (model.getTempo() - 10000));
    this.model.setTempo(tempo);
    long tempoInMilliSeconds = tempo / 1000;
    timer.cancel();
    timer = new Timer();
    timer.scheduleAtFixedRate(new MusicTimer(), 0, tempoInMilliSeconds);


  }

  @Override
  public void decreaseTempo() {

    int tempo = model.getTempo() + 10000;
    this.model.setTempo(tempo);
    long tempoInMilliSeconds = tempo / 1000;
    timer.cancel();
    timer = new Timer();
    timer.scheduleAtFixedRate(new MusicTimer(), 0, tempoInMilliSeconds);
  }

  /**
   * Sets the current and next beat to the given parameters.
   *
   * @param beat the beat of the currentBeat.
   */
  private void setCurrentAndNextBeat(int beat) {
    this.currentBeat = beat;

    if (model.hasRepeatAt(beat)) {
      nextBeat = model.getRepeatAt(beat).goTo();
      model.getRepeatAt(beat).setToPassed();
    } else {
      nextBeat = currentBeat + 1;
    }

    System.out.println(currentBeat + "  " + nextBeat);
  }

  /**
   * Creates a TimerTask to play the song when it is not paused at correct tempo.
   */
  public class MusicTimer extends TimerTask {


    /**
     * If the view is not paused, it advances to the next beat at each tick.
     */
    @Override
    public void run() {
      if (isPlaying) {
        scrollRight();
      }
    }
  }
}