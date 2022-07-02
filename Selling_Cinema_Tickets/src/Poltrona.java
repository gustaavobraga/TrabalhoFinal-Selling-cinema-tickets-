
public class Poltrona {
	
	private int idPoltrona;
	private String numPoltrona;
	private boolean statusPoltrona = false;
	
	public int getIdPoltrona() {
		return idPoltrona;
	}
	
	public void setIdPoltrona(int idPoltrona) {
		this.idPoltrona = idPoltrona;
	}
	
	public String getNumPoltrona() {
		return numPoltrona;
	}
	
	public void setNumPoltrona(String numPoltrona) {
		this.numPoltrona = numPoltrona;
	}
	
	public boolean getStatusPoltrona() {
		return statusPoltrona;
	}
	
	public void setStatusPoltrona(boolean statusPoltrona) {
		this.statusPoltrona = statusPoltrona;
	}	
	
	public void adicionarPoltrona(int idPoltrona, String numPoltrona) {
		this.idPoltrona = idPoltrona;
		this.numPoltrona = numPoltrona;
		this.statusPoltrona = true;
	}
}