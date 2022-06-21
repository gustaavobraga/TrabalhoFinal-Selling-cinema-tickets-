
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
	
	//Metodos
	public void adicionarPoltrona(String numPoltrona, boolean statusPoltrona) {
		this.numPoltrona = numPoltrona;
		boolean
		a1 = true,a2 = true,a3 = true,a4 = true,a5 = true,
		b1 = true,b2 = true,b3 = true,b4 = true,b5 = true,
		c1 = true,c2 = true,c3 = true,c4 = true,c5 = true;
	}
	
	public String verPoltrona() {
		String p = " [a1] [a2] [a3] [a4] [a5] " +
				   " \n[b1] [b2] [b3] [b4] [b5] " +
				   " \n[c1] [c2] [c3] [c4] [c5] " ;
		return p;
	}	
}