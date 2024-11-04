package Networking1.ThucHanh;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String serverIp = "127.0.0.1";
        int serverPort = 8080;

        try {
            Socket socket = new Socket(serverIp, serverPort);

            OutputStream outputStream = socket.getOutputStream();
            String messsage = "Hello, server. I'm huyentrang";
            outputStream.write(messsage.getBytes());

            socket.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
