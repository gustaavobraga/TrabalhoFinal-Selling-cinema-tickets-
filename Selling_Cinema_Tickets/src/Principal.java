import java.util.Scanner;
import java.util.InputMismatchException; 
public class Principal {

	public static void main(String[] args) {
		
		Input input = new Input();
		
		String[] opcoes = {"Usuário","Administrador"};
		int resposta =input.inputInt(opcoes);
		System.out.println(resposta);
		

		

	}

}
