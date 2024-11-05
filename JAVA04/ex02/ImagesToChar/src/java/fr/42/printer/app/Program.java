package fr._42.printer.app;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.IOException;
import javax.imageio.ImageIO;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.JCommander;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import fr._42.printer.logic.ConvertImageToChar;


class Program {

    @Parameter(names = "--white", description = "Character to use for white pixels")
    private String white;

    @Parameter(names = "--black", description = "Character to use for black pixels")
    private String black;
    public static void main(String[] args) {
        List<String> processedArgs = new ArrayList<>();
        for (String arg : args) {
            if (arg.contains("=")) {
                String[] splitArg = arg.split("=", 2);
                processedArgs.add(splitArg[0]);
                processedArgs.add(splitArg[1]);
            } else {
                processedArgs.add(arg);
            }
        }
        String[] modifiedArgs = processedArgs.toArray(new String[0]);

        Program commandLineArgs = new Program();
        JCommander.newBuilder()
                  .addObject(commandLineArgs)
                  .build()
                  .parse(modifiedArgs);

        String pathToImage = "it.bmp";
        try {
            InputStream imageStream = Program.class.getClassLoader().getResourceAsStream(pathToImage);
            BufferedImage image = ImageIO.read(imageStream);
            ConvertImageToChar convertImageToChar = new ConvertImageToChar();
            convertImageToChar.convertImageToArray(image, commandLineArgs.white, commandLineArgs.black);
            
        }
        catch(Exception e) {
            System.out.println("HERE PROB " + e.getMessage());
        }
    }
}