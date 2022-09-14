import java.io.IOException;
import java.net.ServerSocket;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Run {
    public static void main(String[] args) {

        int thread = 128;

        int port = Integer.parseInt(args[0]);

        Map<String, String> serversM= new TreeMap<>();
        try {

            ExecutorService executorService = Executors.newFixedThreadPool(thread);

            ServerSocket socket = new ServerSocket(port);

            while (true)

                executorService.submit(new Server(socket.accept(), serversM));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
