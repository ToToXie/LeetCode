package Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class IOClient {

    public static void main(String[] args) {
        // TODO 创建多个线程，模拟多个客户端连接服务端
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 3333);
                while (true) {
                    try {
//            socket.getOutputStream().write((new Date() + ":I am client : hello world").getBytes());
//            Thread.sleep(2000);

                        int len;
                        byte[] data = new byte[1024];
                        InputStream inputStream = socket.getInputStream();
                        // 按字节流方式读取数据
                        while ((len = inputStream.read(data)) != -1) {
                            System.out.println(new String(data, 0, len));
                        }
                    } catch (Exception e) {
                    }
                }
            } catch (IOException e) {
            }
        }).start();

    }

}