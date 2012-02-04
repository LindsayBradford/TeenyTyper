import java.awt.Color;
import java.awt.Font;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.KeyStroke;

public class TeenyTyperEditorPane extends JEditorPane {

  private static final long serialVersionUID = 1L;
  
  private static final String ENTER = "enter";
  
  public TeenyTyperEditorPane() {
    super();
    this.setFocusable(true);
    this.setEditorKit(new TeenyTyperEditorKit());
    this.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
    
    this.setFont(
        new Font("Serif", Font.BOLD, 42)
    );

    int condition = JComponent.WHEN_FOCUSED;
    InputMap inputMap = this.getInputMap(condition);
    
    inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), ENTER);

    ActionMap myActionMap = this.getActionMap();
    myActionMap.put(ENTER, new EnterAction(this));
    
  }
  
  private class EnterAction extends AbstractAction {
    private static final long serialVersionUID = 1L;

    private boolean colorToggle = false;

    private TeenyTyperEditorPane pane;
    
    public EnterAction(TeenyTyperEditorPane pane) {
      this.pane = pane;
    }
    
    public void actionPerformed(ActionEvent arg0) {
      colorToggle = !colorToggle;
      pane.setForeground(colorToggle == true  ? Color.RED : Color.BLACK);
      String text = pane.getText();
      pane.setText(text + "\n");
    }
  }
}
