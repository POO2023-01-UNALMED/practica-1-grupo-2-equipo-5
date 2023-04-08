package gestorAplicacion;
import java.io.Serializable;
public class noCarnicos extends Alimentos implements Serializable{

	private String nombreAli;
	private int precioUnidad;

	public noCarnicos(String nombre, int precio, Supermercado supermercado, String nombreAli, int precioUnidad) {
		super(nombre, precio, supermercado);
		this.nombreAli = nombreAli;
		this.precioUnidad = precioUnidad;
	}

	public String getNombreAli() {
		return nombreAli;
	}

	public void setNombreAli(String nombreAli) {
		this.nombreAli = nombreAli;
	}

	public int getPrecioUnidad() {
		return precioUnidad;
	}

	public void setPrecioUnidad(int precioUnidad) {
		this.precioUnidad = precioUnidad;
	}

}
