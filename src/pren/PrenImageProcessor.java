package pren;

import ij.ImagePlus;
import ij.plugin.ContrastEnhancer;
import ij.process.ImageConverter;
import ij.process.ImageProcessor;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PrenImageProcessor {

   private ImagePlus image;

   private File destFolder;

   private String executetOperations = "";

   public PrenImageProcessor(File file) {
      image = new ImagePlus(file.getAbsolutePath());

      Date date = new Date();
      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
      destFolder = new File("output" + File.separator + formatter.format(date));
      destFolder.mkdir();
   }

   public void saveImage(String name) {
      File destFile = new File(destFolder.getAbsolutePath() + File.separator + name + ".jpg");
      try {
         ImageIO.write(image.getBufferedImage(), "jpg", destFile);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   public void crop(int x, int y, int targetWidth, int targetHeight) {
      ImageProcessor ip = image.getProcessor();
      ip.setRoi(x, y, targetWidth, targetHeight);
      ip = ip.crop();
      image = new ImagePlus("croppedImage", ip);
      executetOperations += "cropped x=" + " y=" + y + " width=" + targetWidth + " height=" + targetHeight + "; ";
   }

   public void contrastIt() {
      ContrastEnhancer enh = new ContrastEnhancer();
      int i = 50; // i < 100 --> 50 guter Wert
      enh.stretchHistogram(image, i);
      executetOperations += "contrast by " + i + "; ";
   }

   public void greyscaleIt() {
      ImageConverter converter = new ImageConverter(image);
      converter.convertToGray32();
      executetOperations += "greyscaled32; ";
      // converter.convertToGray8();
      // converter.convertToHSB();
   }

   public void drawHorizontalLine(int y) {
      BufferedImage bi = this.image.getBufferedImage();
      Graphics2D g2d = bi.createGraphics();
      g2d.setBackground(Color.red);
      g2d.setColor(Color.red);
      g2d.drawLine(0, 300, bi.getWidth(), 300);
      g2d.drawImage(bi, null, 0, 0);
      this.image = new ImagePlus("test", bi);
      executetOperations += "draw horizontal line y=" + y + "; ";
   }

   public void generateExcel(int y) {
      File templateFile = new File("template" + File.separator + "template.xlsx");
      File destFile = new File(destFolder.getAbsolutePath() + File.separator + "versuch.xlsx");
      copyFileUsingStream(templateFile, destFile);

      try {
         FileInputStream file = new FileInputStream(destFile);

         // Get the workbook instance for XLS file
         XSSFWorkbook workbook = new XSSFWorkbook(file);

         // Get first sheet from the workbook
         XSSFSheet sheet = workbook.getSheetAt(0);

         XSSFRow rowt = sheet.createRow(0);
         rowt.createCell(0).setCellValue(executetOperations);

         BufferedImage bi = this.image.getBufferedImage();
         for (int x = 0; x < bi.getWidth(); x++) {
            Color c = new Color(bi.getRGB(x, y));

            XSSFRow row = sheet.createRow(x + 2);
            row.createCell(0).setCellValue(c.getRed());
            row.createCell(1).setCellValue(c.getRed());
            row.createCell(2).setCellValue(c.getGreen());
         }

         FileOutputStream fileOut = new FileOutputStream(destFile.getAbsolutePath());
         workbook.write(fileOut);
         fileOut.flush();
         fileOut.close();
         workbook.close();

      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   private static void copyFileUsingStream(File source, File dest) {
      InputStream is = null;
      OutputStream os = null;
      try {
         is = new FileInputStream(source);
         os = new FileOutputStream(dest);
         byte[] buffer = new byte[1024];
         int length;
         while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
         }
         is.close();
         os.close();
      } catch (IOException e) {
         e.printStackTrace();
      } finally {

      }
   }
}
