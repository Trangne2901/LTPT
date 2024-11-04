package Netwoking1.BaiTap;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 1111;

        try (Socket socket = new Socket(hostname, port);
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream());
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter first number: ");
            int number1 = scanner.nextInt();

            System.out.print("Enter second number: ");
            int number2 = scanner.nextInt();

            output.writeInt(number1);
            output.writeInt(number2);

            int sum = input.readInt();
            System.out.println("The sum is: " + sum);

        } catch (IOException ex) {
            System.out.println("Client exception: " + ex.getMessage());
        }
    }
}
