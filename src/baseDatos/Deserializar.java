package src.baseDatos;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class Deserializar {
    static File archivo = new File("");

    public static void serializarObjeto(Object objeto) {
        try {
            FileInputStream f = new FileInputStream(new File(archivo.getAbsolutePath()+
                    "\\src\\baseDatos\\temp\\data.txt"));
            ObjectInputStream o = new ObjectInputStream(f);

            Object objeto1 = o.readObject();

            f.close();
            o.close();

        }catch(FileNotFoundException e){
            System.out.println("No se encuentra el archivo"+e.getMessage());
        }
        catch(IOException e){
            System.out.println("Error flujo de inicializacion");
        }
        catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
