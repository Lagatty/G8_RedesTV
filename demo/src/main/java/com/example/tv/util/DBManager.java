package com.example.tv.util;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
    private static final String DB_NAME = "sistematv.db";
    private static final String DB_PATH = System.getProperty("user.home") + "/." + DB_NAME;
    private static final String DB_URL = "jdbc:sqlite:" + DB_PATH;
    
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

    public static String getDBPath() {
        return DB_PATH;
    }

    public static boolean dbExists() {
        return Files.exists(Paths.get(DB_PATH));
    }

    public static void initDB() {
        boolean dbExisted = dbExists();
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
            
            // Crear tabla PaquetesSectores
            stmt.execute("CREATE TABLE IF NOT EXISTS PaquetesSectores (" +
                         "paquete_id INTEGER," +
                         "sector_id INTEGER," +
                         "PRIMARY KEY (paquete_id, sector_id)," +
                         "FOREIGN KEY(paquete_id) REFERENCES PaquetesCanales(id)," +
                         "FOREIGN KEY(sector_id) REFERENCES Sectores(id))");
            
            if (!dbExisted) {
                // Insertar datos de ejemplo
                stmt.execute("INSERT INTO Sectores (nombre) VALUES ('Valparaíso'), ('Viña del Mar'), ('Quilpué')");
                stmt.execute("INSERT INTO Suscriptores (nombre, edad, sector_id) VALUES ('Juan Pérez', 30, 1), ('María González', 25, 2)");
                stmt.execute("INSERT INTO PaquetesCanales (nombre, precioBase) VALUES ('Básico', 10000), ('Premium', 20000)");
                stmt.execute("INSERT INTO Canales (nombre, paquete_id) VALUES ('Canal 1', 1), ('Canal 2', 1), ('Canal 3', 2)");
                stmt.execute("INSERT INTO PaquetesSectores (paquete_id, sector_id) VALUES (1, 1), (1, 2), (2, 2)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}