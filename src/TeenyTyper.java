import javax.swing.JFrame;
import javax.swing.UIManager;


public class TeenyTyper extends TeenyTyperWindowFactory {

  private static final long serialVersionUID = 1L;

  /**
   * @param args
   */
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(
          "javax.swing.plaf.metal.MetalLookAndFeel"
        );
    } catch (Exception e) {}

    JFrame windowFrame = TeenyTyperWindowFactory.createFrame();
    
    windowFrame.setVisible(true);
  }

}
