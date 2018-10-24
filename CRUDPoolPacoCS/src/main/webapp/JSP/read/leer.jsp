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
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Listado de todas las aves de la base de datos</h2>
            <table>
                <%
                    List<Ave> arrayAves = (ArrayList<Ave>) request.getAttribute("aves");
                    for (Ave ave : arrayAves) {
                %>
                <tr>
                    <td><%=ave.getAnilla()%></td>
                    <td><%=ave.getEspecie()%></td>
                    <td><%=ave.getLugar()%></td>
                    <td><%=ave.getFecha()%></td>
                </tr>
                <%
                    }
                %>
            </table>



            <br />
            <p><a href="<%= request.getContextPath()%>">Menú</a></p>
    </body>
</html>
