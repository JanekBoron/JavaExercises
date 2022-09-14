import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.*;
import java.util.Hashtable;

public class ConsumerB implements Runnable, MessageListener {
    private MessageProducer replyProducer;
    private Session session;
    private Connection connection;
    private MessageConsumer consumer;
    JTextArea ta;
    String msgTextReceived;
    public ConsumerB( JTextArea ta) {
        this.ta=ta;
    }

    public void startQue() {
        try {
            Hashtable env = new Hashtable(11);
            env.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            env.put(Context.PROVIDER_URL, "tcp://localhost:61616");
            env.put("queue.myQueue", "myQueue");
            Context ctx = new InitialContext(env);
            ConnectionFactory connectionFactory = (ConnectionFactory) ctx.lookup("ConnectionFactory");
//            Queue queue = (Queue) ctx.lookup("myQueue");

            Connection connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Destination adminQueue = session.createQueue("secondQueue");
            replyProducer = session.createProducer(null);
            replyProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            consumer = session.createConsumer(adminQueue);
            consumer.setMessageListener(this);
            System.err.append("Consumer waiting for data");

        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        startQue();
    }

    @Override
    public void onMessage(Message msg) {
        if (msg instanceof TextMessage) {
            try {
                TextMessage textMessage = (TextMessage) msg;
                msgTextReceived = textMessage.getText();
                ta.append("Kuba:  "+msgTextReceived+"\n");
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

}
