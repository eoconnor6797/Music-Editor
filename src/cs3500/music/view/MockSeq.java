package cs3500.music.view;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MetaEventListener;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;
import javax.sound.midi.Transmitter;

/**
 * This is a mock class to emulate a MIDI Sequencer. It keeps track of events sent to it by a
 * Sequence with the setSequence method in a StringBuilder log instead of playing the sounds back
 * in MIDI.
 */
public class MockSeq implements Sequencer {
  private StringBuilder log;

  /**
   * Creates a new instance of a MockSeq object.
   */
  public MockSeq() {
    this.log = new StringBuilder();
  }

  @Override
  public void setSequence(Sequence sequence) throws InvalidMidiDataException {
    for (int i = 0; i < sequence.getTracks()[0].size() - 1; i++) {
      ShortMessage msg = (ShortMessage)sequence.getTracks()[0].get(i).getMessage();

      if (msg.getCommand() == 128) {
        log.append("off ");
      }
      else if (msg.getCommand() == 144) {
        log.append("on ");
      }

      log.append(msg.getChannel() + " ");
      log.append(msg.getData1() + " ");
      log.append(sequence.getTracks()[0].get(i).getTick() + " ");

      log.append(msg.getData2() + "\n");
    }
  }

  @Override
  public void setSequence(InputStream stream) throws IOException, InvalidMidiDataException {
    // this method does nothing in this implementation
  }

  @Override
  public Sequence getSequence() {
    return null;
  }

  @Override
  public void start() {
    // this method does nothing in this implementation
  }

  @Override
  public void stop() {
    // this method does nothing in this implementation
  }

  @Override
  public boolean isRunning() {
    return false;
  }

  @Override
  public void startRecording() {
    // this method does nothing in this implementation
  }

  @Override
  public void stopRecording() {
    // this method does nothing in this implementation
  }

  @Override
  public boolean isRecording() {
    return false;
  }

  @Override
  public void recordEnable(Track track, int channel) {
    // this method does nothing in this implementation
  }

  @Override
  public void recordDisable(Track track) {
    // this method does nothing in this implementation
  }

  @Override
  public float getTempoInBPM() {
    return 0;
  }

  @Override
  public void setTempoInBPM(float bpm) {
    // this method does nothing in this implementation
  }

  @Override
  public float getTempoInMPQ() {
    return 0;
  }

  @Override
  public void setTempoInMPQ(float mpq) {
    // this method does nothing in this implementation
  }

  @Override
  public void setTempoFactor(float factor) {
    // this method does nothing in this implementation
  }

  @Override
  public float getTempoFactor() {
    return 0;
  }

  @Override
  public long getTickLength() {
    return 0;
  }

  @Override
  public long getTickPosition() {
    return 0;
  }

  @Override
  public void setTickPosition(long tick) {
    // this method does nothing in this implementation
  }

  @Override
  public long getMicrosecondLength() {
    return 0;
  }

  @Override
  public Info getDeviceInfo() {
    return null;
  }

  @Override
  public void open() throws MidiUnavailableException {
    // this method does nothing in this implementation
  }

  @Override
  public void close() {
    // this method does nothing in this implementation
  }

  @Override
  public boolean isOpen() {
    return false;
  }

  @Override
  public long getMicrosecondPosition() {
    return 0;
  }

  @Override
  public int getMaxReceivers() {
    return 0;
  }

  @Override
  public int getMaxTransmitters() {
    return 0;
  }

  @Override
  public Receiver getReceiver() throws MidiUnavailableException {
    return null;
  }

  @Override
  public List<Receiver> getReceivers() {
    return null;
  }

  @Override
  public Transmitter getTransmitter() throws MidiUnavailableException {
    return null;
  }

  @Override
  public List<Transmitter> getTransmitters() {
    return null;
  }

  @Override
  public void setMicrosecondPosition(long microseconds) {
    // this method does nothing in this implementation
  }

  @Override
  public void setMasterSyncMode(SyncMode sync) {
    // this method does nothing in this implementation
  }

  @Override
  public SyncMode getMasterSyncMode() {
    return null;
  }

  @Override
  public SyncMode[] getMasterSyncModes() {
    return new SyncMode[0];
  }

  @Override
  public void setSlaveSyncMode(SyncMode sync) {
    // this method does nothing in this implementation
  }

  @Override
  public SyncMode getSlaveSyncMode() {
    return null;
  }

  @Override
  public SyncMode[] getSlaveSyncModes() {
    return new SyncMode[0];
  }

  @Override
  public void setTrackMute(int track, boolean mute) {
    // this method does nothing in this implementation
  }

  @Override
  public boolean getTrackMute(int track) {
    return false;
  }

  @Override
  public void setTrackSolo(int track, boolean solo) {
    // this method does nothing in this implementation
  }

  @Override
  public boolean getTrackSolo(int track) {
    return false;
  }

  @Override
  public boolean addMetaEventListener(MetaEventListener listener) {
    return false;
  }

  @Override
  public void removeMetaEventListener(MetaEventListener listener) {
    // this method does nothing in this implementation
  }

  @Override
  public int[] addControllerEventListener(ControllerEventListener listener, int[] controllers) {
    return new int[0];
  }

  @Override
  public int[] removeControllerEventListener(ControllerEventListener listener, int[] controllers) {
    return new int[0];
  }

  @Override
  public void setLoopStartPoint(long tick) {
    // this method does nothing in this implementation
  }

  @Override
  public long getLoopStartPoint() {
    return 0;
  }

  @Override
  public void setLoopEndPoint(long tick) {
    // this method does nothing in this implementation
  }

  @Override
  public long getLoopEndPoint() {
    return 0;
  }

  @Override
  public void setLoopCount(int count) {
    // this method does nothing in this implementation
  }

  @Override
  public int getLoopCount() {
    return 0;
  }

  @Override
  public String toString() {
    return this.log.toString();
  }
}