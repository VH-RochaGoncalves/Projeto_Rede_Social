package rede_social;

import java.util.Scanner;

public class Teste {
	
	static final int TAM = 100;
	static Perfil[] perfil = new Perfil[TAM];
	static Scanner ler = new Scanner (System.in);
	
	
	public static void main(String[] args) {
		
		Perfil p = new Perfil();
			
		
		System.out.println("Digite seu Nome:");
		 p.nome = ler.nextLine().trim();
		
			while (ValidaCamposBranco(p.nome)== true ) {				
			System.out.println ("O nome não pode estar em branco!");
			
			System.out.println("Digite seu Nome:");
			p.nome = ler.nextLine().trim();
			}
			
					
		System.out.println("Digite seu Login:");	
		p.login = ler.nextLine().trim();
		
			while (ValidaCamposBranco(p.login)== true) {				
				System.out.println ("O login não pode estar em branco!");
				System.out.println("Digite seu Login:");
				p.login = ler.nextLine().trim();
				}
			
			while (CampoJaExiste (p.login)== true) {				
				System.out.println ("Esse Login já existe!");
				
				System.out.println("Digite novamente o Login:");
				p.login = ler.nextLine().trim();
				}
		
		System.out.println("Digite sua Senha:");
		p.senha = ler.nextLine();
		
			while (ValidaCamposBranco (p.senha) == true) {				
				System.out.println ("A senha não pode estar em branco! \n");
				System.out.println("Digite sua Senha:");
				p.senha = ler.nextLine();
			}
			
	}


	private static boolean ValidaCamposBranco(String Texto) {
		if (Texto.length() == 0 || Texto == " ") {
			return true;
			}
		return false;
		
	}
	
	
	private static boolean CampoJaExiste (String Campo) {
			if (Campo.equals("iapo")) {
			return true;
			}				
		return false;
		
	}
}


