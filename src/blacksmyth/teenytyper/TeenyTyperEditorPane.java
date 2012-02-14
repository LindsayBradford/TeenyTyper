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

import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

public class TeenyTyperEditorPane extends JTextPane {

  private static final long serialVersionUID = 1L;
  
  public TeenyTyperEditorPane(Font editorFont, Color[] colourList, Color defaultColour) {
    super();
    this.setEditorKit(new TeenyTyperEditorKit());
    
    this.setFont(editorFont);
    
    this.setCursor(
        Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
    );
    
    for(Color editorColour : colourList) {
      Style colourStyle = this.addStyle(editorColour.toString(), null);
      StyleConstants.setForeground(colourStyle, editorColour);
    }
    
    setTextColour(defaultColour);
  }
  
  public void setTextColour(Color colour) {
    String colorText = colour.toString();
    this.setCharacterAttributes(
        this.getStyle(colorText), false
    );
  }
}
