package blacksmyth.teenytyper;
import java.awt.BorderLayout;
import java.awt.Window;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JWindow;
import javax.swing.RootPaneContainer;
import javax.swing.WindowConstants;

public class TeenyTyperWindowFactory {
  private static final long serialVersionUID = 1L;
  
  private static int HORIZONTAL_STRUT_WIDTH = 20;
  
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
    
    buttonPanel.add(
        createClearButton(eventComponents)
    );
    
    buttonPanel.add(
        Box.createHorizontalStrut(HORIZONTAL_STRUT_WIDTH)
    );
    
    buttonPanel.add(new JToggleButton("red"));
    buttonPanel.add(new JToggleButton("green"));
    buttonPanel.add(new JToggleButton("blue"));
    
    buttonPanel.add(
        Box.createHorizontalStrut(HORIZONTAL_STRUT_WIDTH)
    );
    
    buttonPanel.add(new JButton("send"));
    
    return buttonPanel;
  }
  
  private static JButton createClearButton(Hashtable<EventComponent, JComponent> eventComponents) {
     JButton clearButton = new JButton("clear");
    
     eventComponents.put(
         EventComponent.ClearButton, 
         clearButton
     );
     
    return clearButton;
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

  }
  
  private static void setWindowBounds(Window window) {
    window.setBounds(20, 20, 1024, 768);
  }
}
