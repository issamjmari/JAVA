package fr._42.printer.app;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.InputStream;
import fr._42.printer.logic.ConvertImageToChar;


class Program {

    public static void main(String[] args) {
        if(args.length != 2) {
            System.out.println("Invalid set of arguments, read README.txt file please");
            System.exit(1);
        }
        char whitechar = ' ';
        char blackChar = ' ';
        String pathToImage = "it.bmp";
        try {
            String firstChar = args[0].split("=")[1];
            String secondChar = args[1].split("=")[1];
            if(firstChar.length() > 1 || secondChar.length() > 1) {
                throw new Exception("arguments invalid");
            }
            whitechar = firstChar.charAt(0);
            blackChar = secondChar.charAt(0);
        }
        catch(Exception e) {
            System.out.println("Invalid argument values, read README.txt file please");
            System.exit(1);
        }
        try {
            InputStream imageStream = Program.class.getClassLoader().getResourceAsStream(pathToImage);
            BufferedImage image = ImageIO.read(imageStream);
            ConvertImageToChar convertImageToChar = new ConvertImageToChar();
            convertImageToChar.convertImageToArray(image, whitechar, blackChar);
            
        }
        catch(Exception e) {
            System.out.println("HERE PROB " + e.getMessage());
        }
    }
}