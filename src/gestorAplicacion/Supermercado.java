package src.gestorAplicacion;

import src.baseDatos.Serializar;
import src.uiMain.Main;
import java.io.Serializable;
import java.util.ArrayList;

public class Supermercado  implements Serializable{
	public String nombre;
	
	public ArrayList<Libro> oferlibros = new ArrayList<Libro>();
	public ArrayList<Alimentos> ofercomi = new ArrayList<Alimentos>();
	public ArrayList<Electronico> oferelectro = new ArrayList<Electronico>();
	
	public Supermercado(String nombre){
		this.nombre = nombre;
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
	
	public ArrayList<Alimentos> getOfercomi() {
		return ofercomi;
	}
	public void setOfercomi(ArrayList<Alimentos> ofercomi) {
		this.ofercomi = ofercomi;
	}
	public ArrayList<Electronico> getOferelectro() {
		return oferelectro;
	}
	public void setOferelectro(ArrayList<Electronico> oferelectro) {
		this.oferelectro = oferelectro;
	}
	
	@Override
	public String toString() {
		return "Supermercado de nombre: "+this.nombre;
	}
}
