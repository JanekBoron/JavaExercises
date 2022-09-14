package Clients;

import Server.Topic;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class EchoClient {
    private static SocketChannel client;
    private static ByteBuffer buffer;
    private static EchoClient instance;

    public static EchoClient start() {
        if (instance == null)
            instance = new EchoClient();

        return instance;
    }

    public static void stop() throws IOException {
        client.close();
        buffer = null;
    }

    private EchoClient() {
        try {
            client = SocketChannel.open(new InetSocketAddress("localhost", 12345));
            buffer = ByteBuffer.allocate(8192);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String sendMessage(String msg) {
        Charset charset = Charset.forName("ISO-8859-2");
        CharBuffer cbuf = null;
        buffer = ByteBuffer.wrap(msg.getBytes());
        String response = null;

        try {
            client.write(buffer);
            buffer.clear();
//            int readBytes = client.read(buffer);

//                if (readBytes > 0) {
//                    cbuf = charset.decode(buffer);
//                    cbuf.toString();
//                    System.out.println(cbuf);
//                }


            client.read(buffer);
            response = new String(buffer.array()).trim();//21
            System.out.println(response);
            buffer.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;

    }

    public ArrayList<Topic> service(String response) {
        ArrayList<Topic> topics = null;
        if (response.startsWith("T:")) {

            String[] temp = response.split(",");
            Topic topic = new Topic(temp[1], temp[2]);

            topics = new ArrayList<>();
            topics.add(topic);
        }
        return topics;
    }


}