<%-- 
    Document   : finActualizar
    Created on : 22 oct. 2018, 19:32:35
    Author     : paco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/normalizer.css">
        <link rel="stylesheet" type="text/css" href="CSS/estilos.css">
        <title>Has actualizado las aves</title>
    </head>
    <body>
        <h1>HAS ACTUALIZADO ESTA AVE:</h1>
        <form action="ControladorFinal" method="post">
            <table border = 1>

                <h3>Este es el ave seleccionada: </h3>
                <tr id="leerTabla">
                    <td><%=request.getParameter("anilla")%></td>
                    <td><%=request.getParameter("especie")%></td>
                    <td><%=request.getParameter("lugar")%></td>
                    <td><%=request.getParameter("fecha")%></td>
                </tr>
                <br>
            </table>

            <button type="submit" value="menu" name="operacion">Menú</button>
        </form>
    </body>
</html>
