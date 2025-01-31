import java.net.*;
import java.io.*;


public class msgclient {

    public static void main(String[] args)throws IOException {

        Socket din =new Socket("localhost",6789);

        PrintWriter pw = new PrintWriter(din.getOutputStream());
        InputStreamReader ip = new InputStreamReader(din.getInputStream());
        BufferedReader br = new BufferedReader(ip);
        BufferedReader ui = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Connected to the server Successfully. Type 'stop' to end the chat");

        String msgsend;
        String serresponse;


        while (true) {

            System.out.println("You:");
            msgsend=ui.readLine();
            pw.println(msgsend);
            pw.flush();

            if("stop".equalsIgnoreCase(msgsend))
            {
                System.out.println("chat ended by you");
                break;
            }
            
            serresponse=br.readLine();
            System.out.println("server msg= "+serresponse);

            if("stop".equalsIgnoreCase(serresponse))
            {
                System.out.println("chat ended by server");
                break;
            }
        }

         din.close();
        
    }
    
}
