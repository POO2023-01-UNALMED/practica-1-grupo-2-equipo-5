package src.gestorAplicacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Se define una clase para los objetos tipo celular
public class Celular extends Electronico implements Serializable,Comparable<Celular> {
    private int almacenamiento;
    private int camaras;
    private int bateria;
    private String color;
    private int ram;
    public Celular(String nombre, int precio,  String marca,
                   Supermercado supermercado,int cantidad,int almacenamiento, int camaras, int bateria, String color, int ram) {
        super(nombre, precio, marca, supermercado, cantidad);
        this.almacenamiento = almacenamiento;
        this.camaras = camaras;
        this.bateria = bateria;
        this.color = color;
        this.ram = ram;
    }
    
    public Celular(Celular celular,int cantidad) {
		//Un uso de this()
		this(celular.getNombre(),celular.getPrecio(),celular.getMarca(),celular.getSupermercado(),
				cantidad,celular.getAlmacenamiento(),celular.getCamaras(),celular.getBateria(),
				celular.getColor(),celular.getRam());
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

    public static ArrayList<Celular> filtroNombre(Supermercado mercado, String nombre){
        ArrayList<Celular> celulares_mercado = mercado.getOfercelular();
        ArrayList<Celular> celulares_filtrados = new ArrayList<>();
        for(Celular celular : celulares_mercado){
            if(nombre.matches(celular.getNombre().toLowerCase())){
                celulares_filtrados.add(celular);
            }
        }
        return celulares_filtrados;
    }
    public static ArrayList<Celular> filtroNombreSimilar(Supermercado mercado, String nombre){
        ArrayList<Celular> celulares_mercado = mercado.getOfercelular();
        ArrayList<Celular> celulares_filtrados = new ArrayList<>();

        // Dividir el filtro en palabras individuales
        String[] palabrasFiltro = nombre.split("\\s+");

        // Construir el patrón de búsqueda
        StringBuilder patronBuilder = new StringBuilder();
        patronBuilder.append(".*");
        for (String palabra : palabrasFiltro) {
            patronBuilder.append(".*").append(Pattern.quote(palabra));
        }
        patronBuilder.append(".*");
        Pattern pattern = Pattern.compile(patronBuilder.toString(), Pattern.CASE_INSENSITIVE);
        for (Celular celular : celulares_mercado) {
            Matcher matcher = pattern.matcher(celular.getNombre());
            if (matcher.matches()) {
                celulares_filtrados.add(celular);
            }
        }
        return celulares_filtrados;
    }

    public static ArrayList<Celular> filtroMarca(Supermercado mercado, String nombre){
        ArrayList<Celular> celulares_mercado = mercado.getOfercelular();
        ArrayList<Celular> celulares_filtrados = new ArrayList<>();
        for(Celular celular : celulares_mercado){
            if(nombre.matches(celular.getMarca().toLowerCase())){
                celulares_filtrados.add(celular);
            }
        }
        return celulares_filtrados;
    }
    public static ArrayList<Celular> filtroMarcaSimilar(Supermercado mercado, String nombre){
        ArrayList<Celular> celulares_mercado = mercado.getOfercelular();
        ArrayList<Celular> celulares_filtrados = new ArrayList<>();

        // Dividir el filtro en palabras individuales
        String[] palabrasFiltro = nombre.split("\\s+");

        // Construir el patrón de búsqueda
        StringBuilder patronBuilder = new StringBuilder();
        patronBuilder.append(".*");
        for (String palabra : palabrasFiltro) {
            patronBuilder.append(".*").append(Pattern.quote(palabra));
        }
        patronBuilder.append(".*");
        Pattern pattern = Pattern.compile(patronBuilder.toString(), Pattern.CASE_INSENSITIVE);
        for (Celular celular : celulares_mercado) {
            Matcher matcher = pattern.matcher(celular.getMarca());
            if (matcher.matches()) {
                celulares_filtrados.add(celular);
            }
        }
        return celulares_filtrados;
    }
    public static ArrayList<Celular> filtroColor(Supermercado mercado, String nombre){
        ArrayList<Celular> celulares_mercado = mercado.getOfercelular();
        ArrayList<Celular> celulares_filtrados = new ArrayList<>();

        // Dividir el filtro en palabras individuales
        String[] palabrasFiltro = nombre.split("\\s+");

        // Construir el patrón de búsqueda
        StringBuilder patronBuilder = new StringBuilder();
        patronBuilder.append(".*");
        for (String palabra : palabrasFiltro) {
            patronBuilder.append(".*").append(Pattern.quote(palabra));
        }
        patronBuilder.append(".*");
        Pattern pattern = Pattern.compile(patronBuilder.toString(), Pattern.CASE_INSENSITIVE);
        for (Celular tv : celulares_mercado) {
            Matcher matcher = pattern.matcher(tv.getColor());
            if (matcher.matches()) {
                celulares_filtrados.add(tv);
            }
        }
        return celulares_filtrados;
    }

    public static ArrayList<Celular> filtroCamaras(Supermercado mercado, int min, int max){
        ArrayList<Celular> celulares_mercado = mercado.getOfercelular();
        ArrayList<Celular> celulares_filtrados = new ArrayList<>();
        if(min > max){
            int temp = min;
            min = max;
            max = temp;
        }
        for(Celular celular: celulares_mercado){
            if(celular.getCamaras()>= min && celular.getCamaras()<= max){
                celulares_filtrados.add(celular);
            }
        }
        return celulares_filtrados;
    }

    public static ArrayList<Celular> filtroPrecio(Supermercado mercado, int min, int max){
        ArrayList<Celular> celulares_mercado = mercado.getOfercelular();
        ArrayList<Celular> celulares_filtrados = new ArrayList<>();
        if(min > max){
            int temp = min;
            min = max;
            max = temp;
        }
        for(Celular celular: celulares_mercado){
            if(celular.getPrecio()>= min && celular.getPrecio()<= max){
                celulares_filtrados.add(celular);
            }
        }
        return celulares_filtrados;
    }
    public static ArrayList<Celular> filtroAlmacenamiento(Supermercado mercado, int min, int max){
        ArrayList<Celular> celulares_mercado = mercado.getOfercelular();
        ArrayList<Celular> celulares_filtrados = new ArrayList<>();
        if(min > max){
            int temp = min;
            min = max;
            max = temp;
        }
        for(Celular celular: celulares_mercado){
            if(celular.getAlmacenamiento()>= min && celular.getAlmacenamiento()<= max){
                celulares_filtrados.add(celular);
            }
        }
        return celulares_filtrados;
    }
    public static ArrayList<Celular> filtroBateria(Supermercado mercado, int min, int max){
        ArrayList<Celular> celulares_mercado = mercado.getOfercelular();
        ArrayList<Celular> celulares_filtrados = new ArrayList<>();
        if(min > max){
            int temp = min;
            min = max;
            max = temp;
        }
        for(Celular celular: celulares_mercado){
            if(celular.getBateria()>= min && celular.getBateria()<= max){
                celulares_filtrados.add(celular);
            }
        }
        return celulares_filtrados;
    }
    public static ArrayList<Celular> filtroRam(Supermercado mercado, int min, int max){
        ArrayList<Celular> celulares_mercado = mercado.getOfercelular();
        ArrayList<Celular> celulares_filtrados = new ArrayList<>();
        if(min > max){
            int temp = min;
            min = max;
            max = temp;
        }
        for(Celular celular: celulares_mercado){
            if(celular.getRam()>= min && celular.getRam()<= max){
                celulares_filtrados.add(celular);
            }
        }
        return celulares_filtrados;
    }
    @Override
    public String toString() {
        return "Nombre: " +this.getNombre()+
                "\nMarca: " +this.getMarca()+
                "\nColor: " +this.getColor()+
                "\nNumero de cámaras: " +this.getCamaras()+
                "\nAlmacenamiento: " +this.getAlmacenamiento()+
                "\nBateria: " +this.getBateria()+
                "\nMemoria RAM: "+this.getRam()+
                "\nCantidad en stock: " +this.getCantidad()+
                "\nPrecio: "+this.getPrecio();
    }
    
    @Override
	public int compareTo(Celular o) {
		if (this.getNombre().equals(o.getNombre())) {
			return 1;
		}
		else return 0;
	}
}
