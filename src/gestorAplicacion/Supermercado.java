package src.gestorAplicacion;

import src.baseDatos.Serializar;
import src.uiMain.Main;
import java.io.Serializable;
import java.util.ArrayList;

public class Supermercado  implements Serializable{
	public String nombre;
	
	public ArrayList<Libro> oferlibros = new ArrayList<Libro>();
	public ArrayList<Carne> ofercarne = new ArrayList<>();
	public ArrayList<Alimentos> ofercomi = new ArrayList<Alimentos>();
	public ArrayList<noCarnicos> ofernocarnicos = new ArrayList<>();
	public ArrayList<Tv> ofertv = new ArrayList<>();
	public ArrayList<Celular> ofercelular = new ArrayList<>();
	
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
	@Override
	public String toString() {
		return "Supermercado "+this.nombre.toUpperCase();
	}
}
