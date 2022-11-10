/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.caelum.jdbc.modelo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//import static javax.swing.text.StyleConstants.Size;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;


/**
 *
 * @author Kelvin Angelus
 */
@Entity
public class Tarefa {

    public Tarefa() {
    }

    public Tarefa(String descricao, boolean finalizado, Calendar dataFinalizacao) {
        //this.id = id;
        this.descricao = descricao;
        this.finalizado = finalizado;
        this.dataFinalizacao = dataFinalizacao;
    }
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Size(min=5)
    public String descricao;
    
    //@Min(value = 3)
    //private int idade;
    
    private boolean finalizado;
    
    //Anotação para o Spring MVC converter automaticamente 
    //a data no formato brasileito para um objeto Calendar
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private Calendar dataFinalizacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    public Calendar getDataFinalizacao() {
        return dataFinalizacao;
    }

    public void setDataFinalizacao(Calendar dataFinalizacao) {
        this.dataFinalizacao = dataFinalizacao;
    }
    
}
