package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

/**
 * This is the interface for the view of the music editor. Contains methods that allow for
 * multiple implementations of different GUI-based views.
 */
public interface IGUIView<K> extends IMusicView<K> {

  /**
   * method to provide scrolling to the left functionality.
   */
  void scrollLeft();

  /**
   * method to provide scrolling to the right functionality.
   */
  void scrollRight();

  /**
   * method to provide scrolling up functionality.
   */
  void scrollUp();

  /**
   * method to provide scrolling to the down functionality.
   */
  void scrollDown();

  /**
   * method to jump to the left most frame of the view.
   */
  void jumpBeginning();

  /**
   * method to jump to the right most frame of the view.
   */
  void jumpEnd();

  /**
   * method to add a key listener to the view.
   * @param l keyListener to be added
   */
  void addKeyListener(KeyListener l);

  /**
   * method to add mouse listener to the view.
   * @param l mouseListener to be added
   */
  void addMouseListener(MouseListener l);

  /**
   * Returns the panel that comprised the viewable area in this IGUIView.
   */
  JPanel getPanel();

  /**
   * getter for scoreBeats.
   * @return scoreBeats
   */
  int getScoreBeats();

}
