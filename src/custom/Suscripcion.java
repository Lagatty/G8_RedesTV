package custom;
import java.util.Date;

public class Suscripcion {
	//Atributos
	private Cliente cliente = new Cliente();
	private Date fecha = new Date();
	//Precio expresado en pesos (1 unidad = 1 peso).
	private int precio = 15000; 	
	
	public Suscripcion() {
		// TODO Auto-generated constructor stub
	}
	
	//Getters y Setters
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	//fecha
	public Date getDate() {
		return fecha;
	}

	public void setDate(Date date) {
		this.fecha = date;
	}
	//precio
	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

}
