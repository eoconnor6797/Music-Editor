package cs3500.music.view;


import java.util.ArrayList;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MetaMessage;
import javax.sound.midi.Track;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;

import cs3500.music.model.IObservableMusicMakerModel;
import cs3500.music.model.MusicNote;

/**
 * This is a view that can play back the song represented in a music model's score
 * using Java's MIDI library.
 */
public class MIDIView implements IMusicView<MusicNote> {
  private Sequencer seq;
  private ArrayList<ArrayList<MusicNote>> score;
  private int tempo;
  private Sequence mySeq;

  /**
   * Creates a new instance of a MIDIView object.
   */
  public MIDIView() {
    try {
      seq = MidiSystem.getSequencer();
    } catch (MidiUnavailableException e) {
      e.printStackTrace();
    }
    if (seq == null) {
      // Error -- sequencer device is not supported.
      // Inform user and return...
    } else {
      // Acquire resources and make operational.
      try {
        seq.open();
      } catch (MidiUnavailableException e) {
        e.printStackTrace();
      }
    }
    try {
      mySeq = new Sequence(Sequence.PPQ, 96);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }

  /**
   * Creates a new instance of a MIDIView object with the given sequencer.
   */
  public MIDIView(Sequencer seq) {
    this.seq = seq;
    if (this.seq == null) {
      // Error -- sequencer device is not supported.
      // Inform user and return...
    } else {
      // Acquire resources and make operational.
      try {
        this.seq.open();
      } catch (MidiUnavailableException e) {
        e.printStackTrace();
      }
    }
    try {
      mySeq = new Sequence(Sequence.PPQ, 96);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void render() {
    Track track = mySeq.createTrack();
    for (int beat = 0; beat < this.score.size(); beat++) {
      for (int notes = 0; notes < this.score.get(beat).size(); notes++) {
        if (this.score.get(beat).get(notes).getHead()) {
          track.add(new MidiEvent(playNote(this.score.get(beat).get(notes)), 96 * beat));
          track.add(new MidiEvent(stopNote(this.score.get(beat).get(notes)), 96
                  * (this.score.get(beat).get(notes).getDuration() + beat)));
        }
      }
    }
    try {
      seq.setSequence(mySeq);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    //tempo as a byte array
    byte[] data = new byte[3];
    data[0] = (byte) ((tempo >> 16) & 0xFF);
    data[1] = (byte) ((tempo >> 8) & 0xFF);
    data[2] = (byte) (tempo & 0xFF);

    //Set tempo
    MetaMessage mm = new MetaMessage();
    try {
      mm.setMessage(81, data, 3);
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    track.add(new MidiEvent(mm, -1));
  }

  @Override
  public int getTempo() {
    return this.tempo;
  }

  @Override
  public void update(IObservableMusicMakerModel<MusicNote> readOnlyModel) {
    this.updateScore(readOnlyModel.getScore());
    this.setTempo(readOnlyModel.getTempo());
  }

  /**
   * create a new midiMessage to start playing a given note.
   * @param note the note to be played.
   * @return the midiMessage created.
   */
  private MidiMessage playNote(MusicNote note) {
    ShortMessage start = null;
    try {
      start = new ShortMessage(
              ShortMessage.NOTE_ON, note.getInstrument(),
              note.getPitch() + note.getOctaveVal(), note.getVolume());
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    return start;
  }

  /**
   * create a new midiMessage to stop playing a given note.
   * @param note the note to be stopped.
   * @return the midiMessage created.
   */
  private MidiMessage stopNote(MusicNote note) {
    ShortMessage stop = null;
    try {
      stop = new ShortMessage(
              ShortMessage.NOTE_OFF, note.getInstrument(),
              note.getPitch() + note.getOctaveVal(), note.getVolume());
    } catch (InvalidMidiDataException e) {
      e.printStackTrace();
    }
    return stop;
  }

  /**
   * Updates the score of notes that the view has to work with.
   *
   * @param score is the score received from the model
   */
  private void updateScore(ArrayList<ArrayList<MusicNote>> score) {
    this.score = score;
  }


  @Override
  public void setTempo(int tempo) {
    this.tempo = tempo;
  }

  @Override
  public void pause() {
    this.seq.stop();
  }

  @Override
  public void play() {
    this.seq.start();
  }

  @Override
  public String toString() {
    return this.seq.toString();
  }
}
