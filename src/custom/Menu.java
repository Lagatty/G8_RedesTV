package custom;
import java.io.*;
public class Menu {
	public Menu() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws IOException {
		//Instanciar admin de suscripciones
		AdminSuscripciones adminSp = new AdminSuscripciones();
		//crear y ejecutar menu
		Menu menu = new Menu();
		menu.ejecutarMenu(adminSp);
	}
	
	public void ejecutarMenu(AdminSuscripciones admin) throws IOException {
		//variables de lectura
		BufferedReader lector = new BufferedReader(new InputStreamReader(System.in));
		String ingresado;
		int input;
		
		while(true) {
			//mostrar opciones
			System.out.println("Lista de opciones\n 1: Agregar Suscriptor ; 2: Listar Suscriptores ; 3: Listar sectores ; 0: Salir \n Ingrese opcion: ");
			ingresado = lector.readLine();
			input = Integer.parseInt(ingresado);
			//Salir
			if(input==0) {
				break;
			}
			//Opciones
			switch (input) {
			case 1:
				break;
				
			case 2:
				
				break;
				
			case 3:
				admin.mostrarSectores();
				break;
		}
		
	}
	
}
}