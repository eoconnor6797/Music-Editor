package cs3500.music;

import java.io.FileReader;
import java.io.IOException;
import javax.sound.midi.InvalidMidiDataException;

import cs3500.music.model.IMusicMakerModel;
import cs3500.music.model.MusicMakerModel;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.controller.Controller;
import cs3500.music.view.GUIViewFactory;
import cs3500.music.view.IGUIView;


public class MusicEditor {
  /**
   * Runs the contents of this program with the given arguments.
   *
   * @param args the arguments to run the program with
   * @throws IOException if the program arguments are invalid
   * @throws InvalidMidiDataException if the midi data is invalid
   */
  public static void main(String[] args) throws IOException, InvalidMidiDataException {
    String file = "";
    String viewMode = "";
    file += args[0];
    viewMode = args[1];
    CompositionBuilder<MusicMakerModel> comp = new MusicMakerModel.MusicBuilder();
    IMusicMakerModel model = null;
    model = MusicReader.parseFile(new FileReader(file), comp);
    IGUIView view = GUIViewFactory.create(viewMode);
    Controller cont = new Controller(view, model);
    cont.start();
  }
}