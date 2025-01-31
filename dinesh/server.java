import java.net.*;
import java.io.*;

public class server 
{
    public static void main(String[] args)throws IOException {

        ServerSocket mar = new ServerSocket(8906);
        Socket din = mar.accept();

        System.out.println("client connected");

        InputStreamReader is = new InputStreamReader(din.getInputStream());
        BufferedReader br = new BufferedReader(is);

        String st = br.readLine();

        System.out.println("string"+st);

        
    }
}