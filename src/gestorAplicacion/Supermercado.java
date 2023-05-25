package src.gestorAplicacion;

import src.baseDatos.Serializar;
import src.uiMain.Main;
import java.io.Serializable;
import java.util.ArrayList;

// Autor: Santiago
// Esta clase es para los objetos Supermercado
public class Supermercado  implements Serializable{
	private String nombre;
	
	private ArrayList<Libro> oferlibros = new ArrayList<Libro>();
	private ArrayList<Carne> ofercarne = new ArrayList<>();
	private ArrayList<Alimentos> ofercomi = new ArrayList<Alimentos>();
	private ArrayList<noCarnicos> ofernocarnicos = new ArrayList<>();
	private ArrayList<Tv> ofertv = new ArrayList<>();
	private ArrayList<Celular> ofercelular = new ArrayList<>();
	private ArrayList<Ropa> oferropa = new ArrayList<>();
	
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
	
	public ArrayList<Ropa> getOferropa() {
	    return oferropa;
	}

	public void setOferropa(ArrayList<Ropa> oferropa) {
	    this.oferropa = oferropa;
	}
	
	public ArrayList<Tv> getOfertv() {
		return ofertv;
	}
	public void setOfertv(ArrayList<Tv> ofertv) {
		this.ofertv = ofertv;
	}

	public ArrayList<Celular> getOfercelular() {
		return ofercelular;
	}
	public void setOfercelular(ArrayList<Celular> ofercelular) {
		this.ofercelular = ofercelular;
	}
	
	public ArrayList<Carne> getOfercarne() {
		return ofercarne;
	}
	public void setOfercarne(ArrayList<Carne> ofercarne) {
		this.ofercarne = ofercarne;
	}
	public ArrayList<noCarnicos> getOfernocarnicos() {
		return ofernocarnicos;
	}
	public void setOfernocarnicos(ArrayList<noCarnicos> ofernocarnicos) {
		this.ofernocarnicos = ofernocarnicos;
	}
	public ArrayList<Alimentos> getOfercomi() {
		return ofercomi;
	}
	public void setOfercomi(ArrayList<Alimentos> ofercomi) {
		this.ofercomi = ofercomi;
	}
	@Override
	public String toString() {
		return ""+this.nombre.toUpperCase();
	}
	
}
