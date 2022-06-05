import java.util.Scanner;
import java.util.InputMismatchException; 

public class Input {
	Scanner ler = new Scanner(System.in);

	public int inputInt(String[] opcoes) {
		int resposta;
		while (true) {
			System.out.println("Escolhar uma opção baixo e digite o número da opção:");
			
			for (int i=0; i < opcoes.length; i++) {
				System.out.println("   " + (i+1) + "." + opcoes[i]);
			}
			
			try {
				resposta = ler.nextInt();
				return resposta;
		
			} catch( InputMismatchException var) {
				System.out.println("Valor invalido, informe um valor inteiro.\n");
				ler.nextLine();
				
			} catch( Exception var) {
				System.out.println("Erro: " + var);
				ler.nextLine();
			}
		}
	}
	
}

