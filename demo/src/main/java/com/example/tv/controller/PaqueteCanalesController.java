package com.example.tv.controller;

import com.example.tv.model.PaqueteCanales;
import com.example.tv.model.Sector;
import com.example.tv.util.DBManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaqueteCanalesController {

    public void agregarPaquete(PaqueteCanales paquete) {
        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                 "INSERT INTO PaquetesCanales (nombre, precioBase, precioOferta, enOferta) VALUES (?, ?, ?, ?)",
                 Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, paquete.getNombre());
            pstmt.setDouble(2, paquete.getPrecioBase());
            pstmt.setDouble(3, paquete.getPrecioOferta());
            pstmt.setBoolean(4, paquete.isEnOferta());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    paquete.setId(generatedKeys.getInt(1));
                }
            }

            for (String canal : paquete.getCanales()) {
                agregarCanalAPaquete(paquete.getId(), canal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void agregarCanalAPaquete(int paqueteId, String nombreCanal) {
        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                 "INSERT INTO Canales (nombre, paquete_id) VALUES (?, ?)")) {
            pstmt.setString(1, nombreCanal);
            pstmt.setInt(2, paqueteId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PaqueteCanales> listarPaquetes() {
        List<PaqueteCanales> paquetes = new ArrayList<>();
        try (Connection conn = DBManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM PaquetesCanales")) {
            while (rs.next()) {
                PaqueteCanales paquete = new PaqueteCanales(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getDouble("precioBase")
                );
                paquete.setPrecioOferta(rs.getDouble("precioOferta"));
                cargarCanalesDePaquete(paquete);
                paquetes.add(paquete);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paquetes;
    }

    private void cargarCanalesDePaquete(PaqueteCanales paquete) {
        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT nombre FROM Canales WHERE paquete_id = ?")) {
            pstmt.setInt(1, paquete.getId());
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    paquete.agregarCanal(rs.getString("nombre"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void asignarPaqueteASector(int paqueteId, int sectorId) {
        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                 "INSERT INTO PaquetesSectores (paquete_id, sector_id) VALUES (?, ?)")) {
            pstmt.setInt(1, paqueteId);
            pstmt.setInt(2, sectorId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PaqueteCanales> listarPaquetesPorSector(int sectorId) {
        List<PaqueteCanales> paquetes = new ArrayList<>();
        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                 "SELECT p.* FROM PaquetesCanales p " +
                 "JOIN PaquetesSectores ps ON p.id = ps.paquete_id " +
                 "WHERE ps.sector_id = ?")) {
            pstmt.setInt(1, sectorId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    PaqueteCanales paquete = new PaqueteCanales(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getDouble("precioBase")
                    );
                    paquete.setPrecioOferta(rs.getDouble("precioOferta"));
                    cargarCanalesDePaquete(paquete);
                    paquetes.add(paquete);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paquetes;
    }
}