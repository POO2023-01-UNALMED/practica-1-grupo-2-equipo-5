package src.gestorAplicacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tv extends Electronico implements Serializable{
    private int pulgadas;
    private String resolucion;
 

    public Tv(String nombre, int precio, String marca, Supermercado supermercado, int cantidad,
              int pulgadas, String resolucion) {
        super(nombre, precio, marca, supermercado, cantidad);
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

    public static ArrayList<Tv> filtroNombre(Supermercado mercado, String nombre){
        ArrayList<Tv> tvs_mercado = mercado.getOfertv();
        ArrayList<Tv> tvs_filtrados = new ArrayList<>();
        for(Tv tv : tvs_mercado){
            if(nombre.matches(tv.getNombre().toLowerCase())){
                tvs_filtrados.add(tv);
            }
        }
        return tvs_filtrados;
    }
     public static ArrayList<Tv> filtroNombreSimilar(Supermercado mercado, String nombre){
        ArrayList<Tv> tvs_mercado = mercado.getOfertv();
        ArrayList<Tv> tvs_filtrados = new ArrayList<>();

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
         for (Tv tv : tvs_mercado) {
             Matcher matcher = pattern.matcher(tv.getNombre());
             if (matcher.matches()) {
                 tvs_filtrados.add(tv);
             }
         }
         return tvs_filtrados;
     }

    public static ArrayList<Tv> filtroMarca(Supermercado mercado, String nombre){
        ArrayList<Tv> tvs_mercado = mercado.getOfertv();
        ArrayList<Tv> tvs_filtrados = new ArrayList<>();
        for(Tv tv : tvs_mercado){
            if(nombre.matches(tv.getMarca().toLowerCase())){
                tvs_filtrados.add(tv);
            }
        }
        return tvs_filtrados;
    }
    public static ArrayList<Tv> filtroMarcaSimilar(Supermercado mercado, String nombre){
        ArrayList<Tv> tvs_mercado = mercado.getOfertv();
        ArrayList<Tv> tvs_filtrados = new ArrayList<>();

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
        for (Tv tv : tvs_mercado) {
            Matcher matcher = pattern.matcher(tv.getMarca());
            if (matcher.matches()) {
                tvs_filtrados.add(tv);
            }
        }
        return tvs_filtrados;
    }

    public static ArrayList<Tv> filtroResolucion(Supermercado mercado, String nombre){
        ArrayList<Tv> tvs_mercado = mercado.getOfertv();
        ArrayList<Tv> tvs_filtrados = new ArrayList<>();
        for(Tv tv : tvs_mercado){
            if(nombre.matches(tv.getResolucion().toLowerCase())){
                tvs_filtrados.add(tv);
            }
        }
        return tvs_filtrados;
    }
    public static ArrayList<Tv> filtroResolucionSimilar(Supermercado mercado, String nombre){
        ArrayList<Tv> tvs_mercado = mercado.getOfertv();
        ArrayList<Tv> tvs_filtrados = new ArrayList<>();

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
        for (Tv tv : tvs_mercado) {
            Matcher matcher = pattern.matcher(tv.getResolucion());
            if (matcher.matches()) {
                tvs_filtrados.add(tv);
            }
        }
        return tvs_filtrados;
    }

    public static ArrayList<Tv> filtroPulgadas(Supermercado mercado, int min, int max){
        ArrayList<Tv> tvs_mercado = mercado.getOfertv();
        ArrayList<Tv> tvs_filtrados = new ArrayList<>();
        if(min > max){
            int temp = min;
            min = max;
            max = temp;
        }
        for(Tv televisor: tvs_mercado){
            if(televisor.getPulgadas()>= min && televisor.getPulgadas()<= max){
                tvs_filtrados.add(televisor);
            }
        }
        return tvs_filtrados;
    }

    public static ArrayList<Tv> filtroPrecio(Supermercado mercado, int min, int max){
        ArrayList<Tv> tvs_mercado = mercado.getOfertv();
        ArrayList<Tv> tvs_filtrados = new ArrayList<>();
        if(min > max){
            int temp = min;
            min = max;
            max = temp;
        }
        for(Tv televisor: tvs_mercado){
            if(televisor.getPrecio()>= min && televisor.getPrecio()<= max){
                tvs_filtrados.add(televisor);
            }
        }
        return tvs_filtrados;
    }
    @Override
    public String toString() {
        return "Nombre: " +this.getNombre()+
                "\nMarca: " +this.getMarca()+
                "\nPulgadas: " +this.getPulgadas()+
                "\nResolucion: " +this.getResolucion()+
                "\nCantidad en stock: " +this.getCantidad()+
                "\nPrecio: "+this.getPrecio();
     }
}
