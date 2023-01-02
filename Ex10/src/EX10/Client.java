package EX10;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.*;

public class Client {
    private JTextField googleTextField;
    private JButton getButton;
    private JPanel panel2;
    private static JFrame frame;
    private JPanel panel1;
    private JLabel ip;
    private DataInputStream din;
    private Socket s;
    private DataOutputStream dout;

    public Client()
    {
        frame = new JFrame("login frame");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(700,500));
        frame.setResizable(false);
        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        getButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String a = googleTextField.getText();
                try {
                    s = new Socket("127.0.0.1", 1578);
                    dout = new DataOutputStream(s.getOutputStream());
                    din = new DataInputStream(s.getInputStream());
                    dout.writeUTF(a);
                    String b=din.readUTF();
                    ip.setText(b);
                }
                catch (Exception e1)
                {
                    JOptionPane.showMessageDialog(null,"Socket error");
                }


            }
        });
    }
}
