package custom;
import java.util.HashMap;
import java.util.Map;

public class Suscripcion {
	//Atributos
	
	
	//Precio expresado en pesos (1 unidad = 1 peso).
	private int precio = 15000; 	
	//Cobertura
	String zonaCobertura;
	//Mapa de llaves integer y valores de suscriptor para las busquedas
	private Map<Integer, Suscriptor> mapSuscriptores = new HashMap<>();
	public Suscripcion(String sector) {
		// TODO Auto-generated constructor stub
		zonaCobertura = sector;
	}
	
	//Getters y Setters
	
	//precio
	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	//HashMap de suscriptores
	public Map<Integer, Suscriptor> getMapSuscriptores() {
		return mapSuscriptores;
	}

	public void setMapSuscriptores(Map<Integer, Suscriptor> mapSuscriptores) {
		this.mapSuscriptores = mapSuscriptores;
	}

}
