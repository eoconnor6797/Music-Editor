package cs3500.music.tests;


import org.junit.Test;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;

import cs3500.music.controller.Controller;
import cs3500.music.model.IMusicMakerModel;
import cs3500.music.model.MusicMakerModel;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.GUIView;

import static org.junit.Assert.assertEquals;

public class ControllerTest {
  @Test
  public void controllerFunctionsTest() {
    GUIView gui = new GUIView();
    IMusicMakerModel model = new MusicMakerModel();
    Controller cont = new Controller(gui, model);
    cont.start();

    gui.getKeyListeners()[0].keyPressed(new KeyEvent(gui, KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, KeyEvent.CHAR_UNDEFINED));

    gui.getKeyListeners()[0].keyPressed(new KeyEvent(gui, KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, KeyEvent.CHAR_UNDEFINED));

    gui.getKeyListeners()[0].keyPressed(new KeyEvent(gui, KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(), 0, KeyEvent.VK_UP, KeyEvent.CHAR_UNDEFINED));

    gui.getKeyListeners()[0].keyPressed(new KeyEvent(gui, KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, KeyEvent.CHAR_UNDEFINED));

    gui.getKeyListeners()[0].keyPressed(new KeyEvent(gui, KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(), 0, KeyEvent.VK_HOME, KeyEvent.CHAR_UNDEFINED));

    gui.getKeyListeners()[0].keyPressed(new KeyEvent(gui, KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(), 0, KeyEvent.VK_END, KeyEvent.CHAR_UNDEFINED));

    gui.getKeyListeners()[0].keyPressed(new KeyEvent(gui, KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(), 0, KeyEvent.VK_A, KeyEvent.CHAR_UNDEFINED));

    gui.getKeyListeners()[0].keyPressed(new KeyEvent(gui, KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(), 0, KeyEvent.VK_D, KeyEvent.CHAR_UNDEFINED));

    gui.getKeyListeners()[0].keyPressed(new KeyEvent(gui, KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(), 0, KeyEvent.VK_SPACE, KeyEvent.CHAR_UNDEFINED));

    gui.getKeyListeners()[0].keyPressed(new KeyEvent(gui, KeyEvent.KEY_PRESSED,
            System.currentTimeMillis(), 0, KeyEvent.VK_P, KeyEvent.CHAR_UNDEFINED));

    gui.getPanel().getMouseListeners()[0].mouseClicked(new MouseEvent(gui.getPanel(),
            MouseEvent.MOUSE_CLICKED, System.currentTimeMillis(), 0, 1, 1, 1, false,
            MouseEvent.BUTTON1));

    assertEquals(cont.toString(),"left\n" +
            "right\n" +
            "up\n" +
            "down\n" +
            "jump->beginning\n" +
            "jump->end\n" +
            "current action: add note\n" +
            "current action: remove note\n" +
            "song playing\n" +
            "song paused\n" +
            "left mouse click\n");
  }

  @Test
  public void startTest() {
    GUIView gui = new GUIView();
    CompositionBuilder<MusicMakerModel> comp = new MusicMakerModel.MusicBuilder();
    IMusicMakerModel model = null;
    try {
      model = MusicReader.parseFile(new FileReader("mary-little-lamb.txt"), comp);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    Controller cont = new Controller(gui, model);
    cont.start();

    assertEquals(gui.getScoreBeats(), model.getScore().size());
  }
}