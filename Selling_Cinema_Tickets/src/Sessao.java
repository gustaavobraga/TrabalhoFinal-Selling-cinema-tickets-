
public class Sessao  {

	private int idSessao;
	private String data;
	private String localDaSala;
	private int idFilme;
	
	//Para diminuir o codigo, vamos supor que todas as sessões tem o mesmo numero de poltronas.
	private Poltrona[] poltronas = new Poltrona[20];
	
	public Sessao() {
		//criando poltronas
		for (int i = 0; i<20; i++) {
			Poltrona poltronaI = new Poltrona();
			poltronaI.setIdPoltrona(i);
			if (i<5) {
				poltronaI.setNumPoltrona("A"+(i+1));
			} else if(i<10 && i>=5) {
				poltronaI.setNumPoltrona("B"+(i+1));
			} else if(i<15 && i>=10) {
				poltronaI.setNumPoltrona("C"+(i+1));
			} else if(i<20 && i>=15) {
				poltronaI.setNumPoltrona("D"+(i+1));
			}
			poltronas[i] = poltronaI;				
		}
	}
	
	public int getIdSessao() {
		return idSessao;
	}

	public void setIdSessao(int idSessao) {
		this.idSessao = idSessao;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	public String getLocalDaSala() {
		return localDaSala;
	}

	public void setLocalDaSala(String localDaSala) {
		this.localDaSala = localDaSala;
	}
	
	public int getIdFilme() {
		return idFilme;
	}

	public void setIdFilme(int idFilme) {
		this.idFilme = idFilme;
	}

	public Poltrona[] getPoltronas() {
		return poltronas;
	}

	public void adicionarSessao(int idSessao, String data, String local) {
		setIdSessao(idSessao);
		setData(data);
		setLocalDaSala(local);
		
	}
	
	public void adicionarSessao(int idSessao, String data, String local, int idFilme) {
		setIdSessao(idSessao);
		setData(data);
		setLocalDaSala(local);
		setIdFilme(idFilme);
		
	}

}