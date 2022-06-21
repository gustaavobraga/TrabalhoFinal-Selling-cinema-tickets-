
public class ComprarIngresso extends Filme{
	private int idFilme;
	private int idSessao;
	private int[] idAssento;
	private Cliente cliente;
	private double total;
	
	public int getIdFilme() {
		return idFilme;
	}
	public void setIdFilme(int idFilme) {
		this.idFilme = idFilme;
	}
	public int getIdSessao() {
		return idSessao;
	}
	public void setIdSessao(int idSessao) {
		this.idSessao = idSessao;
	}
	public int[] getIdAssento() {
		return idAssento;
	}
	public void setIdAssento(int[] idAssento) {
		this.idAssento = idAssento;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
}
