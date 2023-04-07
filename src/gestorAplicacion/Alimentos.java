package gestorAplicacion;
import java.io.Serializable;
// Se define la clase abstracta
public abstract class Alimentos implements Serializable {
	private Supermercado supermercado;
	private String nombre;
	private int precio;
	private int numcomi;

	protected Alimentos (String nombre, int precio, int numcomi, Supermercado supermercado) {
		this.nombre = nombre;
		this.precio = precio;
		this.numcomi = numcomi;
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
}
