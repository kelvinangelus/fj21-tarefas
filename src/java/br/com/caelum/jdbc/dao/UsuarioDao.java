/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.caelum.jdbc.dao;

import Util.ConnectionFactory;
import br.com.caelum.jdbc.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kelvin Angelus
 */
public class UsuarioDao {
    
    private Connection connection;

    //Construtor
    public UsuarioDao() throws ClassNotFoundException {
        this.connection = new ConnectionFactory().getConnection();
    }

    public boolean existeUsuario(Usuario usuario) throws SQLException {
        
        //Resgatar a lista de usuários e verificar se  
        //existe o usuário com o login e senha informados 
     
        Boolean existe = false;
        
        String loginForm = usuario.getLogin();
        String senhaForm = usuario.getSenha();
        
        String sql = "select * from usuarios";
        
        //List<Usuario> usuarios = new ArrayList<>();
        
        PreparedStatement stmt = this.connection.prepareStatement(sql);
        
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
            String loginBanco = rs.getString("login");
            String senhaBanco = rs.getString("senha");
            
            if(loginBanco.equals(loginForm) && senhaBanco.equals(senhaForm)){
                existe = true;
                break;   
            }
        }
        
        rs.close();
        stmt.close();
        
        return existe;
    }
}
