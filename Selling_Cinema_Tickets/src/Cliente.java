
public class Cliente {
	//Atributos
	private String nomeCliente;
	private String cpfCliente;
	private String nascCliente;
		
	//Métodos
	public Cliente(String noCli, String cpf, String nascCli) {
		this.nomeCliente = noCli;
		this.cpfCliente = cpf;
		this.nascCliente = nascCli;
	}
	
	public String getNomeCliente() {
		return nomeCliente;
	}
	
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
	public String getCpfCliente() {
		return cpfCliente;
	}
	
	public void setCpfCliente(String cpfCliente) {
		this.cpfCliente = cpfCliente;
	}
	
	public String getNascCliente() {
		return nascCliente;
	}
	
	public void setNascCliente(String nascCliente) {
		this.nascCliente = nascCliente;
	}
	
	


}