package custom;
import java.util.HashMap;
import java.util.Map;

public class Suscripcion {
    private int precio = 15000;    
    private String zonaCobertura;
    private Map<Integer, Suscriptor> mapSuscriptores = new HashMap<>();
    
    public Suscripcion(String sector) {
        zonaCobertura = sector;
    }
    
    public int getPrecio() {
        return precio;
    }
    
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    
    public String getZonaCobertura() {
        return zonaCobertura;
    }
    
    public void setZonaCobertura(String zonaCobertura) {
        this.zonaCobertura = zonaCobertura;
    }
    
    public Map<Integer, Suscriptor> getMapSuscriptores() {
        return mapSuscriptores;
    }
    
    public void setMapSuscriptores(Map<Integer, Suscriptor> mapSuscriptores) {
        this.mapSuscriptores = mapSuscriptores;
    }
}