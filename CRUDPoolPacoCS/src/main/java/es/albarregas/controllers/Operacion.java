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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
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
@WebServlet(name = "Operacion", urlPatterns = {"/Operacion"})
public class Operacion extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = "";

        if (request.getParameter("operacion") != null) {
            switch (request.getParameter("operacion")) {
                case "Insertar":
                    /**
                     * Al elegir la opción Insertar en el menú inicial
                     * nos redirigirá a la página donde podremos crear un ave
                     */
                    url = "JSP/create/inicioInsertar.jsp";
                    break;
                case "Visualizar":
                    /**
                     * Al pulsar el botón de Visualizar se ejecutará el metodo
                     * obtenerTodasLasAves y mostrará la BBDD de AVES en la página
                     * de leer.jsp
                     */
                    obtenerTodasLasAves(request, response);
                    url = "/JSP/read/leer.jsp";
                    request.setAttribute("operacion", "leer");
                    break;
                case "Actualizar":
                    /**
                     * Cuando se selecciona la opción de Actualizar, primero
                     * ejecutará el método obtenerTodasLasAves para leer la BBDD
                     * y mostrar las aves en la página leerActualizar.jsp
                     * para que se pueda elegir una y modificarla
                     */
                    obtenerTodasLasAves(request, response);
                    url = "JSP/update/leerActualizar.jsp";
                    break;
                case "Eliminar":
                    /**
                     * Cuando se selecciona la opción de Eliminar, primero
                     * ejecutará el método obtenerTodasLasAves para leer la BBDD
                     * y mostrar las aves en la página leerActualizar.jsp
                     * para que se pueda elegir una o varias y eliminarlas
                     */
                    obtenerTodasLasAves(request, response);
                    url = "JSP/delete/leerEliminar.jsp";
                    break;
            }
        }

        //redirigimos a la página correspondiente
        request.getRequestDispatcher(url).forward(request, response);

    }

    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     * 
     * Este método realiza la lectura de la BBDD
     */
    public void obtenerTodasLasAves(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        DataSource datasource = null;
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        String sql = null;
        ResultSet resultSet = null;
        Ave ave = null;
        List<Ave> aves = new ArrayList();
        try {
            datasource = Conexion.crearConexion();
            conexion = datasource.getConnection();
            //Ejecutar sentencia SQL
            sql = "select * from aves;";
            preparedStatement = conexion.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            
            //Setear los valores/atributos de la BBDD
            while (resultSet.next()) {
                ave = new Ave();
                ave.setAnilla(resultSet.getString("anilla"));
                ave.setEspecie(resultSet.getString("especie"));
                ave.setLugar(resultSet.getString("lugar"));
                ave.setFechaString(resultSet.getString("fecha"));
                aves.add(ave);
            }
            request.setAttribute("aves", aves);
            Conexion.cerrarConexion(conexion);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
