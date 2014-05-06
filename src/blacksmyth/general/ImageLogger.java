package blacksmyth.general;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A 
 * @author linds
 *
 */
public class ImageLogger {
  private static final String PNG_EXTENSION = "png";
  
  /**
   * writes the supplied image out to the current working directory 
   * as a png file with a file name based on the current time,
   * with the format: yyyy-mm-dd-hhmmss.png
   * @param image
   */
  public static void log(BufferedImage image) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd-hhmmss");
    savePNGImage(
        image,
        sdf.format(new Date())
    );
  }
  
  private static void savePNGImage(BufferedImage image, String fileName) {
    saveImage(image, fileName, PNG_EXTENSION);
  }
  
  private static void saveImage(BufferedImage image, String fileName, String fileExtension) {
    File file = new File(fileName + "." + fileExtension);  
    try {
      javax.imageio.ImageIO.write(
          image, 
          fileExtension, 
          file
      );  
    } catch(IOException e) {  
        System.out.println("Image save error: " + e.getMessage());
    }  
  }
}
