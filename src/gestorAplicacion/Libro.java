package gestorAplicacion;
import java.io.Serializable;
import uiMain.Main;
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

}
