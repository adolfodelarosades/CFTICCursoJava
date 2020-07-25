package principal;

import java.util.Scanner;

public class Buscador {

   //Hola
	
	public static void main(String[] args) {
		
		StringBuilder titulos = new StringBuilder("");
		
		//String titulos = "La biblia de Java, Python básico, Angular en un día,programación Web con Java EE, Spring Boot, Fundamentos de Python, Java y Spring, la biblia de php";

		int opcion = 0;
		Scanner sc = new Scanner(System.in);

		do {
			
			mostrarMenu();
			System.out.println("\nIntroduce una opción :");
			opcion = sc.nextInt();
			switch (opcion) {
			   case 0: 
				   mostrarTitulo(titulos);
				   break;
			   case 1: 
				   addTitulo(titulos);
				   break;
			   case 2: 
				   buscarTitulo(titulos);
				   break;
			   case 3: 
				   eliminarTitulo(titulos);
				   break;
			   case 4: 
				   System.out.println("ADIOS!!!!");
				   break;
			   default:
				   System.out.println("Opción no válida");
			}
		} while (opcion != 4);
		sc.close();

	}
	
	static void  mostrarMenu() {
		System.out.println("0. Mostrar título");
		System.out.println("1. Añadir nuevo título");
		System.out.println("2. Buscar título");
		System.out.println("3. Eliminar título");
		System.out.println("4. Salir");
	}
	
	static void mostrarTitulo(StringBuilder  titulos) {
		System.out.println("\n*** Mostrar Título ***\n");
		System.out.println(titulos);
	}
	
	static void  addTitulo(StringBuilder titulos) {
		
		System.out.println("\n*** Añadir Título ***\n");
		
		Scanner sc1 = new Scanner(System.in);
		String nuevoTitulo = sc1.nextLine();
		
		titulos = titulos.append(nuevoTitulo).append(",");
		
	}
	
	static void  buscarTitulo( StringBuilder titulos) {
		
		System.out.println("\n*** Buscar Título ***\n");
		
		String texto ="";
		Scanner sc2 = new Scanner(System.in);
		
		System.out.println("\nIntroduce un texto a buscar :");
		texto = sc2.nextLine();
		
		//Quita la últmima coma
		/*if(titulos.charAt(titulos.length()-1)==',') {
			titulos.delete(titulos.length()-1, titulos.length());
		}*/
		

		String[] libros =  titulos.toString().split("(, |,)");

		if(!texto.toLowerCase().equals("salir")) {
		   System.out.println("\nLibros localizados: \n");
		}else {
		   System.out.println("\nADIOS!!! \n");	
		}

		for (String libro : libros) {

			if (libro.toLowerCase().contains(texto.toLowerCase())) {
				System.out.println(libro.trim());
			}
		}
		
	}
	
    static void  eliminarTitulo(StringBuilder titulos) {
    	
    	System.out.println("\n*** Eliminar Título ***\n");
		
		String texto ="";
		Scanner sc3 = new Scanner(System.in);
		
		System.out.println("\nIntroduce un texto a eliminar :");
		texto = sc3.nextLine() +",";
		
		int indice = titulos.indexOf(texto);
		
		if(titulos.indexOf(texto)!= -1) {
			titulos.delete(indice, indice + texto.length()+1);
		}
		
	}

}
