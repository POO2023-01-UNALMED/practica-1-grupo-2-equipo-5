package src.gestorAplicacion;
//package gestorAplicacion;

import java.util.ArrayList;

// Autor: Alejandro
//Se define un clase para el usuario Cliente
public class Cliente {
	private String nombre,direccion;
	private int saldo;
	private ArrayList<Object> carrito = new ArrayList<> ();
	
	public Cliente(String nombre, String direccion,int saldo){
		this.nombre = nombre;
		this.direccion = direccion;
		this.saldo=saldo;
	}
	public int getSaldo() {
		return saldo;
	}
	public void setSaldo(int saldo) {
		this.saldo = saldo;
	}
	

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public ArrayList<Object> getCarrito() {
		return carrito;
	}
	public void setCarrito(ArrayList<Object> carrito) {
		this.carrito = carrito;
	}

}
