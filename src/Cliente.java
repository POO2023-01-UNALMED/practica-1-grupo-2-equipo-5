import java.util.ArrayList;
public class Cliente {
	private String nombre,direcci�n;
	private ArrayList<Object> carrito = new ArrayList<Object> ();
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDirecci�n() {
		return direcci�n;
	}
	public void setDirecci�n(String direcci�n) {
		this.direcci�n = direcci�n;
	}
	public ArrayList<Object> getCarrito() {
		return carrito;
	}
	public void setCarrito(ArrayList<Object> carrito) {
		this.carrito = carrito;
	}
	
}
