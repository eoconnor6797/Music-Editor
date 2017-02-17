package cs3500.music.tests;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import cs3500.music.model.IMusicMakerModel;
import cs3500.music.model.IObservableMusicMakerModel;
import cs3500.music.model.MusicMakerModel;
import cs3500.music.model.MusicNote;
import cs3500.music.model.Pitch;
import cs3500.music.util.CompositionBuilder;
import cs3500.music.util.MusicReader;
import cs3500.music.view.TextView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class TestConsoleMusicMakerModel {
  IMusicMakerModel<MusicNote> model = new MusicMakerModel();
  MusicNote note = new MusicNote(Pitch.Csharp, 4, 4, true);
  MusicNote note1 = new MusicNote(Pitch.G, 7, 3, true);
  MusicNote note2 = new MusicNote(Pitch.E, 2, 4, true);
  MusicNote note3 = new MusicNote(Pitch.D, 2, 4, true);
  MusicNote note4 = new MusicNote(Pitch.C, 2, 4, true);
  MusicNote note5 = new MusicNote(Pitch.E, 8, 3, true);
  MusicNote note6 = new MusicNote(Pitch.G, 8, 3, true);
  MusicNote note7 = new MusicNote(Pitch.G, 2, 3, true);
  MusicNote note8 = new MusicNote(Pitch.C, 8, 4, true);
  MusicNote note9 = new MusicNote(Pitch.D, 4, 4, true);
  MusicNote note10 = new MusicNote(Pitch.E, 3, 4, true);
  MusicNote note11 = new MusicNote(Pitch.G, 2, 4, true);
  MusicNote note12 = new MusicNote(Pitch.G, 4, 4, true);


  @Test
  public void testGetNotesAtBeat() {
    ArrayList<MusicNote> temp = new ArrayList<MusicNote>();
    temp.add(note1);
    temp.add(note2);
    model.addNote(note1, 0);
    model.addNote(note2, 0);
    model.addNote(note3, 2);
    model.addNote(note4, 4);
    model.addNote(note3, 6);
    model.addNote(note1, 8);
    model.addNote(note2, 8);
    model.addNote(note2, 10);
    model.addNote(note10, 12);
    model.addNote(note6, 16);
    model.addNote(note3, 16);
    model.addNote(note3, 18);
    model.addNote(note9, 20);
    model.addNote(note7, 24);
    model.addNote(note2, 24);
    model.addNote(note11, 26);
    model.addNote(note12, 28);
    model.addNote(note6, 32);
    model.addNote(note2, 32);
    model.addNote(note3, 34);
    model.addNote(note4, 36);
    model.addNote(note3, 38);
    model.addNote(note6, 40);
    model.addNote(note2, 40);
    for (MusicNote note : model.getNotesAtBeat(8)) {
      assertTrue(model.getNotesAtBeat(8).contains(note));
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetNotesAtBeat1() {
    model.getNotesAtBeat(1);
  }

  @Test
  public void testAdd() {
    model.addNote(note, 0);
    assertEquals(model.getScore().size(), 4);
  }

  @Test
  public void testAdd1() {
    model.addNote(note, 0);
    assertEquals(model.getScore().get(0).get(0), note);
  }

  @Test(expected = IllegalArgumentException.class)
  public void add2() {
    model.addNote(note, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void add3() {
    model.addNote(null, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemoveNote() {
    model.addNote(note, 0);
    model.removeNote(note.makeSustained().decrimentDuration(), 1);
    assertEquals(model.getScore().get(2).size(), 0);
  }

  @Test
  public void testRemoveNote1() {
    model.addNote(note, 2);
    model.addNote(note1, 1);
    model.removeNote(note1, 1);
    TextView testview = new TextView();
    testview.update(model);
    assertEquals("  C#4 \n" +
            "0     \n" +
            "1     \n" +
            "2  X  \n" +
            "3  |  \n" +
            "4  |  \n" +
            "5  |  \n" +
            "6     \n" +
            "7     ", testview.printScore());
  }

  @Test
  public void testRemoveNote2() {
    model.addNote(note1, 0);
    model.addNote(note2, 0);
    model.addNote(note3, 2);
    model.addNote(note4, 4);
    model.addNote(note3, 6);
    model.addNote(note1, 8);
    model.addNote(note2, 8);
    model.addNote(note2, 10);
    model.addNote(note10, 12);
    model.addNote(note6, 16);
    model.addNote(note3, 16);
    model.addNote(note3, 18);
    model.addNote(note9, 20);
    model.addNote(note7, 24);
    model.addNote(note2, 24);
    model.addNote(note11, 26);
    model.addNote(note12, 28);
    model.addNote(note6, 32);
    model.addNote(note2, 32);
    model.addNote(note3, 34);
    model.addNote(note4, 36);
    model.addNote(note3, 38);
    model.addNote(note6, 40);
    model.addNote(note2, 40);
    model.addNote(note2, 42);
    model.addNote(note2, 44);
    model.addNote(note2, 46);
    model.addNote(note3, 48);
    model.addNote(note3, 50);
    model.addNote(note2, 52);
    model.addNote(note3, 54);
    model.addNote(note5, 56);
    model.addNote(note8, 56);
    model.addNote(note6, 48);
    model.removeNote(note6, 48);
    IObservableMusicMakerModel<MusicNote> model1 = model;
    TextView testview = new TextView();
    testview.update(model1);
    assertEquals(
            "    E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4   F4  F#4   G4 \n" +
                    " 0                 X                                            X           " +
                    "      \n" +
                    " 1                 |                                            |           " +
                    "      \n" +
                    " 2                 |                                  X                     " +
                    "      \n" +
                    " 3                 |                                  |                     " +
                    "      \n" +
                    " 4                 |                        X                               " +
                    "      \n" +
                    " 5                 |                        |                               " +
                    "      \n" +
                    " 6                 |                                  X                     " +
                    "      \n" +
                    " 7                                                    |                     " +
                    "      \n" +
                    " 8                 X                                            X           " +
                    "      \n" +
                    " 9                 |                                            |           " +
                    "      \n" +
                    "10                 |                                            X           " +
                    "      \n" +
                    "11                 |                                            |           " +
                    "      \n" +
                    "12                 |                                            X           " +
                    "      \n" +
                    "13                 |                                            |           " +
                    "      \n" +
                    "14                 |                                            |           " +
                    "      \n" +
                    "15                                                                          " +
                    "      \n" +
                    "16                 X                                  X                     " +
                    "      \n" +
                    "17                 |                                  |                     " +
                    "      \n" +
                    "18                 |                                  X                     " +
                    "      \n" +
                    "19                 |                                  |                     " +
                    "      \n" +
                    "20                 |                                  X                     " +
                    "      \n" +
                    "21                 |                                  |                     " +
                    "      \n" +
                    "22                 |                                  |                     " +
                    "      \n" +
                    "23                 |                                  |                     " +
                    "      \n" +
                    "24                 X                                            X           " +
                    "      \n" +
                    "25                 |                                            |           " +
                    "      \n" +
                    "26                                                                          " +
                    "   X  \n" +
                    "27                                                                          " +
                    "   |  \n" +
                    "28                                                                          " +
                    "   X  \n" +
                    "29                                                                          " +
                    "   |  \n" +
                    "30                                                                          " +
                    "   |  \n" +
                    "31                                                                          " +
                    "   |  \n" +
                    "32                 X                                            X           " +
                    "      \n" +
                    "33                 |                                            |           " +
                    "      \n" +
                    "34                 |                                  X                     " +
                    "      \n" +
                    "35                 |                                  |                     " +
                    "      \n" +
                    "36                 |                        X                               " +
                    "      \n" +
                    "37                 |                        |                               " +
                    "      \n" +
                    "38                 |                                  X                     " +
                    "      \n" +
                    "39                 |                                  |                     " +
                    "      \n" +
                    "40                 X                                            X           " +
                    "      \n" +
                    "41                 |                                            |           " +
                    "      \n" +
                    "42                 |                                            X           " +
                    "      \n" +
                    "43                 |                                            |           " +
                    "      \n" +
                    "44                 |                                            X           " +
                    "      \n" +
                    "45                 |                                            |           " +
                    "      \n" +
                    "46                 |                                            X           " +
                    "      \n" +
                    "47                 |                                            |           " +
                    "      \n" +
                    "48                                                    X                     " +
                    "      \n" +
                    "49                                                    |                     " +
                    "      \n" +
                    "50                                                    X                     " +
                    "      \n" +
                    "51                                                    |                     " +
                    "      \n" +
                    "52                                                              X           " +
                    "      \n" +
                    "53                                                              |           " +
                    "      \n" +
                    "54                                                    X                     " +
                    "      \n" +
                    "55                                                    |                     " +
                    "      \n" +
                    "56  X                                       X                               " +
                    "      \n" +
                    "57  |                                       |                               " +
                    "      \n" +
                    "58  |                                       |                               " +
                    "      \n" +
                    "59  |                                       |                               " +
                    "      \n" +
                    "60  |                                       |                               " +
                    "      \n" +
                    "61  |                                       |                               " +
                    "      \n" +
                    "62  |                                       |                               " +
                    "      \n" +
                    "63  |                                       |                               " +
                    "      ",
            testview.printScore());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemove3() {
    model.removeNote(null, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemove4() {
    model.removeNote(note, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRemove5() {
    model.addNote(note, 1);
    model.removeNote(note, 0);
  }

  @Test
  public void testEditNote() {
    model.addNote(note1, 0);
    model.addNote(note2, 0);
    model.addNote(note3, 2);
    model.addNote(note4, 4);
    model.addNote(note3, 6);
    model.addNote(note1, 8);
    model.addNote(note2, 8);
    model.addNote(note2, 10);
    model.addNote(note10, 12);
    model.addNote(note6, 16);
    model.addNote(note3, 16);
    model.addNote(note3, 18);
    model.addNote(note9, 20);
    model.addNote(note7, 24);
    model.addNote(note2, 24);
    model.addNote(note11, 26);
    model.addNote(note12, 28);
    model.addNote(note6, 32);
    model.addNote(note2, 32);
    model.addNote(note3, 34);
    model.addNote(note4, 36);
    model.addNote(note3, 38);
    model.addNote(note6, 40);
    model.addNote(note2, 40);
    model.addNote(note2, 42);
    model.addNote(note2, 44);
    model.addNote(note2, 46);
    model.addNote(note3, 48);
    model.addNote(note3, 50);
    model.addNote(note2, 52);
    model.addNote(note3, 54);
    model.addNote(note5, 56);
    model.addNote(note8, 56);
    model.addNote(note6, 48);
    model.editNote(note6, note7, 48);
    IObservableMusicMakerModel<MusicNote> model1 = model;
    TextView testview = new TextView();
    testview.update(model1);

    assertEquals(
            "    E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4   F4  F#4   G4 \n" +
                    " 0                 X                                            X           " +
                    "      \n" +
                    " 1                 |                                            |           " +
                    "      \n" +
                    " 2                 |                                  X                     " +
                    "      \n" +
                    " 3                 |                                  |                     " +
                    "      \n" +
                    " 4                 |                        X                               " +
                    "      \n" +
                    " 5                 |                        |                               " +
                    "      \n" +
                    " 6                 |                                  X                     " +
                    "      \n" +
                    " 7                                                    |                     " +
                    "      \n" +
                    " 8                 X                                            X           " +
                    "      \n" +
                    " 9                 |                                            |           " +
                    "      \n" +
                    "10                 |                                            X           " +
                    "      \n" +
                    "11                 |                                            |           " +
                    "      \n" +
                    "12                 |                                            X           " +
                    "      \n" +
                    "13                 |                                            |           " +
                    "      \n" +
                    "14                 |                                            |           " +
                    "      \n" +
                    "15                                                                          " +
                    "      \n" +
                    "16                 X                                  X                     " +
                    "      \n" +
                    "17                 |                                  |                     " +
                    "      \n" +
                    "18                 |                                  X                     " +
                    "      \n" +
                    "19                 |                                  |                     " +
                    "      \n" +
                    "20                 |                                  X                     " +
                    "      \n" +
                    "21                 |                                  |                     " +
                    "      \n" +
                    "22                 |                                  |                     " +
                    "      \n" +
                    "23                 |                                  |                     " +
                    "      \n" +
                    "24                 X                                            X           " +
                    "      \n" +
                    "25                 |                                            |           " +
                    "      \n" +
                    "26                                                                          " +
                    "   X  \n" +
                    "27                                                                          " +
                    "   |  \n" +
                    "28                                                                          " +
                    "   X  \n" +
                    "29                                                                          " +
                    "   |  \n" +
                    "30                                                                          " +
                    "   |  \n" +
                    "31                                                                          " +
                    "   |  \n" +
                    "32                 X                                            X           " +
                    "      \n" +
                    "33                 |                                            |           " +
                    "      \n" +
                    "34                 |                                  X                     " +
                    "      \n" +
                    "35                 |                                  |                     " +
                    "      \n" +
                    "36                 |                        X                               " +
                    "      \n" +
                    "37                 |                        |                              " +
                    "       \n" +
                    "38                 |                                  X                     " +
                    "      \n" +
                    "39                 |                                  |                    " +
                    "       \n" +
                    "40                 X                                            X         " +
                    "        \n" +
                    "41                 |                                            |         " +
                    "        \n" +
                    "42                 |                                            X        " +
                    "         \n" +
                    "43                 |                                            |         " +
                    "        \n" +
                    "44                 |                                            X         " +
                    "        \n" +
                    "45                 |                                            |         " +
                    "        \n" +
                    "46                 |                                            X         " +
                    "        \n" +
                    "47                 |                                            |         " +
                    "        \n" +
                    "48                 X                                  X                    " +
                    "       \n" +
                    "49                 |                                  |                    " +
                    "       \n" +
                    "50                                                    X                    " +
                    "       \n" +
                    "51                                                    |                    " +
                    "       \n" +
                    "52                                                              X           " +
                    "      \n" +
                    "53                                                              |           " +
                    "      \n" +
                    "54                                                    X                     " +
                    "      \n" +
                    "55                                                    |                     " +
                    "      \n" +
                    "56  X                                       X                              " +
                    "       \n" +
                    "57  |                                       |                             " +
                    "        \n" +
                    "58  |                                       |                              " +
                    "       \n" +
                    "59  |                                       |                            " +
                    "         \n" +
                    "60  |                                       |                             " +
                    "        \n" +
                    "61  |                                       |                             " +
                    "        \n" +
                    "62  |                                       |                              " +
                    "       \n" +
                    "63  |                                       |                               " +
                    "      ",
            testview.printScore());
  }

  @Test
  public void testEquals() {
    model.addNote(note6, 48);
    assertEquals(model.getScore().get(48).get(0), note6);
  }

  @Test
  public void testEquals1() {
    MusicNote temp = note.makeSustained().decrimentDuration();
    assertEquals(temp, new MusicNote(Pitch.Csharp, 3, 4, false));
  }

  @Test
  public void testExample() {
    model.addNote(note1, 0);
    model.addNote(note2, 0);
    model.addNote(note3, 2);
    model.addNote(note4, 4);
    model.addNote(note3, 6);
    model.addNote(note1, 8);
    model.addNote(note2, 8);
    model.addNote(note2, 10);
    model.addNote(note10, 12);
    model.addNote(note6, 16);
    model.addNote(note3, 16);
    model.addNote(note3, 18);
    model.addNote(note9, 20);
    model.addNote(note7, 24);
    model.addNote(note2, 24);
    model.addNote(note11, 26);
    model.addNote(note12, 28);
    model.addNote(note6, 32);
    model.addNote(note2, 32);
    model.addNote(note3, 34);
    model.addNote(note4, 36);
    model.addNote(note3, 38);
    model.addNote(note6, 40);
    model.addNote(note2, 40);
    model.addNote(note2, 42);
    model.addNote(note2, 44);
    model.addNote(note2, 46);
    model.addNote(note3, 48);
    model.addNote(note3, 50);
    model.addNote(note2, 52);
    model.addNote(note3, 54);
    model.addNote(note5, 56);
    model.addNote(note8, 56);
    model.addNote(note6, 48);
    IObservableMusicMakerModel<MusicNote> model1 = model;
    TextView testview = new TextView();
    testview.update(model1);
    assertEquals(
            "    E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4   F4  F#4   G4 \n" +
            " 0                 X                                            X                 \n" +
            " 1                 |                                            |                 \n" +
            " 2                 |                                  X                           \n" +
            " 3                 |                                  |                           \n" +
            " 4                 |                        X                                     \n" +
            " 5                 |                        |                                     \n" +
            " 6                 |                                  X                           \n" +
            " 7                                                    |                           \n" +
            " 8                 X                                            X                 \n" +
            " 9                 |                                            |                 \n" +
            "10                 |                                            X                 \n" +
            "11                 |                                            |                 \n" +
            "12                 |                                            X                 \n" +
            "13                 |                                            |                 \n" +
            "14                 |                                            |                 \n" +
            "15                                                                                \n" +
            "16                 X                                  X                           \n" +
            "17                 |                                  |                           \n" +
            "18                 |                                  X                           \n" +
            "19                 |                                  |                           \n" +
            "20                 |                                  X                           \n" +
            "21                 |                                  |                           \n" +
            "22                 |                                  |                           \n" +
            "23                 |                                  |                           \n" +
            "24                 X                                            X                 \n" +
            "25                 |                                            |                 \n" +
            "26                                                                             X  \n" +
            "27                                                                             |  \n" +
            "28                                                                             X  \n" +
            "29                                                                             |  \n" +
            "30                                                                             |  \n" +
            "31                                                                             |  \n" +
            "32                 X                                            X                 \n" +
            "33                 |                                            |                 \n" +
            "34                 |                                  X                           \n" +
            "35                 |                                  |                           \n" +
            "36                 |                        X                                     \n" +
            "37                 |                        |                                     \n" +
            "38                 |                                  X                           \n" +
            "39                 |                                  |                           \n" +
            "40                 X                                            X                 \n" +
            "41                 |                                            |                 \n" +
            "42                 |                                            X                 \n" +
            "43                 |                                            |                 \n" +
            "44                 |                                            X                 \n" +
            "45                 |                                            |                 \n" +
            "46                 |                                            X                 \n" +
            "47                 |                                            |                 \n" +
            "48                 X                                  X                           \n" +
            "49                 |                                  |                           \n" +
            "50                 |                                  X                           \n" +
            "51                 |                                  |                           \n" +
            "52                 |                                            X                 \n" +
            "53                 |                                            |                 \n" +
            "54                 |                                  X                           \n" +
            "55                 |                                  |                           \n" +
            "56  X                                       X                                     \n" +
            "57  |                                       |                                     \n" +
            "58  |                                       |                                     \n" +
            "59  |                                       |                                     \n" +
            "60  |                                       |                                     \n" +
            "61  |                                       |                                     \n" +
            "62  |                                       |                                     \n" +
            "63  |                                       |                                     ",
            testview.printScore());
  }

  @Test
  public void testCombine() {
    model.addNote(note1, 0);
    model.addNote(note2, 0);
    model.addNote(note3, 2);
    model.addNote(note4, 4);
    model.addNote(note3, 6);
    model.addNote(note1, 8);
    model.addNote(note2, 8);
    model.addNote(note2, 10);
    model.addNote(note10, 12);
    model.addNote(note6, 16);
    model.addNote(note3, 16);
    model.addNote(note3, 18);
    model.addNote(note9, 20);
    model.addNote(note7, 24);
    model.addNote(note2, 24);
    model.addNote(note11, 26);
    model.addNote(note12, 28);
    model.addNote(note6, 32);
    model.addNote(note2, 32);
    model.addNote(note3, 34);
    model.addNote(note4, 36);
    model.addNote(note3, 38);
    model.addNote(note6, 40);
    model.addNote(note2, 40);
    model.addNote(note2, 42);
    model.addNote(note2, 44);
    model.addNote(note2, 46);
    model.addNote(note3, 48);
    model.addNote(note3, 50);
    model.addNote(note2, 52);
    model.addNote(note3, 54);
    model.addNote(note5, 56);
    model.addNote(note8, 56);
    model.addNote(note6, 48);
    IMusicMakerModel<MusicNote> model1 = new MusicMakerModel();
    model1.addNote(note1, 0);
    model1.addNote(note2, 0);
    model1.addNote(note3, 2);
    model1.addNote(note4, 4);
    model1.addNote(note3, 6);
    model1.addNote(note1, 8);
    model1.addNote(note2, 8);
    model1.addNote(note2, 10);
    model1.addNote(note10, 12);
    model1.addNote(note6, 16);
    model1.addNote(note3, 16);
    model1.addNote(note3, 18);
    model1.addNote(note9, 20);
    model1.addNote(note7, 24);
    model1.addNote(note2, 24);
    model1.addNote(note11, 26);
    model1.addNote(note12, 28);
    model1.addNote(note6, 32);
    model1.addNote(note2, 32);
    model1.addNote(note3, 34);
    model1.addNote(note4, 36);
    model1.addNote(note3, 38);
    model1.addNote(note6, 40);
    model1.addNote(note2, 40);
    model1.addNote(note2, 42);
    model1.addNote(note2, 44);
    model1.addNote(note2, 46);
    model1.addNote(note3, 48);
    model1.addNote(note3, 50);
    model1.addNote(note2, 52);
    model1.addNote(note3, 54);
    model1.addNote(note5, 56);
    model1.addNote(note8, 56);
    model1.addNote(note6, 48);
    model.combine(model1, true);
    TextView testview = new TextView();
    testview.update(model);
    assertEquals(
            "    E3   F3  F#3   G3  G#3   A3  A#3   B3   C4  C#4   D4  D#4   E4   F4  F#4   G4 \n" +
            " 0                 X                                            X                 \n" +
            " 1                 |                                            |                 \n" +
            " 2                 |                                  X                           \n" +
            " 3                 |                                  |                           \n" +
            " 4                 |                        X                                     \n" +
            " 5                 |                        |                                     \n" +
            " 6                 |                                  X                           \n" +
            " 7                                                    |                           \n" +
            " 8                 X                                            X                 \n" +
            " 9                 |                                            |                 \n" +
            "10                 |                                            X                 \n" +
            "11                 |                                            |                 \n" +
            "12                 |                                            X                 \n" +
            "13                 |                                            |                 \n" +
            "14                 |                                            |                 \n" +
            "15                                                                                \n" +
            "16                 X                                  X                           \n" +
            "17                 |                                  |                           \n" +
            "18                 |                                  X                           \n" +
            "19                 |                                  |                           \n" +
            "20                 |                                  X                           \n" +
            "21                 |                                  |                           \n" +
            "22                 |                                  |                           \n" +
            "23                 |                                  |                           \n" +
            "24                 X                                            X                 \n" +
            "25                 |                                            |                 \n" +
            "26                                                                             X  \n" +
            "27                                                                             |  \n" +
            "28                                                                             X  \n" +
            "29                                                                             |  \n" +
            "30                                                                             |  \n" +
            "31                                                                             |  \n" +
            "32                 X                                            X                 \n" +
            "33                 |                                            |                 \n" +
            "34                 |                                  X                           \n" +
            "35                 |                                  |                           \n" +
            "36                 |                        X                                     \n" +
            "37                 |                        |                                     \n" +
            "38                 |                                  X                           \n" +
            "39                 |                                  |                           \n" +
            "40                 X                                            X                 \n" +
            "41                 |                                            |                 \n" +
            "42                 |                                            X                 \n" +
            "43                 |                                            |                 \n" +
            "44                 |                                            X                 \n" +
            "45                 |                                            |                 \n" +
            "46                 |                                            X                 \n" +
            "47                 |                                            |                 \n" +
            "48                 X                                  X                           \n" +
            "49                 |                                  |                           \n" +
            "50                 |                                  X                           \n" +
            "51                 |                                  |                           \n" +
            "52                 |                                            X                 \n" +
            "53                 |                                            |                 \n" +
            "54                 |                                  X                           \n" +
            "55                 |                                  |                           \n" +
            "56  X                                       X                                     \n" +
            "57  |                                       |                                     \n" +
            "58  |                                       |                                     \n" +
            "59  |                                       |                                     \n" +
            "60  |                                       |                                     \n" +
            "61  |                                       |                                     \n" +
            "62  |                                       |                                     \n" +
            "63  |                                       |                                     ",
            testview.printScore());
  }

  @Test
  public void testCombine1() {
    CompositionBuilder<MusicMakerModel> comp = new MusicMakerModel.MusicBuilder();
    TextView view = new TextView();
    MusicMakerModel model = null;
    try {
      model = MusicReader.parseFile(new FileReader("mary-little-lamb.txt"), comp);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    CompositionBuilder<MusicMakerModel> comp1 = new MusicMakerModel.MusicBuilder();
    IObservableMusicMakerModel model1 = null;
    try {
      model1 = MusicReader.parseFile(new FileReader("mary-little-lamb.txt"), comp);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    model.combine(model1, false);
    view.update(model);
    assertEquals(
            "     E4   F4  F#4   G4  G#4   A4  A#4   B4   C5  C#5   D5  D#5   E5   F5  F#5   G5 \n"
           + "  0                 X                                            X                 \n"
           + "  1                 |                                            |                 \n"
           + "  2                 |                                  X                           \n"
           + "  3                 |                                  |                           \n"
           + "  4                 |                        X                                     \n"
           + "  5                 |                        |                                     \n"
           + "  6                 |                                  X                           \n"
           + "  7                                                    |                           \n"
           + "  8                 X                                            X                 \n"
           + "  9                 |                                            |                 \n"
           + " 10                 |                                            X                 \n"
           + " 11                 |                                            |                 \n"
           + " 12                 |                                            X                 \n"
           + " 13                 |                                            |                 \n"
           + " 14                 |                                            |                 \n"
           + " 15                                                                                \n"
           + " 16                 X                                  X                           \n"
           + " 17                 |                                  |                           \n"
           + " 18                 |                                  X                           \n"
           + " 19                 |                                  |                           \n"
           + " 20                 |                                  X                           \n"
           + " 21                 |                                  |                           \n"
           + " 22                 |                                  |                           \n"
           + " 23                 |                                  |                           \n"
           + " 24                 X                                            X                 \n"
           + " 25                 |                                            |                 \n"
           + " 26                                                                             X  \n"
           + " 27                                                                             |  \n"
           + " 28                                                                             X  \n"
           + " 29                                                                             |  \n"
           + " 30                                                                             |  \n"
           + " 31                                                                             |  \n"
           + " 32                 X                                            X                 \n"
           + " 33                 |                                            |                 \n"
           + " 34                 |                                  X                           \n"
           + " 35                 |                                  |                           \n"
           + " 36                 |                        X                                     \n"
           + " 37                 |                        |                                     \n"
           + " 38                 |                                  X                           \n"
           + " 39                 |                                  |                           \n"
           + " 40                 X                                            X                 \n"
           + " 41                 |                                            |                 \n"
           + " 42                 |                                            X                 \n"
           + " 43                 |                                            |                 \n"
           + " 44                 |                                            X                 \n"
           + " 45                 |                                            |                 \n"
           + " 46                 |                                            X                 \n"
           + " 47                 |                                            |                 \n"
           + " 48                 X                                  X                           \n"
           + " 49                 |                                  |                           \n"
           + " 50                 |                                  X                           \n"
           + " 51                 |                                  |                           \n"
           + " 52                 |                                            X                 \n"
           + " 53                 |                                            |                 \n"
           + " 54                 |                                  X                           \n"
           + " 55                 |                                  |                           \n"
           + " 56  X                                       X                                     \n"
           + " 57  |                                       |                                     \n"
           + " 58  |                                       |                                     \n"
           + " 59  |                                       |                                     \n"
           + " 60  |                                       |                                     \n"
           + " 61  |                                       |                                     \n"
           + " 62  |                                       |                                     \n"
           + " 63  |                                       |                                     \n"
           + " 64                 X                                            X                 \n"
           + " 65                 |                                            |                 \n"
           + " 66                 |                                  X                           \n"
           + " 67                 |                                  |                           \n"
           + " 68                 |                        X                                     \n"
           + " 69                 |                        |                                     \n"
           + " 70                 |                                  X                           \n"
           + " 71                                                    |                           \n"
           + " 72                 X                                            X                 \n"
           + " 73                 |                                            |                 \n"
           + " 74                 |                                            X                 \n"
           + " 75                 |                                            |                 \n"
           + " 76                 |                                            X                 \n"
           + " 77                 |                                            |                 \n"
           + " 78                 |                                            |                 \n"
           + " 79                                                                                \n"
           + " 80                 X                                  X                           \n"
           + " 81                 |                                  |                           \n"
           + " 82                 |                                  X                           \n"
           + " 83                 |                                  |                           \n"
           + " 84                 |                                  X                           \n"
           + " 85                 |                                  |                           \n"
           + " 86                 |                                  |                           \n"
           + " 87                 |                                  |                           \n"
           + " 88                 X                                            X                 \n"
           + " 89                 |                                            |                 \n"
           + " 90                                                                             X  \n"
           + " 91                                                                             |  \n"
           + " 92                                                                             X  \n"
           + " 93                                                                             |  \n"
           + " 94                                                                             |  \n"
           + " 95                                                                             |  \n"
           + " 96                 X                                            X                 \n"
           + " 97                 |                                            |                 \n"
           + " 98                 |                                  X                           \n"
           + " 99                 |                                  |                           \n"
           + "100                 |                        X                                     \n"
           + "101                 |                        |                                     \n"
           + "102                 |                                  X                           \n"
           + "103                 |                                  |                           \n"
           + "104                 X                                            X                 \n"
           + "105                 |                                            |                 \n"
           + "106                 |                                            X                 \n"
           + "107                 |                                            |                 \n"
           + "108                 |                                            X                 \n"
           + "109                 |                                            |                 \n"
           + "110                 |                                            X                 \n"
           + "111                 |                                            |                 \n"
           + "112                 X                                  X                           \n"
           + "113                 |                                  |                           \n"
           + "114                 |                                  X                           \n"
           + "115                 |                                  |                           \n"
           + "116                 |                                            X                 \n"
           + "117                 |                                            |                 \n"
           + "118                 |                                  X                           \n"
           + "119                 |                                  |                           \n"
           + "120  X                                       X                                     \n"
           + "121  |                                       |                                     \n"
           + "122  |                                       |                                     \n"
           + "123  |                                       |                                     \n"
           + "124  |                                       |                                     \n"
           + "125  |                                       |                                     \n"
           + "126  |                                       |                                     \n"
           + "127  |                                       |                                     ",
            view.printScore());
  }

  @Test
  public void testGetTempo() {
    MusicMakerModel model = new MusicMakerModel();
    assertEquals(model.getTempo(), 0);
  }

  @Test
  public void testGetTempo1() {
    CompositionBuilder<MusicMakerModel> comp = new MusicMakerModel.MusicBuilder();
    MusicMakerModel model = null;
    try {
      model = MusicReader.parseFile(new FileReader("mary-little-lamb.txt"), comp);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    assertEquals(model.getTempo(), 200000);
  }
}
