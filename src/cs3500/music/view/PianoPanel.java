package cs3500.music.view;

import cs3500.music.model.INote;
import cs3500.music.model.MusicOperations;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * A class to represent the keyboard displayed at the bottom of the GUIView.
 */
public class PianoPanel extends JPanel {


  private final MusicOperations model;
  private int currentBeat;

  private final int width = 1260;
  private final int height = 200;
  private final int keyWidth = 18;

  /**
   * Initializes the piano to be able to respond according to given mode.
   */
  PianoPanel(MusicOperations model, NotesPanel notesPanel) {

    this.currentBeat = 0;
    this.model = model;
    setPreferredSize(new Dimension(width, height));
    setBackground(Color.black);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    for (int oct = 1; oct <= 10; oct++) {

      drawKeys(oct, g);
    }
  }



  private void drawKeys(int oct, Graphics g) {

    int xposn = ((oct - 1) * 7) * keyWidth;
    for (int pitch = 0; pitch <= 11; pitch++) {

      int value = ((oct - 1) * 12) + pitch;

      if (isSharp(pitch)) {
        xposn = xposn - (keyWidth / 2);
        if (isPlayed(value)) {
          g.setColor(Color.cyan);
        } else {
          g.setColor(Color.black);
        }
        g.fillRect(xposn, 0, keyWidth - 2, height / 2);
        xposn += (keyWidth / 2);
      } else {

        if (isPlayed(value)) {
          g.setColor(Color.cyan);
        } else {
          g.setColor(Color.white);
        }
        g.fillRect(xposn, 0, keyWidth - 2, height);
        xposn += keyWidth;
      }
    }
  }

  private boolean isPlayed(int value) {

    for (int i = 0; i <= currentBeat; i++) {

      ArrayList<INote> notes = model.getNotesAt(i);

      for (INote n : notes) {
        if ((n.value() == value) && n.getDuration() > currentBeat - i) {

          return true;
        }
      }
    }
    return false;
  }

  private boolean isSharp(int p) {

    return p == 1 || p == 3 || p == 6 || p == 8 || p == 10;
  }

  void scrollRight() {
    currentBeat++;
    repaint();
  }

  void scrollLeft() {
    currentBeat--;
    repaint();
  }

  void jumpToBeginning() {
    currentBeat = 0;
    repaint();
  }

  void jumpToEnd() {

    currentBeat = model.maxBeatNum();
    repaint();
  }

  // TODO: 6/21/17
  public int noteDecifer(Point point) {
    return 0;
  }
}
