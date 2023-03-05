package com.javarush.cryptoanalyzer.avdeenko.services.encrypt;

import com.javarush.cryptoanalyzer.avdeenko.constants.CryptoAlphabet;

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


}
