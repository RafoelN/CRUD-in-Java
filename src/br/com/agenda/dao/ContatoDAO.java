package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contatos;

public class ContatoDAO {
	
	public void save (Contatos contato) {
		
		String sql = "INSERT INTO contatos(nome, idade, dataCadastro) VALUES (?, ?, ?)";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		try {
			//Cria conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//Criando prepareStatement para executar uma query
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date (contato.getDataCadastro().getTime()));
			
			//Executando a query
			pstm.execute();
			System.out.println("Contato salvo com sucesso!\n");
			
		} catch (Exception e) {	
			e.printStackTrace();
		} finally {
			//Fechando conexões
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public List<Contatos> getContatos(){
		
		String sql = "SELECT * FROM contatos";
		
		List<Contatos> listContatos = new ArrayList<>();
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		//Classe para puxar dados do banco **SELECT
		ResultSet rset = null;
		
		try {
			
			//Cria conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//Criando prepareStatement para executar uma query
			pstm = conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				
				Contatos contato = new Contatos();
				
				contato.setId(rset.getInt("id"));
				contato.setNome(rset.getString("nome"));
				contato.setIdade(rset.getInt("idade"));
				contato.setDataCadastro(rset.getDate("dataCadastro"));
				
				listContatos.add(contato);
				
			}		
		} catch (Exception e) {
			e.printStackTrace();		
		} finally {
			try {
				
				if (rset != null) {
					rset.close();
				}
				
				if (pstm != null) {
					pstm.close();
				}
				
				if (conn != null) {
					conn.close();
				}
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
			}
		}
		return listContatos;
	}
	
	//Atualizando cadastros
	public void update (Contatos contato) {
		
		String sql = "UPDATE contatos SET nome = ?, idade = ?, datacadastro = ?" + 
				"WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			
			pstm.setInt(4, contato.getId());
			
			pstm.execute();
			System.out.println("Cadastro atualizado com sucesso!\n");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public void deleteByID (int ID) {
		
		Scanner sc = new Scanner (System.in);
		String sql = "DELETE FROM contatos WHERE id = ?";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		try {
					
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			
			pstm.setInt(1, ID);
			
			int confirmacao = 0;
			
			System.out.println("Tem certeza que deseja remover esse cadastro?");
			System.out.println("[1] - SIM");
			System.out.println("[2] - NÃO");
			confirmacao = sc.nextInt();
			
			if (confirmacao == 1) {
				pstm.execute();
				System.out.println("Cadastro removido com sucesso!\n");
			}
				
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
