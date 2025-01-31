import java.net.*;
import java.io.*;

public class encryptserver {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(9876);
        byte[] receiveBuffer = new byte[1024];

        System.out.println("Server is waiting for messages...");

        // Receive encrypted message
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        socket.receive(receivePacket);
        String encryptedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Received Encrypted Message: " + encryptedMessage);

        // Receive key file content
        socket.receive(receivePacket);
        String keyContent = new String(receivePacket.getData(), 0, receivePacket.getLength());
        int key = Integer.parseInt(keyContent);
        System.out.println("Received Key from file: " + key);

        // Decrypt message
        String decryptedMessage = decrypt(encryptedMessage, key);
        System.out.println("Decrypted Message: " + decryptedMessage);

        FileWriter keyFile = new FileWriter("decrypt.txt");
        keyFile.write(String.valueOf(decryptedMessage));
        keyFile.close();

        socket.close();
    }

    // Substitution cipher decryption (Shift Left)
    public static String decrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();
        for (char ch : text.toCharArray()) {
            if (Character.isLetter(ch)) {
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                ch = (char) (base + (ch - base - shift + 26) % 26);
            }
            result.append(ch);
        }
        return result.toString();
    }
}
