
public class Cliente {
	
	private String nomeCliente;
	private String cpf;
	private String dataNacsimento;
	private String emailCliente;


	
	public Cliente(String nome, String cpf, String dataNacsimento, String emailCliente) {
		this.nomeCliente = nome;
		this.cpf = cpf;
		this.dataNacsimento = dataNacsimento;
		this.emailCliente = emailCliente;
	}
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getNascCliente() {
		return dataNacsimento;
	}
	
	public void setNascCliente(String dataNacsimento) {
		this.dataNacsimento = dataNacsimento;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}
	
	

}