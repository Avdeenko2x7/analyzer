package com.javarush.cryptanalyzer.avdeenko.view;

import com.javarush.cryptanalyzer.avdeenko.services.BruteForce;
import com.javarush.cryptanalyzer.avdeenko.services.Decrypt;
import com.javarush.cryptanalyzer.avdeenko.services.Encrypt;

import java.io.IOException;
import java.util.Scanner;

import static com.javarush.cryptanalyzer.avdeenko.constants.FilesConst.*;

public class ConsoleView {

    public ConsoleView() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите действие:");
        System.out.println("1. Зашифровать файл");
        System.out.println("2. Расшифровать файл");
        System.out.println("3. Расшифровать файл методом брутфорса");
        int action = scanner.nextInt();

        String inputFile = defaultInputFile;
        String outputFile = defaultOutputFile;
        String encodedFile = defaultEncodedFile;

        if (action == 1) {
            System.out.println("Введите путь к файлу для шифрования (При нажатии Enter - по умолчанию: " + defaultInputFile + "):");
            scanner.nextLine();
            String input = scanner.nextLine();
            if (!input.isEmpty()) {
                inputFile = input;
            }
            System.out.println("Введите путь к файлу для сохранения (При нажатии Enter - по умолчанию: " + defaultEncodedFile + "):");
            String encoded = scanner.nextLine();
            if (!encoded.isEmpty()) {
                encodedFile = encoded;
            }
            System.out.println("Введите ключ шифрования:");
            int key = scanner.nextInt();
            try {
                Encrypt.encryptWriteFile(inputFile, encodedFile, key);
                System.out.println("Файл успешно зашифрован и сохранен в файл " + encodedFile);
            } catch (IOException e) {
                System.out.println("Ошибка при зашифровании файла: " + e.getMessage());
            }
        } else if (action == 2 || action == 3) {
            System.out.println("Введите путь к файлу для расшифровки (При нажатии Enter - по умолчанию: " + defaultEncodedFile + "):");
            scanner.nextLine();
            String encoded = scanner.nextLine();
            if (!encoded.isEmpty()) {
                encodedFile = encoded;
            }
            System.out.println("Введите путь для сохранения результата (При нажатии Enter - по умолчанию: " + defaultOutputFile + "):");
            String output = scanner.nextLine();
            if (!output.isEmpty()) {
                outputFile = output;
            }

            if (action == 2) {
                System.out.println("Введите ключ шифрования:");
                int key = scanner.nextInt();
                try {
                    Decrypt.decryptWriteFile(encodedFile, outputFile, key);
                    System.out.println("Файл успешно расшифрован и сохранен в файл " + outputFile);
                } catch (IOException e) {
                    System.out.println("Ошибка при расшифровке файла: " + e.getMessage());
                }
            } else if (action == 3) {
                try {
                    int key = BruteForce.bruteForce(encodedFile, outputFile);
                    System.out.println("Файл успешно расшифрован методом брутфорса и сохранен в файл " + outputFile);
                    System.out.println("Подобранный ключ: " + key);
                } catch (IOException e) {
                    System.out.println("Ошибка при расшифровке файла: " + e.getMessage());
                }
            }
        } else {
            System.out.println("Некорректный выбор действия");
        }
    }
}