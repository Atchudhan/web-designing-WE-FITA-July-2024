import java.io.*;
import java.net.*;

public class dnsc {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 9865);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("Connected to DNS Server");

            while (true) {
                System.out.print("Enter command (REGISTER <host> <ip>/RESOLVE <host>/CLEAR/EXIT): ");
                String input = userInput.readLine();

                if (input.equals("EXIT")) {
                    System.out.println("Exiting...");
                    break;
                }

                out.println(input); // Send request to server
                String response = in.readLine();
                System.out.println("Server Response: " + response);
            }

        } catch (IOException e) {
            System.out.println("Client error: " + e.getMessage());
        }
    }
}
