package com.mycompany.examengpj;

import java.sql.*;
/**
 *
 * @author Gonzalo
 */
public class DictionaryConnection {

    private DictionaryConnection() {
    }

    private final String JDBC_DRIVER = "org.sqlite.JDBC";
    private final String DB_PATH = "C:\\Users\\Desktop\\palabras.sqlite3";
    private final String JDBC_URL = "jdbc:sqlite:";

    private static DictionaryConnection instance;
    private Connection conexion;

    public static DictionaryConnection getInstance() {
        if (instance == null) {
            synchronized (DictionaryConnection.class) {
                if (instance == null) {
                    instance = new DictionaryConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnection(String dbPath) {
        try {
            if (conexion == null || conexion.isClosed()) {
                synchronized (DictionaryConnection.class) {
                    if (conexion == null) {
                        try {
                            Class.forName(JDBC_DRIVER);
                            conexion = DriverManager.getConnection(dbPath);
                        } catch (ClassNotFoundException ex) {
                            System.err.println("Error en conexion a BD " + ex.getMessage());
                        }

                    }

                }
            }
        } catch (SQLException e) {
            System.err.println("Error en conexion a BD " + e.getMessage());
        }
        return conexion;
    }
    
    public Connection getConnection(){
        return getConnection(JDBC_URL + DB_PATH);
    }
    
    
}
