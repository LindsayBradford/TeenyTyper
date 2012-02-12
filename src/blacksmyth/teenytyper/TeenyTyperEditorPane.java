/**
 * TeenyTyper by Lindsay Bradford is licensed under a 
 * Creative Commons Attribution-ShareAlike 3.0 Unported License.
 *
 * Year: 2012 
 *
 */

package blacksmyth.teenytyper;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

import blacksmyth.general.swing.ActionBinder;

public class TeenyTyperEditorPane extends JTextPane {

  private static final long serialVersionUID = 1L;
  
  public TeenyTyperEditorPane() {
    super();
    this.setFocusable(true);
    this.setEditorKit(new TeenyTyperEditorKit());
    
    this.setFont(
        new Font("Serif", Font.BOLD, 42)
    );
    
    this.setCursor(
        Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
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

    Style redStyle = this.addStyle(Color.RED.toString(), null);
    StyleConstants.setForeground(redStyle, Color.RED);
    
    Style greenStyle = this.addStyle(Color.GREEN.toString(), null);
    StyleConstants.setForeground(greenStyle, Color.GREEN);
    
    Style blueStyle = this.addStyle(Color.BLUE.toString(), null);
    StyleConstants.setForeground(blueStyle, Color.BLUE);
    
    setTextColour(Color.RED);
  }
  
  public void setTextColour(Color colour) {
    String colorText = colour.toString();
    this.setCharacterAttributes(
        this.getStyle(colorText), false
    );
  }
}
