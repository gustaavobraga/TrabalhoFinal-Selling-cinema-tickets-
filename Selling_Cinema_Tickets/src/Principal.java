import java.util.Scanner;
import java.util.InputMismatchException; 
public class Principal {

	public static void main(String[] args) {
		
		Input input = new Input();
		Filme filme1 = new Filme();
		Filme filme2 = new Filme();
		Cartaz cartaz = new Cartaz();
		
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
		 * 		String nomeFilme, 
		 * 		int idFilme, 
		 * 		double valorFilme, 
		 * 		String tempoFilme, 
		 * 		int classificaçãoIdade,
		 * 		Sessao[] sessoes
		 */
		
		filme1.adicionarFilme("Carros", 1, 30.3, "2:22", 15, listaDeSessoes);
		filme2.adicionarFilme("Carros2", 2, 20.1, "1:42", 18, listaDeSessoes);
		
		//Add Filmes ao Cartaz
		cartaz.setFilmes(filme1);
		cartaz.setFilmes(filme2);
		
		
		//Logica para roda o codigo
		while (true) {
			int resposta;
			int idFilmeEscolhido;
			
			{//Primeiro input. 
				//A variavel opcoes contem os nomes que devem ser impressos para o usuário
				String[] opcoes = {"Usuário","Administrador"};
				String mensagem = "-Olá, escolhar uma opção abaixo. \n-E digite o número da opção:";
				resposta = input.inputInt(opcoes, 0, mensagem);
			}
			
			if (resposta == 1) {
				int quantidadeFilme = cartaz.listarFilmes();
				
				{//Input que recebe o filme escolhido pelo user.
					String[] opcoes = new String[0];
					String mensagem = "-Escolhar um dos filmes citadas acima. \n-E digite o id do Filme:";
					idFilmeEscolhido = input.inputInt(opcoes, quantidadeFilme, mensagem);
				}
				
				//Vai retornar o nome do filme escolhido
				String filmeEscolhido = cartaz.listarFilme(idFilmeEscolhido);
				
				System.out.println("-Você escolheu o filme: ");
				System.out.println("   " + filmeEscolhido + "\n");
				
				{//Input Continuar ou Cancelar
					String[] opcoes = {"Detalhes do Filme \"" + filmeEscolhido+"\"","Voltar"};
					String mensagem = "-Escolhar uma opção abaixo:";
					resposta = input.inputInt(opcoes, 0, mensagem);
				}
				
				if (resposta == 1) {
					cartaz.detalhesDoFilme(idFilmeEscolhido);
					break;
				} else {
					
				}
				
				
				
			}else if (resposta == 2) {
				
			}
		
		}

	}

}
