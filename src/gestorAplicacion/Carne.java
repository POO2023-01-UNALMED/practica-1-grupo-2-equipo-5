package src.gestorAplicacion;

import java.io.Serializable;

public class Carne extends Alimentos implements Serializable{
	private String tipo;
	private float pesoLibra;
	private float costoLibra;

	public Carne(String nombre, int precio, Supermercado supermercado , String tipo, float pesoLibra, float costoLibra) {
		super(nombre, precio, supermercado);
		this.tipo = tipo;
		this.pesoLibra= pesoLibra;
		this.costoLibra = costoLibra;
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

	public void setLbs(float pesoLibra) {
		this.pesoLibra = pesoLibra;
	}

	public float getCostoLibra() {
		return costoLibra;
	}

	public void setCostoLibra(float costoLibra) {
		this.costoLibra = costoLibra;
	}
}