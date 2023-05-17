package src.baseDatos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import src.gestorAplicacion.*;

public class Serializar {
    static File archivo = new File("");
    
    public static void serializarnoCarnicos(ArrayList<noCarnicos> lista) {
        try {
            FileOutputStream f = new FileOutputStream(new File(archivo.getAbsolutePath()+
                    "\\src\\baseDatos\\temp\\noCarnicos.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(lista);

            o.close();
            f.close();

        }catch(FileNotFoundException e){
            System.out.println("No se encuentra el archivo"+e.getMessage());
        }
        catch(IOException e) {
            System.out.println("No se encuentra en archivo");
        }

    }
    
    public static void serializarCarne(ArrayList<Carne> lista) {
        try {
            FileOutputStream f = new FileOutputStream(new File(archivo.getAbsolutePath()+
                    "\\src\\baseDatos\\temp\\Carne.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(lista);

            o.close();
            f.close();

        }catch(FileNotFoundException e){
            System.out.println("No se encuentra el archivo"+e.getMessage());
        }
        catch(IOException e) {
            System.out.println("No se encuentra en archivo");
        }

    }

    public static void serializarLibros(ArrayList<Libro> lista) {
        try {
            FileOutputStream f = new FileOutputStream(new File(archivo.getAbsolutePath()+
                    "\\src\\baseDatos\\temp\\Libros.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(lista);

            o.close();
            f.close();

        }catch(FileNotFoundException e){
            System.out.println("No se encuentra el archivo"+e.getMessage());
        }
        catch(IOException e) {
            System.out.println("No se encuentra en archivo");
        }

    }
    public static void serializarSupermercados(ArrayList<Supermercado> lista){
        try {
            FileOutputStream f = new FileOutputStream(new File(archivo.getAbsolutePath()+
                    "\\src\\baseDatos\\temp\\Supermercados.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(lista);

            o.close();
            f.close();

        }catch(FileNotFoundException e){
            System.out.println("No se encuentra el archivo"+e.getMessage());
        }
        catch(IOException e) {
            System.out.println("No se encuentra en archivo");
        }
    }
    public static void serializarTvs(ArrayList<Tv> lista){
        try {
            FileOutputStream f = new FileOutputStream(new File(archivo.getAbsolutePath()+
                    "\\src\\baseDatos\\temp\\Tvs.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(lista);

            o.close();
            f.close();

        }catch(FileNotFoundException e){
            System.out.println("No se encuentra el archivo"+e.getMessage());
        }
        catch(IOException e) {
            System.out.println("No se encuentra en archivo");
        }
    }
    public static void serializarCelulares(ArrayList<Celular> lista){
        try {
            FileOutputStream f = new FileOutputStream(new File(archivo.getAbsolutePath()+
                    "\\src\\baseDatos\\temp\\Celulares.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(lista);

            o.close();
            f.close();

        }catch(FileNotFoundException e){
            System.out.println("No se encuentra el archivo"+e.getMessage());
        }
        catch(IOException e) {
            System.out.println("No se encuentra en archivo");
        }
    }

    public static void serializarRopa(ArrayList<Ropa> lista) {
        try {
            String rutaArchivo = "src/baseDatos/temp/Ropa.txt";
            FileOutputStream f = new FileOutputStream(new File(rutaArchivo));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(lista);

            o.close();
            f.close();

            System.out.println("La serialización se ha completado correctamente.");

        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}

