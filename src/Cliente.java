import java.util.ArrayList;
public class Cliente {
	private String nombre,dirección;
	private ArrayList<Object> carrito = new ArrayList<Object> ();
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDirección() {
		return dirección;
	}
	public void setDirección(String dirección) {
		this.dirección = dirección;
	}
	public ArrayList<Object> getCarrito() {
		return carrito;
	}
	public void setCarrito(ArrayList<Object> carrito) {
		this.carrito = carrito;
	}
	
}
