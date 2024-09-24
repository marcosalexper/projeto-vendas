/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Clientes;
import br.com.projeto.model.ItemVenda;
import br.com.projeto.model.Produtos;
import br.com.projeto.model.Vendas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos Alexandre Pereira
 */
public class ItemVendaDAO {
    
    private Connection con;
    
    public ItemVendaDAO() {
        this.con = new ConnectionFactory().getConnection();
    }

    //Metodo que cadastra Itens
    public void cadastraItem(ItemVenda obj) {
        try {
            //Criar comando sql
            String sql = "insert into tb_itensvendas(venda_id,produto_id,qtd,subtotal)"
                    + "values(?,?,?,?)";

            //Conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, obj.getVenda().getId());
            stmt.setInt(2, obj.getProduto().getId());
            stmt.setInt(3, obj.getQtd());
            stmt.setDouble(4, obj.getSubtotal());

            //Executar o comando sql
            stmt.execute();
            stmt.close();
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro:" + erro);
            
        }        
    }

    //Metodo que lista itens de uma venda por id
    public List<ItemVenda> listaItensPorVenda(int venda_id) {
        
        List<ItemVenda> lista = new ArrayList<>();
        
        try {

            //Criar, organizar e executar o sql
            String query = "select i.id, p.descrica, i.qtd, p.preco, i.subtotal from tb_itensvenda as i "
                    + " inner join tb_produtos as p on(i,produto_id = p.id) where i.venda_id = ?";
            
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, venda_id);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                ItemVenda item = new ItemVenda();
                Produtos prod = new Produtos();
                
                item.setId(rs.getInt("i.id"));
                prod.setDescricao(rs.getString("p.descricao"));
                item.setQtd(rs.getInt("i.qtdl"));
                prod.setPreco(rs.getDouble("p.preco"));
                item.setSubtotal(rs.getDouble("i.subtotal"));
                
                item.setProduto(prod);
                
                lista.add(item);
                
            }
            return lista;
            
        } catch (SQLException erro) {
            
            JOptionPane.showMessageDialog(null, "Erro..." + erro);
            return null;
        }
    }    
    
}
