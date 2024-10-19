import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.DirectoryStream;
import java.io.IOException;

class Program {

    public static long retFileSize(long fileSize) {
        if(fileSize >= 1000000000) {
            return fileSize / 1000000000;
        }
        else if(fileSize >= 1000000) {
            return fileSize / 1000000;
        }
        else if (fileSize >= 1000) {
            return fileSize / 1000;
        }
        return fileSize;
    }
        public static String retFileFormat(long fileSize) {
        if(fileSize >= 1000000000) {
            return "GB";
        }
        else if(fileSize >= 1000000) {
            return "MB";
        }
        else if (fileSize >= 1000) {
            return "KB";
        }
        return "B";
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        String directory = args[0].split("=")[1];
        while(true) {
            System.out.print("=> ");
            input = scanner.nextLine();
            Path dirPath = Paths.get(directory);
            if (input.equals("ls")) {
                if (Files.isDirectory(dirPath)) {
                    try (DirectoryStream<Path> stream = Files.newDirectoryStream(dirPath)) {
                        for (Path entry : stream) {
                            System.out.print("    " + entry.getFileName() + " ");
                            long fileSize = Files.size(entry);
                            System.out.print(retFileSize(fileSize));
                            System.out.println(" " + retFileFormat(fileSize));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } 
                else {
                    System.out.println("The path is not a directory.");
                }
            }
            String[] splComm = input.split(" ");
            if(splComm[0].equals("cd")) {
                Path inDir = dirPath.resolve(splComm[1]);
                directory = inDir.toAbsolutePath().toString();
                System.out.println(directory);
            }
            else if (splComm[0].equals("mv")) {

            }
            else {
                System.out.println("Invalid Command, try again please");
                continue;
            }
        }
    }
}