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
            System.out.println("\nLista de opciones\n" +
                    "1: Agregar Suscriptor\n" +
                    "2: Listar Suscriptores\n" +
                    "3: Listar sectores\n" +
                    "4: Agregar Paquete Global de Canales\n" +
                    "5: Listar Paquetes Globales\n" +
                    "6: Agregar Paquete a Sector\n" +
                    "7: Mostrar Paquetes por Sector\n" +
                    "8: Identificar Sectores Débiles\n" +
                    "0: Salir\n" +
                    "Ingrese opcion: ");
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
                case 4:
                    agregarPaqueteGlobal(admin, lector);
                    break;
                case 5:
                    admin.listarPaquetesGlobales();
                    break;
                case 6:
                    agregarPaqueteASector(admin, lector);
                    break;
                case 7:
                    mostrarPaquetesPorSector(admin, lector);
                    break;
                case 8:
                    admin.identificarSectoresDebiles();
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

    private void agregarPaqueteGlobal(AdminSuscripciones admin, BufferedReader lector) throws IOException {
        System.out.println("Ingrese nombre del paquete de canales:");
        String nombre = lector.readLine();
        System.out.println("Ingrese precio base del paquete:");
        double precioBase = Double.parseDouble(lector.readLine());
        PaqueteCanales paquete = new PaqueteCanales(nombre, precioBase);
        
        System.out.println("Ingrese canales (escriba 'fin' para terminar):");
        String canal;
        while (!(canal = lector.readLine()).equalsIgnoreCase("fin")) {
            paquete.agregarCanal(canal);
        }
        
        admin.agregarPaqueteGlobal(paquete);
    }

    private void agregarPaqueteASector(AdminSuscripciones admin, BufferedReader lector) throws IOException {
        System.out.println("Ingrese sector para agregar el paquete:");
        String sector = lector.readLine();
        System.out.println("Ingrese ID del paquete global a agregar:");
        int paqueteId = Integer.parseInt(lector.readLine());

        admin.agregarPaqueteCanalesASector(sector, paqueteId);
    }

    private void mostrarPaquetesPorSector(AdminSuscripciones admin, BufferedReader lector) throws IOException {
        System.out.println("Ingrese sector para mostrar los paquetes de canales:");
        String sector = lector.readLine();
        admin.mostrarPaquetesPorSector(sector);
    }
}