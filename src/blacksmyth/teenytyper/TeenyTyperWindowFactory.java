package blacksmyth.teenytyper;
import java.awt.BorderLayout;
import java.awt.Window;

import java.awt.Insets;

import javax.swing.JFrame;
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
    TeenyTyperEditorPane editor = new TeenyTyperEditorPane();
    editor.setMargin(new Insets(5,5,5,5));
    
    rootPaneContainer.getContentPane().add(
        new JScrollPane(editor), 
        BorderLayout.CENTER
    );
  }
  
  private static void setWindowBounds(Window window) {
    window.setBounds(20, 20, 1024, 768);
  }
  
}
