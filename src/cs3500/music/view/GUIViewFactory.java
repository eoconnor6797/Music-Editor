package cs3500.music.view;

/**
 * factory class for IGUIView.
 */
public final class GUIViewFactory {

  /**
   * create an instance of an IGUIView.
   * @param type the name of the view to be created
   * @return an IMusicView
   */
  public static IGUIView create(String type) {
    switch (type) {
      case "gui":
        return new GUIView();
      case "composite":
        return new CompositeView();
      default: throw new IllegalArgumentException("Not a valid view type.");
    }
  }
}