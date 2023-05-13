package src.uiMain;
//package uiMain;

import src.baseDatos.Deserializar;
import src.baseDatos.Serializar;
import src.gestorAplicacion.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.*;

public class Main {
	public static Scanner sc = new Scanner(System.in);
	public static Cliente cliente;
	public static int filtrolibro=0;
	
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
		String nombre = sc.nextLine();
		System.out.print("Ingresa tu direccion: ");
		String direccion = sc.nextLine();
		Cliente cliente = new Cliente(nombre, direccion);
		Main.cliente=cliente;
		System.out.println("\nBienvenido "+nombre+"!\n");
		
		menuQueDeseas();
		
		
		//Falta hacer esto con las demas listas
		if(lista_super.size() >0) {
			Serializar.serializarSupermercados(lista_super);
		}
		if(lista_celulares.size() >0) {
			Serializar.serializarCelulares(lista_celulares);
		}
		if(lista_tvs.size() >0) {
			Serializar.serializarTvs(lista_tvs);
		}
		if(lista_libros.size() >0) {
			Serializar.serializarLibros(lista_libros);
		}

	}
	//Volvi este codigo un metodo para aplicar el boton de volver al menu anterior
	
	public static void menuQueDeseas() {
		while(true){
			System.out.print("\nQue deseas hacer?\n" +
					"1. Ver lista de supermercados" +
					"\n2. Salir\n");
			String respuesta = sc.nextLine();
			
			// los while se ejecutan hasta que se ingrese una respuesta valida
			while(!respuesta.equals("1") && !respuesta.equals("2")){
				System.out.print("La respuesta ingresada no se encuentra entre las opciones (1 o 2)" +
						"\nIngresala nuevamente\n");
				respuesta = Main.sc.nextLine();
				
			}
			
			if(respuesta.equals("1")){
				seleccionarSupermercado();
			}
						
			// Termina la ejecucion del programa
			else{
				System.out.print("Gracias por visitar nuestro gestor de tiendas"+
						", siempre seras bienvenido\n");
				break;
			}
		}
	}
	
	// Se muestra el carrito de compras al finalizar la compra
	public static void finalizarCompra() {
		System.out.println("\nCarrito de compras:");
		System.out.println("Nombre  Tipo de producto  Supermercado  Precio");
		for(Object producto:cliente.getCarrito()) {
			System.out.println(producto);
		}
		// Terminar
		
	}
	
	//Hay que generalizar algunas cosas en este metodo
	public static void seleccionarSupermercado() {

		String respuesta;
		Supermercado super_seleccionado;

		
		if(lista_super.size() == 0){
			//Si el usuario no crea ningun supermercado salimos del metodo para volver al menu anterior
			return;
		}
		System.out.println("Seleccione el supermercado en el cual desea comprar");
		for (int i = 0; i < lista_super.size(); i++) {
			System.out.println((i+1)+". "+lista_super.get(i).nombre);
		}
		System.out.println((lista_super.size()+1)+". Crear un nuevo supermercado");
		respuesta = sc.nextLine();
		

		while(true){
			try{
				if(Integer.parseInt(respuesta)<= 0){
					respuesta = null; //Buscamos saltar el error ya que el intervalo solo contempla numeros positivos
				}
				if(Integer.parseInt(respuesta) == lista_super.size()+1 ){
					System.out.print("Por favor, ingrese el nombre del nuevo supermercado: ");
					Supermercado supermercado = new Supermercado(Main.sc.nextLine());
					
					//Aqui se ejecutaria la opcion para anadir productos
					anadirProducto(supermercado);
					
					lista_super.add(supermercado);
					
					return;
				}
				else{
					super_seleccionado = lista_super.get(Integer.parseInt(respuesta)-1);
					System.out.println("El mercado seleccionado es "+super_seleccionado.nombre);
					ofertaProductos(super_seleccionado);
					break;
				}
			}
			
			catch(Exception e){
				System.out.println("La opcion seleccionada no esta permitida, recapacita XD, intentalo nuevamente");
				respuesta = sc.nextLine();
			}
		}
		
		Serializar.serializarSupermercados(lista_super);
		
		//Esto hay que cambiarlo de modo que aplique para todos los productos
		//if (super_seleccionado.oferelectro.size() == 0) {
			//System.out.println("Supermercado vacio");
			//anadirProducto(super_seleccionado);}
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
		
		respuesta=Main.sc.nextLine();
		// este while es para capturar errores de input
		while (Integer.parseInt(respuesta)<= 0 || Integer.parseInt(respuesta)> 7  ){
			System.out.println("El numero ingresado no es correcto, rectificar por favor");
			respuesta = sc.nextLine();
		}
		
		switch (respuesta){
		
			case "1":
				System.out.print("Ingrese el nombre del Tv: ");
				String nombre = sc.nextLine();
				System.out.print("Ingresa el precio del Tv: ");
				int precio = Integer.parseInt(confirmarNumero(sc.nextLine()));
				System.out.print("Ingresa la marca del Tv: ");
				String marca = sc.nextLine();
				System.out.print("Ingresa el numero de pulgadas del Tv: ");	
				int pulgadas = Integer.parseInt(confirmarNumero(sc.nextLine()));
				System.out.print("Ingresa la resolucion del Tv: ");
				String resolucion = sc.nextLine();
				System.out.print("Ingresa la cantidad de Tvs "+nombre.toUpperCase()+" que desea añadir: ");
				int cantidadtv = Integer.parseInt(confirmarNumero(sc.nextLine()));
				Tv nuevotv = new Tv(nombre, precio, marca, mercado, cantidadtv, pulgadas, resolucion);
				mercado.oferelectro.add(nuevotv);
				lista_tvs.add(nuevotv);
				cuandoSeAgrega(mercado);
				break;
				
			case "2":
				System.out.print("Ingrese el nombre del celular: ");
				String nombrecel = sc.nextLine();
				System.out.print("Ingresa el precio del celular: ");
				int preciocel = sc.nextInt();
				System.out.print("Ingresa la marca del celular: ");
				String marcacel = sc.nextLine();
				System.out.print("Ingresa la cantidad de almacenamiento del celular: ");
				int almacenamiento = sc.nextInt();
				System.out.print("Ingresa el numero de camaras del celular: ");
				int camaras = sc.nextInt();
				System.out.print("Ingresa la bateria del celular: ");
				int bateria = sc.nextInt();
				System.out.print("Ingresa el color del celular: ");
				String color = sc.nextLine();
				System.out.print("Ingresa el numero de megas de ram: ");
				int ram = sc.nextInt();
				System.out.print("Ingresa la cantidad de celulares "+nombrecel.toUpperCase()+" que desea añadir: ");
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
				System.out.print("Ingrese el nombre del libro:");
				String nombrelib = sc.nextLine();
				System.out.print("Ingresa el autor del libro:");
				String autorlib = sc.nextLine();
				System.out.print("Ingresa la descripcion del libro:");
				String descriplib = sc.nextLine();
				System.out.println("Ingrese el codigo ISBN del libro:");
				String isbn = sc.nextLine();
				System.out.print("Ingresa el precio del libro:");
				String preciolib = sc.nextLine();
				Libro nuevolibro = new Libro(nombrelib,autorlib,descriplib,isbn,Integer.parseInt(preciolib),mercado);
				mercado.oferlibros.add(nuevolibro);
				lista_libros.add(nuevolibro);
				cuandoSeAgrega(mercado);
				break;
				
			case "4":
				//Carne
				System.out.print("Ingrese el nombre del producto carnico: ");
				String nombreCarne = sc.next();
				sc.nextLine();
				System.out.print("Ingrese el precio por libra del producto: ");
				int precioCarne = sc.nextInt();
				System.out.print("Ingrese que tipo de producto es "+nombreCarne.toUpperCase()+": ");
				String tipoCarne = sc.next();
				System.out.print("Ingrese el peso en libras del producto "+nombreCarne.toUpperCase()+": ");
				float pesoCarne = sc.nextFloat();
				System.out.print("Ingrese cuantas unidades de "+nombreCarne.toUpperCase()+" que desea añadir:");
				int cantidadCarne = sc.nextInt();
				Carne nuevaCarne =new Carne(nombreCarne,precioCarne, mercado, cantidadCarne, tipoCarne, pesoCarne);
				mercado.ofercomi.add(nuevaCarne);
				lista_carnicos.add(nuevaCarne);
				
				cuandoSeAgrega(mercado);
				break;
			case "5":
				//No Carne
				System.out.print("Ingrese el nombre del producto: ");
				String nombreAli = sc.next();
				sc.nextLine();
				System.out.print("Ingrese el precio por unidad del "+nombreAli.toUpperCase()+": ");
				int precioAli = sc.nextInt();
				System.out.print("Ingrese la cantidad de unidades de "+nombreAli.toUpperCase()+" que desea añadir:");
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
		respuesta=Main.sc.nextLine();

		while(!respuesta.equals("1") && !respuesta.equals("2")){
			System.out.println("La respuesta ingresada es erronea, intentalo nuevamente: ");
			respuesta = Main.sc.nextLine();
		}

		switch(respuesta) {
		case "1":
			anadirProducto(mercado);
			break;
		case "2":
			break;
		}	
	}
	
	public static void ofertaProductos(Supermercado mercado) {
		String respuesta;
		System.out.println("Cual producto estas buscando en "+mercado.getNombre()+"?"+
				"\n1. Electronica"+
				"\n2. Libros"+
				"\n3. Alimentos"+
				"\n4. Ropa"+
				"\n5. Volver al menu anterior");
		respuesta=sc.nextLine();
		switch(respuesta) {
		case "1":
			//comprarElectro()
			break;
		case "2":
			//comprarLibro()
			comprarLibro(mercado);
			break;
		case "3":
			//comprarAlim()
			//System.out.println("¿Cual es el alimento que busca en "+mercado.getNombre()+"\n");
			comprarAlimentos(mercado);
			
			break;
		case "4":
			//comprarRopa()
			break;
		case "5":
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
	
	public static void comprarLibro(Supermercado mercado,String...filtros ) {
		if(mercado.oferlibros.size() > 0){
			
			//Hay que pensar en poner algunos return de acuerdo a la logic del metodo
			String libroselect,respuesta;
			ArrayList<Libro> lstfiltrada= new ArrayList<Libro>();
			
			int i=1;
			System.out.println("Estos son los libros que tenemos disponibles en "+mercado.getNombre());
			//Recordad poner false el filtrolibro y todos los boleanos que utilice al final del flujo
			if(filtrolibro==1) {
				lstfiltrada=Libro.filtrarPorAutor(mercado.oferlibros, filtros[0]);
				for(Libro libro:lstfiltrada) {
					System.out.println("\n"+i+".");
					System.out.println("Titulo: "+libro.getTitulo());
					System.out.println("Autor: "+libro.getAutor());
					System.out.println("Precio: "+libro.getPrecio());
					++i;
				}
				}
			else if(filtrolibro==2) {
				lstfiltrada=Libro.filtrarPorPrecio(mercado.oferlibros,Integer.parseInt(filtros[0]),Integer.parseInt(filtros[1]));
				for(Libro libro:lstfiltrada) {
					System.out.println("\n"+i+".");
					System.out.println("Titulo: "+libro.getTitulo());
					System.out.println("Autor: "+libro.getAutor());
					System.out.println("Precio: "+libro.getPrecio());
					++i;
				}
			}
			else {
				
			for(Libro libro:mercado.oferlibros) {
				lstfiltrada=mercado.oferlibros;
				System.out.println("\n"+i+".");
				System.out.println("Titulo: "+libro.getTitulo());
				System.out.println("Autor: "+libro.getAutor());
				System.out.println("Precio: "+libro.getPrecio());
				++i;
			}
			
			}
			
			System.out.println("\nQue producto deseas comprar?\n");
			System.out.println("Ingresa "+i+" para filtrar");
			System.out.println("Ingresa "+(i+1)+" para buscar por isbn");
			System.out.println("Ingresa "+(i+2)+" para volver al menu anterior");
			if(filtrolibro==1||filtrolibro==2) System.out.println("Ingresa 0 para eliminar filtros");
			libroselect=sc.nextLine();
			
			//Caso volver al menu anterior
			if(Integer.parseInt(libroselect)==(i+2)) {
				ofertaProductos(mercado);
				return;
			}
			//Falta testear la busqueda por isbn
			else if(Integer.parseInt(libroselect)==(i+1)) {
				String isbn;
				Boolean hay=false;
				System.out.println("\nIngrese el codigo isbn del libro:");
				isbn=sc.nextLine();
				System.out.println("\nBuscando...\n");
				for(Libro libro:lstfiltrada) {
					if(libro.getIsbn().equals(isbn)) {
						hay=true;
						libroselect=Integer.toString(lstfiltrada.indexOf(libro)+1);
						break;
					}
				}
				if(!hay) {
					System.out.println("\nLibro no encontrado");
					comprarLibro(mercado);
					return;
				}
				
			}
			
			//Caso de que este filtrado
			else if(Integer.parseInt(libroselect)==0) {
				filtrolibro=0;
				comprarLibro(mercado);
				return;
				
			} 
			//Flujo para filtrar
			else if(Integer.parseInt(libroselect)==i) {
				String filtro;
				System.out.println("Filtrar por:"+
						"\n1.Autor"+
						"\n2.Precio"+
						"\n3.Volver al menu anterior");
				
				filtro=sc.next();
				
				switch(filtro) {
				case "1":
					int numautor=1;
					String autorSelect;
					Object[] lstautores=Libro.listaAutores(mercado.oferlibros);
					System.out.println("Cual de estos autores estas buscando?\n");
					
					for(Object nombreAutor:lstautores) {
						System.out.println(numautor+"."+nombreAutor);
						++numautor;
					}
					autorSelect=sc.next();
					filtrolibro=1;
					comprarLibro(mercado,(String) lstautores[Integer.parseInt(autorSelect)-1]);
					return;
					
				case "2":
					String premin,premax;
					System.out.println("\nIngrese el precio minimo:");
					premin=sc.nextLine();
					System.out.println("\nIngrese el precio maximo:");
					premax=sc.nextLine();
					filtrolibro=2;
					comprarLibro(mercado,premin,premax);
					return;
					
				case "3":
					comprarLibro(mercado);
					return;
				}
			}//Termina el flujo de filtrado 
			
			System.out.println("Titulo: "+lstfiltrada.get(Integer.parseInt(libroselect)-1).getTitulo());
			System.out.println("Autor: "+lstfiltrada.get(Integer.parseInt(libroselect)-1).getAutor());
			System.out.println("Descripcion: "+lstfiltrada.get(Integer.parseInt(libroselect)-1).getDescripcion());
			System.out.println("Precio: "+lstfiltrada.get(Integer.parseInt(libroselect)-1).getPrecio());
			
			System.out.println("1. Agregar al carrito");
			System.out.println("2. Volver al menu anterior");
			respuesta=sc.nextLine();
			
			switch(respuesta) {
			case "1":

				
				cliente.getCarrito().add(lstfiltrada.get(Integer.parseInt(libroselect)-1));
				mercado.oferlibros.remove(lstfiltrada.get(Integer.parseInt(libroselect)-1));
				System.out.println("Producto agregado con exito!");
				
				System.out.println("\n1. Seguir comprando");
				System.out.println("\n2. Finalizar compra ");
				respuesta=sc.nextLine();
				
				switch(respuesta) {
				case "1":
					ofertaProductos(mercado);
					break;
				case "2":
					//Aqui iria el metodo finalizarCompra()
					finalizarCompra();
					break;
				}
				break;
			case "2":
				comprarLibro(mercado);
				break;
				}
			}
		}
	
	
	public static void comprarAlimentos(Supermercado mercado) {
		
		//Alimentos productoA = null;
		if(mercado.ofercomi.size() > 0){
			int elegir,i=1;
			System.out.println("Los productos disponibles para "+mercado.getNombre()+" son los sgiuientes: \n");
			System.out.print("*** !OFERTA ALIEMENTOS¡***\n");
			for(Alimentos productoA:mercado.ofercomi) {
				System.out.println(i++ +")"+
						"\nNombre:"+productoA.getNombre()+" " +
						"\nPrecio unidad: "+productoA.getPrecio()+" " +
						"\nUnidades en stock: "+productoA.getCantidad());
			}
			System.out.println("\n¿Que producto esta interesado en comprar?");
			elegir=sc.nextInt();
			//Terminar
			
		}
	}


	//Confirmar si la entrada es un número o no.
	private static String confirmarNumero(String numero){
		while(!numero.matches("\\d+")){
			System.out.println("El número ingresado anteriormente no es válido, por favor intenalo nuevamente (Numero entero): ");
			numero = sc.nextLine();
		}
		return numero;
	}
	
}
