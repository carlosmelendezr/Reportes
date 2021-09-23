package sample;

import modelos.datos.Constantes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class  Connect {

    public static Connection connect(String nombreArchivo) {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:"+Constantes.dirLocal+nombreArchivo;
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            conn.setAutoCommit(true);

            System.out.println("La conexion to SQLite ha sido establecida. Archivo "+ Constantes.dirLocal+nombreArchivo);

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return conn;
    }
    /**
     * @param args the command line arguments
     */

}


