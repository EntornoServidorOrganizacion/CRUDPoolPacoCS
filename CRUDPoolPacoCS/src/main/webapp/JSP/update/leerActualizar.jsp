<%-- 
    Document   : leerActualizar
    Created on : 22 oct. 2018, 19:33:03
    Author     : paco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Aves a actualizadar</title>
    </head>
    <body>
        <h1>ELIJA LA O LAS AVES QUE DESEA ACTUALIZAR<h1>
        <form action="<%=request.getContextPath()%>/Realizar" method="post">
            <button type="submit" value="cancelar" name="actualizarAve">Cancelar</button>
            <button type="submit" value="aceptar" name="actualizarAve">Actualizar</button>
        </form>
    </body>
</html>
