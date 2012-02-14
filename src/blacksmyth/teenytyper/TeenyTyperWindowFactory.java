/**
 * TeenyTyper by Lindsay Bradford is licensed under a 
 * Creative Commons Attribution-ShareAlike 3.0 Unported License.
 *
 * Year: 2012 
 *
 */

package blacksmyth.teenytyper;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Hashtable;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JWindow;
import javax.swing.KeyStroke;
import javax.swing.RootPaneContainer;
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;

import blacksmyth.general.swing.ActionBinder;
import blacksmyth.general.swing.ColourIcon;

public class TeenyTyperWindowFactory {
  private static final long serialVersionUID = 1L;
  
  private static Font EDITOR_FONT = new Font("Serif", Font.BOLD, 42);
  
  private static int ICON_PIXELS = 55;
  
  private static Color TEXT_COLOURS[] = {Color.RED, Color.GREEN, Color.BLUE};
  
  private static Color DEFAULT_TEXT_COLOUR = TEXT_COLOURS[0]; 
  
  private static enum EventComponent {
    InvalidComponent,
    TeenyTyperEditor,
    ClearButton,
    RedButton,
    GreenButton,
    BlueButton,
    SendButton 
  }
  
  public static JFrame createJFrame() {
    JFrame newFrame = new JFrame();
    
    newFrame.setDefaultCloseOperation(
        WindowConstants.DO_NOTHING_ON_CLOSE
    );
    
    createRootPaneContent(newFrame);
    setWindowBounds(newFrame);

    return newFrame;
  }

  @Deprecated
  public static JWindow createJWindow() {
    JWindow newWindow = new JWindow();
    
    createRootPaneContent(newWindow);
    setWindowBounds(newWindow);

    return newWindow;
  }
  
  private static void createRootPaneContent(RootPaneContainer container) {
    Hashtable<EventComponent, JComponent> eventComponents = new Hashtable<EventComponent, JComponent>();

    JPanel mainPanel = new JPanel(new BorderLayout(5,5));

    mainPanel.add(
        Box.createHorizontalStrut(5),
        BorderLayout.EAST
    );

    mainPanel.add(
        createScrollableEditorPane(eventComponents), 
        BorderLayout.CENTER
    );
    
    mainPanel.add(
        Box.createHorizontalStrut(5),
        BorderLayout.WEST
    );
    
    mainPanel.add(
        createMenuButtonPanel(eventComponents), 
        BorderLayout.PAGE_START
    );
    
    mainPanel.add(
        Box.createVerticalStrut(5),
        BorderLayout.PAGE_END
    );
   
    container.getContentPane().add(
        mainPanel, 
        BorderLayout.CENTER
    );
    
    createEventHandlers(eventComponents);
  }
  
  private static JPanel createMenuButtonPanel(Hashtable<EventComponent, JComponent> eventComponents) {
    JPanel buttonPanel = new JPanel();
    
    final int HORIZONTAL_STRUT_WIDTH = 34;
    
    final JLabel editorLabel = new JLabel("TeenyTyper");
    editorLabel.setFont(EDITOR_FONT);
    
    buttonPanel.add(
        editorLabel
    );

    buttonPanel.add(
        Box.createHorizontalStrut(HORIZONTAL_STRUT_WIDTH)
    );
    
    buttonPanel.add(
        createClearButton(eventComponents)
    );
    
    buttonPanel.add(
        Box.createHorizontalStrut(HORIZONTAL_STRUT_WIDTH)
    );
    
    createTextColourButtons(buttonPanel, eventComponents);
    
    return buttonPanel;
  }

  private static void setColourButtonSizeAndBorder(AbstractButton theButton) {
    theButton.setRequestFocusEnabled(false);
    theButton.setSize(ICON_PIXELS, ICON_PIXELS);
    theButton.setBorder(
        new MatteBorder(5, 5, 5, 5, Color.LIGHT_GRAY)
    );
  }
  
