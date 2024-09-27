package com.example.tv.util;

import java.sql.*;

public class DBManager {
    private static final String DB_URL = "jdbc:sqlite:sistematv.db";
    
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initDB() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            
            // Crear tabla Sectores
            stmt.execute("CREATE TABLE IF NOT EXISTS Sectores (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                         "nombre TEXT NOT NULL UNIQUE)");
            
            // Crear tabla Suscriptores
            stmt.execute("CREATE TABLE IF NOT EXISTS Suscriptores (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                         "nombre TEXT NOT NULL," +
                         "edad INTEGER," +
                         "sector_id INTEGER," +
                         "FOREIGN KEY(sector_id) REFERENCES Sectores(id))");
            
            // Crear tabla PaquetesCanales
            stmt.execute("CREATE TABLE IF NOT EXISTS PaquetesCanales (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                         "nombre TEXT NOT NULL," +
                         "precioBase REAL," +
                         "precioOferta REAL," +
                         "enOferta INTEGER)");
            
            // Crear tabla Canales
            stmt.execute("CREATE TABLE IF NOT EXISTS Canales (" +
                         "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                         "nombre TEXT NOT NULL," +
                         "paquete_id INTEGER," +
                         "FOREIGN KEY(paquete_id) REFERENCES PaquetesCanales(id))");
            
            // Crear tabla PaquetesSectores (relación muchos a muchos)
            stmt.execute("CREATE TABLE IF NOT EXISTS PaquetesSectores (" +
                         "paquete_id INTEGER," +
                         "sector_id INTEGER," +
                         "PRIMARY KEY (paquete_id, sector_id)," +
                         "FOREIGN KEY(paquete_id) REFERENCES PaquetesCanales(id)," +
                         "FOREIGN KEY(sector_id) REFERENCES Sectores(id))");
            
            // Insertar algunos sectores de ejemplo
            stmt.execute("INSERT OR IGNORE INTO Sectores (nombre) VALUES ('Valparaiso'), ('Quillota'), ('Calera')");
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}