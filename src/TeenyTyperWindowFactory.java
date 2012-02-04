import java.awt.BorderLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;


public class TeenyTyperWindowFactory extends JFrame {

  private static final long serialVersionUID = 1L;

  public static JFrame createFrame() {
    JFrame theFrame = new JFrame();
    
    theFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    
    TeenyTyperEditorPane editor = new TeenyTyperEditorPane();
    editor.setMargin(new Insets(5,5,5,5));
    
    theFrame.getContentPane().add(
         new JScrollPane(editor), 
         BorderLayout.CENTER
    );

    theFrame.setTitle("Teeny Typer");
    theFrame.setBounds(20, 20, 1024, 768);
    theFrame.setResizable(true);

    return theFrame;
  }

}
