package src.gestorAplicacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

// Autor: Ruben
//Esta clase se define para los alimentos no carnicos
public class noCarnicos extends Alimentos implements Serializable,Comparable<noCarnicos>{

	private static int cantidadTotal; 
	private String grupo;

	public noCarnicos(String nombre, int precio, Supermercado supermercado, int cantidad, String grupo) {
		super(nombre, precio, supermercado, cantidad);
		this.grupo = grupo;
	}
	
	public noCarnicos(noCarnicos nocarne,int cantidad) {
		this(nocarne.getNombre(),nocarne.getPrecio(),nocarne.getSupermercado(),cantidad,nocarne.getGrupo());
	}


	public static int getCantidadTotal() {
		return cantidadTotal;
	}

	public static void setCantidadTotal(int cantidadTotal) {
		noCarnicos.cantidadTotal = cantidadTotal;
	}
	

	public String getGrupo() {
		return grupo;
	}


	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}


	@Override
	public String oferta() {
		return
	             "\nNombre: "+getNombre()
	             +"\nClasificacion: "+getGrupo()
	            +"\nPrecio por libra: "+getPrecio()
	            +"\nUnidades en stock: "+getCantidad();
	}
	
	public static String validarGrupo(String grupo) {
		
		if (grupo.equals("1")){
			grupo = "Grano";
		}else if(grupo.equals("2")){
			grupo = "Lacteos";
		}else if(grupo.equals("3")){
			grupo = "Vegetal";
		}else if(grupo.equals("4")){
			grupo =  "Otro";
		}
		return grupo;
	}
//<<<<<<< Updated upstream
	
	@Override
	public int compareTo(noCarnicos o) {
		if (this.getNombre().equals(o.getNombre())) {
			return 1;
		}
		else return 0;
	}
//=======
//>>>>>>> Stashed changes
	
	
	public static ArrayList<noCarnicos> listaCatergoria(ArrayList<noCarnicos> nocarnes,String filtro) {
		ArrayList<noCarnicos> nomeat = new ArrayList<noCarnicos>();
		for(noCarnicos X:nocarnes) {
			if(X.getGrupo().equals(filtro)) {
				nomeat.add(X);
			}
		}
		return nomeat;
	}
	
	public static ArrayList<noCarnicos> convertirnoCarne(ArrayList<?> lista) {
        ArrayList<noCarnicos> listaN = new ArrayList<>();
        for (Object elemento : lista) {
            if (elemento instanceof noCarnicos) {
            	noCarnicos nocarnes = (noCarnicos) elemento;
                listaN.add(nocarnes);
            }
        }
        return listaN;
    }
	
	public static ArrayList<Alimentos> convertirAlimentos(ArrayList<?> lista) {
        ArrayList<Alimentos> alimentos = new ArrayList<>();
        for (Object elemento : lista) {
            if (elemento instanceof Alimentos) {
            	Alimentos Ali = (Alimentos) elemento;
               alimentos.add(Ali);
            }
        }
        return alimentos;
    }
}
