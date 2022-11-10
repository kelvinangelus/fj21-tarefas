<%-- 
    Document   : formulario
    Created on : 12/10/2022, 13:52:13
    Author     : Kelvin Angelus
--%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"	%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
        <h3>Adicionar tarefas</h3>
        <form action="adicionaTarefa" method="post"> <!-- A ação adicionaTarefa está em TarefasController -->
            Descrição: <br />
            <textarea name="descricao" rows="5" cols="100"></textarea><br />
            
            <!-- O atributo path indica com que	atributo essa mensagem de validação está relacionada. -->
            <form:errors path="tarefa.descricao" cssStyle="color:red"/><br /> 
            <input type="submit" value="Adicionar">
        </form>
    </body>
</html>
