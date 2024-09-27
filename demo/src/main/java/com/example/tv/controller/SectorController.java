package com.example.tv.controller;

import com.example.tv.model.Sector;
import com.example.tv.util.DBManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SectorController {
    
    public void agregarSector(String nombre) {
        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Sectores (nombre) VALUES (?)")) {
            pstmt.setString(1, nombre);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Sector> listarSectores() {
        List<Sector> sectores = new ArrayList<>();
        try (Connection conn = DBManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, nombre FROM Sectores")) {
            while (rs.next()) {
                sectores.add(new Sector(rs.getInt("id"), rs.getString("nombre")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sectores;
    }

    public List<Sector> identificarSectoresDebiles() {
        List<Sector> sectoresDebiles = new ArrayList<>();
        String query = "SELECT s.id, s.nombre, " +
                       "COUNT(sus.id) as cantidad_suscriptores, " +
                       "COUNT(DISTINCT ps.paquete_id) as cantidad_paquetes, " +
                       "(SELECT AVG(cantidad) FROM (SELECT COUNT(*) as cantidad FROM Suscriptores GROUP BY sector_id) as subconsulta) as promedio_suscriptores " +
                       "FROM Sectores s " +
                       "LEFT JOIN Suscriptores sus ON s.id = sus.sector_id " +
                       "LEFT JOIN PaquetesSectores ps ON s.id = ps.sector_id " +
                       "GROUP BY s.id " +
                       "HAVING cantidad_suscriptores < promedio_suscriptores * 0.5 OR cantidad_paquetes = 0";
        
        try (Connection conn = DBManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Sector sector = new Sector(rs.getInt("id"), rs.getString("nombre"));
                sector.setCantidadSuscriptores(rs.getInt("cantidad_suscriptores"));
                sector.setCantidadPaquetes(rs.getInt("cantidad_paquetes"));
                sectoresDebiles.add(sector);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sectoresDebiles;
    }

    public int contarSuscriptoresPorSector(int sectorId) {
        int count = 0;
        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                 "SELECT COUNT(*) as count FROM Suscriptores WHERE sector_id = ?")) {
            pstmt.setInt(1, sectorId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt("count");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    
}