package com.javarush.cryptoanalyzer.avdeenko.services.bruteforce;

import com.javarush.cryptoanalyzer.avdeenko.constants.CryptoAlphabet;
import com.javarush.cryptoanalyzer.avdeenko.services.decrypt.Decrypt;

import java.io.*;
import java.util.Dictionary;

import static com.javarush.cryptoanalyzer.avdeenko.constants.CryptoAlphabet.ALPHABET;
import static com.javarush.cryptoanalyzer.avdeenko.services.decrypt.Decrypt.decrypt;
import static com.javarush.cryptoanalyzer.avdeenko.services.decrypt.Decrypt.decryptFile;

public class BruteForce {
    public static void bruteForce(String inputFilename, String outputFilename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilename))) {
            String line;
            int maxMatches = 0;
            int bestKey = 0;
            while ((line = reader.readLine()) != null) {
                for (int key = 0; key < CryptoAlphabet.ALPHABET_LENGTH; key++) {
                    String decryptedLine = decrypt(line, key);
                    int matches = countMatches(decryptedLine);
                    if (matches > maxMatches) {
                        maxMatches = matches;
                        bestKey = key;
                    }
                }
            }
            decryptFile(inputFilename, outputFilename, bestKey);
        }
    }
    private static int countMatches(String text) {
        int matches = 0;
        String[] words = text.split("\\s+");
        for (String word : words) {
            if (word.matches("^[а-яА-ЯёЁ]+[.,\"':!?\\-]*$")) {
                matches++;
            }
        }
        return matches;
    }


}
