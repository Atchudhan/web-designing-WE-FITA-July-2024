import java.net.*;
import java.io.*;

public class encryclient {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("localhost");
        int port = 9876;

        String message = "INFORMATION TECHNOLOGY";
        int key = 4;
        
        // Encrypt message using substitution cipher
        String encryptedMessage = encrypt(message, key);
        System.out.println("Encrypted Message: " + encryptedMessage);

        // Save key to a file
        FileWriter keyFile = new FileWriter("Key.txt");
        keyFile.write(String.valueOf(key));
        keyFile.close();

        FileWriter File = new FileWriter("encrypt.txt");
        File.write(String.valueOf(encryptedMessage));
        File.close();

        // Send encrypted message
        byte[] sendBuffer = encryptedMessage.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, port);
        socket.send(sendPacket);

        // Send key file content
        File file = new File("Key.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String keyContent = br.readLine();
        br.close();
        byte[] keyBuffer = keyContent.getBytes();
        DatagramPacket keyPacket = new DatagramPacket(keyBuffer, keyBuffer.length, serverAddress, port);
        socket.send(keyPacket);

        System.out.println("Key and Encrypted Message Sent!");
        socket.close();
    }

    // Substitution cipher encryption (Shift Right)
    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                ch = (char) (base + (ch - base + shift) % 26);
            }
            result.append(ch);
        }
        return result.toString();
        
    }
}
