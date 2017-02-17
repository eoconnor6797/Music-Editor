IObservableMusicMakerModel<T>:
    Model classes implementing this interface contain methods that allow the user to view but not
    edit information from the piece of music stored in the implemented model.

IMusicMakerModel<T>:
    This is the interface for the music editor. It contains methods that allow the user to
    pass information to any relevant classes such as controllers. It also has methods to allow the
    user to add, remove, or edit notes in the music piece. It is an extension of the
    IObservableMusicMakerModel interface.

MusicMakerModel:
    This is an implementation of the IMusicMakerModel interface abstracted over the MusicNote type.
    It stores the information related to the score of the music piece it represents in a 2D
    ArrayList of MusicNotes. Additionally, the tempo (in microseconds per beat) of the music piece
    is stored as a field in the class. It also contains an implementation of the CompositionBuilder
    which allows for easier creation of complex music pieces.

MusicNote:
    This is a data type which represents a standard musical note on a western scale. It contains
    fields which pertain to the pitch, octave, duration, volume. It also has a field which denotes
    the note as the beginning of a note being played or a note being sustained which already
    already started on a previous beat. The instrument the note is supposedly being played in is
    also stored in an integer value for ease of use with the Java MIDI library channels. Its two
    constructors are for general use and for use with MIDI, respectively. All of the public methods
    in this class are used for access to the class's fields.

Pitch:
    This is an enumeration which represents the 12 pitches that a given note can be in a standard
    octave range.

Controller:
    This is the controller which acts as a medium between the model and the view. The controller
    has methods that allow for adding and removing notes from a GUI view as well as scrolling.
    It also allows for pausing and playing of composition views. To do this it handles both mouse
    and keyboard inputs.

KeyboardHandler:
    Class that implements keyListener as well as its methods. Handles keyboard inputs.

MouseHandler:
    Class the implements mouseListener as well as its methods. Handles mouse inputs.

IMusicView<K>:
    This is the interface for the views of the music models. All implementing classes must contain
    the methods render and update, which display the representation of the information in each view
    and update that information, respectively.

GUIView:
    This is a view which allows the rendering of a music model into a graphical representation.
    It's fields serve the purpose of storing information pertaining to the expected size of the
    window created when rendering a model as well as storing the panel which displays the actual
    representation of the music piece. It extends the JFrame class but overrides no methods from it.
    Has functionality for both keyListeners and mouseListeners to implements scrolling and score
    editing. It implements the IGUIView interface abstracted over the MusicNote type.

GUIPanel:
    This is the class used by the GUIView class which generates the panel window that displays the
    music piece in a graphical representation. It stores the information about the music piece that
    it will display in its fields. It contains methods to update its fields and overrides the
    paintComponent methods from the JPanel class it extends which creates the actual panel that is
    displayed.

@Deprecated
TextView:
    This is a view which can display the information contained within it in the console. It contains
    methods to update its fields as well as generate text to display in the console. It implements
    the IMusicView interface abstracted over the MusicNote type. The printScore in this class method
    is public for testing purposes, since it does not reside in the same package as the testing
    classes. It implements the IMusicView interface abstracted over the MusicNote type.

MIDIView:
    This is a view which can generate an audio playback of the information pertaining to a music
    piece stored within it. It uses a Sequencer from Java's MIDI library to accomplish this. The
    render method parses through the notes at the current beat and sends a message to the
    Sequencer as to whether each note should be on or off on any given beat. It implements the
    IMusicView interface abstracted over the MusicNote type.

@Removed
MockSynth:
    This is a mock Synthesizer for testing purposes. It only truly implements the getReceiver
    methods from the Synthesizer interface it implements. Its only field is the mock receiver which
    is accessed by the getReceiver method.

@Removed
MockRec:
    This is a mock Receiver class which stores the information sent to it by the send method it
    implements from the Receiver interface. This information is stored a StringBuilder and can be
    accessed through the overridden toString method in the class.

@Deprecated
ViewFactory:
    This is a builder for the three different types of views we created in this project. It allows
    for easier instantiation of new views in other classes through its create method.

GUIViewFactory:
    This is a builder for the two different types of GUIViews we created in this project. It allows
    for easier instantiation of new views in other classes through its create method.

MusicEditor:
    This class contains the main method to be executed whenever the user would like to run the
    program.

MockSeq:
    Mocks a sequencer using a StringBuilder log. Used for testing purposes.

////////////////////////////////////////////////////////////////////////////////////////////////////

UPDATES (Version 3):
    - Used most of the implementation from Evan O'Connor's HW05
    - Moved the text-based view and all related methods from the model to the TextView class
    - Added the genRange and getTempo methods to the IObservableMusicMakerModel interface.
    - Added a second constructor to the MusicMakerModel class for compatibility with the MIDI view
        and composition builder.
    - Added the getTempo method and a tempo int field to the MusicMakerModel in accordance with the
        IObservableMusicMakerModel interface.
    - Added instrument and volume fields to the MusicNote class as well as 'getter' methods
        associated with those fields.
    - Added a second constructor to the MusicNote class for compatibility with the MIDI view
        and composition builder.

UPDATES (Version 3):
    - added getNote(int pitch, int beat) to the IObservableInterface.
    - GUIView now implements IGUIView instead of IMusicView per the homework instructions.
    - added multiple methods in the IMusicView interface to add compatibility between the midi view
    and the GUI view and to add new functionality.
        - implemented methods in classes as needed.
    - added functionality in the controller to allow for key and mouse input and for IGUIView
    compatibility.
    - changed MIDIView from using a synthesizer to using a sequencer. Allows for easy pause and
    playing. (updated tests to reflect this).
    - removed MockSynth and MockRec
    - Factored controller and related classes into their own package.
    - changed MusicEditor to use GUIViewFactory to support only the new Views.

HOW TO INTERACT WITH GUI
    - the arrow keys scroll the window in the direction you would assume.
    - pressing the home key will bring you to the left most frame of the GUI.
    - pressing the end key will bring you to the right most frame of the GUI.
    - if you would like to add a note press the 'a' key. This will switch to add mode.
        - left-click once where you would like the note to begin (the intersection of the beat and
            pitch)
        - left-click again on the beat you would like the note to end.
    - if you would like to remove a note press the 'd' key. This will switch to remove mode.
        - left-click on the head of the note you would like to remove.
    - pressing 'a' or 'd' will switch you out of the other mode. i.e pressing 'a' while you are in
    remove mode will put you in add mode.

HOW TO USE COMPOSITE VIEW
    - When the view is rendered, it is in pause mode.
    - To un-pause, press the 'space' key.
    - To re-pause, press the 'p' key.
    - scrolling is automatic so no need to worry about that.