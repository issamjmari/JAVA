
class MessagePrinter {
    private boolean turn = true;

    public synchronized void printOne(int count) {
        for (int i = 0; i < count; i++) {
            while (!turn) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("Egg");
            turn = false;
            notify();
        }
    }

    public synchronized void printSec(int count) {
        for (int i = 0; i < count; i++) {
            while (turn) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("Hen");
            turn = true;  
            notify(); 
        }
    }
}
class MyRunnable implements Runnable {
    int count;
    private MessagePrinter printer;

    MyRunnable(int count, MessagePrinter printer) {
        this.count = count;
        this.printer = printer;
    }
    @Override
    public void run() {
        printer.printOne(count);
    }
}

class MyThread extends Thread {
    int count;
    private MessagePrinter printer;

    MyThread(int count, MessagePrinter printer) {
        this.count = count;
        this.printer = printer;
    }
    @Override
    public void run() {
        printer.printSec(count);
    }
}

class Program {

    public static void main(String[] args) {
        int threadNumber;
        try {
            threadNumber = Integer.parseInt(args[0].split("=")[1]);    
        }
        catch(Exception e) {
            System.out.println("invalid argument");
            return ; 
        }
        MessagePrinter printer = new MessagePrinter();
        Thread runnableThread = new Thread(new MyRunnable(threadNumber, printer));
        runnableThread.start();

        MyThread extendedThread = new MyThread(threadNumber, printer);
        extendedThread.start();

        try {
            runnableThread.join();
            extendedThread.join();
        }
        catch (InterruptedException ex) {
            System.out.println("Error while joining threads");
            return ;
        }
        for(int i = 0; i < threadNumber; i++) {
            System.out.println("Human");
        }
    }
}
