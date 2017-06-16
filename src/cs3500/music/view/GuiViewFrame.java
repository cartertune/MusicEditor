package cs3500.music.view;

import cs3500.music.model.MusicOperations;
import java.awt.*;
import java.awt.event.MouseListener; // Possibly of interest for handling mouse events

import javafx.geometry.Orientation;
import javafx.scene.control.SplitPane;
import javax.swing.*;

/**
 * A skeleton Frame (i.e., a window) in Swing
 */
public class GuiViewFrame extends JFrame implements ViewInterface {

  private final JPanel beatNumLabel;
  private final NotesPanel notesPanel;
  private final PianoPanel pianoPanel;
  private MusicOperations model;

  /**
   * Creates new GuiView
   */
  public GuiViewFrame(MusicOperations model) {
    this.model = model;

    this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    beatNumLabel = new JPanel();
    beatNumLabel.setPreferredSize(new Dimension(1260, 60));
    beatNumLabel.setBackground(Color.lightGray);
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

  @Override
  public Dimension getPreferredSize(){
    return new Dimension(100, 100);
  }

  private String[] stringByBeat() {

    String fullNotation = model.getMIDINotation();
    return fullNotation.split("\n");

  }

  private String topLine() {

    return stringByBeat()[0];
  }

}
