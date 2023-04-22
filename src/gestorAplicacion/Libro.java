package src.gestorAplicacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

import src.uiMain.Main;

public class Libro implements Serializable{
	private Supermercado supermercado;
	private String titulo,autor,descripcion,isbn;
	private int precio;
	static int numlibros;
	public Libro(String titulo,String autor,String descripcion, String isbn,int precio, Supermercado supermercado){
		this.titulo=titulo;
		this.autor=autor;
		this.descripcion= descripcion;
		this.isbn=isbn;
		this.precio=precio;
		this.supermercado = supermercado;
		Main.lista_libros.add(this);
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public static int getNumlibros() {
		return numlibros;
	}
	public static void setNumlibros(int numlibros) {
		Libro.numlibros = numlibros;
	}
	
	public String toString() {
		return this.titulo+" Libro "+this.supermercado.getNombre()+" "+this.precio;
	}
	
	public static ArrayList<Libro> filtrarPorAutor(ArrayList<Libro> Libros,String filtro) {
		ArrayList<Libro> libros = new ArrayList<Libro>();
		for(Libro libro:Libros) {
			if(libro.getAutor().equals(filtro)) {
				libros.add(libro);
			}
		}
		return libros;

	}
	
	public static ArrayList<Libro> filtrarPorPrecio(ArrayList<Libro> Libros,int premin,int premax){
		ArrayList<Libro> libros = new ArrayList<Libro>();
		for(Libro libro:Libros) {
			if(libro.getPrecio()>=premin && libro.getPrecio()<=premax) {
				libros.add(libro);
			}
		}
		return libros;
	}
	
	public static Object[] listaAutores(ArrayList<Libro> Libros) {
		HashSet<String> Autores = new HashSet<>();
		Object[] lstautores;
		for(Libro libro:Libros) {
			Autores.add(libro.getAutor());
		}
		lstautores=Autores.toArray();
		return lstautores;
	}
}
