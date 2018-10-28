<%-- 
    Document   : leerEliminar
    Created on : 22 oct. 2018, 19:31:32
    Author     : paco
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="es.albarregas.beans.Ave"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/normalizer.css">
        <link rel="stylesheet" type="text/css" href="CSS/estilos.css">
        <title>Elegir ave a eliminar</title>
    </head>
    <%
        /**
         * Iteramos sobre la lista aves para comprobar si hay más elementos.
         * Si no hay elementos, pinta en pantalla que no existen datos, en caso
         * de que haya elementos, los recorre y los va poniendo en pantalla
         */
        List<Ave> aves = null;
        Iterator<Ave> it = null;//crado el iterador
        if (request.getAttribute("aves") != null) {
            aves = (List) request.getAttribute("aves");
            it = aves.iterator();
        }
    %>
    <body>
        <h1>SELECCIONE UN O VARIAS AVES PARA ELIMINAR</h1>

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
                    <input type="checkbox" value="<%=ave.getAnilla()%>" name="avesEliminar"><label id="listadoEliminar"><%=ave.getEspecie()%></label>
                    <br>
                    <%
                            }
                        }
                    %>
            <button type="submit" value="cancelar" name="operacion">Cancelar</button>
            <button id="eliminar" type="submit" value="aceptarEliminar" name="operacion">Eliminar</button>
        </form>
    </body>
</html>
