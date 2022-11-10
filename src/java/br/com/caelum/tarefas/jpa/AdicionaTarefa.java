/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.caelum.tarefas.jpa;

import br.com.caelum.jdbc.modelo.Tarefa;
import java.util.Calendar;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Kelvin Angelus
 */
public class AdicionaTarefa {
  
    public static void main(String[] args) {
    
        Tarefa tarefa = new Tarefa();
        tarefa.setDescricao("Estudar JPA e Hibernate");
        tarefa.setFinalizado(true);
        tarefa.setDataFinalizacao(Calendar.getInstance());

        //Necessário para comunicar com o JPA
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("tarefas");
        EntityManager manager = factory.createEntityManager();

        manager.getTransaction().begin();								
        manager.persist(tarefa);
        manager.getTransaction().commit();

        System.out.println("ID da tarefa: " + tarefa.getId());

        manager.close();
    
    }
}
