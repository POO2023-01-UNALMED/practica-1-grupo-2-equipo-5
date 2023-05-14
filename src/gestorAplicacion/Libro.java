package src.gestorAplicacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

import src.uiMain.Main;

public class Libro implements Serializable,Cloneable{
	private Supermercado supermercado;
	private String titulo,autor,descripcion,isbn;
	private int precio,cantidad;
	static int numlibros;
	public Libro(String titulo,String autor,String descripcion, String isbn,int precio,int cantidad, Supermercado supermercado){
		this.titulo=titulo;
		this.autor=autor;
		this.descripcion= descripcion;
		this.isbn=isbn;
		this.precio=precio;
		this.supermercado = supermercado;
		this.cantidad=cantidad;
		Main.lista_libros.add(this);
	}
	public Libro(Libro libro) {
		this.cantidad=1;
		this.titulo=libro.getTitulo();
		this.autor=libro.getAutor();
		this.descripcion= libro.getDescripcion();
		this.isbn=libro.getIsbn();
		this.precio=libro.getPrecio();
		this.supermercado = libro.getSupermercado();
	}
	public Supermercado getSupermercado() {
		return supermercado;
	}
	public void setSupermercado(Supermercado supermercado) {
		this.supermercado = supermercado;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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
	
	public static boolean samebook(Libro libro1, Libro libro2) {
		if (libro1.getTitulo().equals(libro2.getTitulo())) {
			return true;
		}
		else return false;
	}
}
