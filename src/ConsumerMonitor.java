import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConsumerMonitor {


    private static InetAddress host;
    private static final int PORT = 9999;

    public static void main(String[] args)
    {
        try
        {
            host = InetAddress.getLocalHost();
        }
        catch(UnknownHostException e)
        {
            System.out.println("Host ID not found!");
            System.exit(1);
        }
        run();
    }

    private static void run()
    {
        Socket link = null;				//Step 1.

        try
        {
            link = new Socket(host,PORT);		//Step 1.

            BufferedReader in =
                    new BufferedReader
                            (new InputStreamReader
                                    (link.getInputStream()));//Step 2.

            PrintWriter out = new PrintWriter(
                    link.getOutputStream(),true);	 //Step 2.

            //Set up stream for keyboard entry...
            BufferedReader userEntry =
                    new BufferedReader
                            (new InputStreamReader(System.in));

            String message, response;
            do
            {
                System.out.print("Enter number of consumer threads ");
                message =  userEntry.readLine();
                out.println(message); 		//Step 3.
                response = in.readLine();		//Step 3.
                System.out.println("\nSERVER> " + response);
//                System.out.println("Enter number of consumer threads");
//                message = userEntry.readLine();
//                int numThreads = Integer.parseInt(message);
//                System.out.println(numThreads);
            }while (!message.equals("***CLOSE***"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        finally
        {
            try
            {
                System.out.println(
                        "\n* Closing connection... *");
                link.close();				//Step 4.
            }
            catch(IOException e)
            {
                System.out.println("Unable to disconnect!");
                System.exit(1);
            }
        }
    }
}


 /*public static void main(String args[]) throws IOException {
     //ServerSocket serverPort = new ServerSocket(9999);
     BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
     System.out.println("Enter number of consumer threads");
     String x = sc.readLine();
     int numThreads = Integer.parseInt(x);
     System.out.println(numThreads);
     String [] data = new String [numThreads];
     bufferMaker(numThreads, data);
     //System.out.println(data[0]);
 }

 public static void bufferMaker (int entries, String [] x) throws IOException {
     BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
     for (int i = 0; i < entries; i++) {
         System.out.println("Enter data");
         x[i] = in.readLine();
         // System.out.println(x[i]);
     }
 }
 public static void producer (String [] prodItems) throws IOException {

     }
}*/
