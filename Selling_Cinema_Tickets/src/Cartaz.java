
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
	
	public void listarFilmes() {
		System.out.println("Filmes em Cartaz");
		System.out.println("____________________________________________________");
		System.out.format(" %-4s%-15s%-14s%-6s \n","ID","Nome","Classificação","Tempo");
		
		for (int i = 0; i < 25; i++) {
			if (filmes[i] != null) {
				System.out.format("*%-4s", filmes[i].getIdFilme());
				System.out.format("%-15s", filmes[i].getNomeFilme());
				System.out.format("%-14s", filmes[i].getClassificacaoIdade());
				System.out.format("%-6s\n", filmes[i].getTempoFilme());
		    }
		}
		
		System.out.println("____________________________________________________");
	}
	
}
