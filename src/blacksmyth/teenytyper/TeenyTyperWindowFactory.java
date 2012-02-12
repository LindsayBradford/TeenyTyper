package blacksmyth.teenytyper;
import java.awt.BorderLayout;
import java.awt.Window;

import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JWindow;
import javax.swing.RootPaneContainer;
import javax.swing.WindowConstants;

public class TeenyTyperWindowFactory {

  private static final long serialVersionUID = 1L;
  
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
  
  private static void createRootPaneContent(RootPaneContainer rootPaneContainer) {
    JPanel mainPanel = new JPanel(new BorderLayout());

    mainPanel.add(
        createMenuButtonPanel(), 
        BorderLayout.PAGE_START
    );
    
    mainPanel.add(
        createScrollableEditorPane(), 
        BorderLayout.CENTER
    );
    
    rootPaneContainer.getContentPane().add(
        mainPanel, 
        BorderLayout.CENTER
    );
  }
  
  private static JPanel createMenuButtonPanel() {
    JPanel buttonPanel = new JPanel();
    
    buttonPanel.add(new JButton("test"));
    
    return buttonPanel;
  }
  
  private static JScrollPane createScrollableEditorPane() {
    TeenyTyperEditorPane editor = new TeenyTyperEditorPane();
    editor.setMargin(new Insets(5,5,5,5));
    
    return new JScrollPane(editor);
  }
  
  private static void setWindowBounds(Window window) {
    window.setBounds(20, 20, 1024, 768);
  }
  
}
