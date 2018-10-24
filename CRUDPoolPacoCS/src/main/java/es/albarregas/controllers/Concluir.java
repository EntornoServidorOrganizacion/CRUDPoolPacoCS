/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.beans.Ave;
import es.albarregas.connections.Conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author paco
 */
@WebServlet(name = "Concluir", urlPatterns = {"/Concluir"})
public class Concluir extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        Connection conexion = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        String sql = null;

        //Objeto(beans) y List
        Ave ave = null;
        List<Ave> arrayAves = null;

        String url = null;
        try {
            conexion = Conexion.crearConexion();


            /**
             * Cuando seleccionamos la opción visualizar en el index, pasa por el 
             * controlador Operacion que reedirige a este controlador y este controlador
             * hace la sentencia sql y pasa por petición los datos al JSP que se llama
             * leer.jsp
             */
            if (request.getAttribute("operacion").equals("leer")) {
                sql = "select * from aves";
                statement = conexion.createStatement();
                resultSet = statement.executeQuery(sql);//pasamos la sentencia sql
                arrayAves = new ArrayList(); //ahora es cuando creamos el arrayList
                url = "/JSP/read/leer.jsp";

                while (resultSet.next()) {

                    ave = new Ave(); //ahora es cuando creamos el objeto ave
                    ave.setAnilla(resultSet.getString("anilla"));
                    ave.setEspecie(resultSet.getString("especie"));
                    ave.setLugar(resultSet.getString("lugar"));
                    ave.setFecha(resultSet.getString("fecha"));
                    arrayAves.add(ave);//añadimos las aves que encuentre en la tabla en el arrayList
                }
                //pasamos por petición
                request.setAttribute("aves", arrayAves);
            }

            //redirigimos a la página correspondiente
            request.getRequestDispatcher(url).forward(request, response);
        } catch (SQLException ex) {
            System.out.println("Error al crear la conexión");
            ex.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                if (statement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                if (conexion != null) {
                    Conexion.cerrarConexion(conexion);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
