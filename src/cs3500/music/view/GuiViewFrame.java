package cs3500.music.view;

import cs3500.music.controller.GUIController;
import cs3500.music.model.MusicOperations;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener; // Possibly of interest for handling mouse events

import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import javax.swing.*;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends JFrame implements IGUIView {

  private final JPanel beatNumLabel;
  private final NotesPanel notesPanel;
  private final PianoPanel pianoPanel;
  private MusicOperations model;
  private int currentBeat;
  private GUIController controller;

  /**
   * Creates new GuiView
   */
  public GuiViewFrame(MusicOperations model, GUIController controller) {
    this.model = model;
    this.controller = controller;

    this.currentBeat = 0;
    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    beatNumLabel = new JPanel();
    beatNumLabel.setPreferredSize(new Dimension(1260, 60));
    beatNumLabel.setBackground(Color.lightGray);
    drawBeatNumLabel();
    beatNumLabel.setVisible(true);

    notesPanel = new NotesPanel(model);
    pianoPanel = new PianoPanel(model, notesPanel);
    JScrollPane noteScroll = new JScrollPane(notesPanel);
    noteScroll.setPreferredSize(new Dimension(1260, 440));
    setLayout(new BorderLayout());
    getContentPane().add(beatNumLabel,BorderLayout.NORTH);
    getContentPane().add(noteScroll, BorderLayout.CENTER);
    getContentPane().add(pianoPanel, BorderLayout.SOUTH);
    setSize(new Dimension(1260, 700));
    setResizable(false);
  }



  @Override
  public void initialize(){
    this.setVisible(true);
  }



  private void drawBeatNumLabel() {

    for (int i = 0; i < 35; i += 4) {

      beatNumLabel.setLayout(null);
      JLabel beatNum = new JLabel(String.valueOf(i));
      beatNum.setBounds(85 + (i * 33), 30, 40, 40);
      beatNumLabel.add(beatNum);
    }
  }


  @Override
  public void moveRight() {
    if (currentBeat < model.maxBeatNum()) {
      currentBeat++;
      pianoPanel.moveRight();
      notesPanel.moveRight();
    }
  }

  @Override
  public void moveLeft() {
    if (currentBeat > 0) {
      currentBeat--;
      pianoPanel.moveLeft();
      notesPanel.moveLeft();
    }
  }


  public void setController(GUIController controller) {
    this.controller = controller;
  }
}
