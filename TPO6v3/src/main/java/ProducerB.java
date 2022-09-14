import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.*;
import java.util.Hashtable;

public class ProducerB implements Runnable,MessageListener {
    private Connection connection;
    private Session session;
    private MessageConsumer consumer;
    private MessageProducer producer;
    JTextArea ta;



    public void sendData(String dataToSend) throws NamingException, JMSException {
        Hashtable env = new Hashtable(11);
        env.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        env.put(Context.PROVIDER_URL, "tcp://localhost:61616");
        env.put("queue.myQueue", "myQueue");
        Context ctx = new InitialContext(env);
        Destination adminQueue = null;
        Destination tempDest = null;
        if (connection == null) {
            ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("ConnectionFactory");
//                Queue queue = (Queue) ctx.lookup("myQueue");
            connection = connectionFactory.createConnection();
            connection.start();
        }
        try {
            if (session == null) {
                session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

                adminQueue = session.createQueue("myQueue");
                producer = session.createProducer(adminQueue);
            }
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            tempDest = session.createTemporaryQueue();
            consumer = session.createConsumer(tempDest);
            consumer.setMessageListener(this);
            TextMessage textMessage = session.createTextMessage();
            textMessage.setText(dataToSend);
            System.out.println("Sending: " + dataToSend);
            producer.send(textMessage);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    public void run() {

        try {
            Thread.sleep(1000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onMessage(Message msg) {
        String messageText = null;
        if (msg instanceof TextMessage) {
            try {
                TextMessage textMessage = (TextMessage) msg;

                messageText = textMessage.getText();
                System.err.println("MessegeText from Consumer" + messageText + "\n");
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
