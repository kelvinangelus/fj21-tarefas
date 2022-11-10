/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.caelum.tarefas.jpa;

import br.com.caelum.jdbc.modelo.Tarefa;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Kelvin Angelus
 */
public class CarregaTarefa {
    
    public static void main(String[] args){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("tarefas");
        EntityManager manager = factory.createEntityManager();
    
        Tarefa encontrada = manager.find(Tarefa.class, 2L);
        System.out.println(encontrada.getDescricao());
        
        manager.close();
    }
}
