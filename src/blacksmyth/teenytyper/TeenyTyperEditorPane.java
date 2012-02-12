/**
 * TeenyTyper by Lindsay Bradford is licensed under a 
 * Creative Commons Attribution-ShareAlike 3.0 Unported License.
 *
 * Year: 2012 
 *
 */

package blacksmyth.teenytyper;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JEditorPane;
import javax.swing.KeyStroke;

import blacksmyth.general.swing.ActionBinder;

public class TeenyTyperEditorPane extends JEditorPane {

  private static final long serialVersionUID = 1L;
  
  public TeenyTyperEditorPane() {
    super();
    this.setFocusable(true);
    this.setEditorKit(new TeenyTyperEditorKit());
    this.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
    
    this.setFont(
        new Font("Serif", Font.BOLD, 42)
    );

    ActionBinder.bindKeyStrokeToAction(
        this, 
        KeyStroke.getKeyStroke(KeyEvent.VK_D, KeyEvent.CTRL_DOWN_MASK), // <CTRL-D>
        new AbstractAction() {
          private static final long serialVersionUID = 1L;

          public void actionPerformed(ActionEvent arg0) {
            System.exit(0);
          }
        }
    );
  }
  
}
