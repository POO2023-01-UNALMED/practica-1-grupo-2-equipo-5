package src.gestorAplicacion;
public class noCarnicos extends Comida{

	private String nombre;
	private int precioUnidad;
	
	public noCarnicos(String nombre, int precio, int numcomi, String nombre2, int precioUnidad) {
		super(nombre, precio, numcomi);
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
