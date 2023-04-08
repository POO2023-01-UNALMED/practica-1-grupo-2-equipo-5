package gestorAplicacion;
import java.io.Serializable;
// Se define la clase abstracta
public abstract class Alimentos implements Serializable {
	
	private Supermercado supermercado;
	private String nombre;
	private int precio;
	private static int cantidadAlimentos;

	protected Alimentos (String nombre, int precio, Supermercado supermercado) {
		this.nombre = nombre;
		this.precio = precio;
		this.supermercado = supermercado;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}


	public Supermercado getSupermercado() {
		return supermercado;
	}


	public void setSupermercado(Supermercado supermercado) {
		this.supermercado = supermercado;
	}


	public static int getCantidadAlimentos() {
		return cantidadAlimentos;
	}


	public static void setCantidadAlimentos(int cantidadAlimentos) {
		Alimentos.cantidadAlimentos = cantidadAlimentos;
	}
	
}
