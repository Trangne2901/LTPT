package Networking1.ThucHanh;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkingUseInetAddress {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getByName("www.example.com");

            String ipAddress = address.getHostAddress();
            System.out.println("IP Address: " + ipAddress);

            String hostName = address.getHostName();
            System.out.println("Host Name: " + hostName);

            boolean reachable = address.isReachable(5000);
            System.out.println("Reachable: " + reachable);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
