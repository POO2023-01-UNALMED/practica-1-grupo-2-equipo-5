package src.gestorAplicacion;
// Se define la clase abstracta	
public abstract class Comida {	
	
	private String nombre;
	private int precio;
	private int numcomi;
	
	protected Comida (String nombre, int precio, int numcomi) {
		this.nombre = nombre;
		this.precio = precio;
		this.numcomi = numcomi;
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
