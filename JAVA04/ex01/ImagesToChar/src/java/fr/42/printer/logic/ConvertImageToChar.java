package fr._42.printer.logic;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class ConvertImageToChar {
    private static int[][] convertTo2DUsingGetRGB(BufferedImage image) {
      int width = image.getWidth();
      int height = image.getHeight();
      int[][] result = new int[height][width];

      for (int row = 0; row < height; row++) {
         for (int col = 0; col < width; col++) {
            result[row][col] = image.getRGB(col, row);
         }
      }

      return result;
    }
    private static void displayRes(int[][] binImage, char white, char black) {
      for(int i = 0; i < 16; i++) {
        for(int j = 0; j < 16; j++) {
          System.out.print(binImage[i][j] == -1 ? white : black);
        }
        System.out.println();
      }
    }
    public static void convertImageToArray(BufferedImage image, char white, char black) {
        int[][] binImage = convertTo2DUsingGetRGB(image);
        displayRes(binImage, white, black);
    }
}