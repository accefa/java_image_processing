package pren;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Main {

   public static void eee() {
      List<Integer> list = new ArrayList<Integer>();
      list.add(1);
      list.add(2);
      list.add(3);
      list.add(4);

      ListIterator<Integer> listIterator = list.listIterator(list.size());
      while (listIterator.hasPrevious()) {
         System.out.println(listIterator.previous());
      }
   }

   public static void main(String[] args) {
      eee();
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
      // processor.saveImage("step3-greyscale");

      // 4. Read Pixel
      for (int i = 0; i < processor.getPixelWidth(); i++) {
         // System.out.println(i + ": " + processor.readPixel(200, i));
      }

   }

}
