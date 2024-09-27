package com.example.tv.model;

public class Suscriptor extends Persona {
    private int id;
    private Sector sector;

    public Suscriptor(int id, String nombre, int edad, Sector sector) {
        super(nombre, edad);
        this.id = id;
        this.sector = sector;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public Sector getSector() { return sector; }
    public void setSector(Sector sector) { this.sector = sector; }

    @Override
    public String toString() {
        return super.toString() + ", ID: " + id + ", Sector: " + sector.getNombre();
    }
}