package cs3500.music.model;

/**
 * This is the interface for a Music Editor Model. The model is meant to store some data type K
 * which represents the notes in a score of music. Notes of K type can be added to,
 * removed from, and edited within the score. The score can also be played back
 * consecutively or simultaneously with the score of another model and returned as a string.
 * contains various other methods to return data that may be relevant to the view or controller.
 */
public interface IMusicMakerModel<T> extends IObservableMusicMakerModel<T> {

  /**
   * method to add notes to a musicmodel.
   *
   * @param note the note to be added.
   * @param beat the beat at which to add the note.
   */
  void addNote(T note, int beat);

  /**
   * remove a note form the music model.
   *
   * @param note note the be removed.
   * @param beat beat a which to remove said note.
   */
  void removeNote(T note, int beat);

  /**
   * changes note at given beat to a new note.
   *
   * @param removenote note to be changed
   * @param addnote    note to be changed to
   * @param beat       beat in which to preform change.
   */
  void editNote(T removenote, T addnote, int beat);

  /**
   * combines two pieces of music to either play simultaneously or one after the other.
   *
   * @param music          the music to be added.
   * @param simultaneously if true play simultaneously otherwise one after the other.
   */
  void combine(IObservableMusicMakerModel<T> music, boolean simultaneously);


}
