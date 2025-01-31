import java.io.*;
import java.net.*;
import java.util.*;

public class arp_rarp_server {
    private static Map<String, String> arpTable = new HashMap<>(); // Stores IP-MAC mappings

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9865)) {
            System.out.println("ARP/RARP Server started on port 9865");

            while (true) {
                System.out.println("Waiting for a client...");
                try (Socket socket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                    System.out.println("Client connected.");

                    while (true) {
                       
                       
                        String request = in.readLine();
                        String[] parts = request.split(" ");
                        String command = parts[0].toUpperCase();

                        if (command.equals("REGISTER") && parts.length == 3) {
                            arpTable.put(parts[1], parts[2]); // Register IP -> MAC
                            out.println("Registered: " + parts[1] + " -> " + parts[2]);

                        } else if (command.equals("ARP") && parts.length == 2) {
                            out.println("MAC: " + arpTable.getOrDefault(parts[1], "MAC not found"));

                        } else if (command.equals("RARP") && parts.length == 2) {
                            String ip = null;
                            for (Map.Entry<String, String> entry : arpTable.entrySet()) {
                                if (entry.getValue().equals(parts[1])) {
                                    ip = entry.getKey();
                                    break;
                                }
                            }
                            out.println("IP: " + (ip != null ? ip : "IP not found"));

                        } else if (command.equals("CLEAR")) {
                            arpTable.clear();
                            out.println("All ARP/RARP entries deleted.");

                        } else {
                            out.println("Invalid command! Use REGISTER <IP> <MAC>, ARP <IP>, RARP <MAC>, CLEAR, EXIT");
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Error handling client: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}
