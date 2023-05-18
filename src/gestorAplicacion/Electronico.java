package src.gestorAplicacion;

import java.io.Serializable;
import java.util.ArrayList;

//Definimos la clase abstracta
public abstract class Electronico implements Serializable {
    private Supermercado supermercado;
    private String nombre;
    private int precio;
    private  int cantidad;

    private String marca;

    protected Electronico (String nombre, int precio, String marca, Supermercado supermercado, int cantidad){
        this.nombre = nombre;
        this.precio = precio;
        this.marca = marca;
        this.supermercado = supermercado;
        this.cantidad = cantidad;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    public Supermercado getSupermercado(){
        return this.supermercado;
    }
    public void setSupermercado(Supermercado supermercado){
        this.supermercado = supermercado;
    }

	public  int getCantidad() {
		return cantidad;
	}

	public  void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


    
    
}
