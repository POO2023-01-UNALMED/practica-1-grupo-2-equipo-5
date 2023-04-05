package src.baseDatos;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
public class Serializar {
    static File archivo = new File("");

    public static void serializarObjeto(Object objeto) {
        try {
            FileOutputStream f = new FileOutputStream(new File(archivo.getAbsolutePath()+
                    "\\src\\baseDatos\\temp\\data.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);

            o.writeObject(objeto);

            o.close();
            f.close();

        }catch(FileNotFoundException e){
            System.out.println("No se encuentra el archivo"+e.getMessage());
        }
        catch(IOException e) {
            System.out.println("No se encuentra en archivo");
        }
    }

}
