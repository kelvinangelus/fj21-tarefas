<%-- 
    Document   : menu
    Created on : 28/10/2022, 16:13:06
    Author     : Kelvin Angelus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
	<h2>Página inicial da Lista de Tarefas</h2>
            <p>Bem vindo, ${usuarioLogado.login}</p>
		<a href="listaTarefas">Clique aqui</a> para acessar a lista de tarefas
                <br/>
                <a href="logout">Sair do sistema</a>
    </body>
</html>
