package src.gestorAplicacion;
import java.io.Serializable;
import java.util.ArrayList;

//Autor: Yiduar
// Esta clase define los objetos clase ropa
public class Ropa implements Serializable, Comparable<Ropa> {
    private String tallaRopa;
    private String colorRopa;
    private int precioRopa;
    private String nombreRopa;
    private int cantidadRopa;
    private Supermercado mercado;
    private String tipoRopa;
    private String generoRopa;
    private boolean enOferta;

    public Ropa(String tallaRopa, String colorRopa, int precioRopa, String nombreRopa, int cantidadRopa, Supermercado mercado, String generoRopa, String tipoRopa, boolean enOferta) {
        this.tallaRopa = tallaRopa;
        this.colorRopa = colorRopa;
        this.precioRopa = precioRopa;
        this.nombreRopa = nombreRopa;
        this.cantidadRopa = cantidadRopa;
        this.mercado = mercado;
        this.generoRopa = generoRopa;
        this.tipoRopa = tipoRopa;
        this.enOferta = enOferta;
    }
    
    public Ropa(Ropa ropa, int cantidad) {
        this(ropa.getTallaRopa(), ropa.getColorRopa(), ropa.getPrecioRopa(), ropa.getNombreRopa(),
                cantidad, ropa.getSupermercado(), ropa.getGeneroRopa(), ropa.getTipoRopa(), ropa.isEnOferta());
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

    public int getPrecioRopa() {
        return precioRopa;
    }

    public void setPrecioRopa(int precioRopa) {
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
    
    public boolean isEnOferta() {
        return enOferta;
    }

    public void setEnOferta(boolean enOferta) {
        this.enOferta = enOferta;
    }

    public static ArrayList<Ropa> filtrarPrendas(ArrayList<Ropa> prendas, String talla, String color, String genero, String tipo, boolean enOferta) {
        ArrayList<Ropa> filtradas = new ArrayList<>();
        for (Ropa prenda : prendas) {
            if (prenda.getTallaRopa().equalsIgnoreCase(talla) &&
                prenda.getColorRopa().equalsIgnoreCase(color) &&
                prenda.getGeneroRopa().equalsIgnoreCase(genero) &&
                prenda.getTipoRopa().equalsIgnoreCase(tipo) &&
                prenda.isEnOferta() == enOferta) {
                filtradas.add(prenda);
            }
        }
        return filtradas;
    }

    @Override
    public int compareTo(Ropa o) {
        if (this.getNombreRopa().equals(o.getNombreRopa())) {
            return 1;
        } else {
            return 0;
        }
    }
}

