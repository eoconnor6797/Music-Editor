package cs3500.music.view;

/**
 * factory class to make IMusicViews.
 */
public final class ViewFactory {

  /**
   * create an instance of an IMusicView.
   * @param type the name of the view to be created
   * @return an IMusicView
   */
  public static IMusicView create(String type) {
    switch (type) {
      case "console":
        return new TextView();
      case "visual":
        return new GUIView();
      case "midi":
        return new MIDIView();
      default: throw new IllegalArgumentException("Not a valid view type.");
    }
  }
}
