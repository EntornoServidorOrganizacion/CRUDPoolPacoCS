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
        <link rel="stylesheet" type="text/css" href="CSS/normalizer.css">
        <link rel="stylesheet" type="text/css" href="CSS/estilos.css">
        <title>Inserte un nuevo ave</title>
    </head>
    <%
        boolean hayErrores = false;
        String anillaRepetida = "";
        String valueEspecie = "";
        String valueLugar = "";
        String valueFecha = "";
        String placeholder = "";
        if(request.getAttribute("anillaRepetida") != null){
            hayErrores = true;
            anillaRepetida = (String) request.getAttribute("anillaRepetida");
            placeholder = "Error en la anilla";
            if(request.getParameter("especie") != null){
                valueEspecie = request.getParameter("especie");
            }
            if(request.getParameter("lugar") != null){
                valueLugar = request.getParameter("lugar");
            }
            if(request.getParameter("fecha") != null){
                valueFecha = request.getParameter("fecha");
            }
        }
    %>
    <body>
        <h1>INSERTE UN NUEVO AVE</h1>
        <%
            if(hayErrores){
        %>
        <h3>La anilla <big><u><%=request.getAttribute("anillaRepetida")%></u></big> ya está introducida en la Base de Datos y no se puede repetir</h3>
        <%
            }
        %>
        <form action="<%=request.getContextPath()%>/Realizar" method="post">
            <table>
                <tr>
                    <td><label>Introduce anilla: </label></td>
                    <td><input type="text" value="" name="anilla" maxlength="3" placeholder="<%=placeholder%>"/></td>
                </tr>
                <tr>
                    <td><label>Introduce la especie: </label></td>
                    <td><input type="text" value="<%=valueEspecie%>" name="especie" maxlength="20"/></td>
                </tr>
                <tr>
                    <td><label>Introduce el lugar: </label></td>
                    <td><input type="text" value="<%=valueLugar%>" name="lugar" maxlength="50"/></td>
                </tr>
                <tr>
                    <td><label>Introduce la fecha: </label></td>
                    <td><input type="text" value="<%=valueFecha%>" name="fecha" placeholder="19/08/1989" maxlength="10"/></td>
                </tr>
            </table>
            <button type="submit" value="cancelar" name="operacion">Cancelar</button>
            <button id="insertar" type="submit" value="crearAve" name="operacion">Crear</button>
        </form>
    </body>
</html>
