package ex01;
import java.util.Set;
import java.util.HashSet;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

class Program {

    public static double returnSimilarity(int[] vec1, int[] vec2) {
        int numerator = 0;
        double denominator1 = 0;
        double denominator2 = 0;
        for(int i = 0; i < vec1.length; i++) {
            numerator += (vec1[i] * vec2[i]);
            denominator1 += Math.pow(vec1[i], 2);
        }
        denominator1 = Math.sqrt(denominator1);
        for(int i = 0; i < vec2.length; i++) {
            denominator2 += Math.pow(vec2[i], 2);
        }
        denominator2 = Math.sqrt(denominator2);
        return (double) numerator / (denominator1 * denominator2);
    }
    public static void fillVec(int[] vec, List<String> dict, List<String> file) {
        int i = 0;
        for(String str: dict) {
            for(String fileStr: file) {
                if(str.equals(fileStr)) {
                    vec[i] += 1;
                }
            }
            i++;
        }
    }
    public static void main(String[] args) {
        if(args.length != 2) {
            System.out.println("Invalid arguments");
            return ;
        }
        Set<String> set = new HashSet<>();
        try {
            InputStream file1 = new FileInputStream(args[0]);
            InputStream file2 = new FileInputStream(args[1]);
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(file1));
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(file2));
            List<String> dict = new ArrayList<>();
            List<String> firstCont = new ArrayList<>();
            List<String> secondCont = new ArrayList<>();
            OutputStream dictionaryFile = new FileOutputStream("/home/ijmari/Desktop/java-latest/JAVA02/ex01/dictionary.txt");
            String line;
            while ((line = reader1.readLine()) != null) {
                String[] lineSpl = line.split(" ");
                for(String l: lineSpl) {
                    firstCont.add(l);
                    set.add(l);
                }
            }
            while ((line = reader2.readLine()) != null) {
                String[] lineSpl = line.split(" ");
                for(String l: lineSpl) {
                    secondCont.add(l);
                    set.add(l);
                }
            }
            for(String item: set) {
                dict.add(item);
            }
            Collections.sort(dict);
            int vec1[] = new int[dict.size()];
            int vec2[] = new int[dict.size()];
            fillVec(vec1, dict, firstCont);
            fillVec(vec2, dict, secondCont);
            String res = "";
            for(int i = 0; i < dict.size(); i++) {
                res += dict.get(i);
                if(i + 1 != dict.size()) {
                    res += " ";
                }
            }
            byte[] byteDict = res.getBytes();
            dictionaryFile.write(byteDict);
            dictionaryFile.write("\n".getBytes());
            dictionaryFile.close();
            reader1.close();
            reader2.close();
            System.out.println((int) (returnSimilarity(vec1, vec2) * 100) / 100.0);
        }
        catch(IOException e) {

        }
    }
}