/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Clientes;
import br.com.projeto.model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos Alexandre Pereira
 */
public class VendasDAO {
    
    private Connection con;
    
    public VendasDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

//Cadastrar Venda
    public void cadastrarVenda(Vendas obj) {
        try {
            //Criar comando sql
            String sql = "insert into tb_vendas(cliente_id,data_venda,total_venda,observacoes)"
                    + "values(?,?,?,?)";

            //Conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, obj.getCliente().getId());
            stmt.setString(2, obj.getData_venda());
            stmt.setDouble(3, obj.getTotal_venda());
            stmt.setString(4, obj.getObs());

            //Executar o comando sql
            stmt.execute();
            stmt.close();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro:" + erro);
            
        }
    }

    //Metodo Retorna Ultima Venda
    public int retornaUltimaVenda() {
        try {
            int idvenda = 0;
            
            String sql = "select max(id)id from tb_vendas";
            PreparedStatement ps = con.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                Vendas p = new Vendas();
                
                p.setId(rs.getInt("id"));
                
                idvenda = p.getId();
            }
            return idvenda;
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
    }

    //Metodo que filtra Vendas por Datas
    public List<Vendas> listarVendasPorPeriodo(String data_inicio, String data_fim) {
        try {
            
            List<Vendas> lista = new ArrayList<>();

            //Criar, organizar e executar o sql
            String sql = "select v.id, v.data_venda, c.nome, v.total_venda, v.observacoes from tb_vendas as v "
                    + " inner join tb_clientes as c on(v.cliente id=c.id)where v.data_venda BETWEEN ? AND ?";
            
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, data_inicio);
            stmt.setString(2, data_fim);
            
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Vendas obj = new Vendas();
                Clientes c = new Clientes();
                
                obj.setId(rs.getInt("v.id"));
                obj.setData_venda(rs.getString("v.data_venda"));
                c.setNome(rs.getString("c.nome"));
                obj.setTotal_venda(rs.getDouble("v.total_venda"));
                obj.setObs(rs.getString("v.observacoes"));
                
                obj.setCliente(c);
                
                lista.add(obj);
                
            }
            return lista;
            
        } catch (SQLException erro) {
            
            JOptionPane.showMessageDialog(null, "Erro..." + erro);
            return null;
        }
    }    
    
}
