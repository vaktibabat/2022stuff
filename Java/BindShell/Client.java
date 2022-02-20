import java.io.*;
import java.net.*;

public class Client
{
    public static void main(String [] args)
    {
        try
        {

            Socket s = new Socket("192.168.229.101", 4444);

            DataOutputStream dos = new DataOutputStream(s.getOutputStream());

            dos.writeUTF(args[0]);
            
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            
            String tmp = null;

            while ((tmp = in.readLine()) != null)
            {
                System.out.println(tmp);
            }

            dos.flush();
            dos.close();
            s.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return;
        }

    }
}
