package projetofinal;
public class Filme extends Sess�o {
	//Atributos
	private String nomeFilme;
	private String idFilme;
	private	int valorFilme;
	//M�todos 
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
	//M�todos 
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
