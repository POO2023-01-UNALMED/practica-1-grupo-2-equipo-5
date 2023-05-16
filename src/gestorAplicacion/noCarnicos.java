package src.gestorAplicacion;

import java.io.Serializable;

public class noCarnicos extends Alimentos implements Serializable{

	private static int cantidadTotal; 

	public noCarnicos(String nombre, int precio, Supermercado supermercado, int cantidad) {
		super(nombre, precio, supermercado, cantidad);
	}


	public static int getCantidadTotal() {
		return cantidadTotal;
	}

	public static void setCantidadTotal(int cantidadTotal) {
		noCarnicos.cantidadTotal = cantidadTotal;
	}

	@Override
	public void oferta() {
		System.out.println(
	             "\nNombre: "+getNombre()
	            +"\nPrecio por libra: "+getPrecio()
	            +"\nUnidades en stock: "+getCantidad());
	}

}
