package com.javarush.cryptoanalyzer.avdeenko.services.decrypt;


import com.javarush.cryptoanalyzer.avdeenko.constants.CryptoAlphabet;

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

}
