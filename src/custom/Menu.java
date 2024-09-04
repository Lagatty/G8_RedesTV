package custom;
import java.io.*;

public class Menu {
    public Menu() {
    }
    
    public static void main(String[] args) throws IOException {
        AdminSuscripciones adminSp = new AdminSuscripciones();
        Menu menu = new Menu();
        menu.ejecutarMenu(adminSp);
    }
    
    public void ejecutarMenu(AdminSuscripciones admin) throws IOException {
        BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
        String ingresado;
        int input;
        
        while(true) {
            System.out.println("\nLista de opciones\n 1: Agregar Suscriptor ; 2: Listar Suscriptores ; 3: Listar sectores ; 0: Salir \n Ingrese opcion: ");
            ingresado = lector.readLine();
            try {
                input = Integer.parseInt(ingresado);
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número válido.");
                continue;
            }
            
            if(input == 0) {
                System.out.println("Saliendo del programa...");
                break;
            }
            
            switch (input) {
                case 1:
                    agregarSuscriptor(admin, lector);
                    break;
                case 2:
                    admin.listarSuscriptores();
                    break;
                case 3:
                    admin.mostrarSectores();
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }
    
    private void agregarSuscriptor(AdminSuscripciones admin, BufferedReader lector) throws IOException {
        System.out.println("Ingrese nombre del suscriptor:");
        String nombre = lector.readLine();
        System.out.println("Ingrese sector:");
        String sector = lector.readLine();
        System.out.println("Ingrese edad (presione Enter para usar edad por defecto):");
        String edadStr = lector.readLine();
        
        if (edadStr.isEmpty()) {
            admin.agregarSuscriptor(nombre, sector);
        } else {
            try {
                int edad = Integer.parseInt(edadStr);
                admin.agregarSuscriptor(nombre, sector, edad);
            } catch (NumberFormatException e) {
                System.out.println("Edad inválida. Se usará la edad por defecto.");
                admin.agregarSuscriptor(nombre, sector);
            }
        }
    }
}