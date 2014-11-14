package pren;

import java.io.File;

public class Main {

   public static void main(String[] args) {

      // String file1 = "korb-bg-white.jpg";
      String file2 = "korb-bg-yellow.jpg";

      String file = file2;

      PrenImageProcessor processor = new PrenImageProcessor(new File("testdaten-roh/" + file));

      processor.saveImage("step0-plain");

      // 1. Bild zuschneiden
      processor.crop(0, 290, 4000, 700);
      processor.saveImage("step1-crop");

      // 2. Kontrast
      processor.contrastIt();
      processor.saveImage("step2-contrast");

      // 3. Greyscale
      processor.greyscaleIt();
      processor.saveImage("step3-greyscale");

      // 4. Read Pixel
      processor.alternatePixel();
   }

}
