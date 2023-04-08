package uiMain;
//package uiMain;

import baseDatos.Deserializar;
import baseDatos.Serializar;
import gestorAplicacion.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
	public static Scanner sc = new Scanner(System.in);
	public static ArrayList<Libro> lista_libros = new ArrayList<Libro>();
	public static ArrayList<Carne> lista_carnicos = new ArrayList<Carne>();
	public static ArrayList<noCarnicos> lista_no_carnicos = new ArrayList<noCarnicos>();
	public static ArrayList<Tv> lista_tvs = new ArrayList<Tv>();
	public static ArrayList<Celular> lista_celulares = new ArrayList<Celular>();
	public static ArrayList<Supermercado> lista_super = new ArrayList<Supermercado>();
	public static void main(String[] args) {
		lista_celulares = Deserializar.deserializarCelulares();
		lista_tvs = Deserializar.deserializarTvs();
		lista_super =  Deserializar.deserializarSupermercados();
		System.out.println("Bienvenido a nuestro sistema gestor de tiendas!");
		System.out.print("Ingresa tu nombre: ");
		String nombre = sc.next();
		System.out.print("Ingresa tu direccion: ");
		String direccion = sc.next();
		Cliente cliente = new Cliente(nombre, direccion);
		System.out.println("Bienvenido "+nombre+"!");
		while(true){
			System.out.print("Dinos que deseas hacer" +
					"\n1. Lista de supermercados" +
					"\n2. Salir");
			sc.nextLine();
			String respuesta =  sc.next();
			while(!respuesta.equals("1") && !respuesta.equals("2")){
				System.out.print("La respuesta ingresada no se encuentra entre las opciones (1 o 2)" +
						"\nIngresala nuevamente");
				respuesta = Main.sc.next();
			}
			if(respuesta.equals("1")){
				seleccionarSupermercado();
			}else{
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
	public static void seleccionarSupermercado() {

		String respuesta;
		Supermercado super_seleccionado;
		System.out.println(lista_super.size());
		if(lista_super.size() == 0){
			//Si el usuario no crea ningun supermercado salimos del método para volver al menú anterior
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
					System.out.print("Ingresa el nombre del supermercado nuevo: ");
					Supermercado supermercado = new Supermercado(Main.sc.next());
					lista_super.add(supermercado);
					return;

				}else{
					super_seleccionado = lista_super.get(Integer.parseInt(respuesta)-1);
					System.out.println("El mercado seleccionado es "+super_seleccionado.nombre);
					seleccionarProducto(super_seleccionado);
					break;
				}

			}catch(Exception e){
				System.out.println("La opción seleccionada no está permitida, recapacita XD, intetalo nuevamente");
				respuesta = sc.next();
			}
		}
		if (super_seleccionado.oferelectro.size() == 0) {
			System.out.println("El supermercado seleccionado no tiene ningún producto guardado" +
					"\n¿Desea usted agregar algún producto?" +
					"\n1. Tevelisores" +
					"\n2. Celulares" +
					"\n3. volver al menú anterior");
			respuesta = sc.next();
			while (!Objects.equals(respuesta, "1") && !Objects.equals(respuesta, "2") && !Objects.equals(respuesta, "3")){
				System.out.println("El numero ingresado no es correcto, rectificar por favor");
				respuesta = sc.next();
			}

			anadirProducto(respuesta, super_seleccionado);

		}

		Serializar.serializarSupermercados(lista_super);
	}

	public static void anadirProducto(String respuesta, Supermercado mercado){
		switch (respuesta){
			case "1":
				System.out.print("Ingrese el nombre del Tv:");
				String nombre = sc.next();
				System.out.print("Ingresa el precio del Tv:");
				int precio = sc.nextInt();
				System.out.print("Ingresa la marca del Tv:");
				String marca = sc.next();
				System.out.print("Ingresa el numero de pulgadas del Tv:");
				int pulgadas = sc.nextInt();
				System.out.print("Ingresa la resolucion del Tv:");
				String resolucion = sc.next();
				Tv nuevotv = new Tv(nombre, precio, marca, mercado, pulgadas, resolucion);
				mercado.oferelectro.add(nuevotv);
				lista_tvs.add(nuevotv);
				break;
			case "2":
				System.out.print("Ingrese el nombre del celular:");
				String nombrecel = sc.next();
				System.out.print("Ingresa el precio del celular:");
				int preciocel = sc.nextInt();
				System.out.print("Ingresa la marca del celular:");
				String marcacel = sc.next();
				System.out.println("Ingresa la cantidad de almacenamiento del celular:");
				int almacenamiento = sc.nextInt();
				System.out.print("Ingresa el numero de camaras del celular:");
				int camaras = sc.nextInt();
				System.out.print("Ingresa la bateria del celular:");
				int bateria = sc.nextInt();
				System.out.println("Ingresa el color del celular:");
				String color = sc.next();
				System.out.println("Ingresa el numero de megas de ram");
				int ram = sc.nextInt();
				Celular nuevocel = new Celular(nombrecel, preciocel, marcacel, mercado,almacenamiento, camaras, bateria, color, ram);
				mercado.oferelectro.add(nuevocel);
				lista_celulares.add(nuevocel);
				break;
			case "3":
				break;
		}
	}
	public static void seleccionarProducto(Supermercado mercado){

		if(mercado.oferelectro.size() > 0){
			Electronico producto_seleccionado = null;
			System.out.println("Estos son los productos electronicos que tenemos disponibles en "+mercado.getNombre());
			for(int i = 0; i<mercado.oferelectro.size(); i++) {
				producto_seleccionado = mercado.oferelectro.get(i);
				System.out.println((i+1)+". Nombre:"+producto_seleccionado.getNombre()+" " +
						"\n Marca: "+producto_seleccionado.getMarca()+"" +
						"\n Precio: "+producto_seleccionado.getPrecio());
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
