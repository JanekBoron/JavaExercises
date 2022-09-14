import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class GUI{
    public GUI(int proxP, int anwsP){

        JFrame jFrame = new JFrame("Translator");

        JPanel jPanel = new JPanel(new FlowLayout());

        jFrame.add(jPanel);

        JTextField wordsField = new JTextField();

        JTextField languageField = new JTextField();

        JLabel wordTotranslate= new JLabel("Word to translate");

        JLabel  lenguageLabel = new JLabel("Language");

        JButton jButton = new JButton("Translate");

        JLabel jLabel = new JLabel("");
        jPanel.add(wordTotranslate);
        jPanel.add(wordsField);
        jPanel.add(lenguageLabel);
        jPanel.add(languageField);
        jPanel.add(jButton);
        jPanel.add(jLabel);

        jButton.setPreferredSize(new Dimension(160,80));

        wordsField.setPreferredSize(new Dimension(400, 120));

        languageField.setPreferredSize(new Dimension(200,120));

        jLabel.setPreferredSize(new Dimension(200,120));

        jFrame.setPreferredSize(new Dimension(1200,600));

        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.setVisible(true);

        jFrame.pack();

        jButton.addActionListener((ActionEvent e) ->{

            String word = wordsField.getText();
            String language = languageField.getText();
            jLabel.setText(Client.task(word,language,proxP,anwsP));
            System.out.println(word);

        });
    }
}
