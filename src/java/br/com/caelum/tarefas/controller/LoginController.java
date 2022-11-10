/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.caelum.tarefas.controller;

import br.com.caelum.jdbc.dao.UsuarioDao;
import br.com.caelum.jdbc.modelo.Usuario;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Kelvin Angelus
 */

@Controller
public class LoginController {
   
    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:loginForm";
    }
    
    //Requisição da barra de endereço do navegador
    @RequestMapping("loginForm")
    public String loginForm(){
        return "formulario-login";
    }
    
    //Requisiçao vem de formulario-login.jsp
    @RequestMapping("efetuaLogin") 
    public String efetuaLogin(Usuario usuario, HttpSession session) throws ClassNotFoundException, SQLException{
        if(new UsuarioDao().existeUsuario(usuario)){
            session.setAttribute("usuarioLogado", usuario);
            return "menu";
        }
        return "redirect:loginForm";
    }
}
