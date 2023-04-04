package src.gestorAplicacion;

public class Libro{
	private String titulo,autor,descripcion,isbn;
	private int precio;
	static int numlibros;
	Libro(String titulo,String autor,String isbn,int precio){
		this.titulo=titulo;
		this.autor=autor;
		this.isbn=isbn;
		this.precio=precio;
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
	public int getNumlibros() {
		return numlibros;
	}
	public void setNumlibros(int numlibros) {
		this.numlibros = numlibros;
	}

}
