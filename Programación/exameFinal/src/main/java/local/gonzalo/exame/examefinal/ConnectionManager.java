package local.gonzalo.exame.examefinal;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dammdprog1
 */
public class ConnectionManager {

    private final String URL = "jdbc:sqlite:C:\\Users\\dammdprog1\\Desktop\\examenesTest.sqlite";
    public ConnectionManager instance;
    public Connection conexion;

    ConnectionManager() {
    }

    public ConnectionManager getInstance() {
        if (instance == null) {
            synchronized (ConnectionManager.class) {
                if (instance == null) {
                    instance = new ConnectionManager();
                }
            }
        }
        return instance;
    }

    public Connection getConnection(String ruta) {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion = DriverManager.getConnection(ruta);
                System.out.println("Conexion establecida con éxito");
            }
        } catch (SQLException ex) {
            System.err.println("Error al establecer conexión con BD: " + ex.getMessage());
        }
        return conexion;
    }
    
    public Connection getConnection(){
        return getConnection(URL);
    }

}
