package com.example.tv.model;

import java.util.ArrayList;
import java.util.List;

public class PaqueteCanales {
    private int id;
    private String nombre;
    private List<String> canales;
    private double precioBase;
    private double precioOferta;
    private boolean enOferta;

    public PaqueteCanales(int id, String nombre, double precioBase) {
        this.id = id;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.precioOferta = precioBase;
        this.canales = new ArrayList<>();
        this.enOferta = false;
    }

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public List<String> getCanales() { return canales; }
    public void agregarCanal(String canal) { this.canales.add(canal); }
    public double getPrecioBase() { return precioBase; }
    public void setPrecioBase(double precioBase) { this.precioBase = precioBase; }
    public double getPrecioOferta() { return precioOferta; }
    public void setPrecioOferta(double precioOferta) {
        this.precioOferta = precioOferta;
        this.enOferta = (precioOferta < precioBase);
    }
    public boolean isEnOferta() { return enOferta; }

    @Override
    public String toString() {
        return "PaqueteCanales{" +
               "nombre='" + nombre + '\'' +
               ", canales=" + canales.size() +
               ", precioBase=" + precioBase +
               ", precioOferta=" + precioOferta +
               ", enOferta=" + enOferta +
               '}';
    }
}