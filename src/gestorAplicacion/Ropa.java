package src.gestorAplicacion;

import java.io.Serializable;
import java.util.ArrayList;

public class Ropa implements Serializable {
    private String talla;
    private String color;
    private double precio;
    private String nombre;
    private int numPrendas;
    private Supermercado supermercado;
    private String genero;
    private String tipo;

    public Ropa(String talla, String color, double precio, String nombre, int numPrendas, Supermercado supermercado, String genero) {
        this.talla = talla;
        this.color = color;
        this.precio = precio;
        this.nombre = nombre;
        this.numPrendas = numPrendas;
        this.supermercado = supermercado;
        this.genero = genero;
    }


    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }
    
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumPrendas() {
        return numPrendas;
    }

    public void setNumPrendas(int numPrendas) {
        this.numPrendas = numPrendas;
    }

    public Supermercado getSupermercado() {
        return supermercado;
    }

    public void setSupermercado(Supermercado supermercado) {
        this.supermercado = supermercado;
    }

	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

    public static ArrayList<Ropa> filtrarPorTalla(ArrayList<Ropa> prendas, String talla) {
        ArrayList<Ropa> filtradas = new ArrayList<>();
        for (Ropa ropa : prendas) {
            if (ropa.getTalla().equalsIgnoreCase(talla)) {
                filtradas.add(ropa);
            }
        }
        return filtradas;
    }

    public static ArrayList<Ropa> filtrarPorColor(ArrayList<Ropa> prendas, String color) {
        ArrayList<Ropa> filtradas = new ArrayList<>();
        for (Ropa ropa : prendas) {
            if (ropa.getColor().equalsIgnoreCase(color)) {
                filtradas.add(ropa);
            }
        }
        return filtradas;
    }
    
    public static ArrayList<Ropa> filtrarPorGenero(ArrayList<Ropa> prendas, String genero) {
        ArrayList<Ropa> filtradas = new ArrayList<>();
        for (Ropa ropa : prendas) {
            if (ropa.getGenero().equalsIgnoreCase(genero)) {
                filtradas.add(ropa);
            }
        }
        return filtradas;
    }
    
    public static ArrayList<Ropa> filtrarPorTipo(ArrayList<Ropa> prendas, String tipo) {
        ArrayList<Ropa> filtradas = new ArrayList<>();
        for (Ropa ropa : prendas) {
            if (ropa.getTipo().equalsIgnoreCase(tipo)) {
                filtradas.add(ropa);
            }
        }
        return filtradas;
    }


}
