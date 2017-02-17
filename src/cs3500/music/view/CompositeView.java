package cs3500.music.view;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import cs3500.music.model.IObservableMusicMakerModel;
import cs3500.music.model.MusicNote;

/**
 * This is a view which combines the GUIView and the MIDIView into one view which can display a
 * score while also showing that score's music being played.
 */
public class CompositeView implements IGUIView<MusicNote> {
  MIDIView midiView;
  GUIView guiView;

  /**
   * constructs a composite view given a MidiView and a GuiView.
   */
  public CompositeView() {
    this.midiView = new MIDIView();
    this.guiView = new GUIView();
  }

  @Override
  public void scrollLeft() {
    this.guiView.scrollLeft();
  }

  @Override
  public void scrollRight() {
    this.guiView.scrollRight();
  }

  @Override
  public void scrollUp() {
    this.guiView.scrollUp();
  }

  @Override
  public void scrollDown() {
    this.guiView.scrollDown();
  }

  @Override
  public void jumpBeginning() {
    this.guiView.jumpBeginning();
  }

  @Override
  public void jumpEnd() {
    this.guiView.jumpEnd();
  }

  @Override
  public void addKeyListener(KeyListener l) {
    this.guiView.addKeyListener(l);
  }

  @Override
  public void addMouseListener(MouseListener l) {
    this.guiView.addMouseListener(l);
  }

  @Override
  public JPanel getPanel() {
    return this.guiView.getPanel();
  }

  @Override
  public int getScoreBeats() {
    return this.guiView.getScoreBeats();
  }

  @Override
  public void pause() {
    this.guiView.pause();
    this.midiView.pause();
  }

  @Override
  public void play() {
    this.guiView.setTempo(this.midiView.getTempo());
    this.guiView.play();
    this.midiView.play();
  }

  @Override
  public void render() {
    this.guiView.render();
    this.midiView.render();
  }

  @Override
  public int getTempo() {
    return this.midiView.getTempo();
  }

  @Override
  public void setTempo(int tempo) {
    this.midiView.setTempo(tempo);
    this.guiView.setTempo(tempo);
  }

  @Override
  public void update(IObservableMusicMakerModel<MusicNote> readOnlyModel) {
    this.guiView.update(readOnlyModel);
    this.midiView.update(readOnlyModel);
  }

}
