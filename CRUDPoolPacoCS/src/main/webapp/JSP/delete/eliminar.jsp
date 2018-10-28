<%-- 
    Document   : eliminar
    Created on : 22 oct. 2018, 19:31:11
    Author     : paco
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="es.albarregas.beans.Ave"%>
<%@page import="es.albarregas.beans.Ave"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/normalizer.css">
        <link rel="stylesheet" type="text/css" href="CSS/estilos.css">
        <title>Se van a eliminar...</title>
    </head>
    <%
        /**
         * Iteramos sobre la lista aves para comprobar si hay más elementos.
         * Si no hay elementos, pinta en pantalla que no existen datos, en
         * caso de que haya elementos, los recorre y los va poniendo en
         * pantalla
         */
        List<Ave> aves = null;
        Iterator<Ave> it = null;//crado el iterador
        if (request.getAttribute("aves") != null) {
            aves = (List) request.getAttribute("aves");
            it = aves.iterator();
        }
    %>
    <body>
        <h1>¿ESTÁS SEGURO QUE DESEAS ELIMINAR ESTAS AVES?</h1>
        <form action="<%=request.getContextPath()%>/Concluir" method="post">
<h3>Estas son las aves seleccionadas: </h3>

            <%
                while (it.hasNext()) {
                    Ave ave = it.next();
            %>
            <table border = 1>

                
                <tr id="leerTabla">
                    <td><%=ave.getAnilla()%></td>
                    <td><%=ave.getEspecie()%></td>
                    <td><%=ave.getLugar()%></td>
                    <td><%=ave.getFechaString()%></td>
                </tr>

            </table>
            <br>
            <%  
                }//fin while
            %>
            <button type="submit" value="cancelar" name="operacion">Cancelar</button>
            <button id="eliminar" type="submit" value="Eliminar" name="operacion">Eliminar</button>
        </form>
    </body>
</html>
