package com.americanland.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    // Datos de la base de datos en la nube (TiDB)
    private static final String URL = "jdbc:mysql://gateway01.us-east-1.prod.aws.tidbcloud.com:4000/BaseDatos?useSSL=true&requireSSL=true"; 
    private static final String USER = "3w5bVPDQwqppyLr.root";
    private static final String PASSWORD = "QzNBUy5MOvxWYrf8";

    public static Connection obtenerConexion() {
        Connection conexion = null;
        try {
            // Registrar el driver de MySQL (opcional en versiones modernas, pero asegura que funcione)
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Intentar conectar
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            
        } catch (ClassNotFoundException e) {
            System.out.println("Error: No se encontró el driver de MySQL (JDBC).");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error de conexión con TiDB Cloud: " + e.getMessage());
        }
        return conexion;
    }
}