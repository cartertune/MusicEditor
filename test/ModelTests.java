import static junit.framework.TestCase.assertEquals;

import cs3500.music.model.MusicModel;
import cs3500.music.model.Note;
import cs3500.music.model.Pitch;
import cs3500.music.model.Sound;

import org.junit.Test;

/**
 * A class to test the model.
 */
public class ModelTests {

  @Test
  public void testAddAndRemoveNote() {
    MusicModel model = new MusicModel();

    model.addNote(4, 8, 1, "CSHARP");

    assertEquals(true, model.getMIDINotation().contains("C#8")
        && model.getMIDINotation().contains("4"));

    model.addNote(2, 7, 5, "B");

    System.out.print(model.getMIDINotation());
  }

  /**
   * Tests that an error is thrown when illegally trying to remove a note.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testIllegalRemove() {
    MusicModel model = new MusicModel();

    Sound s1 = new Sound(25);
    Sound s2 = new Sound(45);
    Sound s3 = new Sound(33);
    Note n1 = new Note(s1, 2);
    Note n2 = new Note(s2, 1);
    Note n3 = new Note(s3, 4);

    model.removeNote(54, "ASHARP", 5);

  }

  /**
   * Ensures an error is thrown if given an invalid octave.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOctaveLimit() {

    MusicModel model = new MusicModel();

    new Sound(400);
  }

  /**
   * Ensures an error is thrown if given an invalid octave.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOctaveLimit2() {

    MusicModel model = new MusicModel();

    new Sound(Pitch.ASHARP, 40);
  }

  /**
   * Ensures an error is thrown if given an invalid octave.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testOctaveLimit3() {

    MusicModel model = new MusicModel();

    new Sound(-1);

  }


  /**
   * Tests to ensure getMIDINotation returns as expected and has equal length lines.
   */
  @Test
  public void testGetMIDINotation() {

    MusicModel model = new MusicModel();

    model.addNote(1, 1, 1, "C");

    System.out.print(model.getMIDINotation());

    String resultNotation = model.getMIDINotation();

    assertEquals(true, resultNotation
        .contains("C1") && !resultNotation.contains("C#"));
    assertEquals(true, resultNotation
        .contains("1") && !resultNotation.contains("5"));

    assertEquals(true, allLinesSameLength(stringSplitByLine(resultNotation)));
    System.out.print(resultNotation);
  }

  private String[] stringSplitByLine(String s) {

    return s.split("\n");
  }

  /**
   * Ensures every string in array is same length.
   *
   * @param stringArray the array to check
   * @return true if all strings are same length
   */
  private boolean allLinesSameLength(String... stringArray) {

    int len = stringArray[0].length();
    for (String s : stringArray) {
      if (s.length() != len) {
        return false;
      }
    }
    return true;
  }
}
