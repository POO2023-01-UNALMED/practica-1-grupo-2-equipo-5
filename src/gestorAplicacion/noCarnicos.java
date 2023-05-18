package src.gestorAplicacion;

import java.io.Serializable;
import java.util.ArrayList;

public class noCarnicos extends Alimentos implements Serializable{

	private static int cantidadTotal; 
	private String grupo;

	public noCarnicos(String nombre, int precio, Supermercado supermercado, int cantidad, String grupo) {
		super(nombre, precio, supermercado, cantidad);
		this.grupo = grupo;
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

	
}
