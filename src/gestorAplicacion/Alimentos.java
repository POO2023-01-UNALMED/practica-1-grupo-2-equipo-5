package src.gestorAplicacion;

import java.io.Serializable;
import java.util.ArrayList;

// Autor: Ruben
// Se define una clase para los objetos de tipo alimento
public abstract class Alimentos implements Serializable {
	
	private Supermercado supermercado;
	private String nombre;
	private int precio;
	private int cantidad;
	private static int cantidadAlimentos;

	protected Alimentos (String nombre, int precio, Supermercado supermercado, int cantidad) {
		this.nombre = nombre;
		this.precio = precio;
		this.supermercado = supermercado;
		this.cantidad = cantidad;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}


	public Supermercado getSupermercado() {
		return supermercado;
	}


	public void setSupermercado(Supermercado supermercado) {
		this.supermercado = supermercado;
	}


	public static int getCantidadAlimentos() {
		return cantidadAlimentos;
	}


	public static void setCantidadAlimentos(int cantidadAlimentos) {
		Alimentos.cantidadAlimentos = cantidadAlimentos;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	public abstract String oferta();//Metodo abstracto
	
}
