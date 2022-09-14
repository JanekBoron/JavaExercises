import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable{

    private Socket socket;

    private Map<String,String> servers;

    public Server(Socket socket,Map<String,String> serversM ){

        this.servers = serversM;

        this.socket = socket;
    }

    @Override
    public void run(){
        try{
            BufferedReader incoming = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String msg;

            while(!(msg = incoming.readLine()).isEmpty()){
                System.out.println("new message: " + msg);
                String[] contents = msg.split(" ");
                switch(contents[0]){
                    case "":

                        String sAdres = socket.getInetAddress().getHostAddress();
                        String fAdres = sAdres + ":" + contents[2];
                        servers.put(contents[1],fAdres);

                    case "works":

                        String A = contents[1];
                        String B = contents[2];
                        String adres = socket.getInetAddress().getHostAddress();
                        int port = Integer.parseInt(contents[3]);
                        String adressSJ = "";
                        int portSJ = 0;
                        for (Map.Entry<String,String> serversM : servers.entrySet()){
                            if (serversM.getKey().equals(B)){
                                String[] T = serversM.getValue().split(":");
                                adressSJ = T[0];
                                portSJ = Integer.parseInt(T[1]);
                            }
                        }
                        Socket socket1 = new Socket(adressSJ,portSJ);
                        DataOutputStream dataOutputStream = new DataOutputStream(socket1.getOutputStream());
                        dataOutputStream.writeBytes(A + ":" + adres + ":" + port + "\n");

                    default:
                        System.out.println("NULL");
                }
            }

        }catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}


