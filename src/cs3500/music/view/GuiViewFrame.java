package cs3500.music.view;

import cs3500.music.controller.MusicController;
import cs3500.music.model.MusicOperations;
import cs3500.music.model.Repeat;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


/**
 * A frame to hold the various Panels that are apart of the GUI view.
 * Contains a separate panel for the notes, piano, and the beatNumberLabel.
 * This class' entire job is to organize the layout of these panels.
 */
public class GuiViewFrame extends JFrame implements IGUIView {

  private final JPanel beatNumLabel;
  private final NotesPanel notesPanel;
  private final PianoPanel pianoPanel;
  private MusicOperations model;
  private int currentBeat;
  private int nextBeat;
  private boolean isPlaying;
  private JScrollPane noteScroll;

  /**
   * Creates new GuiView.
   *
   * <p> Sets the model, controller, and starting beat. Then creates the separate panels
   * and orients the appropriately so they are all in their correct positions.</p>
   */
  GuiViewFrame(MusicOperations model) {
    this.model = model;

    this.currentBeat = 0;
    this.nextBeat = 1;
    this.isPlaying = false;
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    beatNumLabel = new JPanel();
    drawBeatNumLabel();
    notesPanel = new NotesPanel(model);
    pianoPanel = new PianoPanel(model, notesPanel);
    noteScroll = new JScrollPane(notesPanel);
    noteScroll.setPreferredSize(new Dimension(1260, 440));
    setLayout(new BorderLayout());
    getContentPane().add(beatNumLabel, BorderLayout.NORTH);
    getContentPane().add(noteScroll, BorderLayout.CENTER);
    getContentPane().add(pianoPanel, BorderLayout.SOUTH);
    setSize(new Dimension(1260, 700));
    setResizable(false);
  }


  @Override
  public void initialize() {
    this.setVisible(true);
  }


  @Override
  public void playPause() {
    isPlaying = !isPlaying;
  }


  @Override
  public void scrollRight() {
    if (currentBeat < model.maxBeatNum()) {
      setCurrentAndNextBeat(nextBeat);
      pianoPanel.scrollRight();
      notesPanel.scrollRight();
      drawBeatNumLabel();
    }
  }

  private void setCurrentAndNextBeat(int beat) {
    this.currentBeat = beat;

    if (model.hasRepeatAt(beat)) {
      nextBeat = model.getRepeatAt(beat).goTo();
    } else {
      nextBeat = currentBeat + 1;
    }

  }

  @Override
  public void scrollLeft() {
    if (currentBeat > 0) {
      currentBeat--;
      nextBeat--;
      pianoPanel.scrollLeft();
      notesPanel.scrollLeft();
      drawBeatNumLabel();
    }
  }

  /**
   * Jumps ot the end of the song.
   */
  @Override
  public void jumpToEnd() {
    currentBeat = model.maxBeatNum();
    nextBeat = currentBeat + 1;
    notesPanel.jumpToEnd();
    pianoPanel.jumpToEnd();
    drawBeatNumLabel();
  }

  /**
   * Jumps to the beginning of the song.
   */
  @Override
  public void jumpToBeginning() {
    currentBeat = 0;
    nextBeat = 1;
    notesPanel.jumpToBeginning();
    pianoPanel.jumpToBeginning();
    drawBeatNumLabel();
  }

  @Override
  public void increaseTempo() {
    this.model.setTempo(model.getTempo() - 1000);
  }

  @Override
  public void decreaseTempo() {
    this.model.setTempo(model.getTempo() + 1000);
  }

  @Override
  public void addNoteAt(MouseEvent me, MusicController cont, double holdTime) {

    int x = me.getX();
    int y = me.getY();

    int octave = pianoPanel.octInterpreter(x, y);

    String pitch = pianoPanel.pitchInterpreter(x, y);

    int duration = ((int) holdTime / model.getTempo()) + 1;
    cont.addNote(duration, octave, currentBeat, pitch);
    repaint();
    noteScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

  }

  /**
   * Gets the currentBeat of the song.
   *
   * @return the currentBeat.
   */
  @Override
  public int getCurrentBeat() {
    return currentBeat;
  }

  @Override
  public void addMouseListener(MouseListener ml) {
    pianoPanel.addMouseListener(ml);
  }


  private void drawBeatNumLabel() {

    beatNumLabel.removeAll();
    beatNumLabel.validate();
    int minNote = (currentBeat / 36) * 36;
    int maxNote = minNote + 32;

    beatNumLabel.setPreferredSize(new Dimension(1260, 60));
    beatNumLabel.setBackground(Color.lightGray);
    beatNumLabel.setVisible(true);
    for (int i = minNote; i <= maxNote; i += 4) {

      beatNumLabel.setLayout(null);
      JLabel beatNum = new JLabel(String.valueOf(i));
      beatNum.setBounds(85 + ((i % 36) * 33), 30, 40, 40);
      beatNumLabel.add(beatNum);
    }
    beatNumLabel.repaint();
  }
}
