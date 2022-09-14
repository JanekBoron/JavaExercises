package Clients;

import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

import Server.Topic;

public class GuiMember implements ActionListener {
    private static JTextField topicField;
    private int id;
    private ArrayList<Topic> Membertopics;
    private ArrayList<Topic> AllTopic;
    String topic;
    private JFXPanel jfxPanel;
    private EchoClient echoClient;

    //idClient //Lista
    public GuiMember(int id) {
        this.id=id;
        this.Membertopics = new ArrayList<>();
        this.echoClient=EchoClient.start();
        this.AllTopic = new ArrayList<>();
        String msg=("Client:"+id+":"+"AllTopics:");
        echoClient.sendMessage(msg);
        JFrame frame = new JFrame();
        this.jfxPanel = new JFXPanel();


        JPanel panel = new JPanel(new BorderLayout());


        topicField = new JTextField();
        JButton submitButton = new JButton("Submit");

        JPanel inputPanel = new JPanel(new GridLayout(0, 1));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));

        inputPanel.add(new JLabel("(Un)subscribe to a topic"));
        inputPanel.add(topicField);

        inputPanel.add(new JLabel("Your Topics:"));

        JList<Topic> displayList = new JList<Topic>();
        JScrollPane scrollPane = new JScrollPane(displayList);

        inputPanel.add(submitButton);
        submitButton.addActionListener(this);
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.WEST);


        frame.getContentPane().add(panel);

        frame.setPreferredSize(new Dimension(1024, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        frame.repaint();
        frame.revalidate();


    }


    //repaint
    //revalidate

    @Override
    public void actionPerformed(ActionEvent e) {

        topic = topicField.getText();
        echoClient.sendMessage("Client:"+id+":"+topic+"XXXXXXXXXXXAAAAAAAdddd");

    }


}
