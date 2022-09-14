package LanguageServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LanguageServer implements Runnable{
    private Socket socket;
    private String file;

    public LanguageServer(Socket socket, String file) {
        this.socket = socket;
        this.file = file;
    }

    @Override
    public void run(){
        try{
            while(true){
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String line;

                while(!(line = bufferedReader.readLine()).isEmpty()){

                    String[] contents = line.split(":");
                    String word = contents[0];
                    String adresRes = contents[1];
                    int portRes = Integer.parseInt(contents[2]);
                    String odp = translate(word,file);
                    Socket socket = new Socket(adresRes,portRes);
                    DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    dataOutputStream.writeBytes(odp + "\n");

                }
            }
        }catch (
                IOException exception) {
            exception.printStackTrace();
        }
    }
    public static String translate(String word, String file){
        String word1 = "Word: " + word + "not in dictionary";
        try{

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while((line = bufferedReader.readLine()) != null){
                String[] contents = line.split(":");
                String sl = contents[0];
                String tl = contents[1];
                if (word.equals(sl))
                    return tl;
            }

        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return word1;

    }
}


