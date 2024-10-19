package fr._42.printer.logic;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class ConvertImageToChar {
    private static int[][] convertTo2DUsingGetRGB(BufferedImage image) {
      int width = image.getWidth();
      int height = image.getHeight();
      int[][] result = new int[height][width];

      for (int row = 0; row < height; row++) {
        System.out.println("HERE " + row);
         for (int col = 0; col < width; col++) {
            result[row][col] = image.getRGB(col, row);
            System.out.println("col " + col + " = " + result[row][col]);
         }
      }

      return result;
    }
    public static void convertImageToArray(BufferedImage image, char white, char black) {
        int[][] binImage = convertTo2DUsingGetRGB(image);
    }
}