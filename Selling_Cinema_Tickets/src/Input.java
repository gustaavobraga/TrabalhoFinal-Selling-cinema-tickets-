import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Map; 

public class Input {
	Scanner ler = new Scanner(System.in);

	public int inputInt(String[] opcoes, int quantidadeOpcoes , String mensagem) {
		/*
		 *Esse método nem sempre vai precisar imprimir as opções, 
		  pois as opções já foram impressas por outros métodos.
		 *Quando for este o caso, o método deve receber um array de tamanho 0, e
		  o quantidade de opções que foram impressas para o usuário.
		 *E deve mudar o primeiro print, de "opção abaixo" para "opção citadas acima"   
		 */
		
		int resposta;
		int numDeOpcoes;
		if (quantidadeOpcoes == 0) {
			numDeOpcoes = opcoes.length;
		} else {
			numDeOpcoes = quantidadeOpcoes;
		}
		
		while (true) {
			if (quantidadeOpcoes == 0) {
				System.out.println(mensagem);
				
				for (int i=0; i < numDeOpcoes; i++) {
					System.out.println("   " + (i+1) + "." + opcoes[i]);
				}
			} else {
				System.out.println(mensagem);
			}
			
			try {
				resposta = ler.nextInt();
				
				//Verifica se valor passado corresponde a algum valor das opções
				for (int i=0; i < numDeOpcoes; i++) {
					if (resposta == (i+1)) {
						return resposta;
					}
				}
				System.out.println("-Valor invalido, informe um número que seja valido.\n");
		
			} catch( InputMismatchException var) {
				
				ler.nextLine();
				
			} catch( Exception var) {
				System.out.println("Erro: " + var);
				ler.nextLine();
			}
		}
	}
	
	public Integer inputStr(Map <String, Integer> nomeDasPoltronasLivres) {
		String resposta = null;
		
		while(true) {
			try {
				resposta = ler.next().toUpperCase().trim();
				
				if (nomeDasPoltronasLivres.containsKey(resposta)) {
					return nomeDasPoltronasLivres.get(resposta);
				}
				
				System.out.println("\n-Valor invalido, informe um valor que seja valido.");
				return null;
				
			} catch( Exception var) {
				System.out.println("Erro: " + var);
			}
		}
	}
}

