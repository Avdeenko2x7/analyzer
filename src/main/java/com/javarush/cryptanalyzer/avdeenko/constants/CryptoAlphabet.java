package com.javarush.cryptanalyzer.avdeenko.constants;

public class CryptoAlphabet {
    private static final String lettersUpperCase = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    private static final String lettersLowerCase = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private static final String numbers = "0123456789";
    private static final String symbols = ":,.\"-!? ";
    public static final String ALPHABET = lettersUpperCase + lettersLowerCase + numbers + symbols;

    public static final int ALPHABET_LENGTH = ALPHABET.length();
}