  private static JButton createClearButton(Hashtable<EventComponent, JComponent> eventComponents) {
     JButton clearButton = new JButton();

     setColourButtonSizeAndBorder(clearButton);
     
     clearButton .setIcon(
         new ColourIcon(
             ICON_PIXELS, 
             Color.WHITE
         )
     );

     clearButton .setSelectedIcon(
         new ColourIcon(
             ICON_PIXELS, 
             Color.LIGHT_GRAY
         )
     );

     eventComponents.put(
         EventComponent.ClearButton, 
         clearButton
     );
     
    return clearButton;
  }
  
  private static EventComponent getEventComponentFor(Color colour) throws IllegalArgumentException {
    if (colour == Color.RED) return EventComponent.RedButton;
    if (colour == Color.GREEN) return EventComponent.GreenButton;
    if (colour == Color.BLUE) return EventComponent.BlueButton;
    
    // Break the running of the editor until we supply an EventComponent mapping to the colour.
    
    throw new IllegalArgumentException("No EventComponent mapping for colour specified: " + colour.toString());
  }

  private static void createTextColourButtons(
                          JPanel buttonPanel, 
                          Hashtable<EventComponent, JComponent> eventComponents) {

    ButtonGroup colourButtonGroup = new ButtonGroup();
    
    for (Color textColour : TEXT_COLOURS) {
      
      JToggleButton colourButton = createColourButton(
          textColour, 
          getEventComponentFor(textColour), 
          eventComponents
      );  
      
      buttonPanel.add(colourButton);

      // Ensure only one colour button is toggled active at any one time by
      // adding the button to colourButtonGroup.

      colourButtonGroup.add(colourButton);
      
      if (textColour == DEFAULT_TEXT_COLOUR) {
        colourButtonGroup.setSelected(
            colourButton.getModel(), 
            true
        );
      }
      
    }
  }
 
  private static JToggleButton createColourButton(
                                  Color colour, 
                                  EventComponent eventComponentID, 
                                  Hashtable<EventComponent, JComponent> eventComponents) {
    
    JToggleButton theButton = new JToggleButton();

    setColourButtonSizeAndBorder(theButton);
    
    theButton.setIcon(
        new ColourIcon(
            ICON_PIXELS, 
            colour.darker().darker()
        )
    );

    theButton.setSelectedIcon(
        new ColourIcon(
            ICON_PIXELS, 
            colour
        )
    );
    
    eventComponents.put(
        eventComponentID, 
        theButton
    );
    
   return theButton;
  }
  
  private static JScrollPane createScrollableEditorPane(Hashtable<EventComponent, JComponent> eventComponents) {
    TeenyTyperEditorPane editor = new TeenyTyperEditorPane(EDITOR_FONT, TEXT_COLOURS, DEFAULT_TEXT_COLOUR);

    eventComponents.put(
        EventComponent.TeenyTyperEditor, 
        editor
    );
    
    return new JScrollPane(editor);
  }

  private static void createEventHandlers(Hashtable<EventComponent, JComponent> eventComponents) {

    final TeenyTyperEditorPane editor = (TeenyTyperEditorPane) eventComponents.get(EventComponent.TeenyTyperEditor);
    
    ActionBinder.bindKeyStrokeToAction(
        editor, 
        KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK), // <CTRL-D>
        new AbstractAction() {
          private static final long serialVersionUID = 1L;

          public void actionPerformed(ActionEvent arg0) {
            System.exit(0);
          }
        }
    );
    
    JButton clearButton = (JButton) eventComponents.get(EventComponent.ClearButton);

    clearButton.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent arg0) {
            editor.setText("");
            editor.requestFocusInWindow();
          }
        }
    );
    
    for (Color textColour : TEXT_COLOURS) {
      
      JToggleButton colourButton = (JToggleButton) eventComponents.get(
          getEventComponentFor(textColour)
      );
      
      colourButton.addActionListener(
          new ColourActionListener(editor, textColour) 
      );

    }
  }
  
  private static void setWindowBounds(Window window) {
    window.setBounds(20, 20, 1024, 768);
  }
}

class ColourActionListener implements ActionListener {
  private Color colour;
  private TeenyTyperEditorPane editor;
  
  public ColourActionListener(TeenyTyperEditorPane editor, Color colour) {
    this.editor = editor;
    this.colour = colour;
  }

  public void actionPerformed(ActionEvent arg0) {
    editor.setTextColour(colour);
    editor.requestFocusInWindow();
  }
}
