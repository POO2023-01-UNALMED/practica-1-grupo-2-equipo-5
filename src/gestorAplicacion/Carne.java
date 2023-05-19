package src.gestorAplicacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public class Carne extends Alimentos implements Serializable,Comparable<Carne>{
	private String tipo;
	private float pesoLibra;
	private static int cantidadTotal;

	public Carne(String nombre, int precio, Supermercado supermercado, int cantidad, String tipo, float pesoLibra) {
		super(nombre, precio, supermercado, cantidad);
		this.tipo = tipo;
		this.pesoLibra= pesoLibra;
	}
	
	public Carne(Carne carne,int cantidad) {
		this(carne.getNombre(),carne.getPrecio(),carne.getSupermercado(),
				cantidad,carne.getTipo(),carne.getPesoLibra());
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public float getPesoLibra() {
		return pesoLibra;
	}

	public void setPesoLibra(float pesoLibra) {
		this.pesoLibra = pesoLibra;
	}


	public static int getCantidadTotal() {
		return cantidadTotal;
	}

	public static void setCantidadTotal(int cantidadTotal) {
		Carne.cantidadTotal = cantidadTotal;
	}

	@Override
	public String oferta() {
	return
	             "\nNombre: "+getNombre()
	            +"\nPrecio por libra: "+getPrecio()
	            +"\nTipo de carne: "+getTipo()
	            +"\nLibras por unidad: "+getPesoLibra()
	            +"\nUnidades en stock: "+getCantidad();
	}
	
	public static Object[] listaTipos(ArrayList<Carne> carnes) {
		HashSet<String> meat = new HashSet<>();
		Object[] listaTipo;
		for(Carne tipos:carnes) {
			meat.add(tipos.getTipo());
		}
		listaTipo=meat.toArray();
		return listaTipo;
	}
	
	public static ArrayList<Carne> filtroTipo(ArrayList<Carne> carnes,String filtro) {
		ArrayList<Carne> meat = new ArrayList<Carne>();
		for(Carne X:carnes) {
			if(X.getTipo().equals(filtro)) {
				meat.add(X);
			}
		}
		return meat;

	}
	
	@Override
	public int compareTo(Carne o) {
		if (this.getNombre().equals(o.getNombre())) {
			return 1;
		}
		else return 0;
	}
	
}