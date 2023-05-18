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
	public static ArrayList<Tv> lista_tvs = new ArrayList<>();
	public static ArrayList<Celular> lista_celulares = new ArrayList<>();
	public static ArrayList<Supermercado> lista_super = new ArrayList<Supermercado>();
	public static ArrayList<Ropa> lista_ropa = new ArrayList<Ropa>();
	
	//Aqui empieza el main
	public static void main(String[] args) {
		
		lista_ropa = Deserializar.deserializarRopa();
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
		System.out.println("Ingresa tu saldo disponible");
		String saldo =sc.nextLine();
		Cliente cliente = new Cliente(nombre, direccion,Integer.parseInt(saldo));
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
		//agregure la mia//
		if(lista_ropa.size() >0) {
			Serializar.serializarRopa(lista_ropa);
		}

	}
	//Volvi este codigo un metodo para aplicar el boton de volver al menu anterior
	
	public static void menuQueDeseas() {
		while(true){
			System.out.print("\n¿Qué deseas hacer?\n" +
					"1. Ver lista de supermercados" +
					"\n2. Salir\n");
			
			//validarRespuesta se ejecutan hasta que se ingrese una respuesta valida
			String respuesta = validarRespuesta(1, 2, sc.nextLine());
			
			
			if(respuesta.equals("1")){
				seleccionarSupermercado();
			}
						
			// Termina la ejecucion del programa
			else{
				System.out.print("¡Gracias por visitar nuestro gestor de tiendas!"+
						", siempre seras bienvenido :D\n");
				break;
			}
		}
	}
	
	// Se muestra el carrito de compras al finalizar la compra
	public static void finalizarCompra() {
		System.out.println("\nCarrito de compras:");
		System.out.println("Nombre       | Tipo de producto        |  Supermercado            |  Cantidad            |  Precio\n");
		int precio_total = 0;
		int cont = 1;
		for(Object producto:cliente.getCarrito()) {
			/*
			System.out.println(producto);
			Voy a modificar esta línea ya que al imprimir producto solo nos da una referencia en memoria (creo)
			*/
			//Debemos realizar esto con cada instancia de clase para que funcione correctamente
			if(producto instanceof Tv){
				System.out.println(cont+". "+((Tv)producto).getMarca()+": "+((Tv)producto).getNombre()+" | Televisor |"+((Tv)producto).getSupermercado()+" | "+((Tv)producto).getPrecio());
				precio_total += ((Tv)producto).getPrecio();
			}
			
			else if(producto instanceof Libro) {
				System.out.println(cont+". "+((Libro)producto).getTitulo()+ "| Libro |"+((Libro)producto).getSupermercado()+" | "+((Libro)producto).getCantidad()+" | "+((Libro)producto).getPrecio()*((Libro)producto).getCantidad());
				precio_total += ((Libro)producto).getCantidad()*((Libro)producto).getPrecio();
			}
			//else if(producto instanceof OtraClase)
			//Terminar con el resto de tipos de productos
			cont++;
		}
		System.out.println("\nTOTAL :"+precio_total);
		
		if (cliente.getSaldo()<precio_total) {
			System.out.println("Tu saldo es insuficiente. Debes quitar algunos productos del carrito.\n Cual producto deseas quitar?");
			String select =sc.nextLine();
			Object producto= cliente.getCarrito().get(Integer.parseInt(select)-1);
			
			//No pude encontrar una manera mas eficiente para saber la cantidad de diferentes objetos
			if(producto instanceof Libro) {
				boolean libroenmercado=false;
				Libro libro = (Libro) producto;
				int asumar=0, cantidad = libro.getCantidad();
				
				System.out.println("Cuantas unidades deseas quitar?");
				for (int i=1;i<=cantidad;i++) {
					System.out.println(i+". "+i);
				}
				asumar=Integer.parseInt(sc.nextLine());
		
				for(Libro libros:libro.getSupermercado().getOferlibros()) {
					if (libro.compareTo(libros)==1) {
						libros.setCantidad(libros.getCantidad()+asumar);
						libroenmercado=true;
						break;
					}
				}
				
				//((Libro) producto).setCantidad(((Libro) producto).getCantidad()-asumar);
				if(!libroenmercado) {
					libro.getSupermercado().getOferlibros().add(new Libro(libro,asumar));
					}
				
				((Libro) producto).setCantidad(((Libro) producto).getCantidad()-asumar);
				if (((Libro) producto).getCantidad()==0) {
					cliente.getCarrito().remove(producto);
				}
		
			}
			
			//Aqui se pondrian los otros casos para las otras clases (Casi que lo mismo no se si halla una mejor logica)
			
			finalizarCompra();
		}
		else {
			
		}
		
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
		// Santi says(Creo que este codigo lo podemos eliminar)
	}

	// Aqui entran todas las clases de la capa logica
	public static void anadirProducto(Supermercado mercado){
		String respuesta;
		
		System.out.println("\nAgregar productos al "+mercado+
				"\n1. Tevelisores" +
				"\n2. Celulares" +
				"\n3. Libros"+
				"\n4. Carne"+
				"\n5. NoCarnicos"+
				"\n6. Ropa"+
				"\n7. Volver al menu anterior");
		
		// validarRepuesta es para capturar errores de input
		respuesta = validarRespuesta(1, 7, Main.sc.nextLine());
		
		
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
				mercado.ofertv.add(nuevotv);
				lista_tvs.add(nuevotv);
				cuandoSeAgrega(mercado);
				break;
				
			case "2":
				System.out.print("Ingrese el nombre del celular: ");
				String nombrecel = sc.nextLine();
				System.out.print("Ingresa el precio del celular: ");
				int preciocel = Integer.parseInt(confirmarNumero(sc.nextLine()));
				System.out.print("Ingresa la marca del celular: ");
				String marcacel = sc.nextLine();
				System.out.print("Ingresa la cantidad de almacenamiento del celular: ");
				int almacenamiento = Integer.parseInt(confirmarNumero(sc.nextLine()));
				System.out.print("Ingresa el numero de camaras del celular: ");
				int camaras = Integer.parseInt(confirmarNumero(sc.nextLine()));
				System.out.print("Ingresa la bateria del celular: ");
				int bateria = Integer.parseInt(confirmarNumero(sc.nextLine()));
				System.out.print("Ingresa el color del celular: ");
				String color = sc.nextLine();
				System.out.print("Ingresa el numero de megas de ram: ");
				int ram = Integer.parseInt(confirmarNumero(sc.nextLine()));
				System.out.print("Ingresa la cantidad de celulares "+nombrecel.toUpperCase()+" que desea añadir: ");
				int cantidadcel = Integer.parseInt(confirmarNumero(sc.nextLine()));
				Celular nuevocel = new Celular(nombrecel, preciocel, marcacel, mercado, cantidadcel, almacenamiento, camaras, bateria, color, ram);
				mercado.ofercelular.add(nuevocel);
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
				String preciolib = confirmarNumero(sc.nextLine());
				System.out.println("Ingresa la cantidad de libros");
				String cantidad = sc.nextLine();;
				Libro nuevolibro = new Libro(nombrelib,autorlib,descriplib,isbn,Integer.parseInt(preciolib),Integer.parseInt(cantidad),mercado);
				mercado.oferlibros.add(nuevolibro);
				lista_libros.add(nuevolibro);
				cuandoSeAgrega(mercado);
				break;
				
			case "4":
				//Carne
				System.out.print("Ingrese el nombre del producto carnico: ");
				String nombreCarne = sc.nextLine();
				System.out.print("Ingrese el precio por libra del producto: ");
				int precioCarne = Integer.parseInt(confirmarNumero(sc.nextLine()));
				System.out.print("Ingrese que tipo de producto es "+nombreCarne.toUpperCase()+": ");
				String tipoCarne = sc.nextLine();
				System.out.print("Ingrese el peso en libras del producto "+nombreCarne.toUpperCase()+": ");
				float pesoCarne = Float.parseFloat(confirmarNumero(sc.nextLine()));
				System.out.print("Ingrese cuantas unidades de "+nombreCarne.toUpperCase()+" que desea añadir:");
				int cantidadCarne = Integer.parseInt(confirmarNumero(sc.nextLine()));
				Carne nuevaCarne =new Carne(nombreCarne,precioCarne, mercado, cantidadCarne, tipoCarne, pesoCarne);
				mercado.ofercarne.add(nuevaCarne);
				mercado.ofercomi.add(nuevaCarne);
				lista_carnicos.add(nuevaCarne);
				
				cuandoSeAgrega(mercado);
				break;
			case "5":
				//No Carne
				System.out.print("Ingrese el nombre del producto: ");
				String nombreAli = sc.nextLine();
				System.out.print("Ingrese el precio por unidad del "+nombreAli.toUpperCase()+": ");
				int precioAli = Integer.parseInt(confirmarNumero(sc.nextLine()));
				System.out.print("Ingrese la cantidad de unidades de "+nombreAli.toUpperCase()+" que desea añadir:");
				int cantidadAli = Integer.parseInt(confirmarNumero(sc.nextLine()));
				noCarnicos nuevoAli = new noCarnicos(nombreAli, precioAli, mercado, cantidadAli);
				mercado.ofernocarnicos.add(nuevoAli);
				mercado.ofercomi.add(nuevoAli);
				lista_no_carnicos.add(nuevoAli);
				
				cuandoSeAgrega(mercado);
				break;
				
			//acabo de hacer esto//
			case "6":
			    // Ropa
			    System.out.print("Ingrese el nombre de la prenda de ropa: ");
			    String nombreRopa = sc.nextLine();
			    System.out.print("Ingresa el precio de la prenda de ropa: ");
			    int precioRopa = Integer.parseInt(confirmarNumero(sc.nextLine()));
			    System.out.print("Ingresa el color de la prenda de ropa: ");
			    String colorRopa = sc.nextLine();
			    System.out.print("Ingresa la talla de la prenda de ropa: ");
			    String tallaRopa = sc.nextLine();
			    System.out.print("Ingresa el género de la prenda de ropa: ");
			    String generoRopa = sc.nextLine();
			    System.out.print("Ingresa el tipo de prenda de ropa: ");
			    String tipoRopa = sc.nextLine();
			    System.out.print("Ingresa la cantidad de prendas de ropa " + nombreRopa.toUpperCase() + " que desea añadir: ");
			    int cantidadRopa = Integer.parseInt(confirmarNumero(sc.nextLine()));
			    Ropa nuevaRopa = new Ropa(tallaRopa, colorRopa, precioRopa, nombreRopa, cantidadRopa, mercado, generoRopa, tipoRopa);
			    mercado.getOferropa().add(nuevaRopa);
			    lista_ropa.add(nuevaRopa);
			    cuandoSeAgrega(mercado);
			    break;
			case "7":
				//volver al menu anterior
				lista_super =  Deserializar.deserializarSupermercados();
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
		
		respuesta = validarRespuesta(1,2,Main.sc.nextLine());

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
		System.out.println("Cual producto estas buscando en el "+mercado+"?"+
				"\n1. Electronica"+
				"\n2. Libros"+
				"\n3. Alimentos"+
				"\n4. Ropa"+
				"\n5. Volver al menu anterior");
		
		respuesta=validarRespuesta(1, 5, sc.nextLine());
		
		switch(respuesta) {
		case "1":
			//comprarElectro()
			System.out.println("¿Deseas ver la lista de Televisores o de Celulares?\n" +
					"1.Televisores\n" +
					"2.Celulares\n" +
					"3.Volver al menú anterior");
			String input_electro = sc.nextLine();
			while(!input_electro.equals("1") && !input_electro.equals("2") && !input_electro.equals("3")){
				System.out.println("La respuesta ingresada es erronea, intentalo nuevamente: ");
				input_electro = Main.sc.nextLine();
			}
			if (input_electro.equals("1")){
				comprarTelevisor(mercado);
			}else if(input_electro.equals("2")){
				comprarCelular(mercado);
			}else{
				break;
			}
			break;
		case "2":
			//comprarLibro()
			comprarLibro(mercado);
			break;
		case "3":
			//comprarAlim()
			System.out.println("Bienvenido a la seccion de Alimentos del "+mercado+"\n");
			System.out.println(" ¿En que producto esta interesado de nuestra seccion?");
			System.out.println(	"\n"+
		    "1. Solo Alimentos Carnicos\n"
			+"2. Solo Alimentos noCarnicos\n"
			+"3. Alimentos Carnicos y noCarnicos\n"
			+"4. Elegir otra seccion");
			
			String eleccion = validarRespuesta(1, 4, sc.nextLine());
			
			if (eleccion.equals("1")){
				comprarAlimento(mercado, eleccion);
			}else if(eleccion.equals("2")){
				comprarAlimento(mercado, eleccion);
			}else if(eleccion.equals("3")){
				comprarAlimento(mercado, eleccion);
			}else{
				ofertaProductos(mercado);
				break;
			}
			break;
		case "4":
			//comprarRopa()
			comprarRopa(mercado);
			break;
		case "5":
			break;
		}
	}
	
	public static void comprarLibro(Supermercado mercado,String...filtros ) {
		if(mercado.oferlibros.size() > 0){
			
			
			String libroselect,respuesta;
			ArrayList<Libro> lstfiltrada= new ArrayList<Libro>();
			
			int i=1;
			System.out.println("Estos son los libros que tenemos disponibles en "+mercado.getNombre());
			
			if(filtrolibro==1) {
				lstfiltrada=Libro.filtrarPorAutor(mercado.oferlibros, filtros[0]);
				for(Libro libro:lstfiltrada) {
					System.out.println("\n"+i+".");
					System.out.println("Titulo: "+libro.getTitulo());
					System.out.println("Autor: "+libro.getAutor());
					System.out.println("Precio: "+libro.getPrecio());
					System.out.println("Unidades Disponibles: "+libro.getCantidad());
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
					System.out.println("Unidades Disponibles: "+libro.getCantidad());
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
				System.out.println("Unidades Disponibles: "+libro.getCantidad());
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
				
				filtro=sc.nextLine();
				
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
					autorSelect=sc.nextLine();
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
				boolean libroencarrito=false;
				//Este flujo permite eliminar los objetos de la lista y poderlos recuperar posteriormente si se necesita
				Libro compra=mercado.oferlibros.get(mercado.oferlibros.indexOf(lstfiltrada.get(Integer.parseInt(libroselect)-1)));
				compra.setCantidad(compra.getCantidad()-1);
				if(compra.getCantidad()==0) mercado.oferlibros.remove(compra);
				for(Object producto:cliente.getCarrito()) {
					if (producto instanceof Libro) {
						if(compra.compareTo((Libro)producto)==1) {
							((Libro) producto).setCantidad(((Libro) producto).getCantidad()+1);
							libroencarrito=true;
							break;
						}
					}
				}
				
				if(!libroencarrito) {
					cliente.getCarrito().add(new Libro(compra,1));
				}
				
				//Luego, al finalizar compra, hay que hacer un proceso especial por si el cliente se arrepiente y devolver el stock de libros.
				// De pronto podemos crear un metodo que se llame devolver libros.
				
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
					filtrolibro=0;
					finalizarCompra();
					break;
				}
				break;
			case "2":
				comprarLibro(mercado);
				break;
				}
			}
		else {
			System.out.println("Ya no quedan Libros en este supermercado...");
			ofertaProductos(mercado);
		}
		}
	
	
	public static void comprarAlimento(Supermercado mercado, String eleccion) {
		//Carne carnico = null;
		if (eleccion.equals("1")){//Opcion Carnicos
			
			if(mercado.ofercarne.size() > 0){
				int elegir,i=1;
				System.out.println("Bienvenido al Area de Carnes del "+mercado+"\n");
				System.out.print("Acontinuacion nuestros productos disponibles: \n");
				System.out.println("       *** !CARNES EN OFERTA¡***\n");
				for(Carne productoC:mercado.ofercarne) {
					System.out.println(i++ +")"
				            +"\nNombre: "+productoC.getNombre()
				            +"\nPrecio por libra: "+productoC.getPrecio()
				            +"\nTipo de carne: "+productoC.getTipo()
				            +"\nLibras por unidad: "+productoC.getPesoLibra()
				            +"\nUnidades en stock: "+productoC.getCantidad());
				}
				System.out.println("\n¿Que Oferta esta interesado en comprar?");
	            String validado = validarRespuesta(1,mercado.ofercarne.size()+1, String.valueOf(elegir= sc.nextInt()));
				//Terminar
				
			}
		}else if(eleccion.equals("2")){// Opcion noCarnicos
			
			if(mercado.ofernocarnicos.size() > 0){
				int elegir,i=1;
				System.out.println("Bienvenido al Area de noCarnicos del "+mercado+"\n");
				System.out.println("       *** !NOCARNICOS EN OFERTA¡***\n");
				System.out.print("Acontinuacion nuestros productos disponibles: \n");
				for(Alimentos productoN:mercado.ofernocarnicos) {
					System.out.println(i++ +")"
				            +"\nNombre: "+productoN.getNombre()
				            +"\nLibras por unidad: "+productoN.getPrecio()
				            +"\nUnidades en stock: "+productoN.getCantidad());
				}
				System.out.println("\n¿Que Oferta esta interesado en comprar?");
	            String validado = validarRespuesta(1,mercado.ofercarne.size()+1, String.valueOf(elegir= sc.nextInt()));
				//Terminar
				
			}
		}else if(eleccion.equals("3")){// Opcion Carnicos y noCarnicos
			
			if(mercado.ofernocarnicos.size() > 0){
				System.out.println("Bienvenido al Area de Carnicos & noCarnicos del "+mercado+"\n");
				System.out.print("*** !TODAS LAS OFERTAS DE ALIMENTOS EN UN SOLO LUGAR¡***\n");
				System.out.println("    Acontinuacion nuestros productos disponibles: \n");
				for(int i = 0; i<mercado.ofercomi.size(); i++) {
					System.out.print((i+1)+". ");
					mercado.ofercomi.get(i).oferta();//Ligadura dinamica
					System.out.print("\n");
				}
				System.out.println("\n¿Que Oferta esta interesado en comprar?");
	            String validado = validarRespuesta(1,mercado.ofercarne.size()+1, String.valueOf(sc.nextInt()));
				//Terminar
				
			}
			
		}
	
		
	}

	
	
	
	
	public static void comprarTelevisor(Supermercado mercado){
		if(mercado.ofertv.size() > 0){
			Electronico producto_seleccionado = null;
			System.out.println("Estos son los televisores que tenemos disponibles en "+mercado.getNombre());
			for(int i = 0; i<mercado.ofertv.size(); i++) {
				producto_seleccionado = mercado.ofertv.get(i);
				System.out.println((i+1)+". "+
						"\nNombre:"+producto_seleccionado.getNombre()+" " +
						"\n Marca: "+producto_seleccionado.getMarca()+" " +
						"\n Precio: "+producto_seleccionado.getPrecio()+" "+
						"\n Cantidad en stock: "+producto_seleccionado.getCantidad());
			}
			System.out.println("Selecciona uno de nuestros productos a comprar o escribe '"+(mercado.ofertv.size()+1)+"' para salir: ");
			String respuesta = sc.nextLine();
			while(Integer.parseInt(respuesta) > mercado.ofertv.size()+1 || Integer.parseInt(respuesta)<= 0){
				System.out.println("Respuesta erronea, intentalo nuevamente");
				respuesta = sc.nextLine();
			}
			if(respuesta.equals(mercado.ofertv.size()+1+"")){
				return;
			}
			producto_seleccionado = mercado.ofertv.get(Integer.parseInt(respuesta)-1);
			System.out.println("Has seleccionado el televisor: \n"+producto_seleccionado.getMarca()+": "+producto_seleccionado.getNombre());

			System.out.println("Ingresa: " +
					"\n1. Añadir al carrito" +
					"\n2. Volver al menú anterior");
			String respuesta2 = sc.nextLine();
			while(!respuesta2.equals("1") && !respuesta2.equals("2")){
				System.out.println("Rectifica el número ingresado, intentalo nuevamente: ");
				 respuesta2 = sc.nextLine();
			}
			switch (respuesta2){
				case "1":
					cliente.getCarrito().add(mercado.ofertv.get(Integer.parseInt(respuesta)-1));
					System.out.println("Producto agregado con exito!");
					System.out.println("\n1. Seguir comprando");
					System.out.println("\n2. Finalizar compra ");
					while (true) {
						respuesta2 = sc.nextLine();
						if (respuesta2.equals("1")) {
							ofertaProductos(mercado);
							break;
						}else if (respuesta2.equals("2")){
							finalizarCompra();
							break;
						}
						System.out.println("Respuesta erronea, ingresa nuevamente el numero: ");
						}

					break;
				case "2":
					comprarTelevisor(mercado);
					break;
			}
		}else{
			System.out.println("Este supermercado no cuenta con televisores D: ¿Deseas añadir uno?: " +
					"\n1. SI" +
					"\n2. NO");
			String respuesta = sc.nextLine();
			while(!respuesta.equals("1") && !respuesta.equals("2")){
				System.out.println("Rectifica el número ingresado, intentalo nuevamente: ");
				respuesta = sc.nextLine();
			}
			switch (respuesta){
				case "1":
					anadirProducto(mercado);
					break;
				case "2":
					break;
			}
		}
	}


	public static void comprarCelular(Supermercado mercado){
		if(mercado.ofercelular.size() > 0){
			Electronico producto_seleccionado = null;
			System.out.println("Estos son los celulares que tenemos disponibles en "+mercado.getNombre());
			for(int i = 0; i<mercado.ofercelular.size(); i++) {
				producto_seleccionado = mercado.ofercelular.get(i);
				System.out.println((i+1)+". "+
						"\nNombre:"+producto_seleccionado.getNombre()+" " +
						"\n Marca: "+producto_seleccionado.getMarca()+" " +
						"\n Precio: "+producto_seleccionado.getPrecio()+" "+
						"\n Cantidad en stock: "+producto_seleccionado.getCantidad());
			}
			System.out.println("Selecciona uno de nuestros productos a comprar o escribe '"+(mercado.ofercelular.size()+1)+"' para salir: ");
			String respuesta = sc.nextLine();
			while(Integer.parseInt(respuesta) > mercado.ofercelular.size()+1 || Integer.parseInt(respuesta)<= 0){
				System.out.println("Respuesta erronea, intentalo nuevamente");
				respuesta = sc.nextLine();
			}
			if(respuesta.equals(mercado.ofercelular.size()+1+"")){
				return;
			}
			producto_seleccionado = mercado.ofercelular.get(Integer.parseInt(respuesta)-1);
			System.out.println("Has seleccionado el celular: \n"+producto_seleccionado.getMarca()+": "+producto_seleccionado.getNombre());

			System.out.println("Ingresa: " +
					"\n1. Añadir al carrito" +
					"\n2. Volver al menú anterior");
			String respuesta2 = sc.nextLine();
			while(!respuesta2.equals("1") && !respuesta2.equals("2")){
				System.out.println("Rectifica el número ingresado, intentalo nuevamente: ");
				respuesta2 = sc.nextLine();
			}
			switch (respuesta2){
				case "1":
					cliente.getCarrito().add(mercado.ofercelular.get(Integer.parseInt(respuesta)-1));
					System.out.println("Producto agregado con exito!");
					System.out.println("\n1. Seguir comprando");
					System.out.println("\n2. Finalizar compra ");
					while (true) {
						respuesta2 = sc.nextLine();
						if (respuesta2.equals("1")) {
							ofertaProductos(mercado);
							break;
						}else if (respuesta2.equals("2")){
							finalizarCompra();
							break;
						}
						System.out.println("Respuesta erronea, ingresa nuevamente el numero: ");
					}

					break;
				case "2":
					comprarCelular(mercado);
					break;
			}
		}else{
			System.out.println("Este supermercado no cuenta con celulares D: ¿Deseas añadir uno?: " +
					"\n1. SI" +
					"\n2. NO");
			String respuesta = sc.nextLine();
			while(!respuesta.equals("1") && !respuesta.equals("2")){
				System.out.println("Rectifica el número ingresado, intentalo nuevamente: ");
				respuesta = sc.nextLine();
			}
			switch (respuesta){
				case "1":
					anadirProducto(mercado);
					break;
				case "2":
					break;
			}
		}
	}
	
	public static void comprarRopa(Supermercado mercado) {
	    if (mercado.oferropa.size() > 0) {
	        ArrayList<Ropa> prendasFiltradas = new ArrayList<>();
	        System.out.println("Estas son las prendas de ropa disponibles en " + mercado.getNombre());
	        // Mostrar las prendas de ropa disponibles
	        for (int i = 0; i < mercado.oferropa.size(); i++) {
	            Ropa prenda = mercado.oferropa.get(i);
	            System.out.println((i + 1) + ". " +
	                    "\nNombre: " + prenda.getNombreRopa() +
	                    "\nColor: " + prenda.getColorRopa() +
	                    "\nTalla: " + prenda.getTallaRopa() +
	                    "\nPrecio: " + prenda.getPrecioRopa() +
	                    "\nCantidad en stock: " + prenda.getCantidadRopa());
	        }
	        System.out.println("\nSelecciona los filtros de busqueda:");
	        System.out.println("Talla: ");
	        String talla = sc.nextLine();
	        System.out.println("Color: ");
	        String color = sc.nextLine();
	        System.out.println("Genero: ");
	        String genero = sc.nextLine();
	        System.out.println("Tipo: ");
	        String tipo = sc.nextLine();

	        // Filtrar las prendas de ropa segun los criterios de busqueda
	        prendasFiltradas = Ropa.filtrarPrendas(mercado.oferropa, talla, color, genero, tipo);

	        if (prendasFiltradas.size() > 0) {
	            System.out.println("Estas son las prendas de ropa que coinciden con los filtros seleccionados:");
	            for (int i = 0; i < prendasFiltradas.size(); i++) {
	                Ropa prenda = prendasFiltradas.get(i);
	                System.out.println((i + 1) + ". " +
	                        "\nNombre: " + prenda.getNombreRopa() +
	                        "\nColor: " + prenda.getColorRopa() +
	                        "\nTalla: " + prenda.getTallaRopa() +
	                        "\nPrecio: " + prenda.getPrecioRopa() +
	                        "\nCantidad en stock: " + prenda.getCantidadRopa());
	            }
	            System.out.println("Selecciona una prenda de ropa para comprar (escribe '0' para cancelar):");
	            int seleccion = Integer.parseInt(sc.nextLine());
	            if (seleccion == 0) {
	                return;
	            } else if (seleccion > 0 && seleccion <= prendasFiltradas.size()) {
	                Ropa prendaSeleccionada = prendasFiltradas.get(seleccion - 1);
	                System.out.println("Has seleccionado la prenda de ropa: \n" + prendaSeleccionada.getNombreRopa());

	                System.out.println("Ingresa: " +
	                        "\n1. A�adir al carrito" +
	                        "\n2. Volver al menu anterior");
	                String respuesta2 = sc.nextLine();
	                while (!respuesta2.equals("1") && !respuesta2.equals("2")) {
	                    System.out.println("Numero ingresado incorrecto, inténtalo nuevamente: ");
	                    respuesta2 = sc.nextLine();
	                }
	                switch (respuesta2) {
	                    case "1":
	                        cliente.getCarrito().add(prendaSeleccionada);
	                        System.out.println("Producto agregado con exito!");
	                        System.out.println("\n1. Seguir comprando");
	                        System.out.println("\n2. Finalizar compra ");
	                        while (true) {
	                            respuesta2 = sc.nextLine();
	                            if (respuesta2.equals("1")) {
	                                ofertaProductos(mercado);
	                                break;
	                            } else if (respuesta2.equals("2")) {
	                                finalizarCompra();
	                                break;
	                            }
	                            System.out.println("Numero ingresado incorrecto, ingresa nuevamente el numero: ");
	                        }
	                        break;
	                    case "2":
	                        comprarRopa(mercado);
	                        break;
	                }
	            }
	        } else {
	            System.out.println("No hay prendas de ropa disponibles segun los filtros seleccionados. Deseas volver a intentarlo?");
	            System.out.println("1. SI");
	            System.out.println("2. NO");
	            String respuesta = sc.nextLine();
	            while (!respuesta.equals("1") && !respuesta.equals("2")) {
	                System.out.println("Respuesta incorrecta, intentalo nuevamente: ");
	                respuesta = sc.nextLine();
	            }

	            if (respuesta.equals("1")) {
	                comprarRopa(mercado);
	            }
	        }
	    } else {
	        System.out.println("Este supermercado no cuenta con prendas de ropa disponibles. Deseas anadir alguna?");
	        System.out.println("1. SI");
	        System.out.println("2. NO");
	        String respuesta = sc.nextLine();
	        while (!respuesta.equals("1") && !respuesta.equals("2")) {
	            System.out.println("Respuesta incorrecta, intentalo nuevamente: ");
	            respuesta = sc.nextLine();
	        }

	        if (respuesta.equals("1")) {
	            anadirProducto(mercado);
	        }
	    }
	}


	//Confirmar si la entrada es un número o no.
	private static String confirmarNumero(String numero){
		while(!numero.matches("\\d+(\\.\\d+)?")){
			System.out.println("El número ingresado anteriormente no es válido, por favor intenalo "
		            +"nuevamente solo se acepta"
			        + "\n* Número entero"
					+ "\n* Número decimal con punto");
			numero = sc.nextLine();
		}
		return numero;
	}
	
	//Confirma si la respuesta esta entre las opciones presentadas
	private static String validarRespuesta(int lower, int upper, String respuesta) {
		
		while (Integer.parseInt(respuesta)< lower || Integer.parseInt(respuesta)> upper  ){
			System.out.println("El numero ingresado no esta entre las opciones, rectificar por favor");
			respuesta = sc.nextLine();
		}
		 return respuesta;
	}
	
	
}
