package cs3500.music.model;

import java.util.ArrayList;

/**
 * This is the interface of the ObservableMusicMaker model. It is parameterized over the
 * MusicNote type.
 * This is an interface that allows the music to be viewed but not changed.
 */
public interface IObservableMusicMakerModel<T> {

  /**
   * gets the notes at a given beat.
   *
   * @param beat the beat to get the notes from.
   * @return an Arraylist<T></T> to represent the notes at that beat.
   */
  ArrayList<T> getNotesAtBeat(int beat);

  /**
   * gets the entire score;
   * each ArrayList<T></T> is the notes at that beat in the score;
   *
   * @return an ArrayList of ArrayList<T></T> to represents the score.
   */
  ArrayList<ArrayList<T>> getScore();

  /**
   * generates the range of notes being played in a particular music piece.
   *
   * @return an arraylist containing the range of notes.
   */
  ArrayList<T> genRange();

  /**
   * returns the tempo of the music piece.
   *
   * @return the tempo of the music piece.
   */
  int getTempo();

  /**
   * gets the music note of certain pitch at a given beat.
   * @param pitch the pitch to search for
   * @param beat the beat to search in.
   * @return the music note in the score matching the beat and note.
   */
  MusicNote getNote(int pitch, int beat);
}
