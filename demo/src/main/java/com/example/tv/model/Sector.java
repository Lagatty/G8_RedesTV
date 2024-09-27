package com.example.tv.model;

import java.util.ArrayList;
import java.util.List;


public class Sector {
    private int id;
    private String nombre;
    private List<PaqueteCanales> paquetesDisponibles;
    private int cantidadSuscriptores;
    private int cantidadPaquetes;

    public Sector(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.paquetesDisponibles = new ArrayList<>();
    }


    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public List<PaqueteCanales> getPaquetesDisponibles() { return paquetesDisponibles; }
    public void agregarPaquete(PaqueteCanales paquete) { paquetesDisponibles.add(paquete); }

   

    public int getCantidadSuscriptores() {
        return cantidadSuscriptores;
    }

    public void setCantidadSuscriptores(int cantidadSuscriptores) {
        this.cantidadSuscriptores = cantidadSuscriptores;
    }

    public int getCantidadPaquetes() {
        return cantidadPaquetes;
    }

    public void setCantidadPaquetes(int cantidadPaquetes) {
        this.cantidadPaquetes = cantidadPaquetes;
    }

    @Override
    public String toString() {
        return nombre + " (Suscriptores: " + cantidadSuscriptores + ", Paquetes: " + cantidadPaquetes + ")";
    }

}