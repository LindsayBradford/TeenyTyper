/**
 * TeenyTyper by Lindsay Bradford is licensed under a 
 * Creative Commons Attribution-ShareAlike 3.0 Unported License.
 *
 * Year: 2012 
 *
 */

package blacksmyth.teenytyper;

import javax.swing.text.StyledEditorKit;

public class TeenyTyperEditorKit extends StyledEditorKit {

  private static final long serialVersionUID = 1L;

  public TeenyTyperEditorKit() {
    super();
  }
  
  @Override
  public String getContentType() {
    return "text/teenyTyper";
  }
}
