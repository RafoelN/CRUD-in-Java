package br.com.agenda.aplicacao;

import java.util.Date;
import java.util.Scanner;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contatos;

public class Program {

	public static void main(String[] args) {
		 
		ContatoDAO contatoDAO = new ContatoDAO();
		Scanner sc = new Scanner(System.in);
		
		int op = 0;
		
		do {
			
			System.out.println("[1] - Inserir contato");
			System.out.println("[2] - Atualizar cadastro");
			System.out.println("[3] - Deletar contato por ID");
			System.out.println("[4] - Relatório de cadastros");
			System.out.println("[0] - Exit");
			op = sc.nextInt();
			
			switch (op){
			
				case 1:
					Contatos contato = new Contatos();
					System.out.println("Insira o nome: ");
					sc.nextLine();
					String nome = sc.nextLine();
					System.out.println("Informe a idade: ");
					int idade = sc.nextInt();
					
					contato.setNome(nome);
					contato.setIdade(idade);
					contato.setDataCadastro(new Date());
					contatoDAO.save(contato);
					break;
					
				case 2:
					
					Contatos attContato = new Contatos();
					
					System.out.println("Insira o novo nome: ");
					sc.nextLine();
					String attNome = sc.nextLine();
					attContato.setNome(attNome);
					
					System.out.println("Insira a nova idade: ");
					int attIdade = sc.nextInt();
					attContato.setIdade(attIdade);
					
					System.out.println("Insira o ID do cadastro que deseja atualizar: ");
					int attID = sc.nextInt();
					attContato.setId(attID);
					
					attContato.setDataCadastro(new Date());
					contatoDAO.update(attContato);
					
					break;
				case 3:
					
					System.out.println("Digite o ID que deseja deletar: ");
					int id = sc.nextInt();
					contatoDAO.deleteByID(id);
					break;
				case 4:
					
					for(Contatos c: contatoDAO.getContatos()) {
						System.out.println(c);
					}
					break;
			}
		} while (op != 0);
		
		sc.close();
		System.out.println("Finalizando sistema...");
	}	
}
