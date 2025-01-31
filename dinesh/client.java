import java.net.*;
import java.io.*;


public class client {

    public static void main(String[] args) throws IOException {

        Socket din = new Socket("localhost",8906);

        PrintWriter pw = new PrintWriter(din.getOutputStream());
        pw.println("hello");
        pw.flush();
        
    }
    
}
