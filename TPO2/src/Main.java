import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 *
 *  @author Boroń Jan S23370
 *
 */

//package zad1;


public class Main {
    public static void main(String[] args) throws IOException {
        Service s = new Service();
        Gui g = new Gui();

        String weatherJson = s.getWeather("Warsaw","PL");
        String rate1 = s.getRateFor("PLN");
//        Double rate2 = s.getNBPRate();


        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run() {
                Gui g = new Gui();
                g.gui();
            }
        });
    }
}
