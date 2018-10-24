/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.connections;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author paco
 */
public class Conexion {
    static DataSource datasource;
    
    //crear conexion con la base de datos
    public static Connection crearConexion() throws SQLException{
        try {
            Context contextoInicial = new InitialContext();
            datasource = (DataSource) contextoInicial.lookup("java:comp/env/jdbc/nombre-jndi");
        } catch (NamingException ex) {
            System.out.println("Problemas en el acceso a la BD");
            ex.printStackTrace();
        }
        
        return datasource.getConnection();
    }
    
    //cerrar la conexion con la base de datos
    public static void cerrarConexion(Connection connection) throws SQLException{
        connection.close();
    }
}
