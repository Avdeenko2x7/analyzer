package com.javarush.cryptoanalyzer.avdeenko;

import com.javarush.cryptoanalyzer.avdeenko.view.ConsoleView;
import com.javarush.cryptoanalyzer.avdeenko.view.GUI_CryptoAnalyzer;

import javax.swing.*;

public class App
{
    public static void run(){
        int choice = JOptionPane.showOptionDialog(null, "Выберите интерфейс", "Выбор интерфейса",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new String[]{"Консольный интерфейс", "Графический интерфейс"}, null);

        if (choice == JOptionPane.YES_OPTION) {
            ConsoleView consoleView = new ConsoleView();
        } else if (choice == JOptionPane.NO_OPTION) {
            GUI_CryptoAnalyzer gui = new GUI_CryptoAnalyzer();
        } else {
            System.exit(0);
        }
    }
    }
