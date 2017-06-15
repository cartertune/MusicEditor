


class Beat:
  - Represents the Sound played at a single beat and whether it is the beginning of a note
  or a note sustained.

class BeatNotations:

  - Represents all the Beat at a single beat number. aka all the Beat played at the same time.

Enum BeatType:

  - Distinguishes a beat between the beginning of a Note or a sustained Note.

Enum Duration:

  -Represents the 3 different allowable durations for a note: a quarter, half, and whole note.

class EditDuration implements INoteEditor:

  -Used to edit a Duration of a given Note.

class EditOctave implements INoteEditor:

  -Used to edit the octave of a given Note.

class EditPitch implements INoteEditor:

  -Used to edit the Pitch of a given Note.

Interface INoteEditor:

  -An interface that has a function edit(Note), which does as expected; it edits a note.

class MainMusic:

   -A class to demonstrate a basic piece being displayed on console.

class Music Model implenents MusicOperations:

  -Used to implement methods from interface.


	FIELD: 

		- Piece

interface MusicOperations:

  -Represents the operations guaranteed by the model as follows.


	METHODS:

		-addNote(Note, int) //adds a note at given beat number.

		-removeNote //removes a specified note and returns it

		-editNote //which edits a note in different ways depending on the INoteEditor it was given.

		-mergePiece // merges a given piece with the current one.
		
		-addPiece // adds the given piece at the end of the current one.

		-getMIDINotation //returns the song as MIDI Notation in String form

class Notations:

  -Represents the notes that start at the same beat number in a piece. (a List of Note).

class Note:

  -Represents a note in the music editor with a duration.
	
	FIELDS: 
		-Sound // the octave and pitch of the note.
		-Duration // the duration a note is played.

class Piece:

  - Represents a song by using a HashMap<Integer, Notations> where the Integer is the beat number
      and the Notations are all the Notes at that beat number.

class Sound:

  - Represents a Sound. aka a note without a specified duration.