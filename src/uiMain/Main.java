package src.uiMain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static Scanner input = new Scanner(System.in);
	static Supermercado supermercado;
	static Cliente cliente;
	
	public static void seleccionarSupermercado() {
		// Aqui falta completar las otras opciones de supermercado
		System.out.println("Seleccione el supermercado en el cual desea comprar\n"
				+ "1. Dumbo");
		int eleccion= input.nextInt();
		
		switch(eleccion) {
		case 1:
			System.out.println("Eligiste Dumbo\n");
			// libro1 y libro2 son objetos de prueba para testear la interfaz
			// Hacen parte del ArrayList oferlibros de la clase Supermercado
			Libro libro1 = new Libro("Guerra y Paz","Tolstoi","Un libro muy boneto\n","12345",10000);
			Libro libro2 = new Libro("Crimen","dovsto","I M P A K T A D O\n","66882",120000);
			
			supermercado= new Supermercado("Dumbo",new ArrayList<Libro>(Arrays.asList(libro1,libro2)));
			
			break;
			
		// Los otros casos son los demas supermercados
			
		}
	}
	
	public static void seleccionarTipo() {
		// Aqui falta completar las otras opciones de producto
		System.out.println("Seleccione el tipo de producto que desea comprar\n"
				+ "1. Libros\n"
				+ "2. Volver al menu anterior");
		int tipo=input.nextInt();
		switch(tipo) {
		//Aquí empieza el flujo de las 4 de las funcionalidades
		case 1:
			System.out.println("Elegiste libros\n");
			System.out.println("----------------------------------------");
			comprarLibro(supermercado.getOferlibros());
			break;
		case 2:
			System.out.println("Volviendo al menu anterior...");
			seleccionarSupermercado();
			seleccionarTipo();
			break;
		}
		
	}
	public static void comprarLibro(ArrayList<Libro> oferlib) {
		int i=0;
		for(Libro libro:oferlib) {
			System.out.println("Titulo: "+libro.getTitulo()+"\n");
			System.out.println("Autor: "+libro.getAutor()+"\n");
			System.out.println("Precio: "+libro.getPrecio()+"\n");
			System.out.println("INGRESE "+i+" PARA COMPRAR");
			System.out.println("----------------------------------------");
			++i;
		}
		System.out.println("Ingrese -1 para volver al menú anterior\n");
		int opcion= input.nextInt();
		if(opcion==-1) seleccionarTipo();
		else {
			System.out.println("Titulo: "+oferlib.get(opcion).getTitulo()+"\n");
			System.out.println("Precio: "+oferlib.get(opcion).getPrecio()+"\n");
			System.out.println("Descripcion: "+oferlib.get(opcion).getDescripcion());
			System.out.println("1. Agregar al carrito\n");
			System.out.println("2. Volver al menu anterior\n");
			int comprarvolver = input.nextInt();
			switch(comprarvolver) {
			case 1:
				cliente.getCarrito().add(oferlib.get(opcion));
				oferlib.remove(opcion);
				supermercado.setOferlibros(oferlib);
				System.out.println("Se ha agregado al carrito!\n");
				System.out.println("1. Volver al menu anterior\n");
				System.out.println("2. Finalizar compra\n");
				
				int decision = input.nextInt();
				switch(decision) {
				case 1:
					comprarLibro(oferlib);
					break;
				case 2:
					//Aqui iria el metodo que muestra el carrito (finalizarCompra)
					break;
				}
				break;
			case 2:
				comprarLibro(supermercado.getOferlibros());
				break;
			}
			}
		}
	
	public void finalizarCompra() {
		
	}
	//Poner por aqui las funcionalidades
	
	public static void main(String[] args) {
		Cliente cliente = new Cliente("Alejandro","Mi casa");
		Main.cliente=cliente;
		seleccionarSupermercado();
		seleccionarTipo();
	}

}
