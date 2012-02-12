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
import java.awt.Window;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JWindow;
import javax.swing.RootPaneContainer;
import javax.swing.WindowConstants;
import javax.swing.border.MatteBorder;

import blacksmyth.general.swing.ColourIcon;

public class TeenyTyperWindowFactory {
  private static final long serialVersionUID = 1L;
  
  private static int ICON_PIXELS = 55;
  
  private static enum EventComponent {
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

    JPanel mainPanel = new JPanel(new BorderLayout());

    mainPanel.add(
        createScrollableEditorPane(eventComponents), 
        BorderLayout.CENTER
    );
    
    mainPanel.add(
        createMenuButtonPanel(eventComponents), 
        BorderLayout.PAGE_START
    );
    
    container.getContentPane().add(
        mainPanel, 
        BorderLayout.CENTER
    );
    
    createEventHandlers(eventComponents);
  }

  private static JPanel createMenuButtonPanel(Hashtable<EventComponent, JComponent> eventComponents) {
    JPanel buttonPanel = new JPanel();
    
    final int HORIZONTAL_STRUT_WIDTH = 20;
    
    buttonPanel.add(
        createClearButton(eventComponents)
    );
    
    buttonPanel.add(
        Box.createHorizontalStrut(HORIZONTAL_STRUT_WIDTH)
    );
    
    createTextColourButtons(buttonPanel, eventComponents);
    
    buttonPanel.add(
        Box.createHorizontalStrut(HORIZONTAL_STRUT_WIDTH)
    );
    
    buttonPanel.add(new JButton("send"));
    
    return buttonPanel;
  }
  
  private static JButton createClearButton(Hashtable<EventComponent, JComponent> eventComponents) {
     JButton clearButton = new JButton();

     clearButton.setSize(ICON_PIXELS, ICON_PIXELS);
     clearButton.setBorder(
         new MatteBorder(5, 5, 5, 5, Color.LIGHT_GRAY)
     );
     
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

  private static void createTextColourButtons(JPanel buttonPanel, Hashtable<EventComponent, JComponent> eventComponents) {
    buttonPanel.add(
        createRedButton(eventComponents)
    );

    buttonPanel.add(
        createGreenButton(eventComponents)
    );

    buttonPanel.add(
        createBlueButton(eventComponents)
    );
    
    // Ensure only one colour button is toggled active at any one time.
    
    ButtonGroup colourButtons = new ButtonGroup();
    colourButtons.add(
        (AbstractButton) eventComponents.get(EventComponent.RedButton)
    );

    colourButtons.add(
        (AbstractButton) eventComponents.get(EventComponent.GreenButton)
    );

    colourButtons.add(
        (AbstractButton) eventComponents.get(EventComponent.BlueButton)
    );
   }
  
  private static JToggleButton createRedButton(Hashtable<EventComponent, JComponent> eventComponents) {
    return createColourButton(
        Color.RED, 
        EventComponent.RedButton, 
        eventComponents
    );  
  }

  private static JToggleButton createGreenButton(Hashtable<EventComponent, JComponent> eventComponents) {
    return createColourButton(
        Color.GREEN, 
        EventComponent.GreenButton, 
        eventComponents
    );  
  }
  
  private static JToggleButton createBlueButton(Hashtable<EventComponent, JComponent> eventComponents) {
    return createColourButton(
        Color.BLUE, 
        EventComponent.BlueButton, 
        eventComponents
    );  
  }
  
  private static JToggleButton createColourButton(Color colour, EventComponent eventComponentID, Hashtable<EventComponent, JComponent> eventComponents) {
    JToggleButton theButton = new JToggleButton();
    
    theButton.setSize(ICON_PIXELS, ICON_PIXELS);
    theButton.setBorder(
        new MatteBorder(5, 5, 5, 5, Color.LIGHT_GRAY)
    );
    
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
    TeenyTyperEditorPane editor = new TeenyTyperEditorPane();
    editor.setMargin(new Insets(5,5,5,5));

    eventComponents.put(
        EventComponent.TeenyTyperEditor, 
        editor
    );
    
    return new JScrollPane(editor);
  }

  private static void createEventHandlers(Hashtable<EventComponent, JComponent> eventComponents) {
    
    final TeenyTyperEditorPane editor = (TeenyTyperEditorPane) eventComponents.get(EventComponent.TeenyTyperEditor);
    
    JButton clearButton = (JButton) eventComponents.get(EventComponent.ClearButton);

    clearButton.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent arg0) {
            editor.setText("");
            editor.requestFocus();
          }
        }
    );
    
    JToggleButton redButton = (JToggleButton) eventComponents.get(EventComponent.RedButton);
    redButton.addActionListener(
        new ColourActionListener(editor, Color.RED) 
    );

    JToggleButton greenButton = (JToggleButton) eventComponents.get(EventComponent.GreenButton);
    greenButton.addActionListener(
        new ColourActionListener(editor, Color.GREEN) 
    );

    JToggleButton blueButton = (JToggleButton) eventComponents.get(EventComponent.BlueButton);
    blueButton.addActionListener(
        new ColourActionListener(editor, Color.BLUE) 
    );
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
    editor.requestFocus();
  }
}
