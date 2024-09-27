package com.example.tv.controller;

import com.example.tv.model.Sector;
import com.example.tv.model.Suscriptor;
import com.example.tv.util.DBManager;
import com.example.tv.util.SuscriptorException;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class SuscriptorController {
    
     public void agregarSuscriptor(Suscriptor suscriptor) throws SuscriptorException {
        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                 "INSERT INTO Suscriptores (nombre, edad, sector_id) VALUES (?, ?, ?)")) {
            pstmt.setString(1, suscriptor.getNombre());
            pstmt.setInt(2, suscriptor.getEdad());
            pstmt.setInt(3, suscriptor.getSector().getId());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new SuscriptorException("No se pudo agregar el suscriptor");
            }
        } catch (SQLException e) {
            throw new SuscriptorException("Error al agregar suscriptor: " + e.getMessage());
        }
    }

    public List<Suscriptor> listarSuscriptores() {
        List<Suscriptor> suscriptores = new ArrayList<>();
        try (Connection conn = DBManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                 "SELECT s.id, s.nombre, s.edad, sec.id as sector_id, sec.nombre as sector_nombre " +
                 "FROM Suscriptores s " +
                 "JOIN Sectores sec ON s.sector_id = sec.id")) {
            while (rs.next()) {
                Sector sector = new Sector(rs.getInt("sector_id"), rs.getString("sector_nombre"));
                suscriptores.add(new Suscriptor(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("edad"),
                    sector
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suscriptores;
    }

    public void modificarSuscriptor(Suscriptor suscriptor) {
        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                 "UPDATE Suscriptores SET nombre = ?, edad = ?, sector_id = ? WHERE id = ?")) {
            pstmt.setString(1, suscriptor.getNombre());
            pstmt.setInt(2, suscriptor.getEdad());
            pstmt.setInt(3, suscriptor.getSector().getId());
            pstmt.setInt(4, suscriptor.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarSuscriptor(int id) {
        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Suscriptores WHERE id = ?")) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Suscriptor> filtrarSuscriptores(int edadMinima, int edadMaxima, String sector) {
        List<Suscriptor> suscriptoresFiltrados = new ArrayList<>();
        String query = "SELECT s.id, s.nombre, s.edad, sec.id as sector_id, sec.nombre as sector_nombre " +
                       "FROM Suscriptores s " +
                       "JOIN Sectores sec ON s.sector_id = sec.id " +
                       "WHERE s.edad BETWEEN ? AND ? " +
                       (sector != null ? "AND sec.nombre = ?" : "");
        
        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, edadMinima);
            pstmt.setInt(2, edadMaxima);
            if (sector != null) {
                pstmt.setString(3, sector);
            }
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Sector sectorObj = new Sector(rs.getInt("sector_id"), rs.getString("sector_nombre"));
                suscriptoresFiltrados.add(new Suscriptor(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getInt("edad"),
                    sectorObj
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suscriptoresFiltrados;
    }


    public void generarReporte(String fileName) throws IOException {
        List<Suscriptor> suscriptores = listarSuscriptores();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("Reporte de Suscriptores\n");
            writer.write("=======================\n\n");
            for (Suscriptor s : suscriptores) {
                writer.write(s.toString() + "\n");
            }
        }
    }
}