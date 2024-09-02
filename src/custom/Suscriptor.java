package custom;
import java.util.ArrayList;
import java.util.Date;

public class Suscriptor {
	private int edad = 20;
	private String nombre = new String();
	//Fecha de cuando se suscribio
	private Date fecha = new Date();
	private int suscriptorID;
	//Variable para conocer la cantidad de suscriptores desde cualquier otro suscriptor
	static int cantidadSuscriptores;
	//Construct con nombre 
	public Suscriptor(String nombreIngresado) {
		// TODO Auto-generated constructor stub
		nombre = nombreIngresado;
		cantidadSuscriptores++;
		//El ID sera igual a la cantidad actual, eso hace que el ID sea diferente
		suscriptorID = cantidadSuscriptores;
	}
	//Construct sin nombre
	public Suscriptor() {
		nombre = "Sin nombre";
		cantidadSuscriptores++;
		//El ID sera igual a la cantidad actual, eso hace que el ID sea diferente
		suscriptorID = cantidadSuscriptores;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
