package src.gestorAplicacion;
//Definimos la clase abstracta
public abstract class Electronico {
    private String nombre;
    private int precio;
    private  int numelectro;
    private String marca;

    protected Electronico (String nombre, int precio, int numelectro, String marca){
        this.nombre = nombre;
        this.precio = precio;
        this.numelectro = numelectro;
        this.marca = marca;
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

    public int getNumelectro() {
        return numelectro;
    }

    public void setNumelectro(int numelectro) {
        this.numelectro = numelectro;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
}
