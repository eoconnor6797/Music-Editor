package cs3500.music.controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * This is the class that handles mouse input from the user. It implements the KeyListener Java
 * class.
 */
public class MouseHandler implements MouseListener {
  Map<Integer, Runnable> clicked;
  Map<Integer, Runnable> pressed;
  Map<Integer, Runnable> released;
  Map<Integer, Runnable> entered;
  Map<Integer, Runnable> exited;
  MouseEvent event;

  /**
   * Creates a new instance of a MouseHandler object.
   */
  public MouseHandler() {
    this.clicked = new HashMap<>();
    this.pressed = new HashMap<>();
    this.released = new HashMap<>();
    this.entered = new HashMap<>();
    this.exited = new HashMap<>();
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    Objects.requireNonNull(e);
    this.event = e;
    if (this.clicked.containsKey(e.getButton())) {
      this.clicked.get(e.getButton()).run();
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {
    Objects.requireNonNull(e);
    this.event = e;
    if (this.pressed.containsKey(e.getButton())) {
      this.pressed.get(e.getButton()).run();
    }
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    Objects.requireNonNull(e);
    this.event = e;
    if (this.released.containsKey(e.getButton())) {
      this.released.get(e.getButton()).run();
    }
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    Objects.requireNonNull(e);
    this.event = e;
    if (this.entered.containsKey(e.getButton())) {
      this.entered.get(e.getButton()).run();
    }
  }

  @Override
  public void mouseExited(MouseEvent e) {
    Objects.requireNonNull(e);
    this.event = e;
    if (this.exited.containsKey(e.getButton())) {
      this.exited.get(e.getButton()).run();
    }
  }

  /**
   * Creates a new mapping of a Runnable mouseClicked action to they button on the mouse with the
   * given button int value.
   *
   * @param buttonInt the int value of the key
   * @param action the action associated with the key
   */
  public void addMouseClicked(int buttonInt, Runnable action) {
    Objects.requireNonNull(action);
    this.clicked.put(buttonInt, action);
  }

  /**
   * Creates a new mapping of a Runnable mousePressed action to they button on the mouse with the
   * given button int value.
   *
   * @param buttonInt the int value of the key
   * @param action the action associated with the key
   */
  public void addMousePressed(int buttonInt, Runnable action) {
    Objects.requireNonNull(action);
    this.pressed.put(buttonInt, action);
  }

  /**
   * Creates a new mapping of a Runnable mouseReleased action to they button on the mouse with the
   * given button int value.
   *
   * @param buttonInt the int value of the key
   * @param action the action associated with the key
   */
  public void addMouseReleased(int buttonInt, Runnable action) {
    Objects.requireNonNull(action);
    this.released.put(buttonInt, action);
  }

  /**
   * Creates a new mapping of a Runnable mouseEntered action to they button on the mouse with the
   * given button int value.
   *
   * @param buttonInt the int value of the key
   * @param action the action associated with the key
   */
  public void addMouseEntered(int buttonInt, Runnable action) {
    Objects.requireNonNull(action);
    this.entered.put(buttonInt, action);
  }

  /**
   * Creates a new mapping of a Runnable mouseExited action to they button on the mouse with the
   * given button int value.
   *
   * @param buttonInt the int value of the key
   * @param action the action associated with the key
   */
  public void addMouseExited(int buttonInt, Runnable action) {
    Objects.requireNonNull(action);
    this.exited.put(buttonInt, action);
  }

  /**
   * getter for event field.
   * @return MouseEvent event
   */
  public MouseEvent getEvent() {
    return this.event;
  }
}
