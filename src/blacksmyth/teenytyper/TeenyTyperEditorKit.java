package blacksmyth.teenytyper;
import javax.swing.text.StyledEditorKit;

public class TeenyTyperEditorKit extends StyledEditorKit {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public TeenyTyperEditorKit() {
    super();
  }
  
   @Override
  public String getContentType() {
    // TODO Auto-generated method stub
    return "text/teenyTyper";
  }
   

   
 
}
