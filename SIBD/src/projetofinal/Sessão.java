package projetofinal;
public class Sess�o  {
	//Atributos
	private String idSess�o;
	private String diaSess�o;
	private String horaSess�o;
	//M�todos
	private String getIdSess�o() {
		return idSess�o;
	}

	private void setIdSess�o(String idSess�o) {
		this.idSess�o = idSess�o;
	}

	private String getDiaSess�o() {
		return diaSess�o;
	}

	private void setDiaSess�o(String diaSess�o) {
		this.diaSess�o = diaSess�o;
	}

	private String getHoraSess�o() {
		return horaSess�o;
	}

	private void setHoraSess�o(String horaSess�o) {
		this.horaSess�o = horaSess�o;
	}
	
	//M�todos 
	public void adicionarSess�o(String idSess�o, String diaSess�o, String horaSess�o) {
		setIdSess�o(idSess�o);
		setDiaSess�o(diaSess�o);
		setHoraSess�o(horaSess�o);
	}
	public String verSess�o() {
		String s = "Sala: " + this.getIdSess�o() +
					"\nDia: " + this.getDiaSess�o() +
					"\nHora: " + this.getHoraSess�o();
		return s;
		
	}
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
