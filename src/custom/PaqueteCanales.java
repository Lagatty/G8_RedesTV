package custom;

import java.util.ArrayList;
import java.util.List;

public class PaqueteCanales {
    private String nombre;
    private List<String> canales;
    private double precioBase;
    private double precioOferta;
    private boolean enOferta;

    public PaqueteCanales(String nombre, double precioBase) {
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.precioOferta = precioBase;
        this.canales = new ArrayList<>();
        this.enOferta = false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getCanales() {
        return canales;
    }

    public void agregarCanal(String canal) {
        this.canales.add(canal);
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public double getPrecioOferta() {
        return precioOferta;
    }

    public void setPrecioOferta(double precioOferta) {
        this.precioOferta = precioOferta;
        this.enOferta = (precioOferta < precioBase);
    }

    public boolean isEnOferta() {
        return enOferta;
    }

    @Override
    public String toString() {
        return "PaqueteCanales{" +
                "nombre='" + nombre + '\'' +
                ", canales=" + canales +
                ", precioBase=" + precioBase +
                ", precioOferta=" + precioOferta +
                ", enOferta=" + enOferta +
                '}';
    }
}