import java.net.*;
import java.io.*;




public class msgserver {

    public static void main(String[] args)throws IOException {

        ServerSocket mar =new ServerSocket(6789);
        Socket din= mar.accept();

        System.out.println("client connected successfully");

        InputStreamReader ip = new InputStreamReader(din.getInputStream());
        BufferedReader br = new BufferedReader(ip);
        PrintWriter pr = new PrintWriter(din.getOutputStream());
        BufferedReader ui = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(" enter 'stop' to end the chat");

        String climsg;
        String msgsend;

        while(true)
        {
            climsg = br.readLine();
            System.out.println("Client message= "+climsg);

            if("stop".equalsIgnoreCase(climsg))
            {
                System.out.println("Chat ended by client");
                pr.println("stop");
                pr.flush();
                break;

            }


            System.out.println("Enter a message to send:");
            msgsend=ui.readLine();
            pr.println(msgsend);
            pr.flush();

            if("stop".equalsIgnoreCase(msgsend))
            {
                System.out.println("Chat ended by you");
                pr.println("stop");
                pr.flush();
                break;

            }




        }

        din.close();
        mar.close();


        
    }
    
}
