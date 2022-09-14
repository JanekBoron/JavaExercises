import javafx.scene.text.TextAlignment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {

        int prox = Integer.parseInt(args[0]);

        int anws = Integer.parseInt(args[1]);

        SwingUtilities.invokeLater(() -> {
            new GUI(prox, anws);
        });
    }

    static public String task(String word, String language, int portP, int portO) {
        try {
            String msg = "";

            Socket socketP = new Socket("127.0.0.1", portP);

            ServerSocket serverSocket = new ServerSocket(portO);

            DataOutputStream dataOutputStream = new DataOutputStream(socketP.getOutputStream());

            dataOutputStream.writeBytes("NK " + word + " " + language + " " + portO + "\n");

            while (1 == 1) {

                Socket socketJ = serverSocket.accept();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socketJ.getInputStream()));

                String lane = bufferedReader.readLine();

                System.out.println("messege: " + lane);

                msg = lane;
                socketJ.close();
                serverSocket.close();
                return msg;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}


