import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Map; 

public class Principal {
	
	public static void main(String[] args) {
	    	
		Input input = new Input();
		Cartaz cartaz = new Cartaz();
		
		Filme filme1 = new Filme();
		Filme filme2 = new Filme();
		Filme filme3 = new Filme();
		Filme filme4 = new Filme();
		Filme filme5 = new Filme();
		
		
		Sessao sessao1 = new Sessao();
		Sessao sessao2 = new Sessao();
		Sessao sessao3 = new Sessao();
		Sessao sessao4 = new Sessao();
		
		sessao1.adicionarSessao(1, "10/06 10:00h", "Sala 01");
		sessao2.adicionarSessao(2, "10/06 13:00h", "Sala 06");
		sessao3.adicionarSessao(3, "10/06 18:00h", "Sala 03");
		sessao4.adicionarSessao(4, "10/06 20:00h", "Sala 05");
		
		Sessao[] listaDeSessoes = {sessao1, sessao2, sessao3, sessao4}; 

		
		/*Valor esperado pelo método:
		 *
		 *adicionarFilme(
		 * 		int idFilme,
		 * 		String nomeFilme,  
		 * 		double valorFilme, 
		 * 		String tempoFilme, 
		 * 		int classificaçãoIdade,
		 * 		Sessao[] sessoes
		 */
		
		filme1.adicionarFilme(1, "Carros2", 30.3, "2:22", 15, listaDeSessoes);
		filme2.adicionarFilme(2, "Homem de Ferro 3", 20.1, "1:42", 18, listaDeSessoes);
		filme3.adicionarFilme(4, "The Batman", 30.3, "2:22", 15, listaDeSessoes);
		filme4.adicionarFilme(7, "Tenet", 20.1, "1:42", 18, listaDeSessoes);
		filme5.adicionarFilme(6, "Segredos de Dumbledore", 20.1, "1:42", 18, listaDeSessoes);
		
		//Add Filmes ao Cartaz
		cartaz.setFilmes(filme1);
		cartaz.setFilmes(filme2);
		cartaz.setFilmes(filme3);
		cartaz.setFilmes(filme4);
		cartaz.setFilmes(filme5);
			
		//Logica para roda o codigo
		while (true) {
			int resposta;
			int idFilmeEscolhido;
			
			//Nome da aplicação
			System.out.println("____________________________________________________");
			System.out.println("                                                    ");
			System.out.format("%40s" , "Venda de Tickets Para Cinema\n");
			System.out.println("____________________________________________________\n");
			
			{//Primeiro input. 
				//A variavel opcoes contem os nomes que devem ser impressos para o usuário
				String[] opcoes = {"Cliente","Administrador"};
				String mensagem = "-Olá, escolhar uma opção abaixo. \n-E digite o número da opção:";
				resposta = input.inputInt(opcoes, 0, mensagem);
			}
			
			//IF Nº 1
			if (resposta == 1) {
				int quantidadeFilme = cartaz.listarFilmes();
				
				{//Input que recebe o filme escolhido pelo user.
					String[] opcoes = new String[0];
					String mensagem = "-Escolhar um dos filmes citadas acima. \n-E digite o id do Filme:";
					idFilmeEscolhido = input.inputInt(opcoes, quantidadeFilme, mensagem);
					cartaz.setFilmeEscolhido(idFilmeEscolhido);
				}
				
				//Vai retornar o nome do filme escolhido
				String filmeEscolhido = cartaz.listarFilme();
				
				System.out.println("\n-Você escolheu o filme: ");
				System.out.println("   " + filmeEscolhido + "\n");
				
				{//Input lista detalhes do filme ou Voltar para o inicio
					String[] opcoes = {"Detalhes do Filme \"" + filmeEscolhido+"\"","Voltar"};
					String mensagem = "-Escolhar uma opção abaixo. \n-E digite o número da opção:";
					resposta = input.inputInt(opcoes, 0, mensagem);
				}
				
				//IF Nº 2
				if (resposta == 1) {
					cartaz.detalhesDoFilmeEscolhido();
					
					{//Input Comprar ingresso ou Voltar para o inicio
						String[] opcoes = {"Comprar ingresso","Cancelar"};
						String mensagem = "-Escolhar uma opção abaixo. \n-E digite o número da opção:";
						resposta = input.inputInt(opcoes, 0, mensagem);
					}
					
					//IF Nº 3
					if (resposta == 1) {
						interno:
						while (true) {
							int respostaDoUser2;
							int numDeSessoes = cartaz.listarSessoesDoFilmeEscolhido();
							
							{//Input escolher sessão
								String[] opcoes = new String[0];
								String mensagem = "-Escolhar uma sessão listada acima. \n-E digite o número da opção:";
								resposta = input.inputInt(opcoes, numDeSessoes, mensagem);
								cartaz.setSessaoEscolhida(resposta);
							}
							
							{//Input continuar ou trocar sessão ou voltar para inicio
								String[] opcoes = {"Continuar", "Trocar Sessão", "Cancelar"};
								String mensagem = "\n-Escolhar uma opção listada abaixo:";
								respostaDoUser2 = input.inputInt(opcoes, 0, mensagem);
							}
							
							//IF Nº 4
							if (respostaDoUser2 == 1) { //Continuar
								
								interno2:
								while (true) {
									System.out.println();
									System.out.println("-Escolhar uma poltrona listada abaixo.\n-E digite o nome dela:");
									
									Map <String, Integer> nomeDasPoltronasLivres = cartaz.listarPoltronasDoFilmeEscolhido();
									Integer idDaPoltrona = input.inputStr(nomeDasPoltronasLivres);
									
									if (idDaPoltrona != null) {
										cartaz.setDicPoltronasEscolhidas(idDaPoltrona);
										cartaz.imprimirPoltronasEscolhidas();
										
										{//Input escolher mais uma poltrona ou finalizar comprar ou voltar para inicio
											String[] opcoes = {"Finalizar Comprar", "Escolher outra poltrona", "Cancelar"};
											String mensagem = "\n-Escolhar uma opção listada abaixo:";
											resposta = input.inputInt(opcoes, 0, mensagem);
										}
										
										//IF Nº 5
										if (resposta == 1) { //Finalizar Comprar
											break interno2;
										} else if(resposta == 2) { //Escolher outra poltrona
											//Não faz nada, deixa o loop interno2 roda de novo
										} else if(resposta == 3) {//Cancelar
											
										}
										
										
									}
								}
								
								break interno;
							} else if(respostaDoUser2 == 2) { //Trocar Sessão
								//Não faz nada e voltar para o começo do loop interno
							
							} else if(respostaDoUser2 == 3) { //Cancelar
								//Da brack no loop interno e voltar para o começo do loop externo
								break interno;
							}
						}
						
						
					} else if (resposta == 2){
						//Não faz nada e voltar para o começo do loop
					}
				
				} else if (resposta == 2){
					//Não faz nada e voltar para o começo do loop
				}
				
				
			}else if (resposta == 2) {
				
			}
		
		}

	}

}
