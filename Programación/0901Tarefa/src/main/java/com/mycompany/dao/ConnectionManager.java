package com.mycompany.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Gonzalo
 */
public class ConnectionManager {

    // URL conexión base de datos
    public static final String URL = "jdbc:h2:C:\\BD\\biblioteca2;DB_CLOSE_ON_EXIT=TRUE;FILE_LOCK=NO;DATABASE_TO_UPPER=FALSE";    
    
    // Driver base de datos
    public static final String DRIVER = "org.h2.Driver";
    
    // Instancia clase
    private static ConnectionManager instance;
    
    // Conexión con base de datos.
    private Connection con;
    
    // Constructor
    private ConnectionManager(){
        
    }
    
    public static ConnectionManager getInstance(){
        if(instance == null){
            synchronized(ConnectionManager.class){
                if(instance == null){
                    instance = new ConnectionManager();
                }
            }
        }
        return instance;
    }

    // Conexión con la base de datos
    public Connection getConnection(){
        try{
            if(con == null || con.isClosed()){
                synchronized (ConnectionManager.class) {
                    if(con == null){
                        try{
                            Class.forName(DRIVER);
                            con = DriverManager.getConnection(URL);                                  
                        } catch (ClassNotFoundException ex) {
                           System.err.println("Drivers non atopados.");
                            
                        } catch (SQLException ex) {
                            System.err.println("Erro ó establecer conexión: " + ex.getMessage());
                        }
                    }
                    
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ó establecer conexión: " + e.getMessage());
        }
        return con;
    }
    
    // Pechamos conexión coa base de datos
    public void closeConnection(){
        try{
            if(con != null && !con.isClosed()){
                con.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ó pechar conexión: " + e.getMessage());
        }
    }
}
