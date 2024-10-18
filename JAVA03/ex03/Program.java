import java.io.InputStream;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.io.FileOutputStream;
import java.util.Queue;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class MyThread extends Thread {
    private static Queue<String> queue = new LinkedList<>();
    private static int fileNumber = 1;

    public static void setQueue(String file) {
        queue.add(file);
    }

    private int download(String fileURL, String saveDirectory, String threadNum) {
        try {
            URL url = new URL(fileURL);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String fileName = fileURL.substring(fileURL.lastIndexOf("/") + 1);
                InputStream inputStream = httpConn.getInputStream();
                String savePath = saveDirectory + fileName;

                try (BufferedInputStream in = new BufferedInputStream(inputStream);
                    FileOutputStream out = new FileOutputStream(savePath)) {
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                }

            } else {
            }
            httpConn.disconnect();
        }
        catch(Exception e) {
            System.out.println(threadNum + " failed downloading file " + fileURL);
            return -1;
        }
        return 0;
    }

    private void downloadFile() {
         while (true) {
            String fileURL;
            int currentFileNum;
            synchronized (queue) {
                if (queue.isEmpty()) {
                    break;
                }
                fileURL = queue.poll();
                currentFileNum = fileNumber;
                fileNumber++;
            }
            if (fileURL != null) {
                String threadNum = Thread.currentThread().getName();
                System.out.println(threadNum + " start download file number " + currentFileNum);
                int ret = download(fileURL, "./", threadNum);
                if (ret == -1)
                    return ;
                System.out.println(threadNum + " finish download file number " + currentFileNum);
            }
        }
    }

    @Override
    public void run() {
        downloadFile();
    }
}
class Program {
    public static void main(String[] args) {
        int threadCount = 0;

        try {
            threadCount = Integer.parseInt(args[0].split("=")[1]);
        }
        catch(Exception e) {
            System.out.println("argument is incorrect");
            return ;
        }

        try {
            InputStream file = new FileInputStream("files_urls.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                MyThread.setQueue(line);
            }
            for(int i = 0; i < threadCount; i++) {
                MyThread thread = new MyThread();
                thread.start();
            }
        }
        catch(Exception e) {

        }
    }
}