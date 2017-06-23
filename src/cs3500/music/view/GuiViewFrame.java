package cs3500.music.view;

import cs3500.music.controller.Controller;
import cs3500.music.controller.MusicController;
import cs3500.music.model.MusicOperations;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

/**
 * A frame to hold the various Panels that are apart of the GUI view.
 */
public class GuiViewFrame extends JFrame implements IGUIView {

  private final JPanel beatNumLabel;
  private final NotesPanel notesPanel;
  private final PianoPanel pianoPanel;
  private MusicOperations model;
  private int currentBeat;
  private boolean isPlaying;
  private JScrollPane noteScroll;

  /**
   * Creates new GuiView.
   *
   * <p> Sets the model, controller, and starting beat. Then creates the seperate panels
   * and orients the appropriately so they are all in their correct positions.</p>
   *
   */
  GuiViewFrame(MusicOperations model)  {
    this.model = model;


    this.currentBeat = 0;
    this.isPlaying = false;
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    beatNumLabel = new JPanel();
    drawBeatNumLabel();
    notesPanel = new NotesPanel(model);
    pianoPanel = new PianoPanel(model, notesPanel);
    noteScroll = new JScrollPane(notesPanel);
    noteScroll.setPreferredSize(new Dimension(1260, 440));
    setLayout(new BorderLayout());
    getContentPane().add(beatNumLabel,BorderLayout.NORTH);
    getContentPane().add(noteScroll, BorderLayout.CENTER);
    getContentPane().add(pianoPanel, BorderLayout.SOUTH);
    setSize(new Dimension(1260, 700));
    setResizable(false);
    new Controller(this, model);
  }



  @Override
  public void initialize() {
    this.setVisible(true);
  }


  @Override
  public void playPause() {
    isPlaying = !isPlaying;
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


  @Override
  public void scrollRight() {
    if (currentBeat < model.maxBeatNum()) {
      currentBeat++;
      pianoPanel.scrollRight();
      notesPanel.scrollRight();
      drawBeatNumLabel();
    }
  }

  @Override
  public void scrollLeft() {
    if (currentBeat > 0) {
      currentBeat--;
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
    notesPanel.jumpToBeginning();
    pianoPanel.jumpToBeginning();
    drawBeatNumLabel();
  }

  @Override
  public void addNoteAt(MouseEvent me, MusicController cont) {

    int x = me.getX();
    int y = me.getY();

    int octave = pianoPanel.octInterpreter(x, y);

    String pitch = pianoPanel.pitchInterpreter(x, y);

    cont.addNote(octave, currentBeat,pitch);
    repaint();
    noteScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

  }

  @Override
  public void addMouseListener(MouseListener ml) {
    pianoPanel.addMouseListener(ml);
  }
}
