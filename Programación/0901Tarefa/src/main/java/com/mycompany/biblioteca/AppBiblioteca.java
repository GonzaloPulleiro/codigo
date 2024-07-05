package com.mycompany.biblioteca;

import com.mycompany.dao.BookDAO;
import com.mycompany.dao.ConnectionManager;
import com.mycompany.model.Book;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;

/**
 *
 * @author Gonzalo
 */
public class AppBiblioteca {

    public static void main(String[] args) {
        Connection conexion = ConnectionManager.getInstance().getConnection();
        System.out.println("Conexión establecida: " + conexion);

        BookDAO libroDAO = new BookDAO(conexion);
                
        BookView interfaz = new BookView(conexion, "biblioteca");

        // Eliminamos los registros de la base de datos
        libroDAO.deleteAll();

        String ruta = "C:\\Users\\gonza\\Desktop\\FP\\PROG\\UD9\\0901Tarefa\\src\\main\\resources\\images\\";
 
        // Creamos los libros con sus datos y su portada
        Book libro1 = new Book(14, "9780307277672", "Cien años de soledad", "Gabriel García Márquez", (short) 1967, true, portada(ruta + "cien.jpg"));
        Book libro2 = new Book( 15, "9780743273565", "Harry Potter y la piedra filosofal", "J.K. Rowling", (short) 1997, true, portada(ruta + "HP.jpg"));
        Book libro3 = new Book( 16, "9780307386672", "No Country for Old Men", "Cormac McCarthy", (short) 2005, true, portada(ruta + "old.jpg"));
        Book libro4 = new Book(17, "9781400064168", "The Road", "Cormac McCarthy", (short) 2006, false, portada(ruta + "road.jpg"));

        // Los añadimos a la base de datos
        libroDAO.save(libro1);
        libroDAO.save(libro2);
        libroDAO.save(libro3);
        libroDAO.save(libro4);


    }

    private static byte[] portada(String ruta) {
        try {
            byte[] p;
            p = Files.readAllBytes(Paths.get(ruta));
            return p;
        } catch (IOException ex) {
            System.err.println("Erro na ruta da imaxe de portada: " + ex.getMessage());
        }
        return null;
    }
}
