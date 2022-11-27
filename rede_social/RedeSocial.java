package rede_social;

import excecoes.*;
import java.util.Scanner;

public class RedeSocial {
	
	static final int TAM = 100;
	static Perfil[] perfil = new Perfil[TAM];
	static int qtdPerfis = 0;
	static Post[] posts = new Post[TAM];
	static int qtdPosts = 0;
	static int login = 0;
	static Scanner entrada = new Scanner (System.in);
	static String opcao;
	
	public static void main(String[] args) throws UserNotFoundException, InvalidPasswordException, Exception {
		
		String cad = "C";
		String ent = "E";
		String sai = "S";
		do {
			System.out.println("Menu:\n ");
			System.out.println("C - Para cadastrar novo usuário");
			System.out.println("E - Para fazer login na Rede Social");
			System.out.println("S - Para sair do sistema.");
			opcao = entrada.nextLine().toUpperCase();
			if (opcao.equals(cad)) {
				cadastroPerfil();
			} else if (opcao.equals(ent)) {
				fazerLogin();
			}
		}while (!opcao.equals(sai));
		System.out.println("O sistema foi finalizado.");
	}
	
	static void cadastroPerfil() {
		Perfil usuario = new Perfil();
		
		System.out.println("Digite seu Nome:");
		 usuario.nome = entrada.nextLine().trim();
		
			while (ValidaCamposBranco(usuario.nome)== true ) {				
			System.out.println ("O nome não pode estar em branco!");
			
			System.out.println("Digite seu Nome:");
			usuario.nome = entrada.nextLine().trim();
			}
			
					
		System.out.println("Digite seu Login:");	
		usuario.login = entrada.nextLine().trim().toUpperCase();
		
			while (ValidaCamposBranco(usuario.login)== true) {				
				System.out.println ("O login não pode estar em branco!");
				System.out.println("Digite seu Login:");
				usuario.login = entrada.nextLine().trim().toUpperCase();
				}
			
			while (buscarPerfis (usuario.login)== true) {				
				System.out.println ("Esse Login já existe!");
				
				System.out.println("Digite novamente o Login:");
				usuario.login = entrada.nextLine().trim().toUpperCase();
				}
		
		System.out.println("Digite sua Senha:");
		usuario.senha = entrada.nextLine();
		
			while (ValidaCamposBranco (usuario.senha) == true) {				
				System.out.println ("A senha não pode estar em branco! \n");
				System.out.println("Digite sua Senha:");
				usuario.senha = entrada.nextLine();
			}
			
		System.out.println("\n Cadastro feito com sucesso! \n");
		perfil[qtdPerfis] = usuario;
		qtdPerfis++;
	}
	
	static void fazerLogin() throws UserNotFoundException, InvalidPasswordException, Exception {
		Perfil usuario2 = new Perfil();
		Perfil usuario = new Perfil();
		System.out.println("Digite o seu login para entrar na Rede Social: ");
		usuario2.login = entrada.nextLine().toUpperCase();
		usuario = buscaLogin(usuario2.login);
		
		if (qtdPerfis == 0) {
			System.out.println("Você precisar fazer um cadastro primeiro! Vou te levar até lá..");
			System.out.println("Aqui estamos no cadastro da Rede Social");
			cadastroPerfil();		
		}else {
			try {
				if (buscarPerfis(usuario2.login) == false) {
					System.out.println("Login não encontrado!");
					fazerLogin();
					
				} else {
					System.out.println("Digite sua senha: ");
					usuario2.senha = entrada.nextLine();
					if (usuario2.senha.equals(usuario.senha)) {
						System.out.println("Bem vindo, "  + usuario2.login + "!");
						menuDoUsuario();
					} else {
						System.out.println("A senha está incorreta!");
						fazerLogin();
					}
			}
			}catch (UserNotFoundException e) {
				System.out.println("Usuário não encontrado");
				
			}catch (InvalidPasswordException e) {
				System.out.println("A senha não corresponde a mesma feita no cadastro! ");
				
			}catch (Exception e) {
				System.out.println("Erro! Por favor execute o programa novamente.");
			}
		}
	}
	
	static void menuDoUsuario() {
		String post = "P";
		String time = "T";
		String logout = "L";
		do {
			System.out.println("Menu:\n ");
			System.out.println("P - Para fazer um novo post.");
			System.out.println("T - Para acessar a Timeline.");
			System.out.println("L - Para fazer logout da Rede Social.");
			opcao = entrada.nextLine().toUpperCase();
			if (opcao.equals(post)) {						
				fazerPostagem();					
			} else if (opcao.equals(time)) {
				verTimeline();
			}
		}while (!opcao.equals(logout));
		System.out.println("Saindo da Rede Social...");
	}
	
	public static void fazerPostagem () {
		Post novopost = new Post();
		
		System.out.println("Vamos começar o seu post, digite a data: (formato: dd/mm/aaaa)");
		novopost.data = entrada.nextLine().trim();
		System.out.println("Agora digite o horário deste post: (formato: 00:00)");
		novopost.hora = entrada.nextLine().trim();
		System.out.println("Agora o conteúdo do seu post: ");
		novopost.texto = entrada.nextLine().trim();
		
		if (novopost.data.isBlank() || novopost.hora.isBlank() || novopost.texto.isBlank()) {
			System.out.println("Todos os itens do seu post precisam conter algum caractere!");
			fazerPostagem();
		}
		System.out.println("O post foi realizado na sua Timeline!");
		
		posts [qtdPosts] = novopost;
		qtdPosts++;
		}

	static void verTimeline() {
		for (int i = 0; i < qtdPerfis; i++) {
			System.out.printf("Aqui está a sua TimeLine, %s: \n \n", perfil[i].nome);
		}
		for (int j = 0; j < qtdPosts; j++) {
			System.out.printf(" Data: %s\n Hora: %s\n  '%s'  \n", posts[j].data, posts[j].hora, posts[j].texto);
		}
		menuDoUsuario();
	}
	
	static boolean ValidaCamposBranco(String Texto) {
		if (Texto.length() == 0 || Texto == " ") {
			return true;
			}
		return false;
	}
	
	static boolean buscarPerfis (String loginDigitado ) {
		for (int i = 0; i < qtdPerfis; i++) {
			if(perfil[i].login.equals(loginDigitado)) {
				return true;
			}
		}
		return false;
	}
	
	public static Perfil buscaLogin (String loginDigitado ) {
		Perfil encontrados = new Perfil ();
		for (int i = 0; i < qtdPerfis; i++) {
			if(perfil[i].login.equals(loginDigitado)) {
				encontrados = perfil[i];	
			}
		}
		return encontrados;
	}
}