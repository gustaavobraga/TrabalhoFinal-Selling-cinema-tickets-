package projetofinal;
public class Main {
	public static void main(String[] args) {
		Cliente c1 = new Cliente("Ana", "001.002.003-45", "01-01-2000");
		
		Filme f1 = new Filme();
		f1.adicionarFilme("Django Livre", "01", 15);
		System.out.println(f1.verFilme());
		
		System.out.println("========================");
		
		Sess�o s1 = new Sess�o();
		s1.adicionarSess�o("01", "05-07-2022", "14:20");
		System.out.println(s1.verSess�o());
		
		System.out.println("========================");
		
		Poltrona pol1 = new Poltrona();
		pol1.adicionarPoltrona("10", true);
		pol1.verPoltrona();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
