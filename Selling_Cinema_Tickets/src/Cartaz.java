import java.util.HashMap;
import java.util.Map;

public class Cartaz {
	private Filme[] filmes = new Filme[25];
	/* Obs:
	 * Os IDs dos filmes nem sempre vao esta em uma ordem continua
	   pois ao deletar um filme vai surgir uma descontinuidade ate 
	   que a posicao seja preenchida novamente com outro filme.
	 * Logo Nao vamos imprimir essa bagunca de IDs para o usuario.
	 * Para resolver esse problema vamos criar novos IDs temporarios.
	 * Para isso vamos criar um dicionario onde as KEYs desse 
	   dicionario vao ser os novos IDs que vao ser IMPRESSOS
	   para o usuario, e os valores do dicionario vao ser os IDs
	   (baguncados) originais dos filmes.
	 * Toda vez que o metodo  listarFilmes() for chamado, ele vai 
	   apagar todos os valores do dicionario e vai criar novos 
	   IDs para o dicionario.
	 * Dessa forma a impressao dos IDs vai ficar atualizada mesmo 
	   se um filme for deletado.
	 */ 
	private Map <Integer, Integer> dicIDFilme = new HashMap<Integer,Integer>();
	private Map <Integer, Integer> dicIDSessao = new HashMap<Integer,Integer>();
	private Map <String, Integer> dicPoltronas = new HashMap<String, Integer>();
	
	//OBS:Resolver bud dos IDs das sessoes: A impressao dos IDs das sessoes tem o mesmo problema dos IDs dos filmes.
	//Map <Integer, Integer> dicSessoes = new HashMap<Integer,Integer>();
	
	private Filme filmeEscolhido;
	private Sessao sessaoEscolhida;
	private int sizeSessaoDoFilmeEscolhido;
	
	public int getSizeSessaoDoFilmeEscolhido() {
		return sizeSessaoDoFilmeEscolhido;
	}

	public void setSizeSessaoDoFilmeEscolhido(int sizeSessaoDoFilmeEscolhido) {
		this.sizeSessaoDoFilmeEscolhido = sizeSessaoDoFilmeEscolhido;
	}

	//dicPoltronasEscolhidas(idPoltrona: Integer; poltrona: Poltrona)
	private Map <Integer, Poltrona> dicPoltronasEscolhidas = new HashMap<Integer, Poltrona>();
	
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
	
	//Remove 1 filme
	public void removerFilme(int idFilme) {
		for (int i = 0; i < 25; i++) {
			if (filmes[i] != null) {
				if (filmes[i].getIdFilme() == idFilme) {
					filmes[i] = null;
					break;
				}
			}
		}
	}
	
	//Remove todos os filmes
	public void removerFilme() {
		for (int i = 0; i < 25; i++) {
			if (filmes[i] != null) {
				filmes[i] = null;
			}
		}
	}
	
	public Filme getFilmeEscolhido() {
		return filmeEscolhido;
	}

	public void setFilmeEscolhido(int idFilme) {
		//O idFilme passado como paramentro, na verdade e uma key do 
		//dicionario dicIDFilme, onde seu valor e o verdadeiro id do filme.
		int idOriginalDoFilme = dicIDFilme.get(idFilme);
		
		for (int i = 0; i < 25; i++) {
			if (filmes[i] != null) {
				if (filmes[i].getIdFilme() == idOriginalDoFilme) {
					this.filmeEscolhido = filmes[i];
				}
			}
		}
	}

	public Sessao getSessaoEscolhida() {
		return sessaoEscolhida;
	}

	public void setSessaoEscolhida(int idSessao) {
		//O idSessao passado como paramentro, na verdade e uma key do 
		//dicionario dicIDSessao, onde seu valor e o verdadeiro id do sessao.
		int idOriginalDaSessao = dicIDSessao.get(idSessao);
			
		Sessao[] listaDeSessoes = filmeEscolhido.getSessoes();
					
		for (Sessao ses: listaDeSessoes) {
			if(ses.getIdSessao() == idOriginalDaSessao) {
				this.sessaoEscolhida = ses;
				break;
			}
		}
	}

