package EX10;

import javax.swing.*;
import java.io.*;
import java.net.*;

public class Server1 {
    private DataInputStream din,din1;
    int flag=0;
    private DataOutputStream dout,dout1;
    private ServerSocket ss;
    private Socket s,s1;
    String d="",ip="",b="";
    public Server1()
    {
        try {
            ss = new ServerSocket(1578);
            s = ss.accept();
            System.out.println("clien recv");
            dout = new DataOutputStream(s.getOutputStream());
            din = new DataInputStream(s.getInputStream());
            String a = din.readUTF();
            System.out.println("the rec "+a);

            try{
                File f = new File("D:\\LAB\\SEM_V\\ComputerNetwork\\Exercise 10\\Ex10\\src\\EX10\\f1.txt");
                FileInputStream f1=new FileInputStream(f);
                byte []by=new byte[(int)f.length()];
                f1.read(by);
                b=new String(by);
                System.out.println(b);
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
                        flag=1;
                    }
                    d="";
                }
                d = d+b.charAt(i);
            }

            if(flag==0)
            {
                System.out.println("In  reqest of server2");
                try {
                    s1 = new Socket("127.0.0.1", 2578);
                    dout1 = new DataOutputStream(s1.getOutputStream());
                    din1 = new DataInputStream(s1.getInputStream());
                    dout1.writeUTF(a);
                    System.out.println(a);
                    String b2=din1.readUTF();
                    System.out.println("The str from server 2"+b2);
                    dout.writeUTF(b2);
                    try{
                        FileOutputStream f = new FileOutputStream(new File("D:\\LAB\\SEM_V\\ComputerNetwork\\Exercise 10\\Ex10\\src\\EX10\\f1.txt"));
                        String b1 = b+a+" "+b2+"\n";
                        byte []by = b1.getBytes();
                        f.write(by);
                    }
                    catch(Exception e)
                    {
                        System.out.println("Exception");
                    }
                }
                catch (Exception e1)
                {
                    JOptionPane.showMessageDialog(null,"Socket error");
                }
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"Socket error");
        }
    }

    public static void main(String[] args) {
        new Server1();
    }

}
