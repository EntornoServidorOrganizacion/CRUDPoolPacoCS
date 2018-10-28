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

        if (request.getParameter("operacion") != null) {
            switch (request.getParameter("operacion")) {
                case "Actualizar":
                    /**
                     * al pulsar el botón de actualizar, los nuevos datos introducidos
                     * se actualizan en la bbdd llamando al método actualizar()
                     * Si se pulsa en cancelar vuelve al inicio
                     */
                    actualizar(request, response);
                    if (request.getParameter("operacion").equals("cancelar")) {
                        request.getRequestDispatcher("ControladorFinal").forward(request, response);
                    }
                    break;
                case "Eliminar":
                    /**
                     * al pulsar el botón de eliminar, se eliminan las aves
                     * previamente seleccionadas
                     * Si se pulsa en cancelar vuelve al inicio
                     */
                    eliminar(request, response);
                    if (request.getParameter("operacion").equals("cancelar")) {
                        request.getRequestDispatcher("ControladorFinal").forward(request, response);
                    }
                    break;
                case "cancelar":
                    /**
                     * al pulsar el botón de cancelar en cualquier página jsp,
                     * nos dirigirá al ControladorFinal (es un servlet) para
                     * dirigir el flujo de la aplicación
                     */
                    request.getRequestDispatcher("ControladorFinal").forward(request, response);
                    break;
            }//fin switch            
        }//fin if

    }

    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     * 
     * Este método actualiza los datos el ave seleccionada
     */
    public void actualizar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = null;
        DataSource datasource = null;
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        String sql = null;

        try {
            //Hacemos la conexion a la BBDD
            datasource = Conexion.crearConexion();
            conexion = datasource.getConnection();

            //obtenemos los valores del formulario
            String anilla = request.getParameter("anilla");
            String especie = request.getParameter("especie");
            String lugar = request.getParameter("lugar");
            String fecha = request.getParameter("fecha");
            //preparamos la sentencia SQL
            sql = "update aves set especie = ?, lugar = ?, fecha = ? where anilla = '" + anilla + "';";
            preparedStatement = conexion.prepareStatement(sql);
            //insertamos los valores
            preparedStatement.setString(1, especie);
            preparedStatement.setString(2, lugar);
            preparedStatement.setString(3, fecha);
            preparedStatement.executeUpdate();

            //cerramos conexion
            Conexion.cerrarConexion(conexion);

            url = "JSP/update/finActualizar.jsp";

        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher(url).forward(request, response);
    }

    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     * 
     * Este método elimina las aves seleccionadas
     */
    public void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = null;
        DataSource datasource = null;
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        String sql = null;

        try {
            //Hacemos la conexion a la BBDD
            datasource = Conexion.crearConexion();
            conexion = datasource.getConnection();
            //obtenemos los valores del formulario
            String anilla = request.getParameter("anilla");
            //preparamos la sentencia SQL
            sql = "delete from aves where anilla = '"+anilla+"'";
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.executeUpdate();

            //NO FUNCIONA!!!
            
            //cerramos conexion
            Conexion.cerrarConexion(conexion);

            url = "JSP/delete/finEliminar.jsp";
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher(url).forward(request, response);
    }

}//fin clase
