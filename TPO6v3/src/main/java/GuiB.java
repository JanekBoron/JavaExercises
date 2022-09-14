import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiB {
    JTextField tf;
    JTextArea ta;
    JFrame frame;
    String data;
    public GuiB() throws NamingException, JMSException, InterruptedException {
        frame = new JFrame("Czat Janka");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("FILE");
        JMenu m2 = new JMenu("Help");
        mb.add(m1);
        mb.add(m2);



        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter Text");
        tf = new JTextField(50);
        JButton send = new JButton("Send");
        send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    send();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } catch (NamingException ex) {
                    ex.printStackTrace();
                } catch (JMSException ex) {
                    ex.printStackTrace();
                }
            }
        } );
        panel.add(label);
        panel.add(tf);
        panel.add(send);


        // Text Area at the Center
        ta = new JTextArea();

        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);

    }
    public void  send() throws InterruptedException, NamingException, JMSException {
        String data=this.tf.getText();

        ProducerB producer = new ProducerB();
        Thread thread1=new Thread((Runnable) producer);
        thread1.start();
        producer.sendData(data);
        boolean exit = true;
        ConsumerB chatCounsumer= new ConsumerB(ta);
        Thread thread=new Thread(chatCounsumer);
        try {
            Thread.sleep(1000);
            thread.start();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread.sleep(1000);
        ta.append("Janek:  "+data+"\n");
        frame.repaint();
        frame.revalidate();

    }
    public String getData(){
        return data;
    }
}
