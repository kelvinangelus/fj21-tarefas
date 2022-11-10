<%-- 
    Document   : formulario-login
    Created on : 28/10/2022, 15:54:08
    Author     : Kelvin Angelus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link type="text/css" href="resources/css/tarefas.css"rel="stylesheet" />
    </head>
    <body>
        <h2>Página de Login das	Tarefas</h2>
            <form action="efetuaLogin" method="post">
		Login:	<input	type="text" name="login" /> <br	/>
		Senha:	<input	type="password" name="senha" /> <br />
                <input	type="submit" value="Entrar nas tarefas" />
            </form>
    </body>
</html>
