/**
 * TeenyTyper by Lindsay Bradford is licensed under a 
 * Creative Commons Attribution-ShareAlike 3.0 Unported License.
 *
 * Year: 2012 
 *
 */

package blacksmyth.general.swing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

public class ColourIcon implements Icon {

  private int width = 0;
  private int height = 0;
  private Color colour = Color.BLACK;

  public ColourIcon(int pixels,Color colour) {
    this.width = pixels;
    this.height = pixels;
    this.colour = colour;
  }

  
  public ColourIcon(int width, int height, Color colour) {
    this.width = width;
    this.height = height;
    this.colour = colour;
  }

  public int getIconHeight() {
    return this.height;
  }

  public int getIconWidth() {
    return this.width;
  }

  public void paintIcon(Component component, Graphics g, int x, int y) {
    g.setColor(this.colour);
    g.fillRect(x, y, this.width, this.height);
  }
}
