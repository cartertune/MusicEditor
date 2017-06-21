package cs3500.music.view;

import cs3500.music.model.INote;
import cs3500.music.model.MusicOperations;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * A panel for the GUIView that shows all the notes in sequential order on the top of the screen.
 */
public class NotesPanel extends JPanel {


  private final MusicOperations model;
  private int currentBeat;

  private int panelHeight = 440;
  private int beatHeight = 22;
  private final int panelWidth = 1240;
  private final int notesLabelWidth = 85;
  private final int beatWidth = 33;
  private final int maxBeats = 35;
  private int minNoteVal;
  private int maxNoteVal;

  /**
   * Initiates the NotePanel to represent the given model.
   */
  NotesPanel(MusicOperations model) {

    this.model = model;
    this.currentBeat = 0;
    this.minNoteVal = model.minNoteValue();
    this.maxNoteVal = model.maxNoteValue();
    panelHeight = (1 + maxNoteVal - minNoteVal) * beatHeight;
    setPreferredSize(new Dimension(panelWidth, panelHeight));
    setBackground(Color.white);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    g.setColor(Color.black);
    g.drawRect(notesLabelWidth, 0, panelWidth - notesLabelWidth, panelHeight);
    drawNoteLines(g);
    drawMeasureLines(g);
    drawNoteLabels(g);
    drawNotes(g);
    drawBeatTracker(g);
  }

  private void drawBeatTracker(Graphics g) {
    g.setColor(Color.red);
    int xposn = notesLabelWidth + (currentBeat * beatWidth);
    g.drawLine(xposn, 0, xposn, panelHeight);
  }

  private void drawNotes(Graphics g) {

    for (int beat = 0; beat < maxBeats; beat++) {

      ArrayList<INote> currNotes = model.getNotesAt(beat);

      int xposnBlk = notesLabelWidth + (beat * beatWidth);
      int xposnRed = xposnBlk + beatWidth;

      for (INote note : currNotes) {
        g.setColor(Color.black);
        g.fillRect(xposnBlk, (maxNoteVal - note.value()) * beatHeight, beatWidth, beatHeight);
        g.setColor(Color.ORANGE);
        g.fillRect(xposnRed, (maxNoteVal - note.value()) * beatHeight,
            (beatWidth * (note.getDuration() - 1)), beatHeight);
      }
    }
  }

  private void drawNoteLabels(Graphics g) {

    ArrayList<String> noteLabels = new ArrayList<>();

    for (int i = maxNoteVal; i >= minNoteVal; i--) {

      String oct = String.valueOf((i / 12) + 1);
      String pitch = pitchString(i % 12);

      noteLabels.add(pitch + oct);
    }

    for (int j = 0; j < noteLabels.size(); j++) {

      g.drawString(noteLabels.get(j),60, ((j + 1) * beatHeight) - 2);
    }
  }

  private String pitchString(int p) {

    switch (p) {

      case 0:
        return "C";
      case 1:
        return "C#";
      case 2:
        return "D";
      case 3:
        return "D#";
      case 4:
        return "E";
      case 5:
        return "F";
      case 6:
        return "F#";
      case 7:
        return "G";
      case 8:
        return "G#";
      case 9:
        return "A";
      case 10:
        return "A#";
      case 11:
        return "B";
      default:
        return "ERR";
    }
  }

  private void drawNoteLines(Graphics g) {

    int numLines = maxNoteVal - minNoteVal + 1;
    g.setColor(Color.black);
    for (int y = beatHeight; y < beatHeight * numLines; y += beatHeight) {

      g.drawLine(notesLabelWidth, y, panelWidth, y);
    }
  }

  private void drawMeasureLines(Graphics g) {

    for (int i = 0; i < maxBeats; i += 4) {

      g.drawLine(notesLabelWidth + i * beatWidth, 0,
          notesLabelWidth + i * beatWidth, panelHeight);
    }
  }

  void scrollRight() {
    currentBeat++;
    repaint();
  }

  void scrollLeft() {
    currentBeat--;
    repaint();
  }

  void jumpToEnd() {
    currentBeat = model.maxBeatNum();
    repaint();
  }

  void jumpToBeginning() {
    currentBeat = 0;
    repaint();
  }
}
