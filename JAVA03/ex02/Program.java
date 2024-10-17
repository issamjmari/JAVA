import java.util.Random;
class MyThread extends Thread {
    private int index;

    MyThread(int index) {
        this.index = index;
    } 

    @Override
    public void run() {

    }
}

class Program {
    public static void main(String[] args) {
        int arraySize = 0;
        int threadsCount = 0;
        int sum = 0;
        Random random = new Random();
        try {
            arraySize = Integer.parseInt(args[0].split("=")[1]);
            threadsCount = Integer.parseInt(args[1].split("=")[1]);
        }
        catch (Exception e) {
            System.out.println("False arguments");
            return ;
        }
        int[] arr = new int[arraySize];
        for(int i = 0; i < arraySize; i++) {
            arr[i] = random.nextInt(1000);
            sum += arr[i];
        }
        System.out.println("Sum: " + arraySize);
        sum = 0;
        for(int i = 0; i < threadsCount; i++) {
            MyThread thread = new MyThread(i);
        }
    }
}