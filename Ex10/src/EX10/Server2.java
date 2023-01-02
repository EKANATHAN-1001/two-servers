package EX10;

import javax.swing.*;
import java.io.*;
import java.net.*;

public class Server2 {
    private DataInputStream din;
    private DataOutputStream dout;
    private ServerSocket ss;
    private Socket s;
    String d="",ip="",b="";
    public Server2()
    {
        try {
            ss = new ServerSocket(2578);
            s = ss.accept();
            System.out.println("Server recv");
            dout = new DataOutputStream(s.getOutputStream());
            din = new DataInputStream(s.getInputStream());
            String a = din.readUTF();
            System.out.println("the rec "+a);
            try{
                File f = new File("D:\\LAB\\SEM_V\\ComputerNetwork\\Exercise 10\\Ex10\\src\\EX10\\f2.txt");
                FileInputStream f1=new FileInputStream(f);
                byte []by=new byte[(int)f.length()];
                f1.read(by);
                b=new String(by);
            }
            catch(Exception e)
            {
                System.out.println("Exception");
            }
            for(int i=0;i<b.length();i++)
            {
                if(b.charAt(i)==' ')
                {
                    if(a.equals(d.trim()))
                    {
                        int j=i+1;
                        while (b.charAt(j)!='\n')
                        {
                            ip = ip+b.charAt(j);
                            j++;

                        }
                        dout.writeUTF(ip);

                    }
                    d="";
                }
                d = d+b.charAt(i);
            }

        }catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"Socket error");
        }
    }

    public static void main(String[] args) {
        new Server2();
    }

}
