package src.gestorAplicacion;
//package gestorAplicacion;

import java.util.ArrayList;
public class Cliente {
	private String nombre,direccion;
	public Cliente(String nombre, String direccion){
		this.nombre = nombre;
		this.direccion = direccion;
	}
	private ArrayList<Object> carrito = new ArrayList<Object> ();
	public Cliente (String nombre,String direccion) {
		this.nombre=nombre;
		this.direccion=direccion;
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
