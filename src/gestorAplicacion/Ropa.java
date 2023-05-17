package src.gestorAplicacion;

import java.io.Serializable;
import java.util.ArrayList;

public class Ropa implements Serializable {
    private String tallaRopa;
    private String colorRopa;
    private double precioRopa;
    private String nombreRopa;
    private int cantidadRopa;
    private Supermercado mercado;
    private String tipoRopa;
    private String generoRopa;

    public Ropa(String tallaRopa, String colorRopa, double precioRopa, String nombreRopa, int cantidadRopa, Supermercado mercado, String generoRopa, String tipoRopa) {
        this.tallaRopa = tallaRopa;
        this.colorRopa = colorRopa;
        this.precioRopa = precioRopa;
        this.nombreRopa = nombreRopa;
        this.cantidadRopa = cantidadRopa;
        this.mercado = mercado;
        this.generoRopa = generoRopa;
        this.tipoRopa = tipoRopa;
    }

    public String getTallaRopa() {
        return tallaRopa;
    }

    public void setTallaRopa(String tallaRopa) {
        this.tallaRopa = tallaRopa;
    }

    public String getGeneroRopa() {
        return generoRopa;
    }

    public void setGeneroRopa(String generoRopa) {
        this.generoRopa = generoRopa;
    }

    public String getColorRopa() {
        return colorRopa;
    }

    public void setColorRopa(String colorRopa) {
        this.colorRopa = colorRopa;
    }

    public double getPrecioRopa() {
        return precioRopa;
    }

    public void setPrecioRopa(double precioRopa) {
        this.precioRopa = precioRopa;
    }

    public String getNombreRopa() {
        return nombreRopa;
    }

    public void setNombreRopa(String nombreRopa) {
        this.nombreRopa = nombreRopa;
    }

    public int getCantidadRopa() {
        return cantidadRopa;
    }

    public void setCantidadRopa(int cantidadRopa) {
        this.cantidadRopa = cantidadRopa;
    }

    public Supermercado getSupermercado() {
        return mercado;
    }

    public void setSupermercado(Supermercado supermercado) {
        this.mercado = supermercado;
    }

    public String getTipoRopa() {
        return tipoRopa;
    }

    public void setTipoRopa(String tipoRopa) {
        this.tipoRopa = tipoRopa;
    }

    public static ArrayList<Ropa> filtrarPrendas(ArrayList<Ropa> prendas, String talla, String color, String genero, String tipo) {
        ArrayList<Ropa> filtradas = new ArrayList<>();
        for (Ropa prenda : prendas) {
            if (prenda.getTallaRopa().equalsIgnoreCase(talla) &&
                prenda.getColorRopa().equalsIgnoreCase(color) &&
                prenda.getGeneroRopa().equalsIgnoreCase(genero) &&
                prenda.getTipoRopa().equalsIgnoreCase(tipo)) {
                filtradas.add(prenda);
            }
        }
        return filtradas;
    }
}
