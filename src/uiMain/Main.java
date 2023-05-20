package src.uiMain;
//package uiMain;

import src.baseDatos.Deserializar;
import src.baseDatos.Serializar;
import src.gestorAplicacion.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.*;

import static src.gestorAplicacion.Tv.filtroPrecio;
import static src.gestorAplicacion.Tv.filtroPulgadas;

public class Main {
	public static Scanner sc = new Scanner(System.in);
	private static Cliente cliente;
	private static int filtrolibro=0;
	private static int Alimentos = 0;
	private static final double IVA=0.19; //Uso de constante
	
	//Se crean las listas donde va cada tipo de producto
	private static ArrayList<Libro> lista_libros = new ArrayList<Libro>();
	private static ArrayList<Carne> lista_carnicos = new ArrayList<Carne>();
	private static ArrayList<noCarnicos> lista_no_carnicos = new ArrayList<noCarnicos>();
	private static ArrayList<Tv> lista_tvs = new ArrayList<>();
	private static ArrayList<Celular> lista_celulares = new ArrayList<>();
	private static ArrayList<Supermercado> lista_super = new ArrayList<Supermercado>();
	private static ArrayList<Ropa> lista_ropa = new ArrayList<Ropa>();
	
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
		System.out.print("Ingresa tu saldo disponible: ");
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
		if(lista_carnicos.size() >0) {
			Serializar.serializarCarne(lista_carnicos);
		}if(lista_no_carnicos.size() >0) {
			Serializar.serializarnoCarnicos(lista_no_carnicos);
		}
		

	}
	//Volvi este codigo un metodo para aplicar el boton de volver al menu anterior
	
	public static void menuQueDeseas() {
		while(true){
			System.out.print("\nQue deseas hacer?\n" +
					"1. Ver lista de supermercados" +
					"\n2. Salir\n");
			
			//validarRespuesta se ejecutan hasta que se ingrese una respuesta valida
			String respuesta = validarRespuesta(1, 2, sc.nextLine());
			
			
			if(respuesta.equals("1")){
				seleccionarSupermercado();
			}
						
			// Termina la ejecucion del programa
			else{
				System.out.print("Gracias por visitar nuestro gestor de tiendas!"+
						", siempre seras bienvenido :D\n");
				break;
			}
		}
	}
	
	// Se muestra el carrito de compras al finalizar la compra
	public static void finalizarCompra() {
		System.out.println("\nCarrito de compras:");
		System.out.println("Nombre       | Tipo de producto        |  Supermercado            |  Cantidad            |  Precio\n");
		double precio_total = 0;
		int cont = 1;
		for(Object producto:cliente.getCarrito()) {
			if(producto instanceof Tv){
				System.out.println(cont+". "+((Tv)producto).getMarca()+": "+((Tv)producto).getNombre()+" | Televisor |"+((Tv)producto).getSupermercado()+" | "+((Tv)producto).getCantidad()+" | "+((Tv)producto).getPrecio()*((Tv)producto).getCantidad());
				precio_total += ((Tv)producto).getPrecio();
			}
			
			else if(producto instanceof Libro) {
				System.out.println(cont+". "+((Libro)producto).getTitulo()+ "| Libro |"+((Libro)producto).getSupermercado()+" | "+((Libro)producto).getCantidad()+" | "+((Libro)producto).getPrecio()*((Libro)producto).getCantidad());
				precio_total += ((Libro)producto).getCantidad()*((Libro)producto).getPrecio();
			}
			
			else if(producto instanceof Celular) {
				System.out.println(cont+". "+((Celular)producto).getMarca()+": "+((Celular)producto).getNombre()+" | Celular |"+((Celular)producto).getSupermercado()+" | "+((Celular)producto).getCantidad()+" | "+((Celular)producto).getPrecio()*((Celular)producto).getCantidad());
				precio_total += ((Celular)producto).getCantidad()*((Celular)producto).getPrecio();
			}
			
			else if(producto instanceof noCarnicos) {
				System.out.println(cont+". "+((noCarnicos)producto).getNombre()+ "| noCarnicos |"+((noCarnicos)producto).getSupermercado()+" | "+((noCarnicos)producto).getCantidad()+" | "+((noCarnicos)producto).getPrecio()*((noCarnicos)producto).getCantidad());
				precio_total += ((noCarnicos)producto).getCantidad()*((noCarnicos)producto).getPrecio();
			}
			
			else if(producto instanceof Carne) {
				System.out.println(cont+". "+((noCarnicos)producto).getNombre()+ "| Carne |"+((noCarnicos)producto).getSupermercado()+" | "+((noCarnicos)producto).getCantidad()+" | "+((noCarnicos)producto).getPrecio()*((noCarnicos)producto).getCantidad());
				precio_total += ((Carne)producto).getCantidad()*((Carne)producto).getPrecio();
			}
			
			else if(producto instanceof Ropa) {
				System.out.println(cont+". "+((Ropa)producto).getNombreRopa()+ "| Ropa |"+((Ropa)producto).getSupermercado()+" | "+((Ropa)producto).getCantidadRopa()+" | "+((Ropa)producto).getPrecioRopa()*((Ropa)producto).getCantidadRopa());
				precio_total += ((Ropa)producto).getCantidadRopa()*((Ropa)producto).getPrecioRopa();
			
			}
			cont++;
		}
		System.out.println("\nSubtotal :"+precio_total);
		System.out.println("\nImpuesto IVA 19%: "+precio_total*IVA);
		precio_total= precio_total+(precio_total*IVA);
		System.out.println("\nTotal: "+precio_total);
		
		if (cliente.getSaldo()<precio_total) {
			System.out.println("Tu saldo es insuficiente. Debes quitar algunos productos del carrito.\nCual producto deseas quitar?");
			String select =sc.nextLine();
			Object producto= cliente.getCarrito().get(Integer.parseInt(select)-1);
			devolverProducto(producto);
			finalizarCompra();
		}
		else {
			System.out.println("\n1. Pagar"+
		       "\n2.Volver al menu inicial");
			String eleccion=sc.nextLine();
			switch(eleccion) {
			case "1":
				System.out.println("\nEl pago a sido realizado con exito!"
						+ "\nEn unos momentos recibira su pedido a la direccion "+cliente.getDireccion()
						+"\nA nombre de "+cliente.getNombre()
				        +"\nFue un placer atenderlo y esperamos que vuelva pronto!");
				System.exit(0);
				break;
			case "2":
				break;
			}
		}
		
	}
	
	public static void devolverProducto(Object producto) {
		boolean productoenmercado=false;
		
		if(producto instanceof Libro) {
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
					productoenmercado=true;
					break;
				}
			}
			if(!productoenmercado) {
				libro.getSupermercado().getOferlibros().add(new Libro(libro,asumar));
				}
			
			((Libro) producto).setCantidad(((Libro) producto).getCantidad()-asumar);
			if (((Libro) producto).getCantidad()==0) {
				cliente.getCarrito().remove(producto);
			}
	
		}
		
		else if(producto instanceof Celular) {
			Celular celular = (Celular) producto;
			int asumar=0, cantidad = celular.getCantidad();
			
			System.out.println("Cuantas unidades deseas quitar?");
			for (int i=1;i<=cantidad;i++) {
				System.out.println(i+". "+i);
			}
			asumar=Integer.parseInt(sc.nextLine());
	
			for(Celular celulares:celular.getSupermercado().getOfercelular()) {
				if (celular.compareTo(celulares)==1) {
					celulares.setCantidad(celulares.getCantidad()+asumar);
					productoenmercado=true;
					break;
				}
			}
			if(!productoenmercado) {
				celular.getSupermercado().getOfercelular().add(new Celular(celular,asumar));
				}
			
			((Celular) producto).setCantidad(((Celular) producto).getCantidad()-asumar);
			if (((Celular) producto).getCantidad()==0) {
				cliente.getCarrito().remove(producto);
			}
	
		}
		
		else if(producto instanceof Tv) {
			Tv tv = (Tv) producto;
			int asumar=0, cantidad = tv.getCantidad();
			
			System.out.println("Cuantas unidades deseas quitar?");
			for (int i=1;i<=cantidad;i++) {
				System.out.println(i+". "+i);
			}
			asumar=Integer.parseInt(sc.nextLine());
	
			for(Tv tvs:tv.getSupermercado().getOfertv()) {
				if (tv.compareTo(tvs)==1) {
					tvs.setCantidad(tvs.getCantidad()+asumar);
					productoenmercado=true;
					break;
				}
			}
			if(!productoenmercado) {
				tv.getSupermercado().getOfertv().add(new Tv(tv,asumar));
				}
			
			((Tv) producto).setCantidad(((Tv) producto).getCantidad()-asumar);
			if (((Tv) producto).getCantidad()==0) {
				cliente.getCarrito().remove(producto);
			}
	
		}
		
		else if(producto instanceof Carne) {
			Carne carne = (Carne) producto;
			int asumar=0, cantidad = carne.getCantidad();
			
			System.out.println("Cuantas unidades deseas quitar?");
			for (int i=1;i<=cantidad;i++) {
				System.out.println(i+". "+i);
			}
			asumar=Integer.parseInt(sc.nextLine());
	
			for(Carne carnes:carne.getSupermercado().getOfercarne()) {
				if (carne.compareTo(carnes)==1) {
					carnes.setCantidad(carnes.getCantidad()+asumar);
					productoenmercado=true;
					break;
				}
			}
			if(!productoenmercado) {
				carne.getSupermercado().getOfercarne().add(new Carne(carne,asumar));
				}
			
			((Carne) producto).setCantidad(((Carne) producto).getCantidad()-asumar);
			if (((Carne) producto).getCantidad()==0) {
				cliente.getCarrito().remove(producto);
			}
	
		}
		
		else if(producto instanceof noCarnicos) {
			noCarnicos nocarne = (noCarnicos) producto;
			int asumar=0, cantidad = nocarne.getCantidad();
			
			System.out.println("Cuantas unidades deseas quitar?");
			for (int i=1;i<=cantidad;i++) {
				System.out.println(i+". "+i);
			}
			asumar=Integer.parseInt(sc.nextLine());
	
			for(noCarnicos nocarnes:nocarne.getSupermercado().getOfernocarnicos()) {
				if (nocarne.compareTo(nocarnes)==1) {
					nocarnes.setCantidad(nocarnes.getCantidad()+asumar);
					productoenmercado=true;
					break;
				}
			}
			if(!productoenmercado) {
				nocarne.getSupermercado().getOfernocarnicos().add(new noCarnicos(nocarne,asumar));
				}
			
			((noCarnicos) producto).setCantidad(((noCarnicos) producto).getCantidad()-asumar);
			if (((noCarnicos) producto).getCantidad()==0) {
				cliente.getCarrito().remove(producto);
			}
	
		}
		
		else if(producto instanceof Ropa) {
			Ropa ropa = (Ropa) producto;
			int asumar=0, cantidad = ropa.getCantidadRopa();
			
			System.out.println("Cuantas unidades deseas quitar?");
			for (int i=1;i<=cantidad;i++) {
				System.out.println(i+". "+i);
			}
			asumar=Integer.parseInt(sc.nextLine());
	
			for(Ropa ropas:ropa.getSupermercado().getOferropa()) {
				if (ropa.compareTo(ropas)==1) {
					ropas.setCantidadRopa(ropas.getCantidadRopa()+asumar);
					productoenmercado=true;
					break;
				}
			}
			if(!productoenmercado) {
				ropa.getSupermercado().getOferropa().add(new Ropa(ropa,asumar));
				}
			
			((Ropa) producto).setCantidadRopa(((Ropa) producto).getCantidadRopa()-asumar);
			if (((Ropa) producto).getCantidadRopa()==0) {
				cliente.getCarrito().remove(producto);
			}
	
		}
		
		
		
		
		
	}
	
	public static void disminuirStock(Object producto,Supermercado mercado) {
		boolean prodencarrito=false;
		//el producto el cual se desea rebajar el stock
		if(producto instanceof Libro) {
			
			Libro libro= (Libro) producto;
			
			libro.setCantidad(libro.getCantidad()-1);
			if(libro.getCantidad()==0) mercado.getOferlibros().remove(libro);
			for(Object productos:cliente.getCarrito()) {
				if (productos instanceof Libro) {
					if(libro.compareTo((Libro)productos)==1) {
						((Libro) productos).setCantidad(((Libro) productos).getCantidad()+1);
						prodencarrito=true;
						break;
					}
				}
			}
			
			if(!prodencarrito) {
				cliente.getCarrito().add(new Libro(libro,1));
			}
		}
		
		else if(producto instanceof Celular) {
			Celular celular= (Celular) producto;
			celular.setCantidad(celular.getCantidad()-1);
			if(celular.getCantidad()==0) mercado.getOfercelular().remove(celular);
			for(Object productos:cliente.getCarrito()) {
				if (productos instanceof Celular) {
					if(celular.compareTo((Celular)productos)==1) {
						((Celular) productos).setCantidad(((Celular) productos).getCantidad()+1);
						prodencarrito=true;
						break;
					}
				}
			}
			
			if(!prodencarrito) {
				cliente.getCarrito().add(new Celular(celular,1));
			}
		}
		
		else if(producto instanceof Tv) {
			Tv tv= (Tv) producto;
			tv.setCantidad(tv.getCantidad()-1);
			if(tv.getCantidad()==0) mercado.getOfertv().remove(tv);
			for(Object productos:cliente.getCarrito()) {
				if (productos instanceof Tv) {
					if(tv.compareTo((Tv)productos)==1) {
						((Tv) productos).setCantidad(((Tv) productos).getCantidad()+1);
						prodencarrito=true;
						break;
					}
				}
			}
			
			if(!prodencarrito) {
				cliente.getCarrito().add(new Tv(tv,1));
			}
		}
		
		else if(producto instanceof Carne) {
			Carne carne= (Carne) producto;
			carne.setCantidad(carne.getCantidad()-1);
			if(carne.getCantidad()==0) mercado.getOfercarne().remove(carne);
			for(Object productos:cliente.getCarrito()) {
				if (productos instanceof Carne) {
					if(carne.compareTo((Carne)productos)==1) {
						((Carne) productos).setCantidad(((Carne) productos).getCantidad()+1);
						prodencarrito=true;
						break;
					}
				}
			}
			
			if(!prodencarrito) {
				cliente.getCarrito().add(new Carne(carne,1));
			}
		}
		
		else if(producto instanceof noCarnicos) {
			noCarnicos nocarne= (noCarnicos) producto;
			nocarne.setCantidad(nocarne.getCantidad()-1);
			if(nocarne.getCantidad()==0) mercado.getOfernocarnicos().remove(nocarne);
			for(Object productos:cliente.getCarrito()) {
				if (productos instanceof noCarnicos) {
					if(nocarne.compareTo((noCarnicos)productos)==1) {
						((noCarnicos) productos).setCantidad(((noCarnicos) productos).getCantidad()+1);
						prodencarrito=true;
						break;
					}
				}
			}
			
			if(!prodencarrito) {
				cliente.getCarrito().add(new noCarnicos(nocarne,1));
			}
		}
		
		else if(producto instanceof Ropa) {
			Ropa ropa= (Ropa) producto;
			ropa.setCantidadRopa(ropa.getCantidadRopa()-1);
			if(ropa.getCantidadRopa()==0) mercado.getOferropa().remove(ropa);
			for(Object productos:cliente.getCarrito()) {
				if (productos instanceof Ropa) {
					if(ropa.compareTo((Ropa)productos)==1) {
						((Ropa) productos).setCantidadRopa(((Ropa) productos).getCantidadRopa()+1);
						prodencarrito=true;
						break;
					}
				}
			}
			
			if(!prodencarrito) {
				cliente.getCarrito().add(new Ropa(ropa,1));
			}
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
			System.out.println((i+1)+". "+lista_super.get(i).getNombre());
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
					System.out.println("El mercado seleccionado es "+super_seleccionado.getNombre());
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
				"\n1. Televisores" +
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
				mercado.getOfertv().add(nuevotv);
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
				System.out.print("Ingresa los mAH de bateria del celular: 	 \nEjemplo Los mAH son: 3000\n");
				int bateria = Integer.parseInt(confirmarNumero(sc.nextLine()));
				System.out.print("Ingresa el color del celular: ");
				String color = sc.nextLine();
				System.out.print("Ingresa el numero de megas de ram: ");
				int ram = Integer.parseInt(confirmarNumero(sc.nextLine()));
				System.out.print("Ingresa la cantidad de celulares "+nombrecel.toUpperCase()+" que desea añadir: ");
				int cantidadcel = Integer.parseInt(confirmarNumero(sc.nextLine()));
				Celular nuevocel = new Celular(nombrecel, preciocel, marcacel, mercado, cantidadcel, almacenamiento, camaras, bateria, color, ram);
				mercado.getOfercelular().add(nuevocel);
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
				System.out.print("Ingrese el codigo ISBN del libro:");
				String isbn = sc.nextLine();
				System.out.print("Ingresa el precio del libro:");
				String preciolib = confirmarNumero(sc.nextLine());
				System.out.print("Ingresa la cantidad de libros:");
				String cantidad = sc.nextLine();;
				Libro nuevolibro = new Libro(nombrelib,autorlib,descriplib,isbn,Integer.parseInt(preciolib),Integer.parseInt(cantidad),mercado);
				mercado.getOferlibros().add(nuevolibro);
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
				mercado.getOfercarne().add(nuevaCarne);
				mercado.getOfercomi().add(nuevaCarne);
				lista_carnicos.add(nuevaCarne);
				
				cuandoSeAgrega(mercado);
				break;
			case "5":
				//No Carne
				
				System.out.print("Ingrese el nombre del producto: ");
				String nombreAli = sc.nextLine();
				System.out.print("Ingrese su Categoria:"+"\n1.Granos"+"\n2.Lacteos"+"\n3.Vegetales"+"\n4.Otros\n");
				String grupo =  noCarnicos.validarGrupo(validarRespuesta(1, 4, sc.nextLine()));
				System.out.print("Ingrese el precio por unidad del "+nombreAli.toUpperCase()+": ");
				int precioAli = Integer.parseInt(confirmarNumero(sc.nextLine()));
				System.out.print("Ingrese la cantidad de unidades de "+nombreAli.toUpperCase()+" que desea añadir:");
				int cantidadAli = Integer.parseInt(confirmarNumero(sc.nextLine()));
				noCarnicos nuevoAli = new noCarnicos(nombreAli, precioAli, mercado, cantidadAli, grupo);
				mercado.getOfernocarnicos().add(nuevoAli);
				mercado.getOfercomi().add(nuevoAli);
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
			System.out.println("Deseas ver la lista de Televisores o de Celulares?\n" +
					"1.Televisores\n" +
					"2.Celulares\n" +
					"3.Volver al menu anterior");
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
			menuAlimentos(mercado);
			
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
		if(mercado.getOferlibros().size() > 0){
			
			
			String libroselect,respuesta;
			ArrayList<Libro> lstfiltrada= new ArrayList<Libro>();
			
			int i=1;
			System.out.println("Estos son los libros que tenemos disponibles en "+mercado.getNombre());
			
			if(filtrolibro==1) {
				lstfiltrada=Libro.filtrarPorAutor(mercado.getOferlibros(), filtros[0]);
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
				lstfiltrada=Libro.filtrarPorPrecio(mercado.getOferlibros(),Integer.parseInt(filtros[0]),Integer.parseInt(filtros[1]));
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
				
			for(Libro libro:mercado.getOferlibros()) {
				lstfiltrada=mercado.getOferlibros();
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
					Object[] lstautores=Libro.listaAutores(mercado.getOferlibros());
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
				//Este flujo permite eliminar los objetos de la lista y poderlos recuperar posteriormente si se necesita
				
				Libro compra=mercado.getOferlibros().get(mercado.getOferlibros().indexOf(lstfiltrada.get(Integer.parseInt(libroselect)-1)));
				disminuirStock(compra,mercado);
				
				System.out.println("Producto agregado con exito!");
				System.out.println("\n1. Seguir comprando");
				System.out.println("\n2. Finalizar compra ");
				respuesta=sc.nextLine();
				
				switch(respuesta) {
				case "1":
					ofertaProductos(mercado);
					break;
				case "2":
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
			System.out.println("\nYa no quedan Libros en este supermercado...\n");
//					+ "\nDeseas anadir un libro al supermercado?"
//					+ "\n1. Si"
//					+ "\n2. No");
//			String anadir=sc.next();
//			switch(anadir) {
//			case "1":
//				anadirProducto(mercado);
//				ofertaProductos(mercado);
//				break;
//			case "2":
//				menuQueDeseas();
//				break;
//			}
		}
		}
	
	//Creo el menuAlimentos para  si cliente desea volver a la seccion alimentos
	public static void menuAlimentos(Supermercado mercado) {
		System.out.println("Bienvenido a la seccion de Alimentos del "+mercado+"\n");
		System.out.println("En que producto esta interesado de nuestra seccion?");
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
		}	
	}

public static void comprarAlimento(Supermercado mercado, String eleccion) {
		
		if (eleccion.equals("1")){//OPCION NO CARNICOS
			ArrayList<Carne> meat2 = new ArrayList<Carne>();
			
			if(mercado.getOfercarne().size() > 0){
				int elegir,i=1;
				System.out.print("Bienvenido al Area de Carnes del "+mercado+"\n");
				System.out.print("       *** !CARNES EN OFERTA!***\n");
				System.out.print("Acontinuacion nuestros productos disponibles: \n");
				for(Carne productoC:mercado.getOfercarne()) {
					System.out.println(i++ +"."
				            +"\nNombre: "+productoC.getNombre()
				            +"\nPrecio por libra: "+productoC.getPrecio()
				            +"\nTipo de carne: "+productoC.getTipo()
				            +"\nLibras por unidad: "+productoC.getPesoLibra()
				            +"\nUnidades en stock: "+productoC.getCantidad());
					         System.out.print("\n");
				}
				
				System.out.println("\nQue Oferta esta interesado en agregar al carrito de compras?");
				System.out.print("\n         **Opciones Adicionales**");
				System.out.print("\nIngrese "+(mercado.getOfercarne().size()+1)+" para Filtrar por Tipo de Carne");
				System.out.print("\nIngrese "+(mercado.getOfercarne().size()+2)+" Volver al Menu de Alimentos");
				System.out.println("\nIngrese "+(mercado.getOfercarne().size()+3)+" para Escoger otra Seccion");
				elegir = Integer.parseInt(validarRespuesta(1,mercado.getOfercarne().size()+3, String.valueOf(sc.nextInt())));
				
				if(elegir==mercado.getOfercarne().size()+1){
	                  //FILTRO LISTA DE TIPOS DE CARNE
				    System.out.println("\nLos Tipo de Carnes Disponibles Son:");
					Object[] listaTipo = Carne.listaTipos(mercado.getOfercarne());//
					for(int c=0; c<listaTipo.length; c++) {
						System.out.println((c+1)+". Tipo de Carne:"+listaTipo[c]);
					}
					System.out.println("\nQue Tipo de Carne Desea filtrar?");
					System.out.print("\n         **Opciones Adicionales**");
					System.out.print("\nIngrese "+(listaTipo.length+1)+" Volver al Menu de Alimentos");
					System.out.println("\nIngrese "+(listaTipo.length+2)+" para Escoger otra Seccion");
					int elegirtipo = Integer.parseInt(validarRespuesta(1,listaTipo.length+2, String.valueOf(sc.nextInt())));
					
					if(elegirtipo==listaTipo.length+1) {
						menuAlimentos(mercado);
						return;
					}else if(elegirtipo==listaTipo.length+2){
						ofertaProductos(mercado);
						return;
					}else if(elegirtipo-1<listaTipo.length) {
						//FILTRO POR TIPO DE CARNE
						System.out.println("\nOfertas Disponibles para el filtro de "+listaTipo[elegirtipo-1].toString());
						meat2 = Carne.filtroTipo(mercado.getOfercarne(),listaTipo[elegirtipo-1].toString());
						int y =1, oferSelec;
						for(Carne productoF:meat2) {
							System.out.println(y++ +"."
						            +"\nNombre: "+productoF.getNombre()
						            +"\nPrecio por libra: "+productoF.getPrecio()
						            +"\nTipo de carne: "+productoF.getTipo()
						            +"\nLibras por unidad: "+productoF.getPesoLibra()
						            +"\nUnidades en stock: "+productoF.getCantidad());
							         System.out.print("\n");
						}
						System.out.println("\nQue Oferta esta interesado en agregar al carrito de compras?");
						System.out.print("\n         **Opciones Adicionales**");
						System.out.print("\nIngrese "+(y+1)+" Volver al Menu de Alimentos");
						System.out.println("\nIngrese "+(y+2)+" para Escoger otra Seccion");
						oferSelec = Integer.parseInt(validarRespuesta(1,meat2.size()+2, String.valueOf(sc.nextInt())));
						
						if(oferSelec == (y+1)) {
							menuAlimentos(mercado);
							return;
						}else if(oferSelec == (y+2)) {
							ofertaProductos(mercado);
							return;
						}else if(oferSelec<y) {
							comprarAlimentos(mercado,meat2, oferSelec,eleccion);
						}
					}
		               return;
					}else if(elegir==mercado.getOfercarne().size()+2) {
						menuAlimentos(mercado);
						return;
					}else if(elegir==mercado.getOfercarne().size()+3) {
						ofertaProductos(mercado);
						return;

					}else if(elegir-1<mercado.getOfercarne().size()) {
						comprarAlimentos(mercado,mercado.getOfercarne(),elegir,eleccion);

					}

		}else if(eleccion.equals("2")){// OPCION NO CARNICOS
			ArrayList<noCarnicos> nomeat = new ArrayList<noCarnicos>();
			if(mercado.getOfernocarnicos().size() > 0){
				int ii=1,elegir2;
				System.out.print("Bienvenido al Area de noCarnicos del "+mercado+"\n");
				System.out.print("       *** !NOCARNICOS EN OFERTA¡***\n");
				System.out.print("Acontinuacion nuestros productos disponibles: \n");
				for(noCarnicos productoN:mercado.getOfernocarnicos()) {
					System.out.println(ii++ +"."
				            +"\nNombre: "+productoN.getNombre()
				            +"\nClasificacion: "+productoN.getGrupo()
				            +"\nLibras por unidad: "+productoN.getPrecio()
				            +"\nUnidades en stock: "+productoN.getCantidad());
					         System.out.print("\n");
				}
				
				System.out.println("\nQue Oferta esta interesado en agregar al carrito de compras?");
				System.out.print("\n         **Opciones Adicionales**");
				System.out.print("\nIngrese "+(mercado.getOfernocarnicos().size()+1)+" para Filtrar por Categoria");
				System.out.print("\nIngrese "+(mercado.getOfernocarnicos().size()+2)+" Volver al Menu de Alimentos");
				System.out.println("\nIngrese "+(mercado.getOfernocarnicos().size()+3)+" para Escoger otra Seccion");
				elegir2 = Integer.parseInt(validarRespuesta(1,mercado.getOfernocarnicos().size()+3, String.valueOf(sc.nextInt())));
				if(elegir2==mercado.getOfernocarnicos().size()+1){
					//AÑADI UN NUEVO ATRIBUTO PARA HACER ESTE FILTRO
					System.out.print("Ingrese :"+"\n1. Categoria Granos" 
				                      +"\n2. Categoria Lacteos"+"\n3. Categoria Vegetales"+"\n4. Categoria Otros\n");
					System.out.print("\n         **Opciones Adicionales**");
					System.out.print("\nIngrese "+(5)+" Volver al Menu de Alimentos");
					System.out.println("\nIngrese "+(6)+" para Escoger otra Seccion");
					int grupo = Integer.parseInt(validarRespuesta(1,6, String.valueOf(sc.nextInt())));
					if(grupo<5){
						//FILTRO POR CATEGORIA DE NOCARNE
							System.out.println("\nOfertas Disponibles para el Filtro de la "+"Categoria "+noCarnicos.validarGrupo(String.valueOf(grupo)));
							nomeat = noCarnicos.listaCatergoria(mercado.getOfernocarnicos(),noCarnicos.validarGrupo(String.valueOf(grupo)));
							int z =1, ofernoCarne;
							for(noCarnicos productoF:nomeat) {
								System.out.println(z++ +"."
							            +"\nNombre: "+productoF.getNombre()
							            +"\nClasificacion:  "+productoF.getGrupo()
							            +"\nPrecio por libra:  "+productoF.getPrecio()
							            +"\nUnidades en stock: "+productoF.getCantidad());
								         System.out.print("\n");
							}
							System.out.println("\nQue Oferta esta interesado en agregar al carrito de compras?");
							System.out.print("\n         **Opciones Adicionales**");
							System.out.print("\nIngrese "+(z+1)+" Volver al Menu de Alimentos");
							System.out.println("\nIngrese "+(z+2)+" para Escoger otra Seccion");
							ofernoCarne = Integer.parseInt(validarRespuesta(1,nomeat.size()+2, String.valueOf(sc.nextInt())));
							
							if(ofernoCarne == (z+1)) {
								menuAlimentos(mercado);
								return;
							}else if(ofernoCarne == (z+2)) {
								ofertaProductos(mercado);
								return;
							}else if(ofernoCarne-1<z){
								comprarAlimentos(mercado, nomeat, ofernoCarne,eleccion);
							}
						}else if(grupo == 5) {
							menuAlimentos(mercado);
							return;
						}else if(grupo == 6) {
							ofertaProductos(mercado);
							return;
						}
					}else if(elegir2==mercado.getOfernocarnicos().size()+2) {
						menuAlimentos(mercado);
						return;
					}else if(elegir2==mercado.getOfernocarnicos().size()+3) {
						ofertaProductos(mercado);
						return;

					}else if(elegir2-1<mercado.getOfernocarnicos().size()) {
						comprarAlimentos(mercado, mercado.getOfernocarnicos(), elegir2,eleccion);
					}
				}
					
			}
		}else if(eleccion.equals("3")){// OPCION CARNICOS Y NO CARNICOS
			ArrayList<Alimentos> sustento = new ArrayList<>();
			if(mercado.getOfernocarnicos().size() > 0){
				System.out.print("Bienvenido al Area de Carnicos & noCarnicos del "+mercado+"\n");
				System.out.print("*** !TODAS LAS OFERTAS DE ALIMENTOS EN UN SOLO LUGAR!***\n");
				System.out.print("    Acontinuacion nuestros productos disponibles: \n");
				for(int i = 0; i<mercado.getOfercomi().size(); i++) {
					System.out.print((i+1)+". ");
					System.out.println(mercado.getOfercomi().get(i).oferta());//Ligadura dinamica
					System.out.print("\n");
				}
				
				System.out.println("\nQue Oferta esta interesado en agregar al carrito de compras?");
				System.out.print("\n         **Opciones Adicionales**");

				System.out.print("\nIngrese "+(mercado.getOfercomi().size()+1)+" para Filtrar por Precio");
				System.out.print("\nIngrese "+(mercado.getOfercomi().size()+2)+" Volver al Menu de Alimentos");
				System.out.println("\nIngrese "+(mercado.getOfercomi().size()+3)+" para Escoger otra Seccion");
	            int validar = Integer.parseInt(validarRespuesta(1,mercado.getOfercomi().size()+3, String.valueOf(sc.nextInt())));
			if(validar<mercado.getOfercomi().size()) {
				comprarAlimentos(mercado, sustento,validar,eleccion);
				return;
				
			}else if(validar == mercado.getOfercomi().size()+1){
				//FILTRO CARNES Y NOCARNICOS POR PRECIO -copiado de santi :)-
				System.out.println("Ingrese el precio minimo que desea en la oferta: ");
				int min = Integer.parseInt(confirmarNumero(sc.nextLine()));
				System.out.println("Ingrese el precio máximo que desea en la oferta: ");
				int max = Integer.parseInt(confirmarNumero(sc.nextLine()));
				System.out.println("Bus..can..do....");
				sustento = Carne.filPrecioAli(mercado, min, max);//
				if(sustento.size() != 0){
					System.out.println("Las Ofertas Disponibles para el Filtro son : ");
					for(int i = 0; i<sustento.size(); i++) {
						System.out.print((i+1)+". ");
						System.out.println(sustento.get(i).oferta());//Ligadura dinamica
						System.out.print("\n");
					}
					System.out.println("\nQue Oferta esta interesado en agregar al carrito de compras?");
					System.out.print("\n         **Opciones Adicionales**");
					System.out.print("\nIngrese "+(sustento.size() +1)+" Volver al Menu de Alimentos");
					System.out.println("\nIngrese "+(sustento.size() +2)+" para Escoger otra Seccion");
					int respuesta = Integer.parseInt(validarRespuesta(1,sustento.size()+2, String.valueOf(sc.nextInt())));
					if(respuesta-1<sustento.size()) {
						comprarAlimentos(mercado, sustento, respuesta,eleccion);
						return;
					}else if(respuesta==sustento.size()+1) {
						
						menuAlimentos(mercado);
						return;
					}else if(respuesta == sustento.size()+2) {
						ofertaProductos(mercado);
						return;
					}
				}else{
					sustento = mercado.getOfercomi();
					System.out.println("No se encontraron Ofertas Disponibles :(");
					System.out.print("\n         **Opciones Adicionales**");
					System.out.print("\nIngrese "+(1)+" Volver al Menu de Alimentos");
					System.out.println("\nIngrese "+(2)+" para Escoger otra Seccion");
					int vali = Integer.parseInt(validarRespuesta(1,2, String.valueOf(sc.nextInt())));
					switch(vali) {
					case 1:
						menuAlimentos(mercado);
						break;
					case 2:
						ofertaProductos(mercado);
						break;
					}
				}
			}
		 }
			
	    }
	}
	

   //CREO UN METODO PARA FINALIZAR TODAS LAS COMPRAS DE ALIMENTOS
	public static void comprarAlimentos(Supermercado mercado, ArrayList<?> lista, int oferSelec, String eleccion) {
		
			
		switch (eleccion){
		case "1":
			 ArrayList<Carne> listaCarne = Carne.convertirCarne(lista);
			 Carne p = null;
			 p = listaCarne.get(oferSelec-1);
			 System.out.println("Has Selecionado la Oferta de "+p.getNombre());
				disminuirStock(lista.get(oferSelec-1),mercado);
				System.out.println("Producto agregado con exito al carrito de compras!");
				System.out.print("\nSeguir comprando \n1. Seccion Alimentos \n2. Otras Secciones");
				System.out.println("\n3. Finalizar compra ");
				int validado = Integer.parseInt(validarRespuesta(1,3, String.valueOf(sc.nextInt())));
				//String validado = validarRespuesta(1, 3, sc.nextLine());
				
			    switch(validado) {
			    case 1:
					menuAlimentos(mercado);
					return;
			    case 2:
			    	ofertaProductos(mercado);
					return;
			    case 3:
			    	finalizarCompra();
					return;
			    }
			    
			break;
		case "2":
			 ArrayList<noCarnicos> listanoCarne = noCarnicos.convertirnoCarne(lista);
			 noCarnicos q = null;
			 q = listanoCarne.get(oferSelec-1);
			 System.out.println("Has Selecionado la oferta de "+q.getNombre());
				disminuirStock(lista.get(oferSelec-1),mercado);
				System.out.println("Producto agregado con exito al carrito de compras!");
				System.out.print("\nSeguir comprando \n1. Seccion Alimentos \n2. Otras Secciones");
				System.out.println("\n3. Finalizar compra ");
				int validad = Integer.parseInt(validarRespuesta(1,3, String.valueOf(sc.nextInt())));
				//String validad = validarRespuesta(1, 3, sc.nextLine());
				if(validad==1) {
					menuAlimentos(mercado);
	                break;
				}else if(validad==2) {
					ofertaProductos(mercado);
					break;
				}else if(validad==3) {
					finalizarCompra();
					break;
				}
			break;
			
		case"3":
			ArrayList<Alimentos> listAlimento = noCarnicos.convertirAlimentos(lista);
			Alimentos t = null;
			 t = listAlimento.get(oferSelec-1);
			 System.out.println("Has Selecionado la oferta de "+t.getNombre());
				disminuirStock(lista.get(oferSelec-1),mercado);
				System.out.println("Producto agregado con exito al carrito de compras!");
				System.out.print("\nSeguir comprando \n1. Seccion Alimentos \n2. Otras Secciones");
				System.out.println("\n3. Finalizar compra ");
				int res = Integer.parseInt(validarRespuesta(1,3, String.valueOf(sc.nextInt())));
				//String res = sc.nextLine();
				
				if(res == 1) {
					menuAlimentos(mercado);
	                break;
				}else if(res == 2) {
					ofertaProductos(mercado);
					break;
				}else if(res==3) {
					finalizarCompra();
					break;
				}
			break;
		}

	}

	public static void comprarTelevisor(Supermercado mercado){
		if(mercado.getOfertv().size() > 0){
			ArrayList<String> filtros = new ArrayList<>(List.of("Nombre","Marca", "Pulgadas", "Resolucion", "Precio"));
			Electronico producto_seleccionado = null;
			System.out.println("Estos son los televisores que tenemos disponibles en "+mercado.getNombre());
			for(int i = 0; i<mercado.getOfertv().size(); i++) {
				producto_seleccionado = mercado.getOfertv().get(i);
				System.out.println((i+1)+". "+
						"\nNombre:"+producto_seleccionado.getNombre()+" " +
						"\n Marca: "+producto_seleccionado.getMarca()+" " +
						"\n Precio: "+producto_seleccionado.getPrecio()+" "+
						"\n Cantidad en stock: "+producto_seleccionado.getCantidad());
			}
			System.out.println("Selecciona uno de nuestros productos a comprar o escribe '"+(mercado.getOfertv().size()+1)+"' para salir: ");
			System.out.println("Puedes escribir: "+(mercado.getOfertv().size()+2)+" para realizar una busqueda con filtros: ");
			String respuesta = sc.nextLine();
			while(Integer.parseInt(respuesta) > mercado.getOfertv().size()+2 || Integer.parseInt(respuesta)<= 0){
				System.out.println("Respuesta erronea, intentalo nuevamente");
				respuesta = sc.nextLine();
			}
			if(respuesta.equals(mercado.getOfertv().size()+1+"")){
				return;

			}else if(respuesta.equals(mercado.getOfertv().size()+2+"")){
				//MI VERSION DE LOS FILTROS :D

				System.out.println("Dinos por cual tipo de caracteristica deseas filtrar: ");
				int indiceFiltros = 1;
				for (String filtro: filtros){
					System.out.println(indiceFiltros+". "+filtro+"\n");
					indiceFiltros++;
				}
				System.out.println("Escribe un numero para seleccionar su filtro correspondiente: ");
				int inputFiltros = Integer.parseInt(validarRespuesta(1, filtros.size(), sc.nextLine()));
				System.out.println("El filtro seleccionado fue: "+filtros.get(inputFiltros-1));
				ArrayList<Tv> tvsFiltrados = new ArrayList<>();
				switch (inputFiltros) {
					case 1:

						System.out.println("Ingresa el nombre del televisor que estas buscando: ");
						String nombre = sc.nextLine().toLowerCase();
						System.out.println("Buscando...");
						tvsFiltrados = Tv.filtroNombre(mercado, nombre);

						if(tvsFiltrados.size() == 0){
							tvsFiltrados = Tv.filtroNombreSimilar(mercado, nombre);
						}
						if(tvsFiltrados.size()!=0) {
							System.out.println("Estos son los resultados de tu busqueda: ");
						}else{
							tvsFiltrados = mercado.getOfertv();
							System.out.println("No se encontraron resultados a tu busqueda, aqui te ensenamos algunos televisores disponibles en la tienda: ");
						}
						indiceFiltros = 1;
						for (Tv televisor: tvsFiltrados) {
							System.out.println(indiceFiltros+". "+televisor);
							indiceFiltros++;
						}
						System.out.println("Escribe el numero del televisor que desas comprar o dale en "+(tvsFiltrados.size()+1)+" para volver atras");
						respuesta = validarRespuesta(1, tvsFiltrados.size()+1,sc.nextLine());
						if(respuesta.equals((tvsFiltrados.size()+1)+"")){
							comprarTelevisor(mercado);

						}else{
							comprarTelevisor(mercado, respuesta, tvsFiltrados);

						}
						break;
					case 2:
						//Filtrar por marca
						System.out.println("Ingresa la marca del televisor que estas buscando: ");
						String marca = sc.nextLine().toLowerCase();
						System.out.println("Buscando...");
						tvsFiltrados = Tv.filtroMarca(mercado, marca);

						if(tvsFiltrados.size() == 0){
							tvsFiltrados = Tv.filtroMarcaSimilar(mercado, marca);
						}
						if(tvsFiltrados.size()!=0) {
							System.out.println("Estos son los resultados de tu busqueda: ");
						}else{
							tvsFiltrados = mercado.getOfertv();
							System.out.println("No se encontraron resultados a tu busqueda, aqui te enseñamos algunos televisores disponibles en la tienda: ");
						}
						indiceFiltros = 1;
						for (Tv televisor: tvsFiltrados) {
							System.out.println(indiceFiltros+". "+televisor);
							indiceFiltros++;
						}
						System.out.println("Escribe el numero del televisor que desas comprar o dale en "+(tvsFiltrados.size()+1)+" para volver atras");
						respuesta = validarRespuesta(1, tvsFiltrados.size()+1,sc.nextLine());
						if(respuesta.equals((tvsFiltrados.size()+1)+"")){
							comprarTelevisor(mercado);

						}else{
							comprarTelevisor(mercado, respuesta, tvsFiltrados);

						}
						break;
					case 3:

						//Filtramos por pulgadas
						System.out.println("Ingrese el numero minimo de pulgadas que desea en su televisor: ");
						int minPulgadas = Integer.parseInt(confirmarNumero(sc.nextLine()));
						System.out.println("Ingrese el numero maximo de pulgadas que desea en su televisor: ");
						int maxPulgadas = Integer.parseInt(confirmarNumero(sc.nextLine()));
						System.out.println("Buscando... jejeje");
						tvsFiltrados = filtroPulgadas(mercado, minPulgadas,maxPulgadas);
						if(tvsFiltrados.size() != 0){
							System.out.println("Estos son los resultados de tu busqueda: ");
						}else{
							tvsFiltrados = mercado.getOfertv();
							System.out.println("No se encontraron resultados a tu busqueda, aqui te ensenamos algunos televisores disponibles en la tienda: ");
						}
						indiceFiltros = 1;
						for (Tv televisor: tvsFiltrados) {
							System.out.println(indiceFiltros+". "+televisor);
							indiceFiltros++;
						}
						System.out.println("Escribe el numero del televisor que desas comprar o dale en "+(tvsFiltrados.size()+1)+" para volver atras");
						respuesta = validarRespuesta(1, tvsFiltrados.size()+1,sc.nextLine());
						if(respuesta.equals((tvsFiltrados.size()+1)+"")){
							comprarTelevisor(mercado);

						}else{
							comprarTelevisor(mercado, respuesta, tvsFiltrados);

						}
						break;
					case 4:
						//Filtrar por resolucion
						System.out.println("Ingresa la resolucion del televisor que estas buscando: ");
						String resolucion = sc.nextLine().toLowerCase();
						System.out.println("Buscando...");
						tvsFiltrados = Tv.filtroResolucion(mercado, resolucion);

						if(tvsFiltrados.size() == 0){
							tvsFiltrados = Tv.filtroResolucionSimilar(mercado, resolucion);
						}
						if(tvsFiltrados.size()!=0) {
							System.out.println("Estos son los resultados de tu busqueda: ");
						}else{
							tvsFiltrados = mercado.getOfertv();
							System.out.println("No se encontraron resultados a tu busqueda, aqui te ensenamos algunos televisores disponibles en la tienda: ");
						}
						indiceFiltros = 1;
						for (Tv televisor: tvsFiltrados) {
							System.out.println(indiceFiltros+". "+televisor);
							indiceFiltros++;
						}
						System.out.println("Escribe el numero del televisor que desas comprar o dale en "+(tvsFiltrados.size()+1)+" para volver atras");
						respuesta = validarRespuesta(1, tvsFiltrados.size()+1,sc.nextLine());
						if(respuesta.equals((tvsFiltrados.size()+1)+"")){
							comprarTelevisor(mercado);

						}else{
							comprarTelevisor(mercado, respuesta, tvsFiltrados);

						}
						break;
					case 5:
						//Filtrar por precios
						System.out.println("Ingrese el precio minimo que desea en su televisor: ");
						int minPrecio = Integer.parseInt(confirmarNumero(sc.nextLine()));
						System.out.println("Ingrese el precio maximo que desea en su televisor: ");
						int maxPrecio = Integer.parseInt(confirmarNumero(sc.nextLine()));
						System.out.println("Buscando... jejeje");
						tvsFiltrados = filtroPrecio(mercado, minPrecio,maxPrecio);
						if(tvsFiltrados.size() != 0){
							System.out.println("Estos son los resultados de tu busqueda: ");
						}else{
							tvsFiltrados = mercado.getOfertv();
							System.out.println("No se encontraron resultados a tu busqueda, aqui te ensenamos algunos televisores disponibles en la tienda: ");
						}
						indiceFiltros = 1;
						for (Tv televisor: tvsFiltrados) {
							System.out.println(indiceFiltros+". "+televisor);
							indiceFiltros++;
						}
						System.out.println("Escribe el numero del televisor que desas comprar o dale en "+(tvsFiltrados.size()+1)+" para volver atras");
						respuesta = validarRespuesta(1, tvsFiltrados.size()+1,sc.nextLine());
						if(respuesta.equals((tvsFiltrados.size()+1)+"")){
							comprarTelevisor(mercado);

						}else{
							comprarTelevisor(mercado, respuesta, tvsFiltrados);

						}
						break;
				}
			}else{
				comprarTelevisor(mercado, respuesta, mercado.getOfertv());
			}
		}else{
			System.out.println("Este supermercado no cuenta con televisores D: Deseas anadir uno?: " +
					"\n1. SI" +
					"\n2. NO");
			String respuesta = sc.nextLine();
			while(!respuesta.equals("1") && !respuesta.equals("2")){
				System.out.println("Rectifica el numero ingresado, intentalo nuevamente: ");
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
	public static void comprarTelevisor(Supermercado mercado, String respuesta, ArrayList<Tv> televisores){
		Electronico producto_seleccionado = null;
		producto_seleccionado = televisores.get(Integer.parseInt(respuesta)-1);
		System.out.println("Has seleccionado el televisor: \n"+producto_seleccionado.getMarca()+": "+producto_seleccionado.getNombre());

		System.out.println("Ingresa: " +
				"\n1. Anadir al carrito" +
				"\n2. Volver al menu anterior");
		String respuesta2 = sc.nextLine();
		while(!respuesta2.equals("1") && !respuesta2.equals("2")){
			System.out.println("Rectifica el numero ingresado, intentalo nuevamente: ");
			respuesta2 = sc.nextLine();
		}
		switch (respuesta2){
			case "1":
				
				disminuirStock(televisores.get(Integer.parseInt(respuesta)-1),mercado);
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
	}

	public static void comprarCelular(Supermercado mercado){
		ArrayList<String> filtros = new ArrayList<>(List.of("Nombre","Marca", "Color", "Almacenamiento","Bateria", "Ram", "Precio"));
		if(mercado.getOfercelular().size() > 0){
			Electronico producto_seleccionado = null;
			System.out.println("Estos son los celulares que tenemos disponibles en "+mercado.getNombre());
			for(int i = 0; i<mercado.getOfercelular().size(); i++) {
				producto_seleccionado = mercado.getOfercelular().get(i);
				System.out.println((i+1)+". "+producto_seleccionado);
			}
			System.out.println("Selecciona uno de nuestros productos a comprar o escribe '"+(mercado.getOfercelular().size()+1)+"' para salir: ");
			System.out.println("Puedes escribir: "+(mercado.getOfercelular().size()+2)+" para realizar una busqueda con filtros: ");
			String respuesta = sc.nextLine();
			while(Integer.parseInt(respuesta) > mercado.getOfercelular().size()+2 || Integer.parseInt(respuesta)<= 0){
				System.out.println("Respuesta erronea, intentalo nuevamente");
				respuesta = sc.nextLine();
			}
			if(respuesta.equals(mercado.getOfercelular().size()+1+"")){
				return;

			}else if(respuesta.equals(mercado.getOfercelular().size()+2+"")){
				//MI VERSION DE LOS FILTROS :D

				System.out.println("Dinos por cual tipo de caracteristica deseas filtrar: ");
				int indiceFiltros = 1;
				for (String filtro: filtros){
					System.out.println(indiceFiltros+". "+filtro+"\n");
					indiceFiltros++;
				}
				System.out.println("Escribe un numero para seleccionar su filtro correspondiente: ");
				int inputFiltros = Integer.parseInt(validarRespuesta(1, filtros.size(), sc.nextLine()));
				System.out.println("El filtro seleccionado fue: "+filtros.get(inputFiltros-1));
				ArrayList<Celular> celulares_filtrados = new ArrayList<>();
				switch (inputFiltros) {
					case 1:

						System.out.println("Ingresa el nombre del celular que estas buscando: ");
						String nombre = sc.nextLine().toLowerCase();
						System.out.println("Buscando...");
						celulares_filtrados = Celular.filtroNombre(mercado, nombre);

						if(celulares_filtrados.size() == 0){
							celulares_filtrados = Celular.filtroNombreSimilar(mercado, nombre);
						}
						if(celulares_filtrados.size()!=0) {
							System.out.println("Estos son los resultados de tu busqueda: ");
						}else{
							celulares_filtrados = mercado.getOfercelular();
							System.out.println("No se encontraron resultados a tu busqueda, aqui te ensenamos algunos celulares disponibles en la tienda: ");
						}
						indiceFiltros = 1;
						for (Celular celular: celulares_filtrados) {
							System.out.println(indiceFiltros+". "+celular);
							indiceFiltros++;
						}
						System.out.println("Escribe el numero del celular que desas comprar o dale en "+(celulares_filtrados.size()+1)+" para volver atras");
						respuesta = validarRespuesta(1, celulares_filtrados.size()+1,sc.nextLine());
						if(respuesta.equals((celulares_filtrados.size()+1)+"")){
							comprarCelular(mercado);

						}else{
							comprarCelular(mercado, respuesta, celulares_filtrados);

						}
						break;
					case 2:
						//Filtrar por marca
						System.out.println("Ingresa la marca del celular que estas buscando: ");
						String marca = sc.nextLine().toLowerCase();
						System.out.println("Buscando...");
						celulares_filtrados = Celular.filtroMarca(mercado, marca);

						if(celulares_filtrados.size() == 0){
							celulares_filtrados = Celular.filtroMarcaSimilar(mercado, marca);
						}
						if(celulares_filtrados.size()!=0) {
							System.out.println("Estos son los resultados de tu busqueda: ");
						}else{
							celulares_filtrados = mercado.getOfercelular();
							System.out.println("No se encontraron resultados a tu busqueda, aqui te ensenamos algunos celulares disponibles en la tienda: ");
						}
						indiceFiltros = 1;
						for (Celular celular: celulares_filtrados) {
							System.out.println(indiceFiltros+". "+celular);
							indiceFiltros++;
						}
						System.out.println("Escribe el numero del celular que desas comprar o dale en "+(celulares_filtrados.size()+1)+" para volver atras");
						respuesta = validarRespuesta(1, celulares_filtrados.size()+1,sc.nextLine());
						if(respuesta.equals((celulares_filtrados.size()+1)+"")){
							comprarCelular(mercado);

						}else{
							comprarCelular(mercado, respuesta, celulares_filtrados);

						}
						break;
					case 3:
						//Filtrar por Color
						System.out.println("Ingresa el color del celular que estas buscando: ");
						String color = sc.nextLine().toLowerCase();
						System.out.println("Buscando...");
						celulares_filtrados = Celular.filtroColor(mercado, color);

						if(celulares_filtrados.size()!=0) {
							System.out.println("Estos son los resultados de tu busqueda: ");
						}else{
							celulares_filtrados = mercado.getOfercelular();
							System.out.println("No se encontraron resultados a tu busqueda, aqui te ensenamos algunos celulares disponibles en la tienda: ");
						}
						indiceFiltros = 1;
						for (Celular celular: celulares_filtrados) {
							System.out.println(indiceFiltros+". "+celular);
							indiceFiltros++;
						}
						System.out.println("Escribe el numero del celular que desas comprar o dale en "+(celulares_filtrados.size()+1)+" para volver atras");
						respuesta = validarRespuesta(1, celulares_filtrados.size()+1,sc.nextLine());
						if(respuesta.equals((celulares_filtrados.size()+1)+"")){
							comprarTelevisor(mercado);

						}else{
							comprarCelular(mercado, respuesta, celulares_filtrados);

						}
						break;
					case 4:

						//Filtramos por almacenamiento
						System.out.println("Ingrese el numero minimo de almacenamiento que desea en su celular: ");
						int minAlmacenamiento = Integer.parseInt(confirmarNumero(sc.nextLine()));
						System.out.println("Ingrese el número maximo de almacenamiento que desea en su celular: ");
						int maxAlmacenamiento = Integer.parseInt(confirmarNumero(sc.nextLine()));
						System.out.println("Buscando... jejeje");
						celulares_filtrados = Celular.filtroAlmacenamiento(mercado, minAlmacenamiento,maxAlmacenamiento);
						if(celulares_filtrados.size() != 0){
							System.out.println("Estos son los resultados de tu busqueda: ");
						}else{
							celulares_filtrados = mercado.getOfercelular();
							System.out.println("No se encontraron resultados a tu busqueda, aqui te ensenamos algunos celulares disponibles en la tienda: ");
						}
						indiceFiltros = 1;
						for (Celular celular: celulares_filtrados) {
							System.out.println(indiceFiltros+". "+celular);
							indiceFiltros++;
						}
						System.out.println("Escribe el numero del celular que desas comprar o dale en "+(celulares_filtrados.size()+1)+" para volver atras");
						respuesta = validarRespuesta(1, celulares_filtrados.size()+1,sc.nextLine());
						if(respuesta.equals((celulares_filtrados.size()+1)+"")){
							comprarCelular(mercado);

						}else{
							comprarCelular(mercado, respuesta, celulares_filtrados);

						}
						break;

					case 5:

						//Filtramos por bateria
						System.out.println("Ingrese el numero minimo de bateria que desea en su celular: ");
						int minBateria = Integer.parseInt(confirmarNumero(sc.nextLine()));
						System.out.println("Ingrese el numero maximo de bateria que desea en su celular: ");
						int maxBateria = Integer.parseInt(confirmarNumero(sc.nextLine()));
						System.out.println("Buscando... jejeje");
						celulares_filtrados = Celular.filtroBateria(mercado, minBateria,maxBateria);
						if(celulares_filtrados.size() != 0){
							System.out.println("Estos son los resultados de tu busqueda: ");
						}else{
							celulares_filtrados = mercado.getOfercelular();
							System.out.println("No se encontraron resultados a tu busqueda, aqui te ensenamos algunos celulares disponibles en la tienda: ");
						}
						indiceFiltros = 1;
						for (Celular celular: celulares_filtrados) {
							System.out.println(indiceFiltros+". "+celular);
							indiceFiltros++;
						}
						System.out.println("Escribe el numero del celular que desas comprar o dale en "+(celulares_filtrados.size()+1)+" para volver atras");
						respuesta = validarRespuesta(1, celulares_filtrados.size()+1,sc.nextLine());
						if(respuesta.equals((celulares_filtrados.size()+1)+"")){
							comprarCelular(mercado);

						}else{
							comprarCelular(mercado, respuesta, celulares_filtrados);

						}
						break;
					case 6:

						//Filtramos por Ram
						System.out.println("Ingrese el numero minimo de ram que desea en su celular: ");
						int minRam = Integer.parseInt(confirmarNumero(sc.nextLine()));
						System.out.println("Ingrese el numero maximo de ram que desea en su celular: ");
						int maxRam = Integer.parseInt(confirmarNumero(sc.nextLine()));
						System.out.println("Buscando... jejeje");
						celulares_filtrados = Celular.filtroRam(mercado, minRam,maxRam);
						if(celulares_filtrados.size() != 0){
							System.out.println("Estos son los resultados de tu busqueda: ");
						}else{
							celulares_filtrados = mercado.getOfercelular();
							System.out.println("No se encontraron resultados a tu busqueda, aqui te ensenamos algunos celulares disponibles en la tienda: ");
						}
						indiceFiltros = 1;
						for (Celular celular: celulares_filtrados) {
							System.out.println(indiceFiltros+". "+celular);
							indiceFiltros++;
						}
						System.out.println("Escribe el numero del celular que desas comprar o dale en "+(celulares_filtrados.size()+1)+" para volver atras");
						respuesta = validarRespuesta(1, celulares_filtrados.size()+1,sc.nextLine());
						if(respuesta.equals((celulares_filtrados.size()+1)+"")){
							comprarCelular(mercado);

						}else{
							comprarCelular(mercado, respuesta, celulares_filtrados);

						}
						break;
					case 7:

						//Filtramos por precio
						System.out.println("Ingrese el numero minimo de precio que desea en su celular: ");
						int minPrecio = Integer.parseInt(confirmarNumero(sc.nextLine()));
						System.out.println("Ingrese el numero maximo de precio que desea en su celular: ");
						int maxPrecio = Integer.parseInt(confirmarNumero(sc.nextLine()));
						System.out.println("Buscando... jejeje");
						celulares_filtrados = Celular.filtroPrecio(mercado, minPrecio,maxPrecio);
						if(celulares_filtrados.size() != 0){
							System.out.println("Estos son los resultados de tu busqueda: ");
						}else{
							celulares_filtrados = mercado.getOfercelular();
							System.out.println("No se encontraron resultados a tu busqueda, aqui te ensenamos algunos celulares disponibles en la tienda: ");
						}
						indiceFiltros = 1;
						for (Celular celular: celulares_filtrados) {
							System.out.println(indiceFiltros+". "+celular);
							indiceFiltros++;
						}
						System.out.println("Escribe el numero del celular que desas comprar o dale en "+(celulares_filtrados.size()+1)+" para volver atras");
						respuesta = validarRespuesta(1, celulares_filtrados.size()+1,sc.nextLine());
						if(respuesta.equals((celulares_filtrados.size()+1)+"")){
							comprarCelular(mercado);

						}else{
							comprarCelular(mercado, respuesta, celulares_filtrados);

						}
						break;
				}
			}else{
				comprarCelular(mercado, respuesta, mercado.getOfercelular());
			}
		}else{
			System.out.println("Este supermercado no cuenta con Celulares D: Deseas anadir uno?: " +
					"\n1. SI" +
					"\n2. NO");
			String respuesta = sc.nextLine();
			while(!respuesta.equals("1") && !respuesta.equals("2")){
				System.out.println("Rectifica el numero ingresado, intentalo nuevamente: ");
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

	public static void comprarCelular(Supermercado mercado, String respuesta, ArrayList<Celular> celulares){
		Electronico producto_seleccionado = null;
		producto_seleccionado = celulares.get(Integer.parseInt(respuesta)-1);
		System.out.println("Has seleccionado el celular: \n"+producto_seleccionado.getMarca()+": "+producto_seleccionado.getNombre());

		System.out.println("Ingresa: " +
				"\n1. Anadir al carrito" +
				"\n2. Volver al menu anterior");
		String respuesta2 = sc.nextLine();
		while(!respuesta2.equals("1") && !respuesta2.equals("2")){
			System.out.println("Rectifica el numero ingresado, intentalo nuevamente: ");
			respuesta2 = sc.nextLine();
		}
		switch (respuesta2){
			case "1":
				disminuirStock(celulares.get(Integer.parseInt(respuesta)-1),mercado);
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
	}

	
	public static void comprarRopa(Supermercado mercado) {
	    if (mercado.getOferropa().size() > 0) {
	        ArrayList<Ropa> prendasFiltradas = new ArrayList<>();
	        System.out.println("Estas son las prendas de ropa disponibles en " + mercado.getNombre());
	        // Mostrar las prendas de ropa disponibles
	        for (int i = 0; i < mercado.getOferropa().size(); i++) {
	            Ropa prenda = mercado.getOferropa().get(i);
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
	        prendasFiltradas = Ropa.filtrarPrendas(mercado.getOferropa(), talla, color, genero, tipo);

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
	                        "\n1. Anadir al carrito" +
	                        "\n2. Volver al menu anterior");
	                String respuesta2 = sc.nextLine();
	                while (!respuesta2.equals("1") && !respuesta2.equals("2")) {
	                    System.out.println("Numero ingresado incorrecto, intentalo nuevamente: ");
	                    respuesta2 = sc.nextLine();
	                }
	                switch (respuesta2) {
	                    case "1":
	                        disminuirStock(prendaSeleccionada,mercado);
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
			System.out.println("El numero ingresado anteriormente no es valido, por favor intenalo "
		            +"nuevamente solo se acepta"
			        + "\n* Numero entero"
					+ "\n* Numero decimal con punto");
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
