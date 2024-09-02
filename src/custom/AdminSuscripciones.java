package custom;
import java.util.ArrayList;
import java.util.Map.Entry;
public class AdminSuscripciones {
	//Arraylist que contiene las diferentes suscripciones
	private ArrayList<Suscripcion> suscripciones = new ArrayList<Suscripcion>();
	//Lista de todos los sectores 
	private ArrayList<String> sectores = new ArrayList<String>();
	
	public AdminSuscripciones() {
		// TODO Auto-generated constructor stub
		agregarSector("Valparaiso");
		agregarSector("Quillota");
		agregarSector("Calera");
		//Se crean suscripciones a partir de los sectores agregados
		crearSuscripciones();
		
	}
	//Getters/Setters
	public ArrayList<Suscripcion> getSuscripciones() {
		return suscripciones;
	}
	
	public ArrayList<String> getSectores(){
		return sectores;
	}
	
	//Agrega el sector a la lista
	private void agregarSector(String nuevoSector) {
		sectores.add(nuevoSector);
	}
	
	//Mostrar los sectores
	
	public void mostrarSectores() {
		System.out.println("Sectores: ");
		for (String sector : sectores) {
			System.out.println(sector);
		}
	}
	
	//Crear suscripciones para cada sector 
	private void crearSuscripciones() {
		for (String sector : sectores) {
			suscripciones.add(new Suscripcion(sector));
		}
	}
	
	//Listar suscriptores
	
	public void listarSuscriptores() {
		for (Suscripcion sp : suscripciones) {
			//Mostrar la zona de la suscripcion
			System.out.println( sp.zonaCobertura);
			//Mostrar los sucriptores de esta suscripcion contenidos en el hashMap
			for (Entry<Integer, Suscriptor> entry : sp.getMapSuscriptores().entrySet()) {
	            Integer key = entry.getKey();         // Obtiene la clave (llave)
	            Suscriptor value = entry.getValue();     // Obtiene el valor (objeto Persona)

	            // Imprimir la clave y el valor
	            System.out.println("ID: " + key + ", Nombre: " + value.getNombre());
	        }
		}
	}
	
	//Agregar suscriptor
	public boolean agregarSuscriptor() {
		
	}
	
	
}
