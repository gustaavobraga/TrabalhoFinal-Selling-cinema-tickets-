
public class Sessao  {
	//Atributos
	private String idSessão;
	private String diaSessão;
	private String horaSessão;
	
	//Métodos
	private String getIdSessão() {
		return idSessão;
	}

	private void setIdSessão(String idSessão) {
		this.idSessão = idSessão;
	}

	private String getDiaSessão() {
		return diaSessão;
	}

	private void setDiaSessão(String diaSessão) {
		this.diaSessão = diaSessão;
	}

	private String getHoraSessão() {
		return horaSessão;
	}

	private void setHoraSessão(String horaSessão) {
		this.horaSessão = horaSessão;
	}
	
	//Métodos 
	public void adicionarSessão(String idSessão, String diaSessão, String horaSessão) {
		setIdSessão(idSessão);
		setDiaSessão(diaSessão);
		setHoraSessão(horaSessão);
	}
	
	public String verSessão() {
		String s = "Sala: " + this.getIdSessão() +
					"\nDia: " + this.getDiaSessão() +
					"\nHora: " + this.getHoraSessão();
		return s;
		
	}

}