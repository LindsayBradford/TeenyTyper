/**
 * TeenyTyper by Lindsay Bradford is licensed under a 
 * Creative Commons Attribution-ShareAlike 3.0 Unported License.
 *
 * Year: 2012 
 *
 */

package blacksmyth.general.swing;

import java.util.UUID;

import javax.swing.AbstractAction;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public final class ActionBinder {

  /**
   * Binds the supplied <code>actionToPerform</code> to the supplied <code>keyStroke</code> 
   * for the given <code>component</code>. <p>
   * This is a convenience method that auto-generates the necessary <code>keyStrokeLabel</code> for the 
   * caller when mapping the <code>keyStroke</code> to the <code>actionToPerform</code>.
   * @see #bindKeyStrokeToAction(JComponent, String, KeyStroke, AbstractAction)
   * @param component
   * @param keyStroke
   * @param actionToPerform
   */
  public static void bindKeyStrokeToAction(JComponent component, 
                                           KeyStroke keyStroke, 
                                           AbstractAction actionToPerform) {
    bindKeyStrokeToAction(
        component, 
        UUID.randomUUID().toString(),  // just use a random (semi-)unique label we don't need to track
        keyStroke, 
        actionToPerform
    );
  }

  /**
   * Binds the supplied <code>actionToPerform</code> to the supplied 
   * <code>keyStroke</code> for the given <code>component</code> 
   * via the <code>keyStrokeLabel</code>. <p>
   * Note that the <code>keyStroke</code> will trigger <code>actionToPerform</code>
   * whenever the component is in a focused window.
   * @see JComponent#WHEN_IN_FOCUSED_WINDOW
   * @param component
   * @param keyStrokeLabel
   * @param keyStroke
   * @param actionToPerform
   */
  
  public static void bindKeyStrokeToAction(JComponent component, 
                                           String keyStrokeLabel, 
                                           KeyStroke keyStroke, 
                                           AbstractAction actionToPerform) {
    
    InputMap inputMap = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    
    inputMap.put(
        keyStroke, 
        keyStrokeLabel
    );

    component.getActionMap().put(
        keyStrokeLabel, 
        actionToPerform
    );
  }
}
