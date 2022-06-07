
public class Sessao  {

	private String idSessao;
	private String diaSessao;
	private String horaSessao;
	private String localDaSala;
	private Poltrona[] poltronas;
	
	public String getIdSessao() {
		return idSessao;
	}

	public void setIdSessao(String idSessao) {
		this.idSessao = idSessao;
	}

	public String getDiaSessao() {
		return diaSessao;
	}

	public void setDiaSessao(String diaSessao) {
		this.diaSessao = diaSessao;
	}

	public String getHoraSessao() {
		return horaSessao;
	}

	public void setHoraSessao(String horaSessao) {
		this.horaSessao = horaSessao;
	}
	
	public String getLocalDaSala() {
		return localDaSala;
	}

	public void setLocalDaSala(String localDaSala) {
		this.localDaSala = localDaSala;
	}
	
	//Métodos 
	public void adicionarSessao(String idSessao, String diaSessao, String horaSessao) {
		setIdSessao(idSessao);
		setDiaSessao(diaSessao);
		setHoraSessao(horaSessao);
	}
	
	public String verSessão() {
		String s = "Sala: " + this.getIdSessao() +
					"\nDia: " + this.getDiaSessao() +
					"\nHora: " + this.getHoraSessao();
		return s;
		
	}

}