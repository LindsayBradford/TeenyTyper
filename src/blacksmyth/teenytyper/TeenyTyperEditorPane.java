package blacksmyth.teenytyper;

// import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.KeyStroke;

public class TeenyTyperEditorPane extends JEditorPane {

  private static final long serialVersionUID = 1L;
  
//  private static final String ENTER = "enter";
  private static final String CTRL_D = "<Control>-D";
  
  
  public TeenyTyperEditorPane() {
    super();
    this.setFocusable(true);
    this.setEditorKit(new TeenyTyperEditorKit());
    this.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
    
    this.setFont(
        new Font("Serif", Font.BOLD, 42)
    );

//    this.bindKeyStrokeToAction(
//        ENTER, 
//        KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), 
//        new EnterAction(this)
//    );
    
    this.bindKeyStrokeToAction(
        CTRL_D, 
        KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK), 
        new ExitAction()
    );
  }
  
  private void bindKeyStrokeToAction(String keyStrokeLabel, KeyStroke keyStroke, AbstractAction actionToPerform) {
    this.getInputMap(JComponent.WHEN_FOCUSED).put(keyStroke, keyStrokeLabel);
    this.getActionMap().put(keyStrokeLabel, actionToPerform);
  }
  
//  private class EnterAction extends AbstractAction {
//    private static final long serialVersionUID = 1L;
//
//    private boolean colorToggle = false;
//
//    private TeenyTyperEditorPane pane;
//    
//    public EnterAction(TeenyTyperEditorPane pane) {
//      this.pane = pane;
//    }
//    
//    public void actionPerformed(ActionEvent arg0) {
//      colorToggle = !colorToggle;
//      pane.setForeground(colorToggle == true  ? Color.RED : Color.BLACK);
//      String text = pane.getText();
//      pane.setText(text + "\n");
//    }
//  }
  
  private class ExitAction extends AbstractAction {
    private static final long serialVersionUID = 1L;
    
    public void actionPerformed(ActionEvent arg0) {
      System.exit(0);
    }
  }
}
