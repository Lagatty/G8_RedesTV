package custom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Suscripcion {
    private String zonaCobertura;
    private Map<Integer, Suscriptor> mapSuscriptores = new HashMap<>();
    private List<PaqueteCanales> paquetesDisponibles = new ArrayList<>();

    public Suscripcion(String sector) {
        zonaCobertura = sector;
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

    public List<PaqueteCanales> getPaquetesDisponibles() {
        return paquetesDisponibles;
    }

    public void agregarPaqueteCanales(PaqueteCanales paquete) {
        paquetesDisponibles.add(paquete);
    }

    public void mostrarPaquetesCanales() {
        System.out.println("Paquetes disponibles en " + zonaCobertura + ":");
        for (int i = 0; i < paquetesDisponibles.size(); i++) {
            System.out.println((i + 1) + ". " + paquetesDisponibles.get(i));
        }
    }
}