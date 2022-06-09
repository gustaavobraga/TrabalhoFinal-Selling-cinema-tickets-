
public class Filme extends Sessao {
	
	private String nomeFilme;
	private int idFilme;
	private	double valorFilme;
	private String tempoFilme;
	private int classificacaoIdade;
	private Sessao[] sessoes = new Sessao[10];
	
	
	public String getNomeFilme() {
		return nomeFilme;
	}
	
	public void setNomeFilme(String nomeFilme) {
		this.nomeFilme = nomeFilme;
	}
	
	public int getIdFilme() {
		return idFilme;
	}
	
	public void setIdFilme(int idFilme) {
		this.idFilme = idFilme;
	}
	
	public double getValorFilme() {
		return valorFilme;
	}
	
	public void setValorFilme(double valorFilme) {
		this.valorFilme = valorFilme;
	}
	
	public String getTempoFilme() {
		return tempoFilme;
	}

	public void setTempoFilme(String tempoFilme) {
		this.tempoFilme = tempoFilme;
	}

	public int getClassificacaoIdade() {
		return classificacaoIdade;
	}

	public void setClassificacaoIdade(int classificacaoIdade) {
		this.classificacaoIdade = classificacaoIdade;
	}
	
	
	public void adicionarFilme(String nomeFilme, int idFilme, double valorFilme, 
							   String tempoFilme, int classificaçãoIdade, Sessao[] sessoes) {
		setNomeFilme(nomeFilme);
		setIdFilme(idFilme);
		setValorFilme(valorFilme);
		setTempoFilme(tempoFilme);
		setClassificacaoIdade(classificaçãoIdade);
		//Cada filme deve receber uma lista de sessões e cada sessão deve ter uma lista de poltronas
		
		
	}
	
	public String verFilme() {
		String informacoes = "Nome: " + this.getNomeFilme() + "\nValor: " + this.getValorFilme() + " reais";
		return informacoes;
	}
}