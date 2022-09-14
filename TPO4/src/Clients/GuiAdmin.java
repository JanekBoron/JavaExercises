package Clients;

import Server.Topic;
import javafx.embed.swing.JFXPanel;
import jdk.internal.dynalink.support.NameCodec;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class GuiAdmin implements ActionListener {
    private ArrayList<Topic> Membertopics;
    private ArrayList<Topic> AllTopic;
    EchoClient echoClient;
    private static JTextField topicField;
    private JFXPanel jfxPanel;
    String topic;

    public GuiAdmin()  {

        JFrame frame = new JFrame();
        this.jfxPanel = new JFXPanel();
        this.echoClient = EchoClient.start();
        this.AllTopic = new ArrayList<>();

        JPanel panel = new JPanel(new BorderLayout());

        echoClient.sendMessage("Admin:AllTopics:");

        this.topicField = new JTextField();
        JButton submitButton = new JButton("Submit");

        JPanel inputPanel = new JPanel(new GridLayout(0, 1));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));

        inputPanel.add(new JLabel("Add/Delete TOPIC:DESCRIPTION"));
        inputPanel.add(topicField);

        inputPanel.add(new JLabel("All topics in database:"));

        JList<Topic> displayList = new JList<Topic>();
        JScrollPane scrollPane = new JScrollPane(displayList);

        inputPanel.add(submitButton);
        submitButton.addActionListener(this);
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.EAST);
        frame.getContentPane().add(panel);

        frame.setPreferredSize(new Dimension(1024, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.repaint();
        frame.revalidate();


        //repaint
        //revalidate
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        topic = topicField.getText();
        echoClient.sendMessage("Admin:Add:"+topic);


    }


}
