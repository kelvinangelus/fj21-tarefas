/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.caelum.jdbc.dao;

import Util.ConnectionFactory;
import br.com.caelum.jdbc.modelo.Tarefa;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Kelvin Angelus
 */

//Configurando esta classe como bean so Spring
//@Component
@Repository 
public class TarefaDao {

    private Connection connection;

    //Construtor
    @Autowired
    public TarefaDao(DataSource dataSource) throws SQLException { //Injeção de dependência
        this.connection = dataSource.getConnection();
    }
    
    public void adiciona(Tarefa tarefa){
        /*String sql = "insert into tarefas " +
                "(descricao, finalizado, dataFinalizacao)" +
                " values(?,?,?)";*/
        
        String sql = "insert into tarefas " +
                "(descricao, finalizado)" +
                " values(?,?)";
    
        try{
            //prepared statement para inserção
            PreparedStatement stmt = connection.prepareStatement(sql);

            
            //Seta os valores
            stmt.setString(1, tarefa.getDescricao());
            stmt.setBoolean(2, tarefa.isFinalizado());
            //stmt.setDate(3, new Date(tarefa.getDataFinalizacao().getTimeInMillis())); 

            //Executa a consulta
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Tarefa> lista() throws SQLException {
        
        String sql = "select * from tarefas";
        
        List<Tarefa> tarefas = new ArrayList<>();
        
        PreparedStatement stmt = this.connection.prepareStatement(sql);
        
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            
            Tarefa tarefa = new Tarefa();
            tarefa.setId(rs.getLong("id"));
            tarefa.setDescricao(rs.getString("descricao"));
            
            Boolean finalizado = rs.getBoolean("finalizado");
            if(finalizado){
                tarefa.setFinalizado(finalizado);
            
                //Montando a data através do Calendar
                Calendar data = Calendar.getInstance();
                        
                Date dataBanco = rs.getDate("dataFinalizacao");
            
                if(dataBanco != null){
                    data.setTime(dataBanco); //setTime() não pode receber null como argumento  
                    tarefa.setDataFinalizacao(data); //Se a data no banco estiver null, atribua a data de hoje
                }           
            }
            
            tarefas.add(tarefa);
        }
        
        rs.close();
        stmt.close();
        
        return tarefas;
    }

    public void remove(Tarefa tarefa) throws SQLException {
        
        String sql = "delete from tarefas where id = ?";
        
        PreparedStatement stmt = this.connection.prepareStatement(sql);
        
        stmt.setLong(1, tarefa.getId());
        
        stmt.execute();
        
        stmt.close();
        
    }

    public Tarefa buscaPorId(Long id) throws SQLException {
        
        Tarefa tarefa = new Tarefa();
        
        //List<Tarefa> tarefas = new ArrayList<>();
        List<Tarefa> tarefas = this.lista();
        
        //tarefas = this.lista();
        
        for(Tarefa t : tarefas){
            if (Objects.equals(t.getId(), id)){
                return t;
            }
        } 
        
        return null;
    }

    public void altera(Tarefa tarefa) throws SQLException {
        
        String sql = "update tarefas set descricao = ?, finalizado = ?, dataFinalizacao = ? where tarefas.id = ?";
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        java.util.Date utilDate = new java.util.Date();
        
        utilDate = tarefa.getDataFinalizacao().getTime();
        //System.out.println(utilDate);    

        Date sqlDate = new Date(utilDate.getTime()); 
            
        stmt.setString(1, tarefa.getDescricao());
        stmt.setBoolean(2, tarefa.isFinalizado());
        stmt.setDate(3, sqlDate);
        stmt.setLong(4, tarefa.getId());
        
        stmt.execute();
        stmt.close();
        
        }

    public void finaliza(Long id) throws SQLException {
        //System.out.println("A finalização da tarefa ocorreu com sucesso"); //To change body of generated methods, choose Tools | Templates.
                
        String sql = "update tarefas set finalizado = ?, dataFinalizacao = ? where tarefas.id = ?";
        
        PreparedStatement stmt = connection.prepareStatement(sql);
        
        Calendar dataCalendar = Calendar.getInstance(); //Recebe a data de hoje
        java.util.Date utilDate = dataCalendar.getTime(); //Converte Calendar para util.Date
        Date sqlDate = new Date(utilDate.getTime()); //Converte util.Date para sql.Date
        
        stmt.setBoolean(1, true);
        stmt.setDate(2, sqlDate);
        stmt.setLong(3, id);
        
        stmt.execute();
        stmt.close();
    }
    
}
