package custom;
import java.util.ArrayList;
import java.util.Map.Entry;

public class AdminSuscripciones {
    private ArrayList<Suscripcion> suscripciones = new ArrayList<Suscripcion>();
    private ArrayList<String> sectores = new ArrayList<String>();
    
    public AdminSuscripciones() {
        agregarSector("Valparaiso");
        agregarSector("Quillota");
        agregarSector("Calera");
        crearSuscripciones();
        
        // Datos iniciales
        agregarSuscriptor("Juan Pérez", "Valparaiso");
        agregarSuscriptor("María González", "Quillota", 30);
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
}