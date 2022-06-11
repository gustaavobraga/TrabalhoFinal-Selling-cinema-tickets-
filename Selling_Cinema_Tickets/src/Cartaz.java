import java.util.HashMap;
import java.util.Map;

public class Cartaz {
	private Filme[] filmes = new Filme[25];
	/* Obs:
	 * Os IDs dos filmes nem sempre vão está em uma ordem contínua
	   pois ao deletar um filme vai surgir uma descontinuidade até 
	   que a posição seja preenchida novamente com outro filme.
	 * Logo NÃO vamos imprimir essa bagunça de IDs para o usuário.
	 * Para resolver esse problema vamos criar novos IDs temporários.
	 * Para isso vamos criar um dicionário onde as KEYs desse 
	   dicionário vão ser os novos IDs que vão ser IMPRESSOS
	   para o usuário, e os valores do dicionário vão ser os IDs
	   (bagunçados) originais dos filmes.
	 * Toda vez que o método  listarFilmes() for chamado, ele vai 
	   apagar todos os valores do dicionário e vai criar novos 
	   IDs para o dicionário.
	 * Dessa forma a impressão dos IDs vai ficar atualizada mesmo 
	   se um filme for deletado.
	 */ 
	private Map <Integer, Integer> dicID = new HashMap<Integer,Integer>();
	private Map <String, Integer> dicPoltronas = new HashMap<String, Integer>();
	//Map <Integer, Integer> dicSessoes = new HashMap<Integer,Integer>();
	
	private Filme filmeEscolhido;
	private Sessao sessaoEscolhida;
	
	//dicPoltronasEscolhidas(idPoltrona: Integer; poltrona: Poltrona)
	private Map <Integer, Poltrona> dicPoltronasEscolhidas = new HashMap<Integer, Poltrona>();
	
	public Filme[] getFilmes() {
		return filmes;
	}

	public Filme getFilmeEscolhido() {
		return filmeEscolhido;
	}

	public void setFilmeEscolhido(int idFilme) {
		//O idFilme passado como paramentro, na verdade é uma key do 
		//dicionario dic, onde seu valor é o verdadeiro id do filme.
		int idOriginalDoFilme = dicID.get(idFilme);
		
		for (int i = 0; i < 25; i++) {
			if (filmes[i] != null) {
				if (filmes[i].getIdFilme() == idOriginalDoFilme) {
					this.filmeEscolhido = filmes[i];
				}
			}
		}
	}

	public void setSessaoEscolhida(int idSessao) {
		Sessao[] listaDeSessoes = filmeEscolhido.getSessoes();
					
		for (Sessao ses: listaDeSessoes) {
			if(ses.getIdSessao() == idSessao) {
				this.sessaoEscolhida = ses;
				break;
			}
		}
	}

	public void setDicPoltronasEscolhidas(int idPoltrona) {
		Poltrona[] listaDePoltronas = sessaoEscolhida.getPoltronas();
		for (Poltrona i: listaDePoltronas) {
			if (i.getIdPoltrona() == idPoltrona) {
				dicPoltronasEscolhidas.put(i.getIdPoltrona(), i);
				i.setStatusPoltrona(true);
			}
		}	
	}

	public void setFilmes(Filme filme) {
		
		for (int i = 0; i < 25; i++) {
			if (filmes[i] == null) {
				filmes[i] = filme;
		        break;
		    }
		}
	}
	
