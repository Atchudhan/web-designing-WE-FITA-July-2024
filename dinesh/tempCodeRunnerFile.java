import java.io.*;
import java.net.*;
import java.util.*;

public class dnss {
    private static Map<String, String> dnsTable = new HashMap<>();
    public static void main(String[] args) {
        try{

            ServerSocket serverSocket = new ServerSocket(9865);

            Socket socket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                
                
            while (true) { // Keeps the server running
                String request = in.readLine();

                String[] parts = request.split(" ");

                String command = parts[0];

                if (command.equals("REGISTER")) {
                    String domain = parts[1];
                    dnsTable.put(domain, parts[2]);
                    out.println("Registered " + domain + " -> " + parts[2]);

                } 
                else if (command.equals("RESOLVE")) {
                    String domain = parts[1];
                    String ip = dnsTable.get(domain);
                    out.println( "IP: " + ip);

                } 
                else if (command.equals("CLEAR")) {
                    dnsTable.clear();
                    out.println("All entries deleted");
                }
                else{
                    break;
                }
                
            }
        }
        catch (IOException e) {
            System.out.println("Server error: " + e.getMessage());
        }
    
    }
}
