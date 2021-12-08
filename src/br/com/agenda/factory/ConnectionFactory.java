package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	//Nome do usuario mySQL
	private static final String USERNAME = "root";
	
	//Senha do banco
	private static final String PASSWORD = "";

	//Caminho para o banco de dados
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";
	
	public static Connection createConnectionToMySQL() throws Exception {
		//Carregando a classe pela JVM
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//Cria conexão com banco de dados
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return connection;
	}
	
	public static void main(String[] args) throws Exception {
		
		//Recuperar conexão com banco de dados
		Connection con = createConnectionToMySQL();
		
		//Verificando se há conexão ativa
		if (con != null) {
			System.out.println("Conexão obtida com sucesso!");
			con.close();
		}
	}
}
