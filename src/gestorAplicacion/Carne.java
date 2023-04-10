package src.gestorAplicacion;

import java.io.Serializable;

public class Carne extends Alimentos implements Serializable{
	private String tipo;
	private float pesoLibra;
	private static int cantidadTotal;

	public Carne(String nombre, int precio, Supermercado supermercado, int cantidad, String tipo, float pesoLibra) {
		super(nombre, precio, supermercado, cantidad);
		this.tipo = tipo;
		this.pesoLibra= pesoLibra;
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


	public static int getCantidadTotal() {
		return cantidadTotal;
	}

	public static void setCantidadTotal(int cantidadTotal) {
		Carne.cantidadTotal = cantidadTotal;
	}

	
	
	
}