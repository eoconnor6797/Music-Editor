package cs3500.music.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.Timer;


import cs3500.music.model.IObservableMusicMakerModel;
import cs3500.music.model.MusicNote;

/**
 * This is a view which can display a graphical rendition of the score of a music editor using the
 * Java Swing library.
 */
public class GUIView extends JFrame implements IGUIView<MusicNote> {
  private int scoreBeats;
  private int pitchRange;
  private GUIPanel musicPanel;
  private JScrollPane scrollPane;
  private Timer barTimer = new Timer(0, (ActionListener) e -> {
    incrementBar();
    checkMoveWindow();
  });
  private int currentBound;

  public static final int X_OFFSET = 40;
  public static final int Y_OFFSET = 40;
  public static final int CELL_WIDTH = 25;
  public static final int CELL_HEIGHT = 25;
  public static final int TOP_OFFSET = 10;

  /**
   * Creates a new instance of a GUIView object.
   */
  public GUIView() {
    super();
    this.setTitle("Music Editor");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    this.musicPanel = new GUIPanel();

    this.scrollPane = new JScrollPane(this.musicPanel);
    this.scrollPane.getHorizontalScrollBar().setUnitIncrement(240);
    this.scrollPane.getVerticalScrollBar().setUnitIncrement(160);

    this.musicPanel.setAutoscrolls(true);
    this.add(scrollPane);

    this.musicPanel.setPreferredSize(new Dimension(500, 500));
    this.scrollPane.setPreferredSize(new Dimension(500, 500));
    this.setPreferredSize(new Dimension(500, 500));
    this.pack();
    this.currentBound = (int) this.getBounds().getWidth();
    this.setVisible(true);
  }

  @Override
  public void render() {
    this.repaint();
  }

  @Override
  public int getTempo() {
    return 0;
  }

  @Override
  public void setTempo(int tempo) {
    this.barTimer.setDelay(tempo / 1000);
  }

  @Override
  public void update(IObservableMusicMakerModel<MusicNote> readOnlyModel) {
    this.updateScore(readOnlyModel.getScore());
    this.updateRange(readOnlyModel.genRange());
    this.currentBound = (int) this.getBounds().getWidth();
  }

  /**
   * Updates the score of notes that the view has to work with.
   *
   * @param score is the score received from the model
   */
  private void updateScore(ArrayList<ArrayList<MusicNote>> score) {
    this.scoreBeats = score.size();
    this.musicPanel.setPreferredSize(new Dimension(scoreBeats * CELL_WIDTH + X_OFFSET, pitchRange
            * CELL_HEIGHT + Y_OFFSET));
    this.scrollPane.setPreferredSize(new Dimension(scoreBeats * CELL_WIDTH + X_OFFSET, pitchRange
            * CELL_HEIGHT + Y_OFFSET));
    this.setPreferredSize(new Dimension(scoreBeats * CELL_WIDTH + X_OFFSET, pitchRange
            * CELL_HEIGHT + Y_OFFSET));
    this.pack();
    this.musicPanel.setScore(score);
  }

  /**
   * Updates the range of notes that the view has to work with.
   *
   * @param range is the range received from the model
   */
  private void updateRange(ArrayList<MusicNote> range) {
    this.pitchRange = range.size();
    this.musicPanel.setPreferredSize(new Dimension(scoreBeats * CELL_WIDTH + X_OFFSET, pitchRange
            * CELL_HEIGHT + Y_OFFSET));
    this.scrollPane.setPreferredSize(new Dimension(scoreBeats * CELL_WIDTH + X_OFFSET, pitchRange
            * CELL_HEIGHT + Y_OFFSET));
    this.setPreferredSize(new Dimension(scoreBeats * CELL_WIDTH + X_OFFSET, pitchRange
            * CELL_HEIGHT + Y_OFFSET));
    this.pack();
    this.musicPanel.setRange(range);
  }

  /**
   * increments the bar that follows the music notes.
   */
  private void incrementBar() {
    this.musicPanel.incrementBarOffset();
  }

  @Override
  public void scrollLeft() {
    this.scrollPane.getHorizontalScrollBar().setValue(this.scrollPane.getHorizontalScrollBar()
        .getValue() - this.scrollPane.getHorizontalScrollBar().getUnitIncrement());
  }

  @Override
  public void scrollRight() {
    this.scrollPane.getHorizontalScrollBar().setValue(this.scrollPane.getHorizontalScrollBar()
        .getValue() + this.scrollPane.getHorizontalScrollBar().getUnitIncrement());
  }

  @Override
  public void scrollUp() {
    this.scrollPane.getVerticalScrollBar().setValue(this.scrollPane.getVerticalScrollBar()
            .getValue() - this.scrollPane.getVerticalScrollBar().getUnitIncrement());
  }

  @Override
  public void scrollDown() {
    this.scrollPane.getVerticalScrollBar().setValue(this.scrollPane.getVerticalScrollBar()
            .getValue() + this.scrollPane.getVerticalScrollBar().getUnitIncrement());
  }

  @Override
  public void jumpBeginning() {
    this.scrollPane.getHorizontalScrollBar().setValue(this.scrollPane.getHorizontalScrollBar()
            .getMinimum());
  }

  @Override
  public void jumpEnd() {
    this.scrollPane.getHorizontalScrollBar().setValue(this.scrollPane.getHorizontalScrollBar()
        .getMaximum());
  }

  @Override
  public JPanel getPanel() {
    return this.musicPanel;
  }

  @Override
  public int getScoreBeats() {
    return this.scoreBeats;
  }

  @Override
  public void addMouseListener(MouseListener l) {
    this.musicPanel.addMouseListener(l);
  }

  @Override
  public void pause() {
    this.barTimer.stop();
  }

  @Override
  public void play() {
    this.barTimer.start();
  }

  /**
   * checks if the window needs to be scrolled.
   * i.e if the bar is at the end of the window.
   */
  private void checkMoveWindow() {
    if (this.musicPanel.getBarOffset() >= this.currentBound) {
      this.scrollPane.getHorizontalScrollBar()
              .setValue(this.scrollPane.getHorizontalScrollBar().getValue()
                      + (int)this.getBounds().getWidth());
      this.currentBound += this.getBounds().getWidth();
    }
  }
}