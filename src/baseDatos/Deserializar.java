package src.baseDatos;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import src.uiMain.Main;
import src.gestorAplicacion.*;

public class Deserializar {
    static File archivo = new File("");
    public static ArrayList<Celular> deserializarCelulares(){
        try {
            FileInputStream file = new FileInputStream(new File(archivo.getAbsolutePath()+
                    "\\src\\baseDatos\\temp\\Celulares.txt"));
            ObjectInputStream o = new ObjectInputStream(file);

            ArrayList<Celular> lista_celulares = (ArrayList) o.readObject();

            file.close();
            o.close();
            return lista_celulares;

        }catch(FileNotFoundException e){
            return new ArrayList<Celular>();
        }
        catch(IOException e){
            return new ArrayList<Celular>();
        }
        catch(ClassNotFoundException e) {
            return new ArrayList<Celular>();
        }
    }
    
    public static ArrayList<Tv> deserializarTvs(){
        try {
            FileInputStream file = new FileInputStream(new File(archivo.getAbsolutePath()+
                    "\\src\\baseDatos\\temp\\Tvs.txt"));
            ObjectInputStream o = new ObjectInputStream(file);

            ArrayList<Tv> lista_tvs = (ArrayList) o.readObject();

            file.close();
            o.close();
            return lista_tvs;

        }catch(FileNotFoundException e){
            return new ArrayList<Tv>();
        }
        catch(IOException e){
            return new ArrayList<Tv>();
        }
        catch(ClassNotFoundException e) {
            return new ArrayList<Tv>();
        }
    }
    
    public static ArrayList<Libro> deserializarLibros() {
        try {
            FileInputStream file = new FileInputStream(new File(archivo.getAbsolutePath()+
                    "\\src\\baseDatos\\temp\\Libros.txt"));
            ObjectInputStream o = new ObjectInputStream(file);

            ArrayList<Libro> lista_libros = (ArrayList) o.readObject();

            file.close();
            o.close();
            return lista_libros;

        }catch(FileNotFoundException e){
            return new ArrayList<Libro>();
        }
        catch(IOException e){
            return  new ArrayList<Libro>();
        }
        catch(ClassNotFoundException e) {
            return  new ArrayList<Libro>();
        }
    }
    
    public static ArrayList<Carne> deserializarCarne() {
        try {
            FileInputStream file = new FileInputStream(new File(archivo.getAbsolutePath()+
                    "\\src\\baseDatos\\temp\\Carne.txt"));
            ObjectInputStream o = new ObjectInputStream(file);

            ArrayList<Carne> lista_carnicos = (ArrayList) o.readObject();

            file.close();
            o.close();
            return lista_carnicos;

        }catch(FileNotFoundException e){
            return new ArrayList<Carne>();
        }
        catch(IOException e){
            return  new ArrayList<Carne>();
        }
        catch(ClassNotFoundException e) {
            return  new ArrayList<Carne>();
        }
    }
    
    public static ArrayList<noCarnicos> deserializarnoCarnicos(){
        try {
            FileInputStream file = new FileInputStream(new File(archivo.getAbsolutePath()+
                    "\\src\\baseDatos\\temp\\noCarnicos.txt"));
            ObjectInputStream o = new ObjectInputStream(file);

            ArrayList<noCarnicos> lista_no_carnicos = (ArrayList) o.readObject();

            file.close();
            o.close();
            return lista_no_carnicos;

        }catch(FileNotFoundException e){
            return new ArrayList<noCarnicos>();
        }
        catch(IOException e){
            return new ArrayList<noCarnicos>();
        }
        catch(ClassNotFoundException e) {
            return new ArrayList<noCarnicos>();
        }
    }
    
    public static ArrayList<Ropa> deserializarRopa() {
        try {
            String rutaArchivo = "src/baseDatos/temp/Ropa.txt";
            FileInputStream file = new FileInputStream(new File(rutaArchivo));
            ObjectInputStream o = new ObjectInputStream(file);

            ArrayList<Ropa> listaRopa = (ArrayList<Ropa>) o.readObject();

            file.close();
            o.close();
            return listaRopa;

        } catch (FileNotFoundException e) {
            System.out.println("No se encuentra el archivo: " + e.getMessage());
            return new ArrayList<Ropa>();
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
            return new ArrayList<Ropa>();
        } catch (ClassNotFoundException e) {
            System.out.println("Error al deserializar el objeto: " + e.getMessage());
            return new ArrayList<Ropa>();
        }
    }
    public static ArrayList<Supermercado> deserializarSupermercados(){
        try {
            FileInputStream file = new FileInputStream(new File(archivo.getAbsolutePath()+
                    "\\src\\baseDatos\\temp\\Supermercados.txt"));
            ObjectInputStream o = new ObjectInputStream(file);

            ArrayList<Supermercado> lista_supermercados = (ArrayList) o.readObject();

            file.close();
            o.close();
            return lista_supermercados;
        
        }catch(FileNotFoundException e){
            System.out.println("No hay supermercados registrados. Deseas crear un supermercado? " +
                    "\n1 = Si" +
                    "\n2 = No");
            String respuesta = Main.sc.nextLine();
            while(!respuesta.equals("1") && !respuesta.equals("2")){
                System.out.println("La respuesta ingresada no se encuentra entre las opciones (1 o 2)" +
                        "\nIngresala nuevamente");
                respuesta = Main.sc.nextLine();
            }
            if(respuesta.equals("1")){
                ArrayList<Supermercado> lista_supermercados = new ArrayList<Supermercado>();
                System.out.print("Por favor, ingrese el nombre del nuevo supermercado: ");
                Supermercado supermercado = new Supermercado(Main.sc.nextLine());
                //Aqui se ejecutaria la opcion para anadir productos
                Main.anadirProducto(supermercado);
                lista_supermercados.add(supermercado);
                return lista_supermercados;
            }
            
            return  new ArrayList<Supermercado>();
        }
        
        catch(IOException e){
            System.out.println("MOFETICA");
            return new ArrayList<Supermercado>();
        }
        
        catch(ClassNotFoundException e) {
            System.out.println("No hay supermercados registrados. Deseas crear un supermercado? " +
                    "\n1 = Si" +
                    "\n2 = No");
            String respuesta = Main.sc.next();
            while(!respuesta.equals("1") && !respuesta.equals("2")){
                System.out.println("La respuesta ingresada no se encuentra entre las opciones (1 o 2)" +
                        "\nIngresala nuevamente");
                respuesta = Main.sc.next();
            }
            if(respuesta.equals("1")){
                ArrayList<Supermercado> lista_supermercados = new ArrayList<Supermercado>();
                System.out.print("Por favor, ingrese el nombre del nuevo supermercado: ");
                Supermercado supermercado = new Supermercado(Main.sc.next());
                lista_supermercados.add(supermercado);
                return lista_supermercados;
            }
            
            return new ArrayList<Supermercado>();
        }
    }
}
