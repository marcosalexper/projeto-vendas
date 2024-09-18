/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import java.sql.Connection;

/**
 *
 * @author Win11
 */
public class FuncionariosDAO {
    
    //Conexao
     private Connection con;

    public FuncionariosDAO() {
        this.con = new ConnectionFactory().getConnection();
    }
    
}
