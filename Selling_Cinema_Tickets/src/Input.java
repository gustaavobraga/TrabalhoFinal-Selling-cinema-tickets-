import java.util.Scanner;
import java.util.InputMismatchException; 

public class Input {
	Scanner ler = new Scanner(System.in);

	public int inputInt(String[] opcoes) {
		int resposta;
		int numDeOpcoes = opcoes.length;
		
		while (true) {
			System.out.println("Escolhar uma opção baixo e digite o número da opção:");
			
			for (int i=0; i < numDeOpcoes; i++) {
				System.out.println("   " + (i+1) + "." + opcoes[i]);
			}
			
			try {
				resposta = ler.nextInt();
				
				//Verifica se valor passado corresponde a algum valor das opções
				for (int i=0; i < numDeOpcoes; i++) {
					if (resposta == (i+1)) {
						return resposta;
					}
				}
				System.out.println("Valor invalido, informe um número que esteja nas opções.\n");
		
			} catch( InputMismatchException var) {
				
				ler.nextLine();
				
			} catch( Exception var) {
				System.out.println("Erro: " + var);
				ler.nextLine();
			}
		}
	}
	
}

