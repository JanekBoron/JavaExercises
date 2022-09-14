package Server;

import jdk.internal.dynalink.support.NameCodec;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static java.lang.Integer.parseInt;

public class EchoServer {
    private static final Charset charset = Charset.forName("ISO-8859-2");
    private static final String POISON_PILL = "POISON_PILL";
    private static ArrayList<Member> members = new ArrayList<>(100);
    private static ArrayList<Topic> allTopics = new ArrayList<>(100);


    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress("localhost", 12345));
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        ByteBuffer buffer = ByteBuffer.allocate(350);

        while (true) {
            selector.select();
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iter = selectedKeys.iterator();
            while (iter.hasNext()) {

                SelectionKey key = iter.next();

                if (key.isAcceptable()) {
                    register(selector, serverSocket);
                }

                if (key.isReadable()) {
                    answerWithEcho(buffer, key);
                }
                iter.remove();
            }
        }
    }

    public static void answerWithEcho(ByteBuffer buffer, SelectionKey key)
            throws IOException {

        SocketChannel client = (SocketChannel) key.channel();
        client.read(buffer);

        String msg = new String(buffer.array()).trim();
        if (msg.equals(POISON_PILL)) {
            client.close();
            System.out.println("Not accepting client messages anymore");
        } else {
            //parse method
            Topic topic = new Topic("Sport", "PiłkaNożna");
            Topic topic1 = new Topic("Film", "Horrory");

            allTopics.add(0, topic1);
            allTopics.add(1, topic);
//            CharBuffer cbuf = charset.decode(buffer);
            buffer.flip();
            buffer = ByteBuffer.wrap(parse(msg).getBytes());

            client.write(buffer);
//            charset.encode(CharBuffer.wrap((CharSequence) buffer));
//            client.write(charset.encode(parse(msg)));
            buffer.clear();

        }
    }

    private static void register(Selector selector, ServerSocketChannel serverSocket)
            throws IOException {

        SocketChannel client = serverSocket.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
    }


    public static String parse(String input) {

        String[] temp = input.split(":");
        if (temp[0].equals("Client")) {
            int id;
            id = parseInt(temp[1]);
            if (members.stream().anyMatch(i -> i.getId() == id)) {
                return "1234567890";//members.get(id).userTopics.toString();
            } else {
                Member member = new Member(id);
                members.add(member);
            }
            if (temp[2].equals("AllTopics")) {
                return "T:" + allTopics.toString();
            } else {
                //znajdz topic i przypisz
                Optional<Topic> optional = allTopics.stream().filter(s -> temp[2].equals(s.getTopic())).findFirst();
                if (optional.isPresent()) {
                    Topic t = optional.get();
                    members.get(id).addTopic(t);
                    return "UserTopics:" + members.get(id).userTopics.toString();
                    //dodaj topic do listy uzytkownika

                } else {
                    return "there is no topic like that";
                }


            }


        } else if (temp[0].equals("Admin")) {
            if (temp[1].equals("AllTopics")) {
                return "AllTopics:" + allTopics.toString();
            } else if (temp[1].equals("Add")) {

                if (allTopics.stream().anyMatch(s -> s.getTopic().equals(temp[2]))) {
                    //usun taki sam topic
                    return "There is already a topic like that";
                } else {
                    Topic topic = new Topic(temp[2], temp[3]);
                    allTopics.add(topic);
                    return "AllTopics:" + allTopics.toString();
                }
            }

        }
        return input;
    }
}
