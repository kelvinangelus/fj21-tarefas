/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.caelum.tarefas.controller;

import br.com.caelum.jdbc.dao.TarefaDao;
import br.com.caelum.jdbc.modelo.Tarefa;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Kelvin Angelus
 */

//Informa ao Spring MVC que esta classe é a controladora e os métodos são ações (actions)
@Controller 
public class TarefasController {
    
    private final TarefaDao dao;
    
    @Autowired
    public TarefasController(TarefaDao dao){
        this.dao = dao;
    }
    
    @RequestMapping("novaTarefa")
    public String form(){
        return "tarefa/formulario";
    }
    //Consultar página 156 de apostila-java-web.pdf para entender 
    //por que o parâmetro "tarefa" contém a informação preenchida no formulário
    @RequestMapping("adicionaTarefa") //Requisição vem de formulario.jsp
    public String adiciona(@Valid Tarefa tarefa, BindingResult result){ //Antes da chamada do método é executada a validação
        if(result.hasFieldErrors("descricao")){                         //O Spring MVC guarda o resultado da validação em um objeto BindingResult
            return "tarefa/formulario"; //Se houver erro, é devolvida a página formulario.jsp com o local do erro
        }
        
        //TarefaDao dao = new TarefaDao();
        dao.adiciona(tarefa); 
        return "tarefa/adicionada";
    }
    
    @RequestMapping("listaTarefas") //Requisição vem do navegador: http://localhost:8080/fj21-tarefas/listaTarefas
    public String lista(Model model) throws SQLException{
        //TarefaDao dao = new TarefaDao();
        //List<Tarefa> tarefas = dao.lista();
        model.addAttribute("tarefas", dao.lista());
        return "tarefa/lista"; //Devolve uma String como retorno do método que indica o	caminho	para o JSP
                               //Retorna o lista.jsp com a lista de tarefas como atributo 
    }
    
    @RequestMapping("removeTarefa")
    //public String remove(Tarefa tarefa) throws SQLException{
    public void remove(Tarefa tarefa) throws SQLException{
        //TarefaDao dao = new TarefaDao(); //Chama o construtor de TarefaDao. Abre uma nova conexão com o banco
        dao.remove(tarefa);
        //return "redirect:listaTarefas";
    }
    
    @RequestMapping("remove_Tarefa")
    //public String remove(Tarefa tarefa) throws SQLException{
    public String remove1(Tarefa tarefa) throws SQLException{
        //TarefaDao dao = new TarefaDao(); //Chama o construtor de TarefaDao. Abre uma nova conexão com o banco
        dao.remove(tarefa);
        return "redirect:listaTarefas";
    }
    
    //Requisição vem de lista.jsp
    @RequestMapping("mostraTarefa")
    public String mostra(Long id, Model model) throws SQLException{
        //TarefaDao dao = new TarefaDao();
        model.addAttribute("tarefa", dao.buscaPorId(id));
        //System.out.println(dao.buscaPorId(id).descricao);
        return "tarefa/mostra"; //"tarefa/mostra" 
    }
    
    @RequestMapping("alteraTarefa")
    public String altera(Tarefa tarefa) throws SQLException{
        //TarefaDao dao = new TarefaDao();
        dao.altera(tarefa);
        return "redirect:listaTarefas";
    }
    
    //@ResponseBody
    @RequestMapping("finalizaTarefa") //Requisição vem da função do jquery em lista.jsp
    public String finaliza(Long id, Model model) throws IOException, SQLException{
	//TarefaDao dao = new TarefaDao();
	dao.finaliza(id);
	model.addAttribute("tarefa", dao.buscaPorId(id));
        return "tarefa/finalizada";
}

    /*public void	finaliza(Long id, HttpServletResponse response) throws IOException, SQLException	{
	TarefaDao dao = new TarefaDao();
	dao.finaliza(id);
	Date dataDeFinalizacao = dao.buscaPorId(id).getDataFinalizacao().getTime();
	String data = new SimpleDateFormat("dd/MM/yyyy").format(dataDeFinalizacao);
	response.getWriter().write(data);
	response.setStatus(200);
}*/
    /*public String finaliza(Long id, Model model) throws SQLException{
        TarefaDao dao = new TarefaDao();
        dao.finaliza(id);
        model.addAttribute("tarefa", dao.buscaPorId(id));
        return "tarefa/finalizada";
    }*/
    
    /*public void finaliza(Long id) throws SQLException{
        TarefaDao dao = new TarefaDao();
        dao.finaliza(id);
    }*/
}