	//Lista todos os filmes que estão em cartaz
	public int listarFilmes() {
		dicID.clear();
		dicPoltronas.clear();
		dicPoltronasEscolhidas.clear();
		
		int quantidadeFilme = 0;
		System.out.println("____________________________________________________");
		System.out.println("                                                    ");
		System.out.format("%52s","------------------Filmes em Cartaz------------------\n \n");
		System.out.format(" %-4s%-25s%-6s%-14s \n \n","ID","Nome","Tempo","Classificação");
		
		for (int i = 0; i < 25; i++) {
			if (filmes[i] != null) {
				quantidadeFilme++;
				//Vamos reaproveitar a variavel "quantidadeFilme", imprimindo o seu valor no lugar do id do filme
				System.out.format(" %-4s", quantidadeFilme);
				System.out.format("%-25s", filmes[i].getNomeFilme());
				System.out.format("%-6s", filmes[i].getTempoFilme());
				System.out.format("%-14s\n", filmes[i].getClassificacaoIdade());
				
				dicID.put(quantidadeFilme, filmes[i].getIdFilme());
				
		    }
		}
		
		System.out.println("____________________________________________________\n");
		
		//Esse valor int retornado é para o método inputInt() saber quais valores ele deve esperar do usuário
		return quantidadeFilme;
	}

	//Lista um único filme
	public String listarFilme() {
		String dadosDoFilme = filmeEscolhido.getNomeFilme();
		return dadosDoFilme;
	}
	
	public void detalhesDoFilmeEscolhido(){			
		System.out.println("____________________________________________________");
		System.out.println("                                                    ");
		System.out.format("%52s","------------------Detalhes do Filme-----------------\n \n");
					
		System.out.format("%s%-20s\n", "-Nome: ", filmeEscolhido.getNomeFilme());
		System.out.format("%s%-20s\n", "-Idade mínima: ", filmeEscolhido.getClassificacaoIdade());
		System.out.format("%s%-20s\n", "-Tempo: ", filmeEscolhido.getTempoFilme());
		System.out.format("%s%-20s\n", "-Valor: ", filmeEscolhido.getValorFilme());
		System.out.println("-Sessões disponpiveis:");
		Sessao[] listaDeSessoes = filmeEscolhido.getSessoes();
		for (Sessao ses: listaDeSessoes) {
			System.out.println( "    " + ses.getIdSessao() +"  "+ ses.getData());
		}
		System.out.println("\n____________________________________________________\n");		
	}
	
	public int listarSessoesDoFilmeEscolhido() {
		Sessao[] listaDeSessoes = filmeEscolhido.getSessoes();
		
		System.out.println("____________________________________________________");
		System.out.println("                                                    ");
		System.out.format("%52s","------------------Lista de Sessões------------------\n \n");
					
		for (Sessao ses: listaDeSessoes) {
			System.out.println( "    " + ses.getIdSessao() +".  "+ ses.getData());
		}
		System.out.println();
		return listaDeSessoes.length;
		
	}

	public Map <String, Integer> listarPoltronasDoFilmeEscolhido() {
		Poltrona[] listaDePoltronas = sessaoEscolhida.getPoltronas();
		String[] xx = new String[5];
		String[] nomeDasPoltronasLivres = new String[20];
		int indice = 0;
		
		listaDePoltronas[3].setStatusPoltrona(true);
		
		while (true) {
			for (int i=0; i<5; i++) {
				if (listaDePoltronas[(indice + i)].getStatusPoltrona() == false) {
					xx[i] = listaDePoltronas[(indice + i)].getNumPoltrona();
					interno:
					for (int x=0; x<20; x++) {
						String nome = listaDePoltronas[(indice + i)].getNumPoltrona();
						int id = listaDePoltronas[(indice + i)].getIdPoltrona();
						dicPoltronas.put(nome, id);
						break interno;
					}
					
				} else {
					xx[i] = "X";
				}
				
			}
			System.out.format("  %-4s %-4s %-4s %-4s %-4s\n", xx[0], xx[1], xx[2], xx[3], xx[4]);		
			
			indice += 5;
			if (indice == 20) {
				return dicPoltronas;
			}
		}
	}
	
	public void imprimirPoltronasEscolhidas() {
		System.out.println("\nSuas poltronas escolhidas para o filme \""+ filmeEscolhido.getNomeFilme() +"\" são:");
		System.out.print("  ");
		for (Integer key : dicPoltronasEscolhidas.keySet()) {
            Poltrona poltrona = dicPoltronasEscolhidas.get(key);
            System.out.print("-" + poltrona.getNumPoltrona() + " ");
		}
		System.out.println();
	}
}




















