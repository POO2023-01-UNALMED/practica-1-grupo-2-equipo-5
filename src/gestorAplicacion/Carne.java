package src.gestorAplicacion;

import java.io.Serializable;

public class Carne extends Alimentos implements Serializable{
	private String tipo;
	private float pesoLibra;
	//private float costoLibra;
	private int cantidad;
	private static int cantidadTotal;

	public Carne(String nombre, int precio, Supermercado supermercado , String tipo, float pesoLibra,
			int cantidad) {
		super(nombre, precio, supermercado);
		this.tipo = tipo;
		this.pesoLibra= pesoLibra;
		this.cantidad = cantidad;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public float getPesoLibra() {
		return pesoLibra;
	}

	public void setPesoLibra(float pesoLibra) {
		this.pesoLibra = pesoLibra;
	}

	/*public float getCostoLibra() {
		return costoLibra;
	}

	public void setCostoLibra(float costoLibra) {
		this.costoLibra = costoLibra;
	}*/

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
		Carne.cantidadTotal = cantidadTotal;
	}

	
	
	
}