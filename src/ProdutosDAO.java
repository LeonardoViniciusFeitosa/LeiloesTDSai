/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
        String sql = "INSERT INTO produtos(nome, valor, status) VALUES " + "(?,?,?)";
        
        try{
        conn = new conectaDAO().connectDB();
        PreparedStatement stmt = this.conn.prepareStatement(sql);
        stmt.setString(1, produto.getNome());
        stmt.setInt(2, produto.getValor());
        stmt.setString(3, produto.getStatus());
        stmt.execute();
        JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso!");
        
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Não foi possível adicionar o produto. ERRO: " + e.getMessage());
        }
        ;
        
    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
  
        String sql = "SELECT * FROM PRODUTOS";
        
        try{
        conn = new conectaDAO().connectDB();
        PreparedStatement stmt = this.conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        
        while(rs.next()){
        ProdutosDTO produto = new ProdutosDTO();
        produto.setId(rs.getInt("id"));
        produto.setNome(rs.getString("nome"));
        produto.setValor(rs.getInt("valor"));
        produto.setStatus(rs.getString("status"));
        
        listagem.add(produto);
        }
        
       
        }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos. ERRO:" + e.getMessage());
        }
        return listagem;
    }
    
    
    
    public void venderProduto(int id){
   String sql = "UPDATE produtos SET status = 'vendido' WHERE id = ?";
   
   try{
   conn = new conectaDAO().connectDB();
   
   PreparedStatement stmt = conn.prepareStatement(sql);
   stmt.setInt(1, id);
   
   int rs = stmt.executeUpdate();
   
   if (rs > 0){
   JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
   } else{
   JOptionPane.showMessageDialog(null, "Produto não encontrado.");
   }
   } catch(Exception e){
    JOptionPane.showMessageDialog(null, "Erro ao realizar venda. ERRO: " + e.getMessage());
           }
    }
        
}

