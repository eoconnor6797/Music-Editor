package cs3500.music.view;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

import cs3500.music.model.MusicNote;

import javax.swing.JPanel;

/**
 * This panel represents the region where the score of the music will be rendered in a
 * "sheet music" type of view.
 */
public class GUIPanel extends JPanel {
  private ArrayList<ArrayList<MusicNote>> score;
  private ArrayList<MusicNote> range;
  private int barOffset;


  /**
   * Constructs a new instance of a GUIPanel object.
   */
  public GUIPanel() {
    super();
    this.score = new ArrayList<>();
    this.score.add(new ArrayList<>());
    this.range = new ArrayList<>();
    this.setBackground(Color.WHITE);
    this.barOffset = -(GUIView.CELL_WIDTH);
  }

  /**
   * Sets the score of this panel.
   *
   * @param score the score of notes that this object's score will become
   */
  public void setScore(ArrayList<ArrayList<MusicNote>> score) {
    this.score = score;
  }

  /**
   * Sets the range of this panel.
   *
   * @param range the range of notes that this object's range will become
   */
  public void setRange(ArrayList<MusicNote> range) {
    this.range = range;
  }

  /**
   * getter for the barOffset.
   * @return barOffset
   */
  public int getBarOffset() {
    return this.barOffset;
  }

  /**
   * increments the barOffset by the CELL_WIDTH constant (25).
   */
  public void incrementBarOffset() {
    this.barOffset += GUIView.CELL_WIDTH;
    this.repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    ArrayList<MusicNote> descendingRange = new ArrayList<>(this.range);
    Collections.reverse(descendingRange);

    g2d.setColor(Color.BLACK);

    g2d.drawLine(GUIView.X_OFFSET, GUIView.CELL_HEIGHT,
            GUIView.X_OFFSET + score.size() * GUIView.CELL_WIDTH, GUIView.CELL_HEIGHT);

    g2d.drawLine(GUIView.X_OFFSET + score.size() * GUIView.CELL_WIDTH, GUIView.CELL_HEIGHT,
            GUIView.X_OFFSET + score.size() * GUIView.CELL_WIDTH, range.size() * GUIView.CELL_HEIGHT
            + GUIView.CELL_HEIGHT);

    for (int y = 0; y < descendingRange.size(); y++) {
      g2d.drawString(descendingRange.get(y).toString() + descendingRange.get(y).getOctaveVal() / 12,
              0, y * GUIView.CELL_WIDTH + GUIView.Y_OFFSET);

      g2d.drawLine(GUIView.X_OFFSET, y * GUIView.CELL_HEIGHT + GUIView.Y_OFFSET
              + GUIView.TOP_OFFSET, GUIView.X_OFFSET + score.size() * GUIView.CELL_WIDTH, y
              * GUIView.CELL_HEIGHT + GUIView.Y_OFFSET + GUIView.TOP_OFFSET);
    }

    for (int x = 0; x < score.size(); x += 4) {
      if (x % 16 == 0) {
        g2d.drawString("" + x, GUIView.X_OFFSET + x * GUIView.CELL_WIDTH, GUIView.TOP_OFFSET);
      }

      g2d.drawLine(GUIView.X_OFFSET + x * GUIView.CELL_WIDTH, GUIView.CELL_HEIGHT,
              GUIView.X_OFFSET + x * GUIView.CELL_WIDTH, range.size() * GUIView.CELL_HEIGHT
                      + GUIView.CELL_HEIGHT);
    }

    for (int ii = 0; ii < score.size(); ii++) {
      for (int jj = 0; jj < descendingRange.size(); jj++) {
        for (MusicNote m : score.get(ii)) {
          if (descendingRange.get(jj).equalsIgnoreDuration(m)) {
            if (m.getHead()) {
              g2d.setColor(Color.BLUE);
              g2d.fillRect(GUIView.X_OFFSET + ii * GUIView.CELL_WIDTH + 1,
                      (jj + 1) * GUIView.CELL_HEIGHT + 1,
                      GUIView.CELL_WIDTH - 1, GUIView.CELL_HEIGHT - 1);
              break;
            } else {
              g2d.setColor(Color.ORANGE);
              g2d.fillRect(GUIView.X_OFFSET + ii * GUIView.CELL_WIDTH,
                      (jj + 1) * GUIView.CELL_HEIGHT + 1,
                      GUIView.CELL_WIDTH, GUIView.CELL_HEIGHT - 1);
              break;
            }
          }
        }
      }
    }
    g2d.setColor(Color.RED);

    if (this.barOffset >= 0 && this.barOffset <= this.score.size() * GUIView.CELL_WIDTH) {
      g2d.drawLine(GUIView.X_OFFSET + this.barOffset, GUIView.CELL_HEIGHT,
              GUIView.X_OFFSET + this.barOffset,
              GUIView.CELL_HEIGHT + GUIView.CELL_HEIGHT * range.size());
    }
  }
}