	public Map<Integer, Poltrona> getDicPoltronasEscolhidas() {
		return dicPoltronasEscolhidas;
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
	
	public Map<String, Integer> numDasPoltronasEscolhidas() {
		Map<String, Integer> numDasPoltronas= new HashMap<String, Integer>();
		
		
		for (Integer key: dicPoltronasEscolhidas.keySet()) {
			String numPoltrona = dicPoltronasEscolhidas.get(key).getNumPoltrona();
			int id = key;
			
			numDasPoltronas.put(numPoltrona, key);
		}
		
		//key=numPoltrona e values=idPoltrona
		return numDasPoltronas;
	}
	
	//Lista todos os filmes que estao em cartaz
	public int listarFilmes() {
		dicIDFilme.clear();
		dicPoltronas.clear();
		dicPoltronasEscolhidas.clear();
		
		int quantidadeFilme = 0;
		
		System.out.format(" %-4s%-25s%-6s%-14s \n \n","ID","Nome","Tempo","Classificacao");
		
		for (int i = 0; i < 25; i++) {
			if (filmes[i] != null) {
				quantidadeFilme++;
				//Vamos reaproveitar a variavel "quantidadeFilme", imprimindo o seu valor no lugar do id do filme
				System.out.format(" %-4s", quantidadeFilme);
				System.out.format("%-25s", filmes[i].getNomeFilme());
				System.out.format("%-6s", filmes[i].getTempoFilme());
				System.out.format("%-14s\n", filmes[i].getClassificacaoIdade());
				
				dicIDFilme.put(quantidadeFilme, filmes[i].getIdFilme());
				
		    }
		}
		
		
		
		//Esse valor int retornado e para o metodo inputInt() saber quais valores ele deve esperar do usuario
		return quantidadeFilme;
	}

	//Lista um unico filme
	public String listarFilme() {
		String dadosDoFilme = filmeEscolhido.getNomeFilme();
		return dadosDoFilme;
	}
	
	public void detalhesDoFilmeEscolhido(){			
		System.out.println("____________________________________________________");
		System.out.println("                                                    ");
		System.out.format("%52s","------------------Detalhes do Filme-----------------\n \n");
					
		System.out.format("%s%-20s\n", "-Nome: ", filmeEscolhido.getNomeFilme());
		System.out.format("%s%-20s\n", "-Idade minima: ", filmeEscolhido.getClassificacaoIdade());
		System.out.format("%s%-20s\n", "-Tempo: ", filmeEscolhido.getTempoFilme());
		System.out.format("%s%-20s\n", "-Valor: ", filmeEscolhido.getValorFilme());
		System.out.println("-Sessoes disponpiveis:");
		int variavelSemUltilidade = listarSessoesDoFilmeEscolhido();
		
		System.out.println("\n____________________________________________________\n");		
	}
	
	public int listarSessoesDoFilmeEscolhido() {
		dicIDSessao.clear();
		int quantidadeSessoes = 0;
		Sessao[] listaDeSessoes = filmeEscolhido.getSessoes();
	
		for (Sessao ses: listaDeSessoes) {
			if (ses != null) {
				quantidadeSessoes++;
				int novoId = quantidadeSessoes;
				dicIDSessao.put(novoId, ses.getIdSessao());
				System.out.println( "    " + novoId +".  "+ ses.getData());
			
			}
		}
		System.out.println();
		setSizeSessaoDoFilmeEscolhido(quantidadeSessoes);
		return quantidadeSessoes;
		
	}

	public Map <String, Integer> listarPoltronasDoFilmeEscolhido() {
		Poltrona[] listaDePoltronas = sessaoEscolhida.getPoltronas();
		String[] xx = new String[5];
		int indice = 0;
		
		listaDePoltronas[3].setStatusPoltrona(true);
		
		while (true) {
			for (int i=0; i<5; i++) {
				if (listaDePoltronas[(indice + i)].getStatusPoltrona() == false) {
					xx[i] = listaDePoltronas[(indice + i)].getNumPoltrona();
					interno:
					for (int x=0; x<20; x++) {
						String numPoltrona = listaDePoltronas[(indice + i)].getNumPoltrona();
						int id = listaDePoltronas[(indice + i)].getIdPoltrona();
						
						dicPoltronas.put(numPoltrona, id);
						break interno;
					}
					
				} else {
					//OBS: Imprimir o X com cor. 
					xx[i] = "X";
				}
				
			}
			System.out.format("  %-4s %-4s %-4s %-4s %-4s\n", xx[0], xx[1], xx[2], xx[3], xx[4]);		
			
			indice += 5;
			if (indice == 20) {
				//Retorna um dicionario onde a key=numPoltrona e o values=idPoltrona
				return dicPoltronas;
			}
		}
	}
	
	public void imprimirPoltronasEscolhidas() {
		System.out.println("\nSuas poltronas escolhidas para o filme \""+ filmeEscolhido.getNomeFilme() +"\" sao:");
		System.out.print("  ");
		for (Integer key : dicPoltronasEscolhidas.keySet()) {
            Poltrona poltrona = dicPoltronasEscolhidas.get(key);
            System.out.print("-" + poltrona.getNumPoltrona() + " ");
		}
		System.out.println();
	}
	
	public void removerPoltronaEscolhida(int idPoltrona) {
		dicPoltronasEscolhidas.get(idPoltrona).setStatusPoltrona(false);
		dicPoltronasEscolhidas.remove(idPoltrona);
	}
	
	public int sizeDePoltronasEscolhidas() {
		return dicPoltronasEscolhidas.size();
	}
	
	public String[] dadosDaCompra() {
		String[] dados = new String[5];
		String nomeFilme = filmeEscolhido.getNomeFilme();
		String sessao = sessaoEscolhida.getData();
		String valorDoFilme = String.format("%.2f", filmeEscolhido.getValorFilme());
		int sizePoltronas = dicPoltronasEscolhidas.size();
		String valorTotal = String.format("%.2f", (sizePoltronas * filmeEscolhido.getValorFilme()));
		
		
		String poltronasEscolhidas = " ";
		for (Integer key: dicPoltronasEscolhidas.keySet()) {
			String numPoltrona = dicPoltronasEscolhidas.get(key).getNumPoltrona();
			poltronasEscolhidas = poltronasEscolhidas.concat("  ");
			poltronasEscolhidas = poltronasEscolhidas.concat(numPoltrona);
			
		}
		
		dados[0] = nomeFilme;
		dados[1] = sessao;
		dados[2] = poltronasEscolhidas;
		dados[3] = valorDoFilme;
		dados[4] = valorTotal;
		
		return dados;
	}
}




















