package ch.hslu.pren.accefa.image.processing;

import java.io.File;

public class Main {

   public static void main(String[] args) {

      // WebCamera cam = new WebCamera();
      // cam.saveWebCame();

      // String file1 = "korb-bg-white.jpg";
      String file2 = "korb-bg-yellow.jpg";

      String file = file2;

      PrenImageProcessor processor = new PrenImageProcessor(new File("testdaten-roh/" + file));

      processor.saveImage("step0-plain");

      // 1. Bild zuschneiden
      processor.crop(0, 290, 4000, 700);
      processor.saveImage("step10-crop");

      // processor.sharpen();
      // processor.saveImage("step12-sharpen");

      // saturation
      // processor.saturation();
      // processor.saveImage("step15-saturation");

      // 2. Kontrast
      processor.contrastIt();
      processor.saveImage("step20-contrast");

      // 3. Greyscale
      processor.greyscaleIt();
      processor.saveImage("step30-greyscale");

      // 4. Analyze Line
      int line = 300;
      processor.generateExcel(line);

      // Mark analyzed line horizontal Line
      processor.drawHorizontalLine(line);
      processor.saveImage("step40-analyzed-line");

   }

}
