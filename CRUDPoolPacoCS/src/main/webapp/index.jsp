<%-- 
    Document   : index
    Created on : 22 oct. 2018, 19:25:10
    Author     : paco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="CSS/normalizer.css">
        <link rel="stylesheet" type="text/css" href="CSS/estilos.css">
        <title>CRUD INICIO</title>
    </head>
    <body>
        <h1>Bienvenido al maravilloso mundo de las aves, elija una opción:</h1>

        
            <img id="pajarosIndex" src="IMG/pajaros.png">
            <form action="Operacion" method="post">
                <ol>
                    <li><button type="submit" value="Insertar" name="operacion">Insertar ave</button></li>
                    <li><button type="submit" value="Visualizar" name="operacion">Visualizar aves</button></li>
                    <li><button type="submit" value="Actualizar" name="operacion">Actualizar ave</button></li>
                    <li><button type="submit" value="Eliminar" name="operacion">Eliminar aves</button></li>
                </ol>
                
                </form>
            
        
    </body>
</html>
