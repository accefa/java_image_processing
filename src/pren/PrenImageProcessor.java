package pren;

import ij.ImagePlus;
import ij.plugin.ContrastEnhancer;
import ij.process.ImageConverter;
import ij.process.ImageProcessor;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class PrenImageProcessor {

   private ImagePlus image;

   public PrenImageProcessor(File file) {
      image = new ImagePlus(file.getAbsolutePath());
   }

   public void saveImage(String name) {
      try {
         ImageIO.write(image.getBufferedImage(), "jpg", new File("output" + File.separator + name + ".jpg"));
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public void crop(int x, int y, int targetWidth, int targetHeight) {
      ImageProcessor ip = image.getProcessor();
      ip.setRoi(x, y, targetWidth, targetHeight);
      ip = ip.crop();
      image = new ImagePlus("croppedImage", ip);
   }

   public void contrastIt() {
      ContrastEnhancer enh = new ContrastEnhancer();
      int i = 50; // i < 100
      enh.stretchHistogram(image, i);
   }

   public void greyscaleIt() {
      ImageConverter converter = new ImageConverter(image);
      converter.convertToGray32();
      // converter.convertToGray8();
      // converter.convertToHSB();
   }

   public String readPixel(int x, int y) {
      ImageProcessor imp = image.getProcessor();
      int[] rgb = new int[3];
      imp.getPixel(x, y, rgb);
      return Arrays.toString(rgb);
   }

   public int getPixelWidth() {
      return image.getWidth();
   }

}
