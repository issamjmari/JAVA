import java.util.Random;

class MyThread extends Thread {
    private static int sum = 0;
    private int     index;
    private int     numThreads;
    private int     eachThreadCount;
    private int[]   arr;

    MyThread(int index, int numThreads, int eachThreadCount, int[] arr) {
        this.index = index;
        this.numThreads = numThreads;
        this.eachThreadCount = eachThreadCount;
        this.arr = arr;
    } 
    private synchronized void countPart() {
        int beg = index * eachThreadCount;
        int end = index == numThreads - 1 ? arr.length : beg + eachThreadCount;
        int currSum = 0;
        for(int i = beg; i < end; i++) {
            currSum += arr[i];
        }
        System.out.println("Thread " + (index + 1) + ": from " + beg + " to " + end + " sum is " + currSum);
        sum += currSum;
    }

    public static int retSum() {
        return sum;
    }
    @Override
    public void run() {
        countPart();
    }
}

class Program {
    public static void main(String[] args) {
        int arraySize = 0;
        int threadsCount = 0;
        int sum = 0;
        int eachThreadCount = 0; 
        Random random = new Random();
        try {
            arraySize = Integer.parseInt(args[0].split("=")[1]);
            threadsCount = Integer.parseInt(args[1].split("=")[1]);
        }
        catch (Exception e) {
            System.out.println("False arguments");
            return ;
        }
        eachThreadCount = (int) Math.ceil((arraySize / (double) threadsCount));
        int[] arr = new int[arraySize];
        for(int i = 0; i < arraySize; i++) {
            arr[i] = random.nextInt(1000);
            sum += arr[i];
        }
        System.out.println("Sum: " + sum);
        sum = 0;
        for(int i = 0; i < threadsCount; i++) {
            MyThread thread = new MyThread(i, threadsCount, eachThreadCount, arr);
            thread.start();
            try {
                thread.join();
            }
            catch (InterruptedException ex) {
                System.out.println("Error while joining threads");
                return ;
            }
        }
        System.out.println("Sum Threads: " + MyThread.retSum());
    }
}