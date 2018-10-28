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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.Object;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

/**
 *
 * @author paco
 */
@WebServlet(name = "Realizar", urlPatterns = {"/Realizar"})
public class Realizar extends HttpServlet {

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

        if (request.getParameter("operacion") != null) {
            switch (request.getParameter("operacion")) {
                case "crearAve":
                    /**
                     * Si pulsamos en el botón de Crear se llamará al método
                     * crearAve para insertar un nuevo ave en la BBDD
                     */
                    crearAve(request, response);
                    break;
                case "aceptarActualizar":
                    /**
                     * Al pulsar el botón Actualizar y habiendo seleccionado un ave
                     * pasaremos a la página actualizar.jsp 
                     * Si se pulsa cancelar, vuelve al index.jsp
                     */
                    actualizarAve(request, response);

                    if (request.getParameter("operacion").equals("cancelar")) {
                        request.getRequestDispatcher("ControladorFinal").forward(request, response);
                    } else {
                        request.getRequestDispatcher("JSP/update/actualizar.jsp").forward(request, response);
                    }
                    break;
                case "aceptarEliminar":
                    /**
                     * Al pulsar el botón Eliminar y habiendo seleccionado un ave
                     * o varias aves pasaremos a la página eliminar.jsp 
                     * Si se pulsa cancelar, vuelve al index.jsp
                     */
                    eliminarAve(request, response);

                    if (request.getParameter("operacion").equals("cancelar")) {
                        request.getRequestDispatcher("ControladorFinal").forward(request, response);
                    } else {
                        request.getRequestDispatcher("JSP/delete/eliminar.jsp").forward(request, response);
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
     * Este método crea un ave nueva en la BBDD, para ello primero comprobamos
     * que la PrimaryKey no esté repetida. Si no está repetida, procederemos a
     * poder crear un nuevo ave.
     */
    public void crearAve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = null;
        DataSource datasource = null;
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        String sql = null;
        ResultSet resultSet = null;
        Ave ave = null;
        List<Ave> aves = new ArrayList();

        try {
            //Hacemos la conexion a la BBDD
            datasource = Conexion.crearConexion();
            conexion = datasource.getConnection();

            sql = ("select * from aves where anilla = ?;");
            preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, request.getParameter("anilla"));
            resultSet = preparedStatement.executeQuery();
            //Comprobar que la PK no esté repetida para que no de error
            if (resultSet.next()) {
                request.setAttribute("anillaRepetida", request.getParameter("anilla"));
                url = "JSP/create/inicioInsertar.jsp";

            } else {
                //obtenemos los valores del formulario
                String anilla = request.getParameter("anilla");
                String especie = request.getParameter("especie");
                String lugar = request.getParameter("lugar");
                String fecha = request.getParameter("fecha");
                //preparamos la sentencia SQL
                sql = "insert into aves values (?,?,?,?)";
                preparedStatement = conexion.prepareStatement(sql);
                //insertamos los valores
                preparedStatement.setString(1, anilla);
                preparedStatement.setString(2, especie);
                preparedStatement.setString(3, lugar);
                preparedStatement.setString(4, fecha);
                preparedStatement.executeUpdate();

                //cerramos conexion
                Conexion.cerrarConexion(conexion);

                url = "JSP/create/finInsertar.jsp";
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher(url).forward(request, response);
    }//fin metodo crear

    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     * 
     * Este método selecciona a través de un radio button el ave que se pretende modificar
     */
    public void actualizarAve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataSource datasource = null;
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        String sql = null;
        ResultSet resultSet = null;
        Ave ave = null;
        List<Ave> aves = new ArrayList();
        //Array de anillas. Se usa getParameterValues().
        String[] anilla = request.getParameterValues("avesActualizar");
        try {
            datasource = Conexion.crearConexion();
            conexion = datasource.getConnection();
            //Ejecutar sentencia SQL
            sql = "select * from aves where anilla = '" + anilla[0] + "';";//se selecciona siempre el elemento 0 del array ya que sólo se puede seleccionar un ave
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

    }//fin metodo actualizar

    
    /**
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException 
     * 
     * Este método selecciona una o varias aves a traves de un checkbox para posteriormente
     * eliminar las aves seleccionadas
     */
    public void eliminarAve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DataSource datasource = null;
        Connection conexion = null;
        PreparedStatement preparedStatement = null;
        String sql = null;
        ResultSet resultSet = null;
        Ave ave = null;
        List<Ave> aves = new ArrayList();
        //Array de anillas. Se usa getParameterValues().
        String[] anilla = request.getParameterValues("avesEliminar");
        try {
            datasource = Conexion.crearConexion();
            conexion = datasource.getConnection();
            
            //SQL completa****
            sql = "select * from aves where ";
            for (int i = 0; i < request.getParameterValues("avesEliminar").length; i++) {
                sql += " anilla = ? or"; //añadir or dimanicamente según las aves que haya en la BBDD
            }
            sql = sql.substring(0, sql.length() - 3);//al salir del for eliminar or;
            sql += ";";//cerrar sentencia
            //SQL completa****
            
            preparedStatement = conexion.prepareStatement(sql);
            for (int i = 0; i < request.getParameterValues("avesEliminar").length; i++) {
                //insertar valores de forma dinámica. Se suma 1 a la i, ya que i empieza por 0 y el primer ? de la sentencia sql es 1
                preparedStatement.setString(i + 1, request.getParameterValues("avesEliminar")[i]);
            }
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
    }//fin metodo eliminar

}//FIN CLASE
