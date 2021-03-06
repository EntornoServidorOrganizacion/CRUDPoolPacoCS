<%-- 
    Document   : leer
    Created on : 22 oct. 2018, 19:31:44
    Author     : paco
--%>

<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="es.albarregas.beans.Ave"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/normalizer.css">
        <link rel="stylesheet" type="text/css" href="CSS/estilos.css">
        <title>Estas son las aves que hay</title>
    </head>
    <body>
        <h2>Listado de todas las aves de la base de datos</h2>
        <form action="ControladorFinal" method="post">
            <table border = 1>
                <%
                    List<Ave> arrayAves = (ArrayList<Ave>) request.getAttribute("aves");
                    for (Ave ave : arrayAves) {
                %>
                <tr id="leerTabla">
                    <td><%=ave.getAnilla()%></td>
                    <td><%=ave.getEspecie()%></td>
                    <td><%=ave.getLugar()%></td>
                    <td><%=ave.getFechaString()%></td>
                </tr>
                <%
                    }
                %>
            </table>
            <br />
            <button type="submit" value="menu" name="operacion">Menú</button>
            </form>
    </body>
</html>
