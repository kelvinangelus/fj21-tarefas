/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.caelum.tarefas.jpa;

import br.com.caelum.jdbc.modelo.Tarefa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Kelvin Angelus
 */
public class BuscaTarefas {
    
    public static void main(String[] args){
        
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("tarefas");
        
        EntityManager manager = factory.createEntityManager();
        
        Query query = manager.createQuery("select t from Tarefa as t " +
            "where t.finalizado = :paramFinalizado");
        
        query.setParameter("paramFinalizado", true);
        
        List<Tarefa> lista = query.getResultList();
        
        for(Tarefa t : lista){
            System.out.println(t.getDescricao());
        }
        
        manager.close();
    }
}
