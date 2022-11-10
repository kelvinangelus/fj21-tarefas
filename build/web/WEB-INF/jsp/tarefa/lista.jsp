<%-- 
    Document   : lista
    Created on : 15/10/2022, 20:19:54
    Author     : Kelvin Angelus
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<!-- Requisição vem de TarefasController.lista(Model model) -->
<html>
    <head>
        <script	type="text/javascript" src="resources/js/jquery-3.6.1.js"></script>
    </head>
    <body>
        <script type="text/javascript">
            function finalizaAgora(id){
                <!-- a callback function do método post é chamada quando o status da resposta da requisição é 200 (sucesso) -->
                $.post("finalizaTarefa", {'id': id}, function(resposta){
                        //Selecionando o elemento html através da ID 
                        //e alterando o HTML dele
                        $("#tarefa_"+id).html(resposta); <!-- Substitui todo o conteúdo da "<tr>" pelo conteúdo de "finalizada.jsp" -->
                                                         <!-- pois a página é retornada pelo método "finaliza" em TarefasController
                       /* $("#tarefa_"+id).html("Finalizada");
                        $("#tarefa_data_"+id).html(resposta);*/
                        //alert(resposta);
                });
            }
            
            function removeTarefa(id){
                //alert("Removendo a tarefa " + id);
                $("#remover_"+id).closest("tr").hide(); //Remove a linha no navegador
                $.post("removeTarefa",{'id':id}); //Executa a ação removeTarefa em TarefasController
                                                  //O Spring busca o atributo 'id' no parâmetro 'Tarefa'
            }
        </script>
	<a href="novaTarefa">Criar nova	tarefa</a>
	<br /> <br/>
	<table>
            <tr>
               	<th>Id</th>
		<th>Descrição</th>
		<th>Finalizada?</th>
		<th>Data de finalização</th>
            </tr>
            <c:forEach	items="${tarefas}" var="tarefa">
                <tr id="tarefa_${tarefa.id}">
                    <td>${tarefa.id}</td>
                    <td>${tarefa.descricao}</td>
                    <c:if test="${tarefa.finalizado eq false}">
                        <!--  id="tarefa_{tarefa.id}" -->
                        <td>
                            <a href="#" onclick="finalizaAgora(${tarefa.id})">
                                Finaliza agora!
                            </a>
                        </td>
                    </c:if>
                    <c:if test="${tarefa.finalizado eq true}">
                        <td>Finalizada</td>
                    </c:if>
                    <!--  id="tarefa_data_{tarefa.id}" -->
                    <td> 
                        <fmt:formatDate	value="${tarefa.dataFinalizacao.time}" pattern="dd/MM/yyyy"/>
                    </td>
                    <td>
                        <!-- Envia o parâmetro "id" na requisição para identificar a tarefa a ser removida -->
                        <a href="remove_Tarefa?id=${tarefa.id}">Remover<a/>
                    </td>
                    <td>
                        <!-- Envia o parâmetro "id" na requisição para identificar a tarefa a ser mostrada -->
                        <a href="mostraTarefa?id=${tarefa.id}">Alterar</a>
                    </td>
                    <td id="remover_${tarefa.id}">
                        <a href="#" onclick="removeTarefa(${tarefa.id})">Excluir</a>  <!-- Chama método javascript -->
                    </td>
		</tr>
            </c:forEach>
	</table>
    </body>
</html>
