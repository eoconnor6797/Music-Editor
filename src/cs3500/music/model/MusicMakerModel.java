package cs3500.music.model;

import java.util.ArrayList;

import cs3500.music.util.CompositionBuilder;


/**
 * This is an implementation of the IMusicEditorModel interface abstracted
 * over the MusicNote data type.
 * This model is meant as a basic representation of a score of music stored as a 2D ArrayList of
 * Notes where each row is a "beat" of the score containing all of the notes.
 */

public class MusicMakerModel implements IMusicMakerModel<MusicNote> {

  private ArrayList<ArrayList<MusicNote>> score = new ArrayList<>();
  private int tempo;

  /**
   * empty constructor for MusicMakerModel.
   */
  public MusicMakerModel() {
    // empty constructor
  }

  public MusicMakerModel(ArrayList<ArrayList<MusicNote>> score, int tempo) {
    this.score = score;
    this.tempo = tempo;
  }

  /**
   * gets the notes at a given beat.
   *
   * @param beat the beat to get the notes from.
   * @return an Arraylist<T> to represent the notes at that beat.
   */
  public ArrayList<MusicNote> getNotesAtBeat(int beat) {
    ArrayList<MusicNote> temp = new ArrayList<MusicNote>();
    if (score.size() < beat) {
      throw new IllegalArgumentException("Not a valid beat");
    }
    for (MusicNote note : score.get(beat)) {
      temp.add(note);
    }
    return temp;
  }

  /**
   * gets the music note of certain pitch at a given beat.
   * @param pitch the pitch to search for
   * @param beat the beat to search in.
   * @return the music note in the score matching the beat and note.
   */
  public MusicNote getNote(int pitch, int beat) {
    ArrayList<MusicNote> temp = this.getNotesAtBeat(beat);
    for (MusicNote note : temp) {
      if (note.getPitch() + note.getOctaveVal() == pitch) {
        return note;
      }
    }
    throw new IllegalArgumentException("No note was found.");
  }

  /**
   * method to add notes to a musicmodel.
   *
   * @param note the note to be added.
   * @param beat the beat at which to add the note.
   */
  public void addNote(MusicNote note, int beat) {
    // if the beat
    if (beat < 0) {
      throw new IllegalArgumentException("Not a valid beat");
    }
    if (note == null) {
      throw new IllegalArgumentException("Not a valid note");
    }
    if (score.size() < (beat + note.getDuration())) {
      this.extendScore(score, beat + note.getDuration());
    }
    score.get(beat).add(note);
    for (int ii = 1; ii < note.getDuration(); ii++) {
      score.get(beat + ii).add(note.makeSustained().decrimentDuration());
    }
  }

  /**
   * remove a note form the music model.
   *
   * @param note note the be removed.
   * @param beat beat a which to remove said note.
   */
  public void removeNote(MusicNote note, int beat) {
    if (score.size() < beat) {
      throw new IllegalArgumentException("Not a valid beat");
    }
    if (note == null) {
      throw new IllegalArgumentException("Not a valid note");
    }
    if (!note.getHead()) {
      throw new IllegalArgumentException("Can only remove from the start of a note.");
    }
    for (MusicNote n : score.get(beat)) {
      if (n.equals(note)) {
        score.get(beat).remove(n);
        for (int ii = 1; ii < note.getDuration(); ii++) {
          score.get(ii + beat).remove(n.makeSustained().decrimentDuration());
        }
        return;
      }
    }
    throw new IllegalArgumentException("Cannot find note.");
  }

  /**
   * changes note at given beat to a new note.
   *
   * @param removenote note to be changed
   * @param addnote    note to be changed to
   * @param beat       beat in which to preform change.
   */
  public void editNote(MusicNote removenote, MusicNote addnote, int beat) {
    this.removeNote(removenote, beat);
    this.addNote(addnote, beat);
  }

  /**
   * combines two pieces of music to either play simultaneously or one after the other.
   *
   * @param music          the music to be added.
   * @param simultaneously if true play simultaneously otherwise one after the other.
   */
  public void combine(IObservableMusicMakerModel<MusicNote> music, boolean simultaneously) {
    if (music == null) {
      throw new IllegalArgumentException("Not a valid score");
    }
    ArrayList<ArrayList<MusicNote>> musicPiece = music.getScore();
    if (simultaneously) {
      for (int ii = 0; ii < musicPiece.size(); ii++) {
        for (MusicNote note : musicPiece.get(ii)) {
          this.score.get(ii).add(note);
        }
      }
    } else {
      ArrayList<ArrayList<MusicNote>> temp = music.getScore();
      this.score.addAll(temp);
    }
  }

  /**
   * extends the score until it is a certain number of beats long.
   *
   * @param score the score to be extended.
   * @param x     how long the score will be when it is finished.
   */
  private static void extendScore(ArrayList<ArrayList<MusicNote>> score, int x) {
    if (x < 0) {
      return;
    }
    while (score.size() < x) {
      score.add(new ArrayList<MusicNote>());
    }
  }

