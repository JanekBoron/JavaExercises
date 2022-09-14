package LanguageServer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class LanguageServerRun {
    public static void main(String[] args) {

        String language = args[0];

        int port = Integer.parseInt(args[1]);

        String[] prox = args[2].split(":");

        String proxAdress = prox[0];

        int proxPort = Integer.parseInt(prox[1]);

        String file = "txtFiles\\" + language + ".txt";

        try {

            Socket socketProx = new Socket(proxAdress, proxPort);

            DataOutputStream dataOutputStream = new DataOutputStream(socketProx.getOutputStream());

            dataOutputStream.writeBytes("NSJ " + language + " " + port + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            ServerSocket serverSocket = new ServerSocket(port);

            ExecutorService executorService = Executors.newFixedThreadPool(32);
            while (true) {

                executorService.submit(new LanguageServer(serverSocket.accept(), file));

            }

        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
