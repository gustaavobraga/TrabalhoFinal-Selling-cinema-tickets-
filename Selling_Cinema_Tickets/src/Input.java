import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Map; 

public class Input {
	Scanner ler = new Scanner(System.in);
	Scanner ler2 = new Scanner(System.in);

	public int inputInt(String[] opcoes, int quantidadeOpcoes , String mensagem) {
		/*
		 *Esse metodo nem sempre vai precisar imprimir as opcoes, 
		  pois as opcoes ja foram impressas por outros metodos.
		 *Quando for este o caso, o metodo deve receber um array de tamanho 0, e
		  o quantidade de opcoes que foram impressas para o usuario.
		 *E deve mudar o primeiro print, de "opcao abaixo" para "opcao citadas acima"   
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
				
				//Verifica se valor passado corresponde a algum valor das opcoes
				for (int i=0; i < numDeOpcoes; i++) {
					if (resposta == (i+1)) {
						return resposta;
					}
				}
				System.out.println("-Valor invalido, informe um numero que seja valido.\n");
		
			} catch( InputMismatchException var) {
				
				ler.nextLine();
				
			} catch( Exception var) {
				System.out.println("Erro: " + var);
				ler.nextLine();
			}
		}
	}
	
	public int inputInt(String mensagem) {
		int resposta;
		while(true) {
			try {
				System.out.println(mensagem);
				resposta = ler.nextInt();
				
				return resposta;
				
			} catch( Exception var) {
				System.out.println("\n-Valor invalido, informe um numero inteiro.");
				ler.nextLine();
			}
		}
	}
	
	public Integer inputStr(Map <String, Integer> numDasPoltronasLivres) {
		//O parametro recebe uma key=numPoltrona e values=idPoltrona
		String resposta = null;
		
		while(true) {
			try {
				resposta = ler.next().toUpperCase().trim();
				
				if (numDasPoltronasLivres.containsKey(resposta)) {
					//retorna o id da poltrona escolhida
					return numDasPoltronasLivres.get(resposta);
				}
				
				System.out.println("\n-Valor invalido, informe um valor que seja valido.");
				return null;
				
			} catch( Exception var) {
				System.out.println("Erro: " + var);
			}
		}
	}
	
	public String inputStr(String mensagem) {
		while(true) {
			try {
				System.out.println(mensagem);
				String resposta = ler2.nextLine();
				return resposta;
				
			} catch( Exception var) {
				System.out.println("\n-Valor invalido, informe um valor que seja valido.");
			}
		}
	}
	
	public String inputStrLogin() {
		while(true) {
			try {
				String resposta = ler.next().toUpperCase().trim();
				
				return resposta;
				
			} catch( Exception var) {
				System.out.println("\n-Valor invalido, informe um valor que seja valido.");
			}
		}
	}

	public Double inputDouble(String mensagem) {
		Double resposta;
		while(true) {
			try {
				System.out.println(mensagem);
				resposta = ler.nextDouble();
				
				return resposta;
				
			} catch( Exception var) {
				System.out.println("\n-Valor invalido, informe um valor que seja valido.");
				ler.nextLine();
			}
		}
	}
	
}

