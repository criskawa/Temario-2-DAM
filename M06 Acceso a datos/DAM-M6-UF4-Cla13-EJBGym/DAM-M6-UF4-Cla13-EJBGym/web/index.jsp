<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EJB Gym</title>
    </head>
    <body>
        <h1>Gym DAM</h1>
        <h2>-------------------------------------------------</h2>
        <h2>Obtener datos</h2>
        <form action="ServletActividad" method="POST">Presiona el botón para obtener las actividades.
            <input type="submit" name="enviar" value="Enviar" />
        </form>
        <form action="ServletSocio" method="POST">Presiona el botón para obtener los socios.
            <input type="submit" name="enviar" value="Enviar" />
        </form>
        <form action="ServletMatricula" method="POST">Presiona el botón para obtener las matriculas.
            <input type="submit" name="enviar" value="Enviar" />
        </form>
        <br>
        <form action="Buscar.html" method="POST">Presiona el botón para hacer una busqueda personalizada.
            <input type="submit" name="enviar" value="Enviar" />
        </form>
        <h2>-------------------------------------------------</h2>
        <h2>Gestión Actividades</h2>
        <form action="insertarActividad.html" method="POST">Presiona el botón para añadir las actividades.
            <input type="submit" name="insertActividad" value="Insertar" />
        </form>
        <form action="modificarActividad.html" method="POST">Presiona el botón para modificar las actividades.
            <input type="submit" name="modActividad" value="Modificar" />
        </form>
        <form action="eliminarActividad.html" method="POST">Presiona el botón para eliminar las actividades.
            <input type="submit" name="elActividad" value="Eliminar" />
        </form>
        <h2>-------------------------------------------------</h2>
        <h2>Gestión Socios</h2>
        <form action="insertarSocio.html" method="POST">Presiona el botón para añadir los socios.
            <input type="submit" name="insertSocio" value="Insertar" />
        </form>
        <form action="modificarSocio.html" method="POST">Presiona el botón para modificar los socios.
            <input type="submit" name="modSocio" value="Modificar" />
        </form>
        <form action="eliminarSocio.html" method="POST">Presiona el botón para eliminar los socios.
            <input type="submit" name="elSocio" value="Eliminar" />
        </form>
        <h2>-------------------------------------------------</h2>
        <h2>Gestión Matrículas</h2>
        <form action="insertarMatricula.html" method="POST">Presiona el botón para añadir las matriculas.
            <input type="submit" name="insertSocio" value="Insertar" />
        </form>
        <form action="modificarMatricula.html" method="POST">Presiona el botón para modificar las matriculas.
            <input type="submit" name="modSocio" value="Modificar" />
        </form>
        <form action="eliminarMatricula.html" method="POST">Presiona el botón para eliminar las matriculas.
            <input type="submit" name="elSocio" value="Eliminar" />
        </form>
        <h2>-------------------------------------------------</h2>
    </body>
</html>
