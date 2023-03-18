package com.javarush.cryptoanalyzer.avdeenko.services;

import com.javarush.cryptoanalyzer.avdeenko.constants.CryptoAlphabet;

import java.io.*;

public class Encrypt {

    //Шифрует текст шифром Цезаря с заданным ключом.

    public static String encrypt(String simpleText, int key) {
        StringBuilder encryptedText = new StringBuilder();
        for (char c : simpleText.toCharArray()) {
            int index = CryptoAlphabet.ALPHABET.indexOf(c);
            if (index != -1) {
                int shiftedIndex = (index + key) % CryptoAlphabet.ALPHABET_LENGTH;
                encryptedText.append(CryptoAlphabet.ALPHABET.charAt(shiftedIndex));
            }
        }
        return encryptedText.toString();
    }


    //Шифрует файл шифром Цезаря с заданным ключом и сохраняет результат в новый файл.
    public static void encryptFile(String inputFilename, String outputFilename, int key) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilename));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String encryptedLine = encrypt(line, key);
                writer.write(encryptedLine);
                writer.newLine();
            }
        }
    }

}
