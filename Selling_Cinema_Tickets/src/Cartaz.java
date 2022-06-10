
public class Cartaz {
	private Filme[] filmes = new Filme[25];

	public Filme[] getFilmes() {
		return filmes;
	}

	public void setFilmes(Filme filme) {
		
		for (int i = 0; i < 25; i++) {
			if (filmes[i] == null) {
				filmes[i] = filme;
		        break;
		    }
		}
	}
	
	//Lista um único filme
	public String listarFilme(int idFilme) {
		for (int i = 0; i < 25; i++) {
			if (filmes[i].getIdFilme() == idFilme) {
				String dadosDoFilme = filmes[i].getNomeFilme();
				return dadosDoFilme;
			}
		}
		return null;
	}
	
	//Lista todos os filmes que estão em cartaz
	public int listarFilmes() {
		int quantidadeFilme = 0;
		System.out.println("____________________________________________________");
		System.out.println("                                                    ");
		System.out.format("%32s","Filmes em Cartaz\n");
		System.out.println("____________________________________________________\n");
		System.out.format(" %-4s%-15s%-14s%-6s \n","ID","Nome","Classificação","Tempo");
		
		for (int i = 0; i < 25; i++) {
			if (filmes[i] != null) {
				quantidadeFilme++;
				System.out.format("*%-4s", filmes[i].getIdFilme());
				System.out.format("%-15s", filmes[i].getNomeFilme());
				System.out.format("%-14s", filmes[i].getClassificacaoIdade());
				System.out.format("%-6s\n", filmes[i].getTempoFilme());
		    }
		}
		
		System.out.println("____________________________________________________");
		
		//Esse valor int retornado é para o método inputInt() saber quais valores ele deve esperar do usuário
		return quantidadeFilme;
	}
	
	public void detalhesDoFilme(int idFilme){
		for (int i = 0; i < 25; i++) {
			
			if (filmes[i] != null) {
				
				if (filmes[i].getIdFilme() == idFilme) {
					System.out.println("____________________________________________________");
					System.out.println("                                                    ");
					System.out.format("%31s","Detalhes do Filme\n");
					System.out.println("____________________________________________________\n");
					System.out.format("%s%-20s\n", "-Nome: ", filmes[i].getNomeFilme());
					System.out.format("%s%-20s\n", "-Idade mínima: ", filmes[i].getClassificacaoIdade());
					System.out.format("%s%-20s\n", "-Tempo: ", filmes[i].getTempoFilme());
					System.out.format("%s%-20s\n", "-Valor: ", filmes[i].getValorFilme());
					System.out.println("-Sessões disponpiveis:");
					Sessao[] listaDeSessoes = filmes[i].getSessoes();
					for (Sessao ses: listaDeSessoes) {
						System.out.println( "    " + ses.getIdSessao() +"  "+ ses.getData());
					}
					System.out.println("\n____________________________________________________\n");
					break;
				}
			}
			
		}
		
	}
	
}
