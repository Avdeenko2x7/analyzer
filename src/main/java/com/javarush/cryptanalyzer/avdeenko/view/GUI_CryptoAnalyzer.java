package com.javarush.cryptanalyzer.avdeenko.view;
import com.javarush.cryptanalyzer.avdeenko.services.BruteForce;
import com.javarush.cryptanalyzer.avdeenko.services.Decrypt;
import com.javarush.cryptanalyzer.avdeenko.services.Encrypt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static com.javarush.cryptanalyzer.avdeenko.constants.FilesConst.*;

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

        JLabel defaultFilesLabel = new JLabel(" *if the fields are empty, the values will be default");
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
        int key = 0;

        if (e.getActionCommand().equals("Encrypt") || e.getActionCommand().equals("Decrypt")) {

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
            key = Integer.parseInt(keyString);
        }

        boolean twoAreEmpty = inputFilename.equals("") && outputFilename.equals("");

        try {

            if (e.getActionCommand().equals("Encrypt")) {
                if(twoAreEmpty){
                    Encrypt.encryptWriteFile(defaultInputFile, defaultEncodedFile, key);
                    JOptionPane.showMessageDialog(this, encryptSuccess);
                } else{
                    Encrypt.encryptWriteFile(inputFilename, outputFilename, key);
                    JOptionPane.showMessageDialog(this, encryptSuccess);
                }

            } else if (e.getActionCommand().equals("Decrypt")) {
                if(twoAreEmpty){
                    Decrypt.decryptWriteFile(defaultEncodedFile, defaultOutputFile, key);
                    JOptionPane.showMessageDialog(this, decryptSuccess);
                }else {
                    Decrypt.decryptWriteFile(inputFilename, outputFilename, key);
                    JOptionPane.showMessageDialog(this, decryptSuccess);
                }

            } else if (e.getActionCommand().equals("Brute Force")) {
                if(twoAreEmpty){
                    int bestKey = BruteForce.bruteForce(defaultEncodedFile, defaultOutputFile);
                    System.out.println("Подобранный ключ: " + bestKey);
                    BruteForce.bruteForce(defaultEncodedFile, defaultOutputFile);
                    JOptionPane.showMessageDialog(this, bruteforceSuccess + "(key= " + bestKey + ")");
                }else{
                    int bestKey = BruteForce.bruteForce(defaultEncodedFile, defaultOutputFile);
                    System.out.println("Подобранный ключ: " + bestKey);
                    BruteForce.bruteForce(inputFilename, outputFilename);
                    JOptionPane.showMessageDialog(this, bruteforceSuccess + "(key= " + bestKey + ")");
                }

            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "An error occurred: " + ex.getMessage());
        }
    }


}
