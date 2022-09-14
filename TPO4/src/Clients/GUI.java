package Clients;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Integer.parseInt;

public class GUI implements ActionListener {

    private static JLabel password1, label;
    private static JTextField username;
    private static JButton button;
    private static JPasswordField Password;
    private int id;
    private JFrame frame;
    private ArrayList<SocketChannel> clients;


    public GUI() {


        JPanel panel = new JPanel();
        panel.setLayout(null);
        this.frame = new JFrame();
        frame.setTitle("LOGIN PAGE");
        frame.setLocation(new Point(500, 300));
        frame.add(panel);
        frame.setSize(new Dimension(400, 200));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        label = new JLabel("ID");
        label.setBounds(100, 8, 70, 20);
        panel.add(label);
        username = new JTextField();
        username.setBounds(100, 27, 193, 28);
        panel.add(username);
        password1 = new JLabel("Password");
        password1.setBounds(100, 55, 70, 20);
        panel.add(password1);
        Password = new JPasswordField();
        Password.setBounds(100, 75, 193, 28);
        panel.add(Password);
        button = new JButton("Login");
        button.setBounds(100, 110, 90, 25);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.BLACK);
        button.addActionListener(this);


        panel.add(button);
        panel.show();
        frame.show();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String Username = username.getText();
        String Password1 = Password.getText();
        id=parseInt(Username);


        if ( id!=4&&Password1.equals("123")) {
            JOptionPane.showMessageDialog(null, "Login Successful");
            new GuiMember(id);

//          frame.hide();
        }  else
            JOptionPane.showMessageDialog(null, "Username or Password mismatch ");

        if (id==4&&Password1.equals("123")) {
            new GuiAdmin();

//            frame.hide();
        } else
            JOptionPane.showMessageDialog(null, "Username or Password mismatch ");

    }


}
