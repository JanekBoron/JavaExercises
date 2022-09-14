import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Gui extends JFrame implements ActionListener {


    static JTextField txtField;

    static JTextField txtField2;

    static JTextField txtField3;

    static JFrame frame;

    static JButton button;

    static JLabel label;

    static JLabel label2;

    static JLabel label3;

    private WebEngine engine;



    Gui() {
    }


    public void gui() {



        frame = new JFrame("cities info app");


        label = new JLabel("Please enter citie name and ");

        label2 = new JLabel("Please enter country name and");

        label3 = new JLabel("Please enter citie currency and");

        button = new JButton("submit");

        Gui gui = new Gui();

        button.addActionListener(gui);

         button.addActionListener(gui);


        txtField = new JTextField(10);

        txtField2 = new JTextField(10);

        txtField3 = new JTextField(10);

        JScrollPane scrollPane = new JScrollPane(new JTextArea(5,50));

        JPanel p = new JPanel();


        p.add(txtField);
        p.add(label2);
        p.add(txtField2);
        p.add(label);
        p.add(txtField3);
        p.add(label3);
        p.add(button);
        p.add(scrollPane);

        String txt = txtField.toString();
        String txt1 = txtField2.toString();
        String txt2 = txtField3.toString();


        frame.add(p);


        frame.setSize(1200, 1800);

        frame.show();


    }


    public void actionPerformed(ActionEvent e) {

        String input = txtField2.getText();
        String input2 = txtField.getText();
        String input3 = txtField.getText();
        Service s = new Service();
        JPanel weather = new JPanel();
        JPanel currency = new JPanel();
        String url = String.format("https://en.wikipedia.org/wiki/%s", input);

        try {

            JLabel weatherInput = new JLabel("\n" + s.getWeather(input, input2) + "\n");
            weatherInput.setPreferredSize(new Dimension(300, 600));
            weather.add(weatherInput);
            frame.add(weather);
            frame.show();
            JLabel currencyInput = new JLabel(String.valueOf(s.getRateFor(input3)));
            currency.add(currencyInput);
            currency.setPreferredSize(new Dimension(300, 600));
            frame.add(currency);
            frame.show();


        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }


}
