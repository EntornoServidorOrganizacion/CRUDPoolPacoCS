<%-- 
    Document   : finInsertar
    Created on : 22 oct. 2018, 19:30:14
    Author     : paco
--%>

<%@page import="es.albarregas.beans.Ave"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/normalizer.css">
        <link rel="stylesheet" type="text/css" href="CSS/estilos.css">
        <title>Has creado un nuevo ave</title>
    </head>
    <body>
        <h1>HAS CREADO UN NUEVO AVE</h1>
        <form action="ControladorFinal" method="post">
            <p>Ave con anilla: <strong><%=request.getParameter("anilla")%></strong></p>
            <p>Especie del ave: <strong><%=request.getParameter("especie")%></strong></p>
            <p>Lugar de avistamiento: <strong><%=request.getParameter("lugar")%></strong></p>
            <p>Fecha de avistamiento: <strong><%=request.getParameter("fecha")%></strong></p>
            <button type="submit" value="menu" name="operacion">Menú</button>
        </form>
    </body>
</html>
