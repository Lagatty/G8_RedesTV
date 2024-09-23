package g8_ant;
import java.util.ArrayList;
import java.util.Map.Entry;

public class AdminSuscripciones {
    private ArrayList<Suscripcion> suscripciones = new ArrayList<Suscripcion>();
    private ArrayList<String> sectores = new ArrayList<String>();
    private ArrayList<PaqueteCanales> paquetesGlobales = new ArrayList<PaqueteCanales>();

    public AdminSuscripciones() {
        agregarSector("Valparaiso");
        agregarSector("Quillota");
        agregarSector("Calera");
        crearSuscripciones();
        // Datos iniciales
        agregarSuscriptor("Juan", "Valparaiso");
        agregarSuscriptor("Maria", "Quillota", 30);
    }

    public ArrayList<Suscripcion> getSuscripciones() {
        return suscripciones;
    }

    public ArrayList<String> getSectores(){
        return sectores;
    }

    private void agregarSector(String nuevoSector) {
        sectores.add(nuevoSector);
    }

    public void mostrarSectores() {
        System.out.println("Sectores: ");
        for (String sector : sectores) {
            System.out.println(sector);
        }
    }

    private void crearSuscripciones() {
        for (String sector : sectores) {
            suscripciones.add(new Suscripcion(sector));
        }
    }

    public void listarSuscriptores() {
        for (Suscripcion sp : suscripciones) {
            System.out.println("Zona: " + sp.getZonaCobertura());
            for (Entry<Integer, Suscriptor> entry : sp.getMapSuscriptores().entrySet()) {
                Suscriptor suscriptor = entry.getValue();
                System.out.println("ID: " + suscriptor.getSuscriptorID() + ", Nombre: " + suscriptor.getNombre() + ", Edad: " + suscriptor.getEdad());
            }
            System.out.println();
        }
    }

    // Sobrecarga de métodos
    public void agregarSuscriptor(String nombre, String sector) {
        agregarSuscriptor(nombre, sector, 20); // Edad por defecto
    }

    public void agregarSuscriptor(String nombre, String sector, int edad) {
        for (Suscripcion suscripcion : suscripciones) {
            if (suscripcion.getZonaCobertura().equalsIgnoreCase(sector)) {
                Suscriptor nuevoSuscriptor = new Suscriptor(nombre, edad);
                suscripcion.getMapSuscriptores().put(nuevoSuscriptor.getSuscriptorID(), nuevoSuscriptor);
                System.out.println("Suscriptor agregado con éxito.");
                return;
            }
        }
        System.out.println("Sector no encontrado.");
    }

    public void agregarPaqueteGlobal(PaqueteCanales paquete) {
        paquetesGlobales.add(paquete);
        System.out.println("Paquete global agregado con ID: " + paquetesGlobales.size());
    }

    public void listarPaquetesGlobales() {
        System.out.println("Paquetes globales disponibles:");
        for (int i = 0; i < paquetesGlobales.size(); i++) {
            System.out.println("ID: " + (i + 1) + " - " + paquetesGlobales.get(i));
        }
    }

    public void agregarPaqueteCanalesASector(String sector, int paqueteId) {
        if (paqueteId <= 0 || paqueteId > paquetesGlobales.size()) {
            System.out.println("ID de paquete inválido.");
            return;
        }
        PaqueteCanales paquete = paquetesGlobales.get(paqueteId - 1);
        for (Suscripcion suscripcion : suscripciones) {
            if (suscripcion.getZonaCobertura().equalsIgnoreCase(sector)) {
                suscripcion.agregarPaqueteCanales(paquete);
                System.out.println("Paquete de canales agregado con éxito a la suscripción de " + sector);
                return;
            }
        }
        System.out.println("Sector no encontrado.");
    }

    public void mostrarPaquetesPorSector(String sector) {
        for (Suscripcion suscripcion : suscripciones) {
            if (suscripcion.getZonaCobertura().equalsIgnoreCase(sector)) {
                suscripcion.mostrarPaquetesCanales();
                return;
            }
        }
        System.out.println("Sector no encontrado.");
    }

    public void identificarSectoresDebiles() {
        System.out.println("Sectores débiles en captación de clientes:");
        for (Suscripcion suscripcion : suscripciones) {
            if (suscripcion.getMapSuscriptores().size() < 5) { // Ejemplo de criterio
                System.out.println(suscripcion.getZonaCobertura() + " - Suscriptores: " + suscripcion.getMapSuscriptores().size());
            }
        }
    }
}