package src.gestorAplicacion;
import java.io.Serializable;
public class Tv extends Electronico implements Serializable{
    private int pulgadas;
    private String resolucion;

    public Tv(String nombre, int precio, int numelectro, String marca, int pulgadas, String resolucion) {
        super(nombre, precio, numelectro, marca);
        this.pulgadas = pulgadas;
        this.resolucion = resolucion;
    }

    public int getPulgadas() {
        return pulgadas;
    }

    public void setPulgadas(int pulgadas) {
        this.pulgadas = pulgadas;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }
}
