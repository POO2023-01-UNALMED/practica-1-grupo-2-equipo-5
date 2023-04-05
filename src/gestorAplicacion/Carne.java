package src.gestorAplicacion;
public class Carne extends Comida {
	private String tipo;
	private float lbs;
	private float $libra;
	
	public Carne(String nombre, int precio, int numcomi, String tipo, float lbs, float $libra) {
		super(nombre, precio, numcomi);
		this.tipo = tipo;
		this.lbs = lbs;
		this.$libra = $libra;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public float getLbs() {
		return lbs;
	}

	public void setLbs(float lbs) {
		this.lbs = lbs;
	}

	public float get$libra() {
		return $libra;
	}

	public void set$libra(float $libra) {
		this.$libra = $libra;
	}

}
