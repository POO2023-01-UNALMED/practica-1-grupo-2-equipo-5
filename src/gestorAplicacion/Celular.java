package gestorAplicacion;
import java.io.Serializable;
public class Celular extends Electronico implements Serializable {
    private int almacenamiento;
    private int camaras;
    private int bateria;
    private String color;
    private int ram;
    public Celular(String nombre, int precio,  String marca,
                   Supermercado supermercado, int almacenamiento, int camaras, int bateria, String color, int ram) {
        super(nombre, precio, marca, supermercado);
        this.almacenamiento = almacenamiento;
        this.camaras = camaras;
        this.bateria = bateria;
        this.color = color;
        this.ram = ram;
    }

    public int getAlmacenamiento() {
        return almacenamiento;
    }

    public void setAlmacenamiento(int almacenamiento) {
        this.almacenamiento = almacenamiento;
    }

    public int getCamaras() {
        return camaras;
    }

    public void setCamaras(int camaras) {
        this.camaras = camaras;
    }

    public int getBateria() {
        return bateria;
    }

    public void setBateria(int bateria) {
        this.bateria = bateria;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }
}
