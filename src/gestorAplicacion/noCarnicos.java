package gestorAplicacion;
import java.io.Serializable;
public class noCarnicos extends Alimentos implements Serializable{

	private String nombre;
	private int precioUnidad;

	public noCarnicos(String nombre, int precio, int numcomi, Supermercado supermercado, String nombre2, int precioUnidad) {
		super(nombre, precio, numcomi, supermercado);
		nombre = nombre2;
		this.precioUnidad = precioUnidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPrecioUnidad() {
		return precioUnidad;
	}

	public void setPrecioUnidad(int precioUnidad) {
		this.precioUnidad = precioUnidad;
	}

}
