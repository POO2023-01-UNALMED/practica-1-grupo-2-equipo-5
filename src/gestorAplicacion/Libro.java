package src.gestorAplicacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

import src.uiMain.Main;
// Autor: Alejandro
// Esta clase se define para los objetos tipo libro
public class Libro implements Serializable,Comparable<Libro>{
	private Supermercado supermercado;
	private String titulo,autor,descripcion,isbn;
	private int precio,cantidad;
	public Libro(String titulo,String autor,String descripcion, String isbn,int precio,int cantidad, Supermercado supermercado){
		this.titulo=titulo;
		this.autor=autor;
		this.descripcion= descripcion;
		this.isbn=isbn;
		this.precio=precio;
		this.supermercado = supermercado;
		this.cantidad=cantidad;
	}
	public Libro(Libro libro,int cantidad) {
		//Un uso de this()
		this(libro.getTitulo(),libro.getAutor(),libro.getDescripcion(),libro.getIsbn(),libro.getPrecio(),cantidad,libro.getSupermercado());
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
	
	 @Override
	public int compareTo(Libro o) {
		if (this.getTitulo().equals(o.getTitulo())) {
			return 1;
		}
		else return 0;
	}

}
