<%-- 
    Document   : leerActualizar
    Created on : 22 oct. 2018, 19:33:03
    Author     : paco
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="es.albarregas.beans.Ave"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/normalizer.css">
        <link rel="stylesheet" type="text/css" href="CSS/estilos.css">
        <title>Aves a actualizadar</title>
    </head>
    <%  
        /**
         * Iteramos sobre la lista aves para comprobar si hay más elementos.
         * Si no hay elementos, pinta en pantalla que no existen datos, en caso
         * de que haya elementos, los recorre y los va poniendo en pantalla
         */
        List<Ave> aves = null;
        Iterator<Ave> it = null; //crado el iterador
        if (request.getAttribute("aves") != null) {
            aves = (List) request.getAttribute("aves");
            it = aves.iterator();
        }
    %>
    <body>
        <h1>ELIJA LA O LAS AVES QUE DESEA ACTUALIZAR<h1>
                <form action="<%=request.getContextPath()%>/Realizar" method="post">
                    <%
                        if (!it.hasNext()) {
                    %>
                    <h3>No existen datos, puede crear unos nuevos en la opción insertar</h3>    
                    <%
                    } else {
                        while (it.hasNext()) {
                            Ave ave = it.next();
                    %>
                    <input type="radio" value="<%=ave.getAnilla()%>" name="avesActualizar"><label id="listadoActualizar"><%=ave.getEspecie()%></label>
                    <br>
                    <%
                            }
                        }
                    %>
                    <button type="submit" value="cancelar" name="operacion">Cancelar</button>
                    <button id="actualizar" type="submit" value="aceptarActualizar" name="operacion">Actualizar</button>
                </form>
                </body>
                </html>
