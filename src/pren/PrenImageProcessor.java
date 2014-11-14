package pren;

import ij.ImagePlus;
import ij.plugin.ContrastEnhancer;
import ij.process.ImageConverter;
import ij.process.ImageProcessor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class PrenImageProcessor {

   private File file;

   private ImagePlus image;

   public PrenImageProcessor(File file) {
      image = new ImagePlus(file.getAbsolutePath());
   }

   public void saveImage(String name) {
      this.file = new File("output" + File.separator + name + ".jpg");
      try {
         ImageIO.write(image.getBufferedImage(), "jpg", this.file);
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

   public void alternatePixel() {
      BufferedImage bi;
      try {
         bi = ImageIO.read(this.file);
         for (int x = 0; x < bi.getWidth(); x++) {
            Color c = new Color(bi.getRGB(x, 300));
            // System.out.println("red==" + c.getRed() + " green==" +
            // c.getGreen() + "    blue==" + c.getBlue());
            System.out.println(c.getGreen());
         }

         Graphics2D g2d = bi.createGraphics();
         g2d.setBackground(Color.red);
         g2d.setColor(Color.red);
         g2d.drawLine(0, 300, bi.getWidth(), 300);

         g2d.drawImage(bi, null, 0, 0);
         ImageIO.write(bi, "JPEG", new File("gogogo.jpg"));
         // for (int x = 0; x < bi.getWidth(); x++) {
         // for (int y = 0; y < bi.getHeight(); y++) {
         // Color c = new Color(bi.getRGB(x, y));
         // System.out.println("red==" + c.getRed() + " green==" + c.getGreen()
         // + "    blue==" + c.getBlue());
         // }
         // }
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }

   public int getPixelWidth() {
      return image.getWidth();
   }

}
