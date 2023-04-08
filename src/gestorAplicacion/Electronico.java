package src.gestorAplicacion;

import java.io.Serializable;

//Definimos la clase abstracta
public abstract class Electronico implements Serializable {
    private Supermercado supermercado;
    private String nombre;
    private int precio;

    private String marca;

    protected Electronico (String nombre, int precio, String marca, Supermercado supermercado){
        this.nombre = nombre;
        this.precio = precio;
        this.marca = marca;
        this.supermercado = supermercado;
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
}
