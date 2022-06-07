
public class Cliente {
	
	private String nomeCliente;
	private String cpf;
	private String dataNacsimento;
	private String email;

	
	public Cliente(String nome, String cpf, String dataNacsimento) {
		this.nomeCliente = nome;
		this.cpf = cpf;
		this.dataNacsimento = dataNacsimento;
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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


}