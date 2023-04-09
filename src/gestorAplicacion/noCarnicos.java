package src.gestorAplicacion;

import java.io.Serializable;

public class noCarnicos extends Alimentos implements Serializable{

	//private String nombreAli;
	private int cantidad;
	private static int cantidadTotal;

	public noCarnicos(String nombre, int precio, Supermercado supermercado, int cantidad) {
		super(nombre, precio, supermercado);
		this.cantidad = cantidad;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public static int getCantidadTotal() {
		return cantidadTotal;
	}

	public static void setCantidadTotal(int cantidadTotal) {
		noCarnicos.cantidadTotal = cantidadTotal;
	}


}
