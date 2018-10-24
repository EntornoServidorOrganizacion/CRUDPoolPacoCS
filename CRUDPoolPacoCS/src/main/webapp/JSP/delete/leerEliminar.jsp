<%-- 
    Document   : leerEliminar
    Created on : 22 oct. 2018, 19:31:32
    Author     : paco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Elegir ave a eliminar</title>
    </head>
    <body>
        <h1>SELECCIONE UN O VARIAS AVES PARA ELIMINAR</h1>

        <form action="<%=request.getContextPath()%>/Realizar" method="post">
            <button type="submit" value="cancelar" name="eliminarAve">Cancelar</button>
            <button type="submit" value="aceptar" name="eliminarAve">Eliminar</button>
        </form>
    </body>
</html>
