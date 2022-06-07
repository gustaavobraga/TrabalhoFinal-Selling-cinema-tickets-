import java.util.Scanner;
import java.util.InputMismatchException; 
public class Principal {

	public static void main(String[] args) {
		
		Input input = new Input();
		Filme filme1 = new Filme();
		Filme filme2 = new Filme();
		Cartaz cartaz = new Cartaz();
		

		
		//Criar Filmes
		/*adicionarFilme(
		 * 		String nomeFilme, 
		 * 		int idFilme, 
		 * 		double valorFilme, 
		 * 		String tempoFilme, 
		 * 		int classificaçãoIdade
		 * )*/
		
		filme1.adicionarFilme("Carros", 1, 30.3, "2:22", 15);
		filme2.adicionarFilme("Carros2", 2, 20.1, "1:42", 18);
		
		//Add Filmes ao Cartaz
		cartaz.setFilmes(filme1);
		cartaz.setFilmes(filme2);
		
		
		//Logica para roda o codigo
		String[] opcoes = {"Usuário","Administrador"};
		int resposta =input.inputInt(opcoes);
		System.out.println(resposta);
		
		if (resposta == 1) {
			cartaz.listarFilmes();
		}
		
		

	}

}
