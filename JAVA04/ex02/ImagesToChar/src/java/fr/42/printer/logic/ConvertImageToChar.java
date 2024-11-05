package fr._42.printer.logic;

import java.awt.image.BufferedImage;
import java.io.IOException;
import com.diogonunes.jcdp.color.api.Ansi;
import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi.BColor;
import com.diogonunes.jcdp.color.api.Ansi.FColor;


public class ConvertImageToChar {
    private static BColor getColorFromArgument(String colorName) {
      if (colorName == null) {
          return BColor.WHITE;
      }
      switch (colorName.toUpperCase()) {
          case "RED": return BColor.RED;
          case "GREEN": return BColor.GREEN;
          case "BLUE": return BColor.BLUE;
          case "CYAN": return BColor.CYAN;
          case "YELLOW": return BColor.YELLOW;
          case "MAGENTA": return BColor.MAGENTA;
          case "BLACK": return BColor.BLACK;
          default: return BColor.WHITE;
      }
  }
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
    private static void displayRes(int[][] binImage, BColor white, BColor black) {
      ColoredPrinter whitePrinter = new ColoredPrinter.Builder(1, false)
      .foreground(FColor.NONE)
      .background(white)
      .build();

      ColoredPrinter blackPrinter = new ColoredPrinter.Builder(1, false)
            .foreground(FColor.NONE)
            .background(black)
            .build();
      for(int i = 0; i < 16; i++) {
        for(int j = 0; j < 16; j++) {
          if(binImage[i][j] == -1) {
              whitePrinter.print(" ");
          }
          else {
            blackPrinter.print(" ");
          }
        }
        System.out.println();
      }
    }
    public static void convertImageToArray(BufferedImage image, String white, String black) {
        BColor whiteColor = getColorFromArgument(white);
        BColor blackColor = getColorFromArgument(black);
        int[][] binImage = convertTo2DUsingGetRGB(image);
        displayRes(binImage, whiteColor, blackColor);
    }
}