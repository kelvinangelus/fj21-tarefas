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
public class GeraTabelas {
    
    public static void main(String[] args) {
	       
        //Necessário para comunicar com o JPA
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("tarefas");
        
        factory.close();
    }
}
