package custom;
import java.util.Date;

public class Suscriptor {
    private int edad;
    private String nombre;
    private Date fecha;
    private int suscriptorID;
    private static int cantidadSuscriptores = 0;
    
    public Suscriptor(String nombreIngresado, int edad) {
        this.nombre = nombreIngresado;
        this.edad = edad;
        this.fecha = new Date();
        cantidadSuscriptores++;
        this.suscriptorID = cantidadSuscriptores;
    }
    
    public Suscriptor(String nombreIngresado) {
        this(nombreIngresado, 20);
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getEdad() {
        return edad;
    }
    
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    public Date getFecha() {
        return fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public int getSuscriptorID() {
        return suscriptorID;
    }
    
    public static int getCantidadSuscriptores() {
        return cantidadSuscriptores;
    }
}