<%-- 
    Document   : mostra
    Created on : 19/10/2022, 20:30:30
    Author     : Kelvin Angelus
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3>Alterar tarefa - ${tarefa.id}</h3>
        <form action="alteraTarefa" method="post">
            <input type="hidden" name="id" value="${tarefa.id}" />
            
            Descrição:<br />
            <textarea name="descricao" cols="100" rows="5">
                ${tarefa.descricao}
            </textarea>
            <!-- cols="100" rows="5" {tarefa.descricao}-->
            <br />
            
            Finalizado? <input type="checkbox" name="finalizado"
            value="true" ${tarefa.finalizado? 'checked': ''}/><br />
            
            Data de finalização: <br />
            <input type="text" name="dataFinalizacao" value="<fmt:formatDate value="${tarefa.dataFinalizacao.time}" pattern="dd/MM/yyyy" />" /><br/>
            <input type="submit" value="Alterar"/>
        </form>
    </body>
</html>
