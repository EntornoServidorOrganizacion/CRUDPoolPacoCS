<%-- 
    Document   : inicioInsertar
    Created on : 22 oct. 2018, 19:30:29
    Author     : paco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inserte un nuevo ave</title>
    </head>
    <body>
        <h1>INSERTE UN NUEVO AVE</h1>
        <form action="<%=request.getContextPath()%>/Realizar" method="post">
            <table>
                <tr>
                    <td><label>Introduce anilla: </label></td>
                    <td><input type="text" value="" name=""/></td>
                </tr>
                 <tr>
                    <td><label>Introduce la especie: </label></td>
                    <td><input type="text" value="" name=""/></td>
                </tr>
                 <tr>
                    <td><label>Introduce el lugar: </label></td>
                    <td><input type="text" value="" name=""/></td>
                </tr>
                 <tr>
                    <td><label>Introduce la fecha: </label></td>
                    <td><input type="text" value="" name=""/></td>
                </tr>
            </table>
            <button type="submit" value="cancelar" name="crearAve">Cancelar</button>
            <button type="submit" value="aceptar" name="crearAve">Insertar</button>
        </form>
    </body>
</html>
