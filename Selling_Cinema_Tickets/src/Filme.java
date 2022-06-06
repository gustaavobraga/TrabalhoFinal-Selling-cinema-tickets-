
public class Filme extends Sessao {
	//Atributos
	private String nomeFilme;
	private String idFilme;
	private	int valorFilme;
	
	//Métodos 
	private String getNomeFilme() {
		return nomeFilme;
	}
	
	private void setNomeFilme(String nomeFilme) {
		this.nomeFilme = nomeFilme;
	}
	
	private String getIdFilme() {
		return idFilme;
	}
	
	private void setIdFilme(String idFilme) {
		this.idFilme = idFilme;
	}
	
	private int getValorFilme() {
		return valorFilme;
	}
	
	private void setValorFilme(int valorFilme) {
		this.valorFilme = valorFilme;
	}
	
	//Métodos 
	public void adicionarFilme(String nomeFilme, String idFilme, int valorFilme) {
		setNomeFilme(nomeFilme);
		setIdFilme(idFilme);
		setValorFilme(valorFilme);
	}
	
	public String verFilme() {
		String f = "Nome: " + this.getNomeFilme() +
					"\nValor: " + this.getValorFilme() + " reais";
		return f;
	}
	
}