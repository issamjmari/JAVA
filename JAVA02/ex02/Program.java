import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.DirectoryStream;
import java.io.IOException;

class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        String directory = args[0].split("=")[1];
        while(true) {
            try {
                input = scanner.nextLine();
                if (input.equals("ls")) {
                    try {
                        Path dirPath = Paths.get(directory);
                        if (Files.isDirectory(path)) {
                            try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
                                for (Path entry : stream) {
                                    System.out.println(entry.getFileName());
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } 
                        else {
                            System.out.println("The path is not a directory.");
                        }
                    }
                    catch(InvalidPathException e) {
                        System.err.println("Error: Invalid path - " + e.getMessage());
                    }
                }
                else if(input.equals("cd")) {

                }
                else if(input.equals(""))
            }
            catch (Exception e) {

            }
        }
    }
}