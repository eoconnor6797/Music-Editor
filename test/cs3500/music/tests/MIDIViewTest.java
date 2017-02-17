package cs3500.music.tests;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.sound.midi.Sequencer;

import cs3500.music.model.IObservableMusicMakerModel;
import cs3500.music.model.MusicMakerModel;
import cs3500.music.model.MusicNote;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.IMusicView;
import cs3500.music.view.MIDIView;
import cs3500.music.view.MockSeq;

import static org.junit.Assert.assertEquals;


public class MIDIViewTest {

  @Test
  public void midiMaryTest() {
    String projectedOutcome =
            "on 1 64 0 72\n" +
                    "on 1 55 0 70\n" +
                    "off 1 64 192 72\n" +
                    "on 1 62 192 72\n" +
                    "off 1 62 384 72\n" +
                    "on 1 60 384 71\n" +
                    "off 1 60 576 71\n" +
                    "on 1 62 576 79\n" +
                    "off 1 55 672 70\n" +
                    "off 1 62 768 79\n" +
                    "on 1 55 768 79\n" +
                    "on 1 64 768 85\n" +
                    "off 1 64 960 85\n" +
                    "on 1 64 960 78\n" +
                    "off 1 64 1152 78\n" +
                    "on 1 64 1152 74\n" +
                    "off 1 55 1440 79\n" +
                    "off 1 64 1440 74\n" +
                    "on 1 55 1536 77\n" +
                    "on 1 62 1536 75\n" +
                    "off 1 62 1728 75\n" +
                    "on 1 62 1728 77\n" +
                    "off 1 62 1920 77\n" +
                    "on 1 62 1920 75\n" +
                    "off 1 55 2304 77\n" +
                    "off 1 62 2304 75\n" +
                    "on 1 55 2304 79\n" +
                    "on 1 64 2304 82\n" +
                    "off 1 55 2496 79\n" +
                    "off 1 64 2496 82\n" +
                    "on 1 67 2496 84\n" +
                    "off 1 67 2688 84\n" +
                    "on 1 67 2688 75\n" +
                    "off 1 67 3072 75\n" +
                    "on 1 55 3072 78\n" +
                    "on 1 64 3072 73\n" +
                    "off 1 64 3264 73\n" +
                    "on 1 62 3264 69\n" +
                    "off 1 62 3456 69\n" +
                    "on 1 60 3456 71\n" +
                    "off 1 60 3648 71\n" +
                    "on 1 62 3648 80\n" +
                    "off 1 55 3840 78\n" +
                    "off 1 62 3840 80\n" +
                    "on 1 55 3840 79\n" +
                    "on 1 64 3840 84\n" +
                    "off 1 64 4032 84\n" +
                    "on 1 64 4032 76\n" +
                    "off 1 64 4224 76\n" +
                    "on 1 64 4224 74\n" +
                    "off 1 64 4416 74\n" +
                    "on 1 64 4416 77\n" +
                    "off 1 55 4608 79\n" +
                    "off 1 64 4608 77\n" +
                    "on 1 55 4608 78\n" +
                    "on 1 62 4608 75\n" +
                    "off 1 62 4800 75\n" +
                    "on 1 62 4800 74\n" +
                    "off 1 62 4992 74\n" +
                    "on 1 64 4992 81\n" +
                    "off 1 64 5184 81\n" +
                    "on 1 62 5184 70\n" +
                    "off 1 55 5376 78\n" +
                    "off 1 62 5376 70\n" +
                    "on 1 52 5376 72\n" +
                    "on 1 60 5376 73\n" +
                    "off 1 52 6144 72\n" +
                    "off 1 60 6144 73\n";


    Sequencer logSeq = new MockSeq();
    IMusicView<MusicNote> maryView = new MIDIView(logSeq);

    CompositionBuilder<MusicMakerModel> comp = new MusicMakerModel.MusicBuilder();
    IObservableMusicMakerModel model = null;
    try {
      model = MusicReader.parseFile(new FileReader("mary-little-lamb.txt"), comp);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    maryView.update(model);
    maryView.render();

    assertEquals(logSeq.toString(), projectedOutcome);
  }
}