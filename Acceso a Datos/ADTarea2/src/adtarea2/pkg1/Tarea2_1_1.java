
package adtarea2.pkg1;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.io.IOException;


public class Tarea2_1_1 {

    public static void main(String[] args) throws Exception {

        try {
            RandomAccessFile fichero = new RandomAccessFile("EMPLEADOS.DAT", "rw");

            Empleado empleado1 = new Empleado(1, "Juan", "Direccion A", 1000.0f, 200.0f);
            Empleado empleado2 = new Empleado(2, "Jose", "Direccion B", 1100.0f, 100.0f);
            Empleado empleado3 = new Empleado(3, "Antonio", "Direccion C", 1200.0f, 150.0f);
            Empleado empleado4 = new Empleado(4, "Maria", "Direccion D", 1300.0f, 250.0f);
            Empleado empleado5 = new Empleado(5, "Luisa", "Direccion E", 1400.0f, 230.0f);

            fichero.writeInt(empleado1.getCodigo());
            fichero.writeUTF(empleado1.getNombre());
            fichero.writeUTF(empleado1.getDireccion());
            fichero.writeFloat(empleado1.getSalario());
            fichero.writeFloat(empleado1.getComision());

            fichero.writeInt(empleado2.getCodigo());
            fichero.writeUTF(empleado2.getNombre());
            fichero.writeUTF(empleado2.getDireccion());
            fichero.writeFloat(empleado2.getSalario());
            fichero.writeFloat(empleado2.getComision());

            fichero.writeInt(empleado3.getCodigo());
            fichero.writeUTF(empleado3.getNombre());
            fichero.writeUTF(empleado3.getDireccion());
            fichero.writeFloat(empleado3.getSalario());
            fichero.writeFloat(empleado3.getComision());

            fichero.writeInt(empleado4.getCodigo());
            fichero.writeUTF(empleado4.getNombre());
            fichero.writeUTF(empleado4.getDireccion());
            fichero.writeFloat(empleado4.getSalario());
            fichero.writeFloat(empleado4.getComision());

            fichero.writeInt(empleado5.getCodigo());
            fichero.writeUTF(empleado5.getNombre());
            fichero.writeUTF(empleado5.getDireccion());
            fichero.writeFloat(empleado5.getSalario());
            fichero.writeFloat(empleado5.getComision());

            System.out.println("Los datos de los empleados se han escrito en el archivo Empleados.dat");
        } catch (IOException e) {
        }

    }
}
