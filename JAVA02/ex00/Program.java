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

    private static int getMaxBytes(int i) {
        if(i == 0)
            return 8;
        else if(i == 1)
            return 6;
        else if(i == 2)
            return 6;
        else if(i == 3)
            return 3;
        else if(i == 4)
            return 4;
        else if(i == 5)
            return 4;
        else if(i == 6)
            return 3;
        else if(i == 7)
            return 2;
        else if(i == 8)
            return 7;
        else if(i == 9)
            return 4;
        return 0;
    }
    private static int formatTypeBytes(List<String> bytes) {
        String[] arr = {
            "89 50 4E 47 0D 0A 1A 0A",
            "47 49 46 38 37 61",
            "47 49 46 38 39 61",
            "FF D8 FF",
            "25 50 44 46",
            "50 4B 03 04",
            "49 44 33",
            "4D 5A",
            "52 61 72 21 1A 07 00",
            "52 49 46 46"
        };
        int i = 0;
        for(String format: arr) {
            String[] splFormat = format.split(" ");
            int j = 0;
            boolean equals = true;
            if(splFormat.length != bytes.size())
                continue;
            for(String spl: splFormat) {
                if(!spl.equals(bytes.get(j))) {
                    equals = false;
                    i++;
                    break;
                }
                j++;
            }
            if(equals == true)
                return getMaxBytes(i);
            i++;
        }
        return -1;
    }
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
                    if(formatTypeBytes(bytes) != -1)
                        break;
                    i++;
                }
                currInput.close();
                String format = checkFormat(bytes, signatures);
                if(format.length() == 0) {
                    System.out.println("UNDEFINED");
                    continue;
                }
                System.out.println("PROCESSED");
                byte[] resBytes = format.getBytes();
                result.write(resBytes);
                result.write("\n".getBytes());
            }
        }
        catch (IOException e) {
            System.out.println("An error occurred while writing the file.");
            e.printStackTrace();
        }
    }
}
