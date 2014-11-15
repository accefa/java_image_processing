package ch.hslu.pren.accefa.image.processing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.github.sarxos.webcam.Webcam;

public class WebCamera {

   public void saveWebCame() {
      Webcam webcam = Webcam.getDefault();
      webcam.open();
      BufferedImage image = webcam.getImage();
      try {
         ImageIO.write(image, "PNG", new File("test.png"));
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

}
