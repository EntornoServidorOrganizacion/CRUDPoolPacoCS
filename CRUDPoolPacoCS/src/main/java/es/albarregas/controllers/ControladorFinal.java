/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author paco
 */
@WebServlet(name = "ControladorFinal", urlPatterns = {"/ControladorFinal"})
public class ControladorFinal extends HttpServlet {
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     * Lo que hace este método es retorar al index.jsp cada vez que se pulsa el botón cancelar,
     * por lo que sólo tendrán una instrucción con el método getRequestDispatcher() redirigiendo
     * al "index.jsp"
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //redirección al index.jsp
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }


}
