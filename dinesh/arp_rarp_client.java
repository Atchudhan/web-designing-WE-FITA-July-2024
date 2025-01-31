import java.io.*;
import java.net.*;

public class arp_rarp_client {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 9865);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to ARP/RARP Server.");
            System.out.println("Commands: REGISTER <IP> <MAC>, ARP <IP>, RARP <MAC>, CLEAR, EXIT");

            while (true) {
                System.out.print("\nCommand: ");
                String input = userInput.readLine();

                if (input.equalsIgnoreCase("EXIT")) {
                    out.println("EXIT");
                    System.out.println("Exiting...");
                    break;
                }

                out.println(input); // Send command to server
                System.out.println("Server Response: " + in.readLine()); // Receive response
            }
        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
