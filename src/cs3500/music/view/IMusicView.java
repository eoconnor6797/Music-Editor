package cs3500.music.view;


import cs3500.music.model.IObservableMusicMakerModel;

/**
 * This is the interface for the view of the music editor. Contains methods that allow for
 * multiple implementations of different views.
 */
public interface IMusicView<K> {
  /**
   * Constructs a view from the information given by the controller.
   */
  void render();

  /**
   * method to get the tempo of the view.
   * @return the tempo of the view.
   */
  int getTempo();

  /**
   * method to set the tempo of a view.
   * @param tempo the song tempo
   */
  void setTempo(int tempo);

  /**
   * method to pause a view.
   */
  void pause();

  /**
   * method to play a view.
   */
  void play();

  /**
   * Updates the information that the render method needs to display the correct content.
   *
   * @param readOnlyModel is a read-only model to receive information from.
   */
  void update(IObservableMusicMakerModel<K> readOnlyModel);
}