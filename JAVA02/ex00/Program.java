package ex00;
import java.util.Scanner;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class Program {
    private static String checkFormat(List<String> bytes, Map<String, String> signatures) {
        for(Map.Entry<String, String> entry: signatures.entrySet()) {
            String[] splitSignature = entry.getValue().split(" ");
            int i = 0;
            boolean matches = true;
            for(String b: bytes) {
                if(i == splitSignature.length) {
                    matches = false;
                    break;
                }
                System.out.println("b " + b);
                System.out.println("splitSignature[i++] " + splitSignature[i]);
                if(b.equals(splitSignature[i++]) == false) {
                    matches = false;
                    break;
                }
            }
            if(matches == true)
                return entry.getKey();
        }
        return "";
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        Map<String, String> signatures = new HashMap<>();

        try {
            OutputStream result = new FileOutputStream("/home/ijmari/Desktop/java-latest/JAVA02/ex00/result.txt");
            InputStream inputStream = new FileInputStream("./ex00/signatures.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splStrings = line.split(", ");
                signatures.put(splStrings[0], splStrings[1]);
            }
            reader.close();
            while (true) {
                System.out.print("=> ");
                input = scanner.nextLine();
                if(input.equals("42")) {
                    result.close();
                    return ;
                }
                InputStream currInput = new FileInputStream(input);
                int byteRead;
                List<String> bytes = new ArrayList<>();
                int i = 0;
                while ((byteRead = currInput.read()) != -1) {
                    if(i == 8)
                        break;
                    bytes.add(String.format("%02X", byteRead));
                    i++;
                }
                currInput.close();
                String format = checkFormat(bytes, signatures);
                System.out.println("format " + format);
                if(format.length() == 0) {
                    System.out.println("UNDEFINED");
                    continue;
                }
                System.out.println("PROCESSED");
                byte[] resBytes = format.getBytes();
                result.write(resBytes);
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred while writing the file.");
            e.printStackTrace();
        }
    }
}
