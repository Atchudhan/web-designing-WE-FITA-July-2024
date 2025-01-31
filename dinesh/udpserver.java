import java.net.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class udpserver {

    public static void main(String[] args)throws IOException {

        DatagramSocket sock = new DatagramSocket();
        sock.setBroadcast(true);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter broadcast message");
        String msg = br.readLine();
        byte[] buffer = msg.getBytes();

        InetAddress address = InetAddress.getByName("255.255.255.255");
        DatagramPacket packet = new DatagramPacket(buffer,buffer.length,address,7890);

        sock.send(packet);
        sock.close();


        
    }
    
}
