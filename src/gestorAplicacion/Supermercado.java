package src.gestorAplicacion;
import java.io.Serializable;
import java.util.ArrayList;

public class Supermercado  implements Serializable{
	public String nombre;
	public ArrayList<Libro> oferlibros;
	public Supermercado(String nombre,ArrayList<Libro> oferlibros) {
		this.nombre=nombre;
		this.oferlibros=oferlibros;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<Libro> getOferlibros() {
		return oferlibros;
	}
	public void setOferlibros(ArrayList<Libro> oferlibros) {
		this.oferlibros = oferlibros;
	}
	
}
