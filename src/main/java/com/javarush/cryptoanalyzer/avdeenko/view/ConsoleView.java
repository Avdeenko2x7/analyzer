package com.javarush.cryptoanalyzer.avdeenko.view;

import com.javarush.cryptoanalyzer.avdeenko.services.BruteForce;
import com.javarush.cryptoanalyzer.avdeenko.services.Decrypt;
import com.javarush.cryptoanalyzer.avdeenko.services.Encrypt;

import java.io.IOException;
import java.util.Scanner;

import static com.javarush.cryptoanalyzer.avdeenko.constants.FilesConst.*;

public class ConsoleView {

    public ConsoleView() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите путь к файлу (по умолчанию: " + defaultInputFile + "):");
        String inputFile = scanner.nextLine();
        if (inputFile.isEmpty()) {
            inputFile = defaultInputFile;
        }

        System.out.println("Введите путь для сохранения результата (по умолчанию: " + defaultOutputFile + "):");
        String outputFile = scanner.nextLine();
        if (outputFile.isEmpty()) {
            outputFile = defaultOutputFile;
        }

        System.out.println("Выберите действие:");
        System.out.println("1. Зашифровать файл");
        System.out.println("2. Расшифровать файл");
        System.out.println("3. Расшифровать файл методом брутфорса");
        int action = scanner.nextInt();

        System.out.println("Введите ключ шифрования (по умолчанию: 0):");
        int key = scanner.nextInt();

        if (action == 1) {
            try {
                Encrypt.encryptWriteFile(inputFile, outputFile, key);
                System.out.println("Файл успешно зашифрован и сохранен в файл " + outputFile);
            } catch (IOException e) {
                System.out.println("Ошибка при зашифровании файла: " + e.getMessage());
            }
        } else if (action == 2) {
            try {
                Decrypt.decryptWriteFile(inputFile, outputFile, key);
                System.out.println("Файл успешно расшифрован и сохранен в файл " + outputFile);
            } catch (IOException e) {
                System.out.println("Ошибка при расшифровке файла: " + e.getMessage());
            }
        } else if (action == 3) {
            try {
                BruteForce.bruteForce(inputFile, outputFile);
                System.out.println("Файл успешно расшифрован методом брутфорса и сохранен в файл " + outputFile);
            } catch (IOException e) {
                System.out.println("Ошибка при расшифровке файла: " + e.getMessage());
            }
        } else {
            System.out.println("Некорректный выбор действия");
        }
    }
}