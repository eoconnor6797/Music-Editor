package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import cs3500.music.model.IMusicMakerModel;
import cs3500.music.model.MusicNote;
import cs3500.music.view.GUIView;
import cs3500.music.view.IGUIView;

/**
 * This is the controller for the music maker program. It serves to pass information from the model
 * to the view.
 */
public class Controller {
  private IGUIView view;
  private IMusicMakerModel model;
  private KeyboardHandler kh;
  private MouseHandler mh;
  private boolean addNoteFlag;
  private boolean removeNoteFlag;
  private int startbeat = -1;
  private int notePitch;
  private StringBuilder log;

  Runnable scrollLeft = () -> {
    this.view.scrollLeft();
    this.log.append("left\n");
  };
  Runnable scrollRight = () -> {
    this.view.scrollRight();
    this.log.append("right\n");
  };
  Runnable scrollUp = () -> {
    this.view.scrollUp();
    this.log.append("up\n");
  };
  Runnable scrollDown = () -> {
    this.view.scrollDown();
    this.log.append("down\n");
  };
  Runnable jumpBeginning = () -> {
    this.view.jumpBeginning();
    this.log.append("jump->beginning\n");
  };
  Runnable jumpEnd = () -> {
    this.view.jumpEnd();
    this.log.append("jump->end\n");
  };
  Runnable addNote = () -> {
    this.setAddNoteFlag();
    this.log.append("current action: add note\n");
  };
  Runnable removeNote = () -> {
    this.setRemoveNoteFlag();
    this.log.append("current action: remove note\n");
  };
  Runnable mouseAction = () -> {
    this.doMouseAction();
    this.log.append("left mouse click\n");
  };
  Runnable pause = () -> {
    this.view.pause();
    this.log.append("song paused\n");
  };
  Runnable play = () -> {
    this.view.play();
    this.log.append("song playing\n");
  };

  /**
   * Constructor for the Controller.
   * @param view the View the controller will pass information to.
   * @param model the model the controller will be getting information from.
   */
  public Controller(IGUIView view, IMusicMakerModel model) {
    this.view = view;
    this.model = model;
    this.log = new StringBuilder();
    this.kh = new KeyboardHandler();
    this.mh = new MouseHandler();
    this.addNoteFlag = false;
    this.removeNoteFlag = false;
    this.kh.addKeyPressed(KeyEvent.VK_LEFT, scrollLeft);
    this.kh.addKeyPressed(KeyEvent.VK_RIGHT, scrollRight);
    this.kh.addKeyPressed(KeyEvent.VK_UP, scrollUp);
    this.kh.addKeyPressed(KeyEvent.VK_DOWN, scrollDown);
    this.kh.addKeyPressed(KeyEvent.VK_HOME, jumpBeginning);
    this.kh.addKeyPressed(KeyEvent.VK_END, jumpEnd);
    this.kh.addKeyPressed(KeyEvent.VK_A, addNote);
    this.kh.addKeyPressed(KeyEvent.VK_D, removeNote);
    this.mh.addMouseClicked(MouseEvent.BUTTON1, mouseAction);
    this.kh.addKeyPressed(KeyEvent.VK_SPACE, play);
    this.kh.addKeyPressed(KeyEvent.VK_P, pause);
  }

  /**
   * updates the view with information from the model.
   * adds keyListener to the view.
   * adds mouseListener to the view.
   * renders the view.
   */
  public void start() {
    view.update(model);
    view.addKeyListener(kh);
    view.addMouseListener(mh);


    view.render();
  }

  /**
   * method to toggle the addNoteFlag
   * toggling addNote makes sure other modes are off.
   */
  private void setAddNoteFlag() {
    this.addNoteFlag = !(this.addNoteFlag);
    this.removeNoteFlag = false;
  }

  /**
   * method to toggle the RemoveNoteFlag
   * toggling removeNote makes sure other modes are off.
   */
  private void setRemoveNoteFlag() {
    this.removeNoteFlag = !(this.removeNoteFlag);
    this.addNoteFlag = false;
  }

  /**
   * method that handles responds to the left mouse button
   * being clicked.
   * if in add mode, it will check if a start beat has been set, if not it sets one.
   * Otherwise it adds a note from the start beat to the beat of the click.
   * in remove mode, it removes the note located at the mouse click, if there was a note head
   * located there.
   */
  private void doMouseAction() {
    if (this.model.genRange().size() > 0) {
      ArrayList<MusicNote> temp = this.model.genRange();
      int pitch = this.mh.getEvent().getY() / GUIView.CELL_HEIGHT * -1
              + temp.get(temp.size() - 1).getPitch()
              + temp.get(temp.size() - 1).getOctaveVal()
              + 1;
      if (this.addNoteFlag) {
        if (this.startbeat == -1) {
          startbeat = this.mh.getEvent().getX() - GUIView.X_OFFSET;
          notePitch = pitch;
        } else {
          this.model.addNote(new MusicNote(notePitch, (this.mh.getEvent().getX()
                          - GUIView.X_OFFSET - startbeat) / GUIView.CELL_WIDTH + 1, true, 0, 10),
                  (startbeat / GUIView.CELL_WIDTH));
          startbeat = -1;
          notePitch = -1;
        }
      } else if (this.removeNoteFlag) {
        MusicNote tempNote = this.model.getNote(pitch,
                (this.mh.getEvent().getX() - GUIView.X_OFFSET) / GUIView.CELL_WIDTH);
        try {
          this.model.removeNote(tempNote,
                  (this.mh.getEvent().getX() - GUIView.X_OFFSET) / GUIView.CELL_WIDTH);
        } catch (IllegalArgumentException e) {
          System.out.print("Can only remove from head");
        }
      }

      this.view.update(model);
      this.view.render();
    }
  }

  /**
   * to string for key and mouse log.
   *
   * @return string version of key and mouse log.
   */
  public String toString() {
    return this.log.toString();
  }
}
