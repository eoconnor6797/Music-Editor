package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This is the class that handles keyboard input from the user. It implements the KeyListener Java
 * class.
 */
public class KeyboardHandler implements KeyListener {
  private Map<Integer, Runnable> typed;
  private Map<Integer, Runnable> pressed;
  private Map<Integer, Runnable> released;

  /**
   * Creates a new instance of a KeyboardHandler object.
   */
  public KeyboardHandler() {
    this.typed = new HashMap<>();
    this.pressed = new HashMap<>();
    this.released = new HashMap<>();
  }

  @Override
  public void keyTyped(KeyEvent e) {
    Objects.requireNonNull(e);
    if (this.typed.containsKey(e.getKeyCode())) {
      this.typed.get(e.getKeyCode()).run();
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    Objects.requireNonNull(e);
    if (this.pressed.containsKey(e.getKeyCode())) {
      this.pressed.get(e.getKeyCode()).run();
    }
  }

  @Override
  public void keyReleased(KeyEvent e) {
    Objects.requireNonNull(e);
    if (this.released.containsKey(e.getKeyCode())) {
      this.released.get(e.getKeyCode()).run();
    }
  }

  /**
   * Creates a new mapping of a Runnable keyTyped action to they key on the keyboard with the given
   * keyCode.
   *
   * @param keyCode the int value of the key
   * @param action the action associated with the key
   */
  public void addKeyTyped(int keyCode, Runnable action) {
    Objects.requireNonNull(action);
    this.typed.put(keyCode, action);
  }

  /**
   * Creates a new mapping of a Runnable keyPressed action to they key on the keyboard with the
   * given keyCode.
   *
   * @param keyCode the int value of the key
   * @param action the action associated with the key
   */
  public void addKeyPressed(int keyCode, Runnable action) {
    Objects.requireNonNull(action);
    this.pressed.put(keyCode, action);
  }

  /**
   * Creates a new mapping of a Runnable keyReleased action to they key on the keyboard with the
   * given keyCode.
   *
   * @param keyCode the int value of the key
   * @param action the action associated with the key
   */
  public void addKeyReleased(int keyCode, Runnable action) {
    Objects.requireNonNull(action);
    this.released.put(keyCode, action);
  }
}
