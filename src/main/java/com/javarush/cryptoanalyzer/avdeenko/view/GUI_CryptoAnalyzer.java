package com.javarush.cryptoanalyzer.avdeenko.view;
import com.javarush.cryptoanalyzer.avdeenko.services.bruteforce.BruteForce;
import com.javarush.cryptoanalyzer.avdeenko.services.decrypt.Decrypt;
import com.javarush.cryptoanalyzer.avdeenko.services.encrypt.Encrypt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GUI_CryptoAnalyzer extends JFrame implements ActionListener{

    private final JTextField inputFilenameField;
    private final JTextField outputFilenameField;
    private final JTextField keyField;

    public GUI_CryptoAnalyzer() {

        super("Caesar Cipher");


        setSize(600, 400);
        setLayout(new GridLayout(5, 2));


        JLabel inputFilenameLabel = new JLabel(" Input Filename:");
        inputFilenameField = new JTextField();
        add(inputFilenameLabel);
        add(inputFilenameField);


        JLabel outputFilenameLabel = new JLabel(" Output Filename:");
        outputFilenameField = new JTextField();
        add(outputFilenameLabel);
        add(outputFilenameField);


        JLabel keyLabel = new JLabel(" Key:");
        keyField = new JTextField();
        add(keyLabel);
        add(keyField);

        JLabel defaultFilesLabel = new JLabel(" if the fields are empty, the values will be default");
        defaultFilesLabel.setHorizontalAlignment(SwingConstants.LEFT);
        defaultFilesLabel.setVerticalAlignment(SwingConstants.CENTER);
        add(defaultFilesLabel);


        JButton encryptButton = new JButton("Encrypt");
        encryptButton.addActionListener(this);
        add(encryptButton);


        JButton decryptButton = new JButton("Decrypt");
        decryptButton.addActionListener(this);
        add(decryptButton);


        JButton bruteForceButton = new JButton("Brute Force");
        bruteForceButton.addActionListener(this);
        add(bruteForceButton);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String inputFilename = inputFilenameField.getText();
        String outputFilename = outputFilenameField.getText();

        //Проверки на коректный ввод ключа
        String keyString = keyField.getText();
        if (keyString.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Пожалуйста, введите ключ.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (char c : keyString.toCharArray()) {
            if (!Character.isDigit(c)) {
                JOptionPane.showMessageDialog(null, "Ключ должен состоять только из цифр.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        int key = Integer.parseInt(keyString);




        boolean twoAreEmpty = inputFilename.equals("") && outputFilename.equals("");

        try {


            String path = "D:\\Cryptoanalyzer\\CryptoAnalyzer\\src\\main\\java\\com\\javarush\\cryptoanalyzer\\avdeenko\\resources\\";

            if (e.getActionCommand().equals("Encrypt")) {
                if(twoAreEmpty){
                    Encrypt.encryptFile(path + "input.txt", path + "encoded.txt", key);
                    JOptionPane.showMessageDialog(this, "File encrypted successfully!");
                } else{
                    Encrypt.encryptFile(inputFilename, outputFilename, key);
                    JOptionPane.showMessageDialog(this, "File encrypted successfully!");
                }

            } else if (e.getActionCommand().equals("Decrypt")) {
                if(twoAreEmpty){
                    Decrypt.decryptFile(path + "encoded.txt", path + "output.txt", key);
                    JOptionPane.showMessageDialog(this, "File decrypted successfully!");
                }else {
                    Decrypt.decryptFile(inputFilename, outputFilename, key);
                    JOptionPane.showMessageDialog(this, "File decrypted successfully!");
                }

            } else if (e.getActionCommand().equals("Brute Force")) {
                if(twoAreEmpty){
                    BruteForce.bruteForce(path + "encoded.txt", path + "output.txt");
                    JOptionPane.showMessageDialog(this, "File decrypted with brute force successfully!");
                }else{
                    BruteForce.bruteForce(inputFilename, outputFilename);
                    JOptionPane.showMessageDialog(this, "File decrypted with brute force successfully!");
                }

            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage());
        }
    }


}
