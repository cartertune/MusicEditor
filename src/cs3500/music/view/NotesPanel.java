package cs3500.music.view;

import cs3500.music.model.INote;
import cs3500.music.model.MusicOperations;
import cs3500.music.model.Repeat;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * A panel for the GUIView that shows all the notes in sequential order on the top of the screen.
 */
public class NotesPanel extends JPanel {


  private final MusicOperations model;
  private int currentBeat;
  private int nextBeat;

  private int panelHeight = 500;
  private int beatHeight = 22;
  private final int panelWidth = 1240;
  private final int notesLabelWidth = 85;
  private final int beatWidth = 33;


  /**
   * Initiates the NotePanel to represent the given model.
   */
  NotesPanel(MusicOperations model) {

    this.model = model;
    this.currentBeat = 0;
    this.nextBeat = 1;
    setPreferredSize(new Dimension(panelWidth, panelHeight));
    setBackground(Color.white);
  }

  @Override
  protected void paintComponent(Graphics g) {

    panelHeight = (1 + model.maxNoteValue() - model.minNoteValue()) * beatHeight;
    this.setPreferredSize(new Dimension(panelWidth, panelHeight));
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
    int xposn = notesLabelWidth + ((currentBeat % 36) * beatWidth);
    g.drawLine(xposn, 0, xposn, panelHeight);
  }

  private void drawNotes(Graphics g) {

    int beatStart = (currentBeat / 36) * 36;
    int beatEnd = beatStart + 36;
    for (int beat = beatStart; beat < beatEnd; beat++) {

      ArrayList<INote> currNotes = model.getNotesAt(beat);

      int xposnBlk = notesLabelWidth + ((beat % 36) * beatWidth);
      int xposnRed = xposnBlk + beatWidth;

      for (INote note : currNotes) {
        g.setColor(Color.black);
        g.fillRect(xposnBlk, (model.maxNoteValue() - note.value()) * beatHeight,
            beatWidth, beatHeight);
        g.setColor(Color.ORANGE);
        g.fillRect(xposnRed, (model.maxNoteValue() - note.value()) * beatHeight,
            (beatWidth * (note.getDuration() - 1)), beatHeight);
      }
    }
  }

  private void drawNoteLabels(Graphics g) {

    ArrayList<String> noteLabels = new ArrayList<>();

    for (int i = model.maxNoteValue(); i >= model.minNoteValue(); i--) {

      String oct = String.valueOf((i / 12) - 1);
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

    int numLines = model.maxNoteValue() - model.minNoteValue() + 1;
    g.setColor(Color.black);
    for (int y = beatHeight; y < beatHeight * numLines; y += beatHeight) {

      g.drawLine(notesLabelWidth, y, panelWidth, y);
    }
  }

  private void drawMeasureLines(Graphics g) {

    for (int i = 0; i < model.maxBeatNum(); i += 4) {

      g.drawLine(notesLabelWidth + i * beatWidth, 0,
          notesLabelWidth + i * beatWidth, panelHeight);
    }
  }

  void scrollRight() {
    setCurrentAndNextBeat(nextBeat);
    repaint();
  }

  private void setCurrentAndNextBeat(int beat) {
    this.currentBeat = beat;

    if (model.hasRepeatAt(beat)) {
      nextBeat = model.getRepeatAt(beat).goTo();
    } else {
      nextBeat = currentBeat + 1;
    }
  }

  void scrollLeft() {
    currentBeat--;
    nextBeat--;
    repaint();
  }

  void jumpToEnd() {
    currentBeat = model.maxBeatNum();
    nextBeat = currentBeat + 1;
    repaint();
  }

  void jumpToBeginning() {
    currentBeat = 0;
    nextBeat = 1;
    repaint();
  }
}
