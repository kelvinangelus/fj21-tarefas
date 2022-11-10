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
public class RemoveTarefa {
    
    public static void main(String[] args){
        
        EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("tarefas");
        
        EntityManager gerente = fabrica.createEntityManager();
        
        Tarefa encontrada = gerente.find(Tarefa.class, 2L);
        
        gerente.getTransaction().begin();
        gerente.remove(encontrada);
        gerente.getTransaction().commit();
    } 
}
