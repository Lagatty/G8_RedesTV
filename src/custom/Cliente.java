package custom;
import java.util.ArrayList;

public class Cliente {
	private int edad = 20;
	private String nombre = new String();
	private Compania x = new Compania();
	
	
	public Cliente() {
		// TODO Auto-generated constructor stub
		nombre = "Alberto";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("hola");
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
