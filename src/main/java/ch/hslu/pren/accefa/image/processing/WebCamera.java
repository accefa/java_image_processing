package ch.hslu.pren.accefa.image.processing;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

public class WebCamera {

   public void saveWebCame() {
      Webcam webcam = Webcam.getDefault();
      webcam.setViewSize(new Dimension(640, 480));
      webcam.open();
      BufferedImage image = webcam.getImage();
      // try {
      // ImageIO.write(image, "PNG", new File("test.png"));
      // } catch (IOException e) {
      // TODO Auto-generated catch block
      // e.printStackTrace();
      // }
      JFrame window = new JFrame("Test Webcam Panel");
      window.add(new WebcamPanel(Webcam.getDefault()));
      window.pack();
      window.setVisible(true);
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   }

}
