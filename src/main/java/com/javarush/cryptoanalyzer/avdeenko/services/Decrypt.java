package com.javarush.cryptoanalyzer.avdeenko.services;


import com.javarush.cryptoanalyzer.avdeenko.constants.CryptoAlphabet;

import java.io.*;

public class Decrypt {

    //Расшифровывает текст, зашифрованный шифром Цезаря с заданным ключом.

    public static String decrypt(String ciphertext, int key) {
        StringBuilder plaintext = new StringBuilder();
        for (char c : ciphertext.toCharArray()) {
            int index = CryptoAlphabet.ALPHABET.indexOf(c);
            if (index != -1) {
                int shiftedIndex = (index - key + CryptoAlphabet.ALPHABET_LENGTH) % CryptoAlphabet.ALPHABET_LENGTH;
                plaintext.append(CryptoAlphabet.ALPHABET.charAt(shiftedIndex));
            }
        }
        return plaintext.toString();
    }

    //Расшифровывает файл, зашифрованный шифром Цезаря с заданным ключом, и сохраняет результат в новый файл.
    public static void decryptWriteFile(String inputFilename, String outputFilename, int key) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilename));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String decryptedLine = decrypt(line, key);
                writer.write(decryptedLine);
                writer.newLine();
            }
        }
    }
}
