package src.gestorAplicacion;

import java.util.ArrayList;
public class Cliente {
	private String nombre,direccion;
	private ArrayList<Object> carrito = new ArrayList<Object> ();
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