  /**
   * gets the entire score.
   * each ArrayList<T> is the notes at that beat in the score.
   *
   * @return an ArrayList of Array list of MusicNotes represents the score.
   */
  public ArrayList<ArrayList<MusicNote>> getScore() {
    ArrayList<ArrayList<MusicNote>> temp = new ArrayList<ArrayList<MusicNote>>();
    for (ArrayList<MusicNote> beat : score) {
      ArrayList<MusicNote> temp2 = new ArrayList<MusicNote>();
      for (MusicNote note : beat) {
        temp2.add(note);
      }
      temp.add(temp2);
    }
    return temp;
  }

  /**
   * get the lowest note in a score.
   *
   * @param score the score to be checked.
   * @return the value of the lowest note.
   */
  private static int getMinNote(ArrayList<ArrayList<MusicNote>> score) {
    int min = Integer.MAX_VALUE;
    for (ArrayList<MusicNote> beat : score) {
      for (MusicNote note : beat) {
        if (note.getOctaveVal() + note.getPitch() < min) {
          min = note.getOctaveVal() + note.getPitch();
        }
      }
    }
    return min;
  }

  /**
   * get the highest note in a score.
   *
   * @param score the score to be checked.
   * @return the value of the highest note.
   */
  private static int getMaxNote(ArrayList<ArrayList<MusicNote>> score) {
    int max = -1;
    for (ArrayList<MusicNote> beat : score) {
      for (MusicNote note : beat) {
        if (note.getOctaveVal() + note.getPitch() > max) {
          max = note.getOctaveVal() + note.getPitch();
        }
      }
    }
    return max;
  }

  /**
   * gets the music notes in a range.
   *
   * @return an ArrayList<MusicNotes>  of notes in a range.
   */
  public ArrayList<MusicNote> genRange() {
    int min = this.getMinNote(score);
    int max = this.getMaxNote(score);
    ArrayList<MusicNote> temp = new ArrayList<MusicNote>();
    for (int ii = min; ii <= max; ii++) {
      int temp1 = ii % 12;
      int temp2 = ii / 12;
      switch (temp1) {
        case 9:
          temp.add(new MusicNote(Pitch.A, 1, temp2, true));
          break;
        case 10:
          temp.add(new MusicNote(Pitch.Asharp, 1, temp2, true));
          break;
        case 11:
          temp.add(new MusicNote(Pitch.B, 1, temp2, true));
          break;
        case 0:
          temp.add(new MusicNote(Pitch.C, 1, temp2, true));
          break;
        case 1:
          temp.add(new MusicNote(Pitch.Csharp, 1, temp2, true));
          break;
        case 2:
          temp.add(new MusicNote(Pitch.D, 1, temp2, true));
          break;
        case 3:
          temp.add(new MusicNote(Pitch.Dsharp, 1, temp2, true));
          break;
        case 4:
          temp.add(new MusicNote(Pitch.E, 1, temp2, true));
          break;
        case 5:
          temp.add(new MusicNote(Pitch.F, 1, temp2, true));
          break;
        case 6:
          temp.add(new MusicNote(Pitch.Fsharp, 1, temp2, true));
          break;
        case 7:
          temp.add(new MusicNote(Pitch.G, 1, temp2, true));
          break;
        case 8:
          temp.add(new MusicNote(Pitch.Gsharp, 1, temp2, true));
          break;
        default:
      }
    }
    return temp;
  }

  public int getTempo() {
    return tempo;
  }

  /**
   * Composition builder for MusicMakerModel.
   * Constructs a music builder for paramaterized over the MusicMakerModel type.
   * Contains methods that build a music piece from input.
   */
  public static final class MusicBuilder implements CompositionBuilder<MusicMakerModel> {
    private ArrayList<ArrayList<MusicNote>> score = new ArrayList<>();
    private int tempo;

    @Override
    public MusicMakerModel build() {
      return new MusicMakerModel(score, tempo);
    }

    @Override
    public CompositionBuilder<MusicMakerModel> setTempo(int tempo) {
      this.tempo = tempo;
      return this;
    }

    @Override
    public CompositionBuilder<MusicMakerModel> addNote(int start, int end,
                                                       int instrument, int pitch, int volume) {
      if (pitch > 127) {
        throw new IllegalArgumentException("Not a valid pitch");
      }
      if (start < 0) {
        throw new IllegalArgumentException("Not a valid beat");
      }
      if (score.size() < (start + (end - start))) {
        int x = start + (end - start);
        while (score.size() < x) {
          score.add(new ArrayList<MusicNote>());
        }
      }
      MusicNote temp = new MusicNote(pitch, (end - start), true, instrument, volume);
      score.get(start).add(temp);
      for (int ii = 1; ii < temp.getDuration(); ii++) {
        score.get(start + ii).add(temp.makeSustained().decrimentDuration());
      }
      return this;
    }
  }

}
