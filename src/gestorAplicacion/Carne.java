package src.gestorAplicacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

//Autor: Ruben
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
	
//<<<<<<< Updated upstream
	@Override
	public int compareTo(Carne o) {
		if (this.getNombre().equals(o.getNombre())) {
			return 1;
		}
		else return 0;
	}
//=======
	public static ArrayList<Carne> convertirCarne(ArrayList<?> lista) {
        ArrayList<Carne> listaC = new ArrayList<>();
        for (Object elemento : lista) {
            if (elemento instanceof Carne) {
                Carne carnes = (Carne) elemento;
                listaC.add(carnes);
            }
        }
        return listaC;
    }
	
	public static ArrayList<Alimentos> filPrecioAli(Supermercado mercado, int min, int max){
        ArrayList<Alimentos> alimentos = mercado.getOfercomi();
        ArrayList<Alimentos> AlimentosFil = new ArrayList<>();
        if(min > max){
            int temp = min;
            min = max;
            max = temp;
        }
        for(Alimentos Comi: alimentos){
            if(Comi.getPrecio()>= min && Comi.getPrecio()<= max){
            	AlimentosFil.add(Comi);
            }
        }
        return AlimentosFil;
    }
//>>>>>>> Stashed changes
	
}