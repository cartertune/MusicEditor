import static junit.framework.TestCase.assertEquals;

import cs3500.music.model.EditDuration;
import cs3500.music.model.EditOctave;
import cs3500.music.model.EditPitch;
import cs3500.music.model.MusicModel;
import cs3500.music.model.Notations;
import cs3500.music.model.Note;
import cs3500.music.model.Piece;
import cs3500.music.model.Pitch;
import cs3500.music.model.Sound;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

/**
 * A class to test the model.
 */
public class ModelTests {

  @Test
  public void testAddAndRemoveNote() {
    MusicModel model = new MusicModel();

    model.addNote(4, 8,1,"CSHARP");


    assertEquals(true, model.getMIDINotation().contains("C#8")
        && model.getMIDINotation().contains("4"));

    model.addNote(2, 7, 5, "B");

    System.out.print(model.getMIDINotation());
  }

//  @Test(expected = IllegalArgumentException.class)
//  public void testIllegalRemove() {
//    MusicModel model = new MusicModel();
//
//    Sound s1 = new Sound(25);
//    Sound s2 = new Sound(45);
//    Sound s3 = new Sound(33);
//    Note n1 = new Note(s1, 2);
//    Note n2 = new Note(s2, 1);
//    Note n3 = new Note(s3, 4);
//    model.addNote(n2, 0);
//    model.addNote(n2, 54);
//    model.addNote(n3, 17);
//    model.addNote(n3, 12);
//    model.addNote(n1, 64);
//
//    model.removeNote(54, Pitch.ASHARP, 5);
//
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void testOctaveLimit() {
//
//    MusicModel model = new MusicModel();
//
//    new Sound(400);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void testOctaveLimit2() {
//
//    MusicModel model = new MusicModel();
//
//    new Sound(Pitch.ASHARP, 40);
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void testOctaveLimit3() {
//
//    MusicModel model = new MusicModel();
//
//    new Sound(-1);
//
//  }
//
//  @Test
//  public void testEditNote() {
//
//    MusicModel model = new MusicModel();
//
//    Sound s1 = new Sound(25);
//    Sound s2 = new Sound(45);
//    Note n1 = new Note(s1, 2);
//    Note n2 = new Note(s2, 1);
//    model.addNote(n1, 0);
//    model.addNote(n2, 0);
//    model.addNote(n2, 54);
//
//    model.editNote(new EditDuration(4), 0,
//        s1.getPitch(), s1.getOctave());
//
//    assertEquals(4, n1.getDuration());
//
//    model.editNote(new EditOctave(10), 0, s2.getPitch(), s2.getOctave());
//
//    assertEquals(10, s2.getOctave());
//
//    model.editNote(new EditPitch(Pitch.CSHARP), 54, s2.getPitch(), s2.getOctave());
//
//    assertEquals(Pitch.CSHARP, s2.getPitch());
//  }
//
//  @Test(expected = IllegalArgumentException.class)
//  public void testInvalidEditOctave() {
//
//    MusicModel model = new MusicModel();
//
//    Sound s1 = new Sound(25);
//    Sound s2 = new Sound(45);
//    Sound s3 = new Sound(33);
//    Note n1 = new Note(s1, 2);
//    Note n2 = new Note(s2, 1);
//    Note n3 = new Note(s3, 4);
//    model.addNote(n1, 0);
//    model.addNote(n2, 0);
//    model.addNote(n2, 54);
//    model.addNote(n3, 17);
//    model.addNote(n3, 12);
//    model.addNote(n1, 64);
//
//    model.editNote(new EditOctave(15), 0, s1.getPitch(), s1.getOctave());
//  }
//
//  @Test
//  public void testMergePiece() {
//
//    MusicModel model = new MusicModel();
//    Sound s1 = new Sound(25);
//    Sound s2 = new Sound(45);
//    Sound s3 = new Sound(33);
//    Note n1 = new Note(s1, 2);
//    Note n2 = new Note(s2, 1);
//    Note n3 = new Note(s3, 4);
//    Note n4 = new Note(s2, 4);
//    model.addNote(n1, 0);
//    model.addNote(n2, 0);
//    model.addNote(n2, 54);
//    model.addNote(n1, 64);
//
//    ArrayList<Note> noteArrayList = new ArrayList<>();
//    noteArrayList.add(n3);
//    noteArrayList.add(n4);
//    Notations notations = new Notations(noteArrayList);
//    HashMap<Integer, Notations> map = new HashMap<>();
//    map.put(5, notations);
//    Piece p = new Piece(map);
//    model.mergePiece(p);
//
//    assertEquals(n3, model.removeNote(5, s3.getPitch(), s3.getOctave()));
//
//  }
//
//  @Test
//  public void testAddPiece() {
//
//    MusicModel model = new MusicModel();
//    Sound s1 = new Sound(25);
//    Sound s2 = new Sound(45);
//    Sound s3 = new Sound(33);
//    Note n1 = new Note(s1, 2);
//    Note n2 = new Note(s2, 1);
//    Note n3 = new Note(s3, 4);
//    Note n4 = new Note(s2, 4);
//    model.addNote(n1, 0);
//    model.addNote(n2, 0);
//    model.addNote(n2, 54);
//    model.addNote(n1, 64);
//
//    ArrayList<Note> noteArrayList = new ArrayList<>();
//    noteArrayList.add(n3);
//    noteArrayList.add(n4);
//    Notations notations = new Notations(noteArrayList);
//    HashMap<Integer, Notations> map = new HashMap<>();
//    map.put(5, notations);
//    Piece p = new Piece(map);
//    model.addPiece(p);
//
//    assertEquals(n3, model.removeNote(73, s3.getPitch(), s3.getOctave()));
//
//  }
//
//  @Test
//  public void testGetMIDINotation() {
//
//    MusicModel model = new MusicModel();
//
//    Sound s1 = new Sound(25);
//    Sound s2 = new Sound(45);
//    Sound s3 = new Sound(33);
//
//    Note n1 = new Note(s1, 2);
//    Note n2 = new Note(s2, 1);
//    Note n3 = new Note(s3, 4);
//
//    model.addNote(n1, 0);
//
//    model.addNote(n2, 54);
//    model.addNote(n3, 17);
//    model.addNote(n3, 12);
//    model.addNote(n1, 64);
//
//    String expectedTopLine =
//        "   A#2   B2  B#2   C3  C#3   D3  D#3   E3  E#3   F3  F#3   G3  G#3   A3  "
//            + "A#3   B3  B#3   C4  C#4   D4  D#4 \n";
//    String resultNotation = model.getMIDINotation();
//    assertEquals(true, resultNotation.startsWith(expectedTopLine));
//
//    assertEquals(true, resultNotation
//        .contains("67") && !resultNotation.contains("68"));
//
//    assertEquals(true, allLinesSameLength(stringSplitByLine(resultNotation)));
//    System.out.print(resultNotation);
//  }
//
//  private String[] stringSplitByLine(String s) {
//
//    return s.split("\n");
//  }
//
//  /**
//   * Ensures every string in array is same length.
//   *
//   * @param stringArray the array to check
//   * @return true if all strings are same length
//   */
//  private boolean allLinesSameLength(String... stringArray) {
//
//    int len = stringArray[0].length();
//    for (String s : stringArray) {
//      if (s.length() != len) {
//        return false;
//      }
//    }
//    return true;
//  }
}
