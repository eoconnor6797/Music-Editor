package cs3500.music.view;

import java.util.ArrayList;

import cs3500.music.model.IObservableMusicMakerModel;
import cs3500.music.model.MusicNote;

/**
 * This is a view which can display the score of a music editor in the console as text.
 */
public class TextView implements IMusicView<MusicNote> {
  ArrayList<ArrayList<MusicNote>> score;
  ArrayList<MusicNote> range;

  /**
   * Creates a new instance of a TextView object.
   */
  public TextView() {
    // empty constructor
  }

  @Override
  public void render() {
    System.out.print(this.printScore());
  }

  @Override
  public int getTempo() {
    return 0;
  }

  @Override
  public void setTempo(int tempo) {
    // do nothing
  }

  @Override
  public void pause() {
    // this does nothing in this implementation
  }

  @Override
  public void play() {
    // this does nothing in this implementation
  }

  @Override
  public void update(IObservableMusicMakerModel<MusicNote> readOnlyModel) {
    this.updateScore(readOnlyModel.getScore());
    this.updateRange(readOnlyModel.genRange());
  }

  /**
   * Updates the score of notes that the view has to work with.
   *
   * @param score is the score received from the model
   */
  private void updateScore(ArrayList<ArrayList<MusicNote>> score) {
    this.score = score;
  }

  /**
   * Updates the range of notes that the view has to work with.
   *
   * @param range is the range received from the model
   */
  private void updateRange(ArrayList<MusicNote> range) {
    this.range = range;
  }

  /**
   * Return the score as a string. This method is public for testing purposes.
   *
   * @return the string representation of the score.
   */
  public String printScore() {
    if (score.size() == 0) {
      return "";
    }
    StringBuilder temp = new StringBuilder();
    int cush = (int) Math.log10(score.size()) + 1;
    while (cush > 0) {
      temp.append(" ");
      cush--;
    }
    ArrayList<MusicNote> range = this.range;
    int rangesize = range.size();
    for (int ii = 0; ii < rangesize; ii++) {
      temp.append(this.padding(range.get(ii).toString() + range.get(ii).getOctaveVal() / 12));
    }
    for (int ii = 0; ii < score.size(); ii++) {
      temp.append("\n");
      int newcush = (int) Math.log10(score.size()) + 1;
      int beatsize = (ii + "").length();
      while (beatsize < newcush) {
        temp.append(" ");
        beatsize++;
      }
      temp.append(ii);
      for (MusicNote n : range) {
        boolean found = false;
        for (MusicNote m : score.get(ii)) {
          if (n.equalsIgnoreDuration(m)) {
            found = true;
            if (m.getHead()) {
              temp.append(this.padding("X"));
              break;
            } else {
              temp.append(this.padding("|"));
              break;
            }
          }
        }
        if (!found) {
          temp.append("     ");
        }
      }
    }
    return temp.toString();
  }

  /**
   * Generates a space padding for strings.
   *
   * @param in the string to be padded.
   * @return the string with padding.
   */
  private String padding(String in) {
    int size = in.length();
    switch (size) {
      case 0:
        return "     ";
      case 1:
        return "  " + in + "  ";
      case 2:
        return "  " + in + " ";
      case 3:
        return " " + in + " ";
      case 4:
        return " " + in;
      default:
        return "     ";
    }
  }
}