package blacksmyth.teenytyper;
import java.awt.BorderLayout;

import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JWindow;
import javax.swing.WindowConstants;

public class TeenyTyperWindowFactory {

  private static final long serialVersionUID = 1L;
  
  public static JFrame createFrame() {
    JFrame newFrame = new JFrame();
    
    newFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    
    TeenyTyperEditorPane editor = new TeenyTyperEditorPane();
    editor.setMargin(new Insets(5,5,5,5));
    
    newFrame.getContentPane().add(
         new JScrollPane(editor), 
         BorderLayout.CENTER
    );

    newFrame.setBounds(20, 20, 1024, 768);

    return newFrame;
  }

  public static JWindow createWindow() {
    JWindow newWindow = new JWindow();
    
    // theFrame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    
    TeenyTyperEditorPane editor = new TeenyTyperEditorPane();
    editor.setMargin(new Insets(5,5,5,5));
    
    newWindow.getContentPane().add(
         new JScrollPane(editor), 
         BorderLayout.CENTER
    );

    newWindow.setBounds(20, 20, 1024, 768);

    return newWindow;
  }

}
