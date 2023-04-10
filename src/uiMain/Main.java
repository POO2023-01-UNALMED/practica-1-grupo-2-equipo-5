package src.uiMain;
//package uiMain;

import src.baseDatos.Deserializar;
import src.baseDatos.Serializar;
import src.gestorAplicacion.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
	public static Scanner sc = new Scanner(System.in);
	
	//Se crean las listas donde va cada tipo de producto
	public static ArrayList<Libro> lista_libros = new ArrayList<Libro>();
	public static ArrayList<Carne> lista_carnicos = new ArrayList<Carne>();
	public static ArrayList<noCarnicos> lista_no_carnicos = new ArrayList<noCarnicos>();
	public static ArrayList<Tv> lista_tvs = new ArrayList<Tv>();
	public static ArrayList<Celular> lista_celulares = new ArrayList<Celular>();
	public static ArrayList<Supermercado> lista_super = new ArrayList<Supermercado>();
	
	//Aqui empieza el main
	public static void main(String[] args) {
		
		lista_celulares = Deserializar.deserializarCelulares();
		lista_tvs = Deserializar.deserializarTvs();
		lista_libros = Deserializar.deserializarLibros();
		lista_no_carnicos = Deserializar.deserializarnoCarnicos();
		lista_carnicos = Deserializar.deserializarCarne();
		
		//Si no hay supermercados se ejecuta un flujo para crear el supermercado dentro de deserializarSupermercados()
		lista_super =  Deserializar.deserializarSupermercados();
		
		System.out.println("\nBienvenido a nuestro sistema gestor de tiendas!\n");
		System.out.print("Ingresa tu nombre: ");
		String nombre = sc.next();
		System.out.print("Ingresa tu direccion: ");
		String direccion = sc.next();
		Cliente cliente = new Cliente(nombre, direccion);
		System.out.println("\nBienvenido "+nombre+"!\n");
		
		while(true){
			System.out.print("Que deseas hacer?\n" +
					"1. Ver lista de supermercados" +
					"\n2. Salir\n");
			sc.nextLine();
			String respuesta =  sc.next();
			
			// los while se ejecutan hasta que se ingrese una respuesta valida
			while(!respuesta.equals("1") && !respuesta.equals("2")){
				System.out.print("La respuesta ingresada no se encuentra entre las opciones (1 o 2)" +
						"\nIngresala nuevamente\n");
				respuesta = Main.sc.next();
				
			}
			
			if(respuesta.equals("1")){
				seleccionarSupermercado();
			}
						
			// Termina la ejecucion del programa
			else{
				System.out.print("Gracias por visitar nuestro gestor de tiendas"+
						", siempre será bienvenido\n");
				break;
			}
		}

		if(lista_super.size() >0) {
			Serializar.serializarSupermercados(lista_super);
		}
		if(lista_celulares.size() >0) {
			Serializar.serializarCelulares(lista_celulares);
		}
		if(lista_tvs.size() >0) {
			Serializar.serializarTvs(lista_tvs);
		}

	}
	// Termina el Main
	
	public static void seleccionarSupermercado() {

		String respuesta;
		Supermercado super_seleccionado;
		//System.out.println(lista_super.size()); //por que?
		
		if(lista_super.size() == 0){
			//Si el usuario no crea ningun supermercado salimos del metodo para volver al menu anterior
			return;
		}
		System.out.println("Seleccione el supermercado en el cual desea comprar");
		for (int i = 0; i < lista_super.size(); i++) {
			System.out.println((i+1)+". "+lista_super.get(i).nombre);
		}
		System.out.println((lista_super.size()+1)+". Crear un nuevo supermercado");
		respuesta = sc.next();
		

		while(true){
			try{
				if(Integer.parseInt(respuesta)<= 0){
					respuesta = null; //Buscamos saltar el error ya que el intervalo solo contempla numeros positivos
				}
				if(Integer.parseInt(respuesta) == lista_super.size()+1 ){
					System.out.print("Por favor, ingrese el nombre del nuevo supermercado: ");
					Supermercado supermercado = new Supermercado(Main.sc.next());
					
					//Aqui se ejecutaria la opcion para añadir productos
					anadirProducto(supermercado);
					
					lista_super.add(supermercado);
					
					return;
				}
				else{
					super_seleccionado = lista_super.get(Integer.parseInt(respuesta)-1);
					System.out.println("El mercado seleccionado es "+super_seleccionado.nombre);
					seleccionarProducto(super_seleccionado);
					break;
				}
			}
			
			catch(Exception e){
				System.out.println("La opcion seleccionada no esta permitida, recapacita XD, intentalo nuevamente");
				respuesta = sc.next();
			}
		}
		
		Serializar.serializarSupermercados(lista_super);
		if (super_seleccionado.oferelectro.size() == 0) {anadirProducto(super_seleccionado);}
	}

	// Aqui entran todas las clases de la capa logica
	public static void anadirProducto(Supermercado mercado){
		String respuesta;
		
		System.out.println("\nAgregar productos al supermercado"+
				"\n1. Tevelisores" +
				"\n2. Celulares" +
				"\n3. Libros"+
				"\n4. Carne"+
				"\n5. NoCarnicos"+
				"\n6. Ropa"+
				"\n7. Volver al menu anterior");
		
		respuesta=Main.sc.next();
		// este while es para capturar errores de input
		while (Integer.parseInt(respuesta)<= 0 || Integer.parseInt(respuesta)> 7  ){
			System.out.println("El numero ingresado no es correcto, rectificar por favor");
			respuesta = sc.next();
		}
		
		switch (respuesta){
		
			case "1":
				
				System.out.print("Ingrese el nombre del Tv: ");
				String nombre = sc.next();
				sc.nextLine();
				System.out.print("Ingresa el precio del Tv "+nombre.toUpperCase()+": ");
				int precio = sc.nextInt();
				System.out.print("Ingresa la marca del Tv "+nombre.toUpperCase()+": ");
				String marca = sc.next();
				System.out.print("Ingresa el numero de pulgadas del Tv "+nombre.toUpperCase()+": ");	
				int pulgadas = sc.nextInt();
				System.out.print("Ingresa la resolucion del Tv "+nombre.toUpperCase()+": ");
				String resolucion = sc.next();
				System.out.print("Ingresa la cantidad de Tvs "+nombre.toUpperCase()+" desea añadir: ");
				int cantidadtv = sc.nextInt();
				Tv nuevotv = new Tv(nombre, precio, marca, mercado, cantidadtv, pulgadas, resolucion);
				mercado.oferelectro.add(nuevotv);
				lista_tvs.add(nuevotv);
				cuandoSeAgrega(mercado);
				break;
				
			case "2":
				System.out.print("Ingrese el nombre del celular: ");
				String nombrecel = sc.next();
				sc.nextLine();
				System.out.print("Ingresa el precio del celular "+nombrecel.toUpperCase()+": ");
				int preciocel = sc.nextInt();
				System.out.print("Ingresa la marca del celular "+nombrecel.toUpperCase()+": ");
				String marcacel = sc.next();
				sc.nextLine();
				System.out.println("Ingresa la cantidad de almacenamiento del celular "+
				nombrecel.toUpperCase()+": ");
				int almacenamiento = sc.nextInt();
				System.out.print("Ingresa el numero de camaras del celular "+nombrecel.toUpperCase()+": ");
				int camaras = sc.nextInt();
				System.out.print("Ingresa la bateria del celular "+nombrecel.toUpperCase()+": ");
				int bateria = sc.nextInt();
				System.out.println("Ingresa el color del celular "+nombrecel.toUpperCase()+": ");
				String color = sc.next();
				sc.nextLine();
				System.out.println("Ingresa el numero de megas de ram "+nombrecel.toUpperCase()+": ");
				int ram = sc.nextInt();
				System.out.print("Ingresa la cantidad de celulares "+nombrecel.toUpperCase()+" desea añadir: ");
				int cantidadcel = sc.nextInt();
				Celular nuevocel = new Celular(nombrecel, preciocel, marcacel, mercado, cantidadcel, almacenamiento, camaras, bateria, color, ram);
				mercado.oferelectro.add(nuevocel);
				lista_celulares.add(nuevocel);
				
				//Posibilidad de agregar otro producto cuando se acaba de crear uno
				//Solucion temporal?
				cuandoSeAgrega(mercado);
				break;
				
			case "3":
				
				//Libros
				break;
			case "4":
				//Carne
				System.out.println("Ingrese el nombre del producto carnico: ");
				String nombreCarne = sc.next();
				sc.nextLine();
				System.out.println("Ingrese el precio por libra del producto "+nombreCarne.toUpperCase()+": ");
				int precioCarne = sc.nextInt();
				System.out.println("Ingrese que tipo de producto es "+nombreCarne.toUpperCase()+": ");
				String tipoCarne = sc.next();
				System.out.println("Ingrese el peso en libras del producto "+nombreCarne.toUpperCase()+": ");
				float pesoCarne = sc.nextFloat();
				System.out.println("Ingrese cuantas unidades de "+nombreCarne.toUpperCase()+" desea añadir:");
				int cantidadCarne = sc.nextInt();
				Carne nuevaCarne =new Carne(nombreCarne,precioCarne, mercado, cantidadCarne, tipoCarne, pesoCarne);
				mercado.ofercomi.add(nuevaCarne);
				lista_carnicos.add(nuevaCarne);
				
				cuandoSeAgrega(mercado);
				break;
			case "5":
				//No Carne
				System.out.println("Ingrese el nombre del producto: ");
				String nombreAli = sc.next();
				sc.nextLine();
				System.out.println("Ingrese el precio por unidad del "+nombreAli.toUpperCase()+": ");
				int precioAli = sc.nextInt();
				System.out.println("Ingrese la cantidad de unidades de "+nombreAli.toUpperCase()+" que desea añadir:");
				int cantidadAli = sc.nextInt();
				noCarnicos nuevoAli = new noCarnicos(nombreAli, precioAli, mercado, cantidadAli);
				mercado.ofercomi.add(nuevoAli);
				lista_no_carnicos.add(nuevoAli);
				
				cuandoSeAgrega(mercado);
				break;
			case "6":
				//Ropa
				break;
			case "7":
				//volver al menu anterior
				break;
		}
	}
	
	//Posibilidad de agregar otro producto cuando se acaba de crear uno
	public static void cuandoSeAgrega(Supermercado mercado) {
		String respuesta;
		System.out.println("\nProducto agregado con exito!");
		
		System.out.println("\nDeseas agregar otro producto?"+
		                   "\n1. Si "
		                  +"\n2. No");
		respuesta=Main.sc.next();
		switch(respuesta) {
		case "1":
			anadirProducto(mercado);
			break;
		case "2":
			break;
		}	
	}
	
	//Esta seria la funcionalidad comprarElectro()
	public static void seleccionarProducto(Supermercado mercado){

		if(mercado.oferelectro.size() > 0){
			Electronico producto_seleccionado = null;
			System.out.println("Estos son los productos electronicos que tenemos disponibles en "+mercado.getNombre());
			for(int i = 0; i<mercado.oferelectro.size(); i++) {
				producto_seleccionado = mercado.oferelectro.get(i);
				System.out.println((i+1)+". "+
						"\nNombre:"+producto_seleccionado.getNombre()+" " +
						"\n Marca: "+producto_seleccionado.getMarca()+" " +
						"\n Precio: "+producto_seleccionado.getPrecio()+" "+
						"\n Cantidad en stock: "+producto_seleccionado.getCantidad());
			}
			System.out.println("Selecciona uno de nuestros productos a comprar: ");
			String respuesta = sc.next();
			while(Integer.parseInt(respuesta) > mercado.oferelectro.size() || Integer.parseInt(respuesta)<= 0){
				System.out.println("Respuesta erronea, intentalo nuevamente");
				respuesta = sc.next();
			}
			producto_seleccionado = mercado.oferelectro.get(Integer.parseInt(respuesta)-1);
			System.out.println("Info del producto\n Nombre"+producto_seleccionado.getNombre()+
					"\nCOMPLETAR DESPUES");
		}

	}

}
