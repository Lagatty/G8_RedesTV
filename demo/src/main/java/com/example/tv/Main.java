package com.example.tv;

import com.example.tv.util.DBManager;
import com.example.tv.view.MainWindow;

public class Main {
    public static void main(String[] args) {
        DBManager.initDB();
        javax.swing.SwingUtilities.invokeLater(() -> {
            new MainWindow().setVisible(true);
        });
    }
}