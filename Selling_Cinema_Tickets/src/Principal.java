import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Scanner;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map; 


public class Principal {
	
	public static void main(String[] args) {
		Input input = new Input();
		Cartaz cartaz = new Cartaz();
		
		Connection conn = null;
		try {
			
			String url = "jdbc:sqlite:/home/gustavobraga/Downloads/TrabalhoFinal/TrabalhoFinalSIBD.db";
			conn = DriverManager.getConnection(url);
			//Statement statement = conn.createStatement();

			Filme[] filmes = new Filme[25];
			{//Puxa todos os filmes do BD e salva no array de filmes acima
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery("Select * From Filme");
				
				int index = 0;
				while(rs.next()) {
					int idFilme = rs.getInt("idfilme");
					String nomeFilme = rs.getString("nome");
					Double valorFilme = rs.getDouble("valor");
					String tempoFilme = rs.getString("tempo");
					int calssificaoIdadeFilme = rs.getInt("classificacao");
					
					//Contar o numero de sessoes desse filme
					PreparedStatement statement2 = conn.prepareStatement("Select count(*) From Sessao Where idfilme = ?");
					statement2.setInt(1, idFilme);
					ResultSet rs2 = statement2.executeQuery();
					rs2.next();
					int numDeSessoes = rs2.getInt(1);
					
					if (numDeSessoes > 0) {
						Sessao[] sessoes = new Sessao[numDeSessoes];
						
						//Puxa as sessoes desse filme
						statement2 = conn.prepareStatement("Select * From Sessao Where idfilme = ?");
						statement2.setInt(1, idFilme);
						rs2 = statement2.executeQuery();
						
						int index2 = 0;
						while(rs2.next()) {
							int idSessao = rs2.getInt("idsessao");
							String dataSessao = rs2.getString("data");
							String localSessao = rs2.getString("local");
							int idFilmeSessao = rs2.getInt("idfilme");
							
							sessoes[index2] = new Sessao();
							sessoes[index2].adicionarSessao(idSessao, dataSessao, localSessao, idFilmeSessao);
							index2 += 1;
						}
						filmes[index] = new Filme();
						filmes[index].adicionarFilme(idFilme, nomeFilme, valorFilme, tempoFilme, calssificaoIdadeFilme, sessoes);
						rs2.close();
						statement2.close();
					} else {
						filmes[index] = new Filme();
						filmes[index].adicionarFilme(idFilme, nomeFilme, valorFilme, tempoFilme, calssificaoIdadeFilme);
					}
					index += 1;
				}
				rs.close();
				statement.close();
			}
			//ADD os filmes ao cartaz
			for(Filme i: filmes) {
				cartaz.setFilmes(i);
			}
			
			/*	
			
			
			Filme filme1 = new Filme();
			Filme filme2 = new Filme();
			Filme filme3 = new Filme();
			Filme filme4 = new Filme();
			Filme filme5 = new Filme();
			
			
			Sessao sessao1 = new Sessao();
			Sessao sessao2 = new Sessao();
			Sessao sessao3 = new Sessao();
			Sessao sessao4 = new Sessao();
			
			sessao1.adicionarSessao(1, "10/06 10:00h", "Sala 01");
			sessao2.adicionarSessao(2, "10/06 13:00h", "Sala 06");
			sessao3.adicionarSessao(3, "10/06 18:00h", "Sala 03");
			sessao4.adicionarSessao(4, "10/06 20:00h", "Sala 05");
			
			Sessao[] listaDeSessoes = {sessao1, sessao2, sessao3, sessao4}; 
	
			
			Valor esperado pelo metodo:
			 *
			 *adicionarFilme(
			 * 		int idFilme,
			 * 		String nomeFilme,  
			 * 		double valorFilme, 
			 * 		String tempoFilme, 
			 * 		int classificacaoIdade,
			 * 		Sessao[] sessoes
			 
			
			filme1.adicionarFilme(1, "Carros2", 30.3, "2:22", 15, listaDeSessoes);
			filme2.adicionarFilme(2, "Homem de Ferro 3", 20.1, "1:42", 18, listaDeSessoes);
			filme3.adicionarFilme(4, "The Batman", 30.3, "2:22", 15, listaDeSessoes);
			filme4.adicionarFilme(7, "Tenet", 20.1, "1:42", 18, listaDeSessoes);
			filme5.adicionarFilme(6, "Segredos de Dumbledore", 20.1, "1:42", 18, listaDeSessoes);
			
			//Add Filmes ao Cartaz
			cartaz.setFilmes(filme1);
			cartaz.setFilmes(filme2);
			cartaz.setFilmes(filme3);
			cartaz.setFilmes(filme4);
			cartaz.setFilmes(filme5);
			*/
				
				
			//Logica para roda o codigo
			while (true) {
				int resposta;
				int idFilmeEscolhido;
				
				//Nome da aplicacao
				System.out.println("____________________________________________________");
				System.out.println("                                                    ");
				System.out.format("%40s" , "Venda de Tickets Para Cinema\n");
				System.out.println("____________________________________________________\n");
				
				{//Primeiro input. 
					//A variavel opcoes contem os nomes que devem ser impressos para o usuário
					String[] opcoes = {"Cliente","Administrador"};
					String mensagem = "-Ola, escolhar uma opcao abaixo. \n-E digite o numero da opcao:";
					resposta = input.inputInt(opcoes, 0, mensagem);
				}
				
				//IF Nº 1
				if (resposta == 1) {
					System.out.println("____________________________________________________");
					System.out.println("                                                    ");
					System.out.format("%52s","------------------Filmes em Cartaz------------------\n \n");
					int quantidadeFilme = cartaz.listarFilmes();
					System.out.println("____________________________________________________");
					
					{//Input que recebe o filme escolhido pelo user.
						String[] opcoes = new String[0];
						String mensagem = "-Escolhar um dos filmes citadas acima. \n-E digite o id do Filme:";
						idFilmeEscolhido = input.inputInt(opcoes, quantidadeFilme, mensagem);
						cartaz.setFilmeEscolhido(idFilmeEscolhido);
					}
					
					//Vai retornar o nome do filme escolhido
					String filmeEscolhido = cartaz.listarFilme();
					
					System.out.println("\n-Voce escolheu o filme: ");
					System.out.println("   " + filmeEscolhido + "\n");
					
					{//Input lista detalhes do filme ou Voltar para o inicio
						String[] opcoes = {"Detalhes do Filme \"" + filmeEscolhido+"\"","Voltar"};
						String mensagem = "-Escolhar uma opcao abaixo. \n-E digite o numero da opcao:";
						resposta = input.inputInt(opcoes, 0, mensagem);
					}
					
					//IF Nº 2
					if (resposta == 1) {
						cartaz.detalhesDoFilmeEscolhido();
						
						{//Input Comprar ingresso ou Voltar para o inicio
							String[] opcoes = {"Comprar ingresso","Cancelar"};
							String mensagem = "-Escolhar uma opcao abaixo. \n-E digite o numero da opcao:";
							resposta = input.inputInt(opcoes, 0, mensagem);
						}
						
						//IF Nº 3
						if (resposta == 1) {
							interno:
							while (true) {
								System.out.println("____________________________________________________");
								System.out.println("                                                    ");
								System.out.format("%52s","------------------Lista de Sessoes------------------\n \n");
								
								int respostaDoUser2;
								int numDeSessoes = cartaz.listarSessoesDoFilmeEscolhido();
								
								{//Input escolher sessao
									String[] opcoes = new String[0];
									String mensagem = "-Escolhar uma sessao listada acima. \n-E digite o numero da opcao:";
									resposta = input.inputInt(opcoes, numDeSessoes, mensagem);
									cartaz.setSessaoEscolhida(resposta);
								}
								
								{//Input continuar ou trocar sessao ou voltar para inicio
									String[] opcoes = {"Continuar", "Trocar Sessao", "Cancelar"};
									String mensagem = "\n-Escolhar uma opcao listada abaixo:";
									respostaDoUser2 = input.inputInt(opcoes, 0, mensagem);
								}
								
								//IF Nº 4
								if (respostaDoUser2 == 1) { //Continuar
									Boolean finalizarCompra = false;
									Integer idDaPoltrona = null;
									
									
									interno2:
									while (true) {
										if (finalizarCompra == false) {
											System.out.println();
											System.out.println("-Escolhar uma poltrona listada abaixo.\n-E digite o nome dela:");
											
											//key=numPoltrona e values=idPoltrona
											Map <String, Integer> numDasPoltronasLivres = cartaz.listarPoltronasDoFilmeEscolhido();
											idDaPoltrona = input.inputStr(numDasPoltronasLivres);
										}
										
										
										if (idDaPoltrona != null) {
											if (finalizarCompra == false) {
												cartaz.setDicPoltronasEscolhidas(idDaPoltrona);
												//OBS: Resolver bug das poltronas: Se a comprar for cancelar as poltronas escolhidas pelo cliente devem voltar ao estado anterior "false".
												
												cartaz.imprimirPoltronasEscolhidas();
												
												{//Input escolher mais uma poltrona ou finalizar comprar ou voltar para inicio
													String[] opcoes = {"Finalizar Comprar", "Escolher outra poltrona", "Remover Poltrona Escolhida","Cancelar"};
													String mensagem = "\n-Escolhar uma opcao listada abaixo:";
													resposta = input.inputInt(opcoes, 0, mensagem);
												}
											}
											
											
											//IF Nº 5
											if (resposta == 1) { //Finalizar Comprar
												if(cartaz.sizeDePoltronasEscolhidas()>0) {
													//Imprimir nota fiscal
													
													System.out.println("______________________________________");
													System.out.println("                                                    ");
													System.out.format("%38s","-------------Nota Fiscal--------------\n\n");
													String[] dadosDaCompra = cartaz.dadosDaCompra();
													
													System.out.format("%-7s%31s\n","Filme: ",dadosDaCompra[0]);
													System.out.format("%-10s%28s\n","Sessao: ",dadosDaCompra[1]);
													System.out.format("%-11s%27s\n","Poltronas: ",dadosDaCompra[2]);
													System.out.format("%-16s%22s\n\n\n\n","Valor Do Filme: ","R$ "+dadosDaCompra[3]);
													System.out.format("%-10s%28s\n","Total: ","R$ "+dadosDaCompra[4]);
													System.out.println("______________________________________");
													
													{//Input pagar compra ou cancelar compra
														String[] opcoes = {"Pagar", "Cancelar compra"};
														String mensagem = "\n-Escolhar uma opcao listada abaixo:";
														resposta = input.inputInt(opcoes, 0, mensagem);
													}
													if(resposta == 1) {//Pagar
														System.out.println("Informe alguns dados, para podermos finalizar a compra.");
														System.out.print("Nome Completo: ");
														String nomeCliente = input.inputStrLogin();
														System.out.print("Data de Nascimento: ");
														String dataCliente = input.inputStrLogin();
														System.out.print("CPF: ");
														String cpfCliente = input.inputStrLogin();
														
														Cliente cliente1 = new Cliente(nomeCliente, cpfCliente, dataCliente);
														
														System.out.print("Numero do Cartao de Credito: ");
														String numeroCartao = input.inputStrLogin();
														System.out.print("Vencimento do Cartao de Credito: ");
														String vencimentoCartao = input.inputStrLogin();
														System.out.print("CVV do Cartao de Credito: ");
														String cvvCartao = input.inputStrLogin();
														
														
														System.out.println("Comprar realizada com sucesso");
														
														//Implementar funcionalidade de imprimir os tickets depois da compra ser efetuada
														
													} else if(resposta == 2) {//Cancelar compra
														break interno;
													}
													
													break interno2;
												}else {
													System.out.println("e preciso escolher uma poltrona para poder finalizar a compra.");
													finalizarCompra = false;
												}
												
												
											} else if(resposta == 2) { //Escolher outra poltrona
												//Nao faz nada, deixa o loop interno2 roda de novo
												
											} else if(resposta == 3) { //Remover Poltrona Escolhida
												
												interno3:
												while(true) {
													System.out.println();
													cartaz.imprimirPoltronasEscolhidas();
													System.out.println("-Das poltronas listadas acima. Escolhar uma para removela.\n-E digite o nome dela:");
													
													Map <String, Integer> numDasPoltronasEscolhidas = cartaz.numDasPoltronasEscolhidas();
													idDaPoltrona = input.inputStr(numDasPoltronasEscolhidas);
													
													if(idDaPoltrona != null) {
														cartaz.removerPoltronaEscolhida(idDaPoltrona);
														
														{//Input escolher mais uma poltrona ou finalizar comprar ou voltar para inicio
															String[] opcoes = {"Finalizar Comprar", "Escolher outra poltrona", "Remover Outra Poltrona Escolhida","Cancelar"};
															String mensagem = "\n-Escolhar uma opcao listada abaixo:";
															resposta = input.inputInt(opcoes, 0, mensagem);
														}
														if (resposta == 1) { //Finalizar Comprar
															finalizarCompra = true;
															resposta = 1;
															break interno3;
															
														} else if(resposta == 2) { //Escolher outra poltrona
															//Da break no loop interno3, e deixar o loop interno2 roda de novo
															break interno3;
															
														} else if(resposta == 3) { //Remover outra Poltrona Escolhida
															//Faz nada, deixar o loop interno3 roda de novo
															
														} else if(resposta == 4) {//Cancelar
															break interno;
														}
													}
												}
												
												
											} else if(resposta == 4) {//Cancelar
												break interno;
											}
											
											
										}
									}
	//								
									break interno;
								} else if(respostaDoUser2 == 2) { //Trocar Sessao
									//Nao faz nada e voltar para o comeco do loop interno
								
								} else if(respostaDoUser2 == 3) { //Cancelar
									//Da brack no loop interno e voltar para o comeco do loop externo
									break interno;
								}
							}
							
							
						} else if (resposta == 2){
							//Nao faz nada e voltar para o comeco do loop
						}
					
					} else if (resposta == 2){
						//Nao faz nada e voltar para o comeco do loop
					}
					
					
				}else if (resposta == 2) {
					/*Implmentar Funcionalidades do administrador
					 *  -Cadastrar Filme
					 *  -Deletar Filme
					 *  -Criar Sessoes
					 *  -Deletar Sessoes
					 */
					
					System.out.println("____________________________________________________");
					System.out.println("                                                    ");
					System.out.format("%46s", "Voce esta acessando como administrador\n");
					System.out.println("____________________________________________________");
					
					{//Primeiro input do ADD 
						//A variavel opcoes contem os nomes que devem ser impressos para o usuário
						String[] opcoes = {"Cadastrar Filme", "Deletar Filme", "Cadastrar Sessao", "Deletar Sessao"};
						String mensagem = "\n-Escolha uma das opcoes abaixo. \n-E digite o numero da opcao: ";
						resposta = input.inputInt(opcoes, 0, mensagem);
					}
					
					//Cadastrar Filme
					if (resposta == 1) {
						System.out.println("____________________________________________________");
						System.out.println("                                                    ");
						System.out.format("%34s", "Cadastrar Filme\n");
						System.out.println("____________________________________________________");
						
						int idFilme;
						String nomeFilme;
						double valorFilme;
						String tempoFilme;
						int classificacaoIdade;
						
						{//Input do nome do filme
							String mensagem = "Digite o nome do Filme: ";
							nomeFilme = input.inputStr(mensagem);
						}
						{//Input do valor do filme
							String mensagem = "Digite o valor do Filme: ";
							valorFilme = input.inputDouble(mensagem);
						}
						{//Input do tempo do filme
							String mensagem = "Digite o tempo do Filme: ";
							tempoFilme = input.inputStr(mensagem);
						}
						{//Input da classificacao de idade do filme
							String mensagem = "Digite a classificacao de idade para o Filme: ";
							classificacaoIdade= input.inputInt(mensagem);
						}
						
						
						PreparedStatement statement = conn.prepareStatement("insert into Filme (nome, valor, tempo, classificacao) values(?,?,?,?)");
						statement.setString(1, nomeFilme);
						statement.setDouble(2, valorFilme);
						statement.setString(3, tempoFilme);
						statement.setInt(4, classificacaoIdade);
						statement.executeUpdate();
						statement.close();
					
					}else if (resposta == 2) { //Deletar Filme
						int idDeletar;
						
						System.out.println("____________________________________________________");
						System.out.println("                                                    ");
						System.out.format("%35s", "Deletar Filme\n");
						System.out.println("____________________________________________________");
						System.out.println("                                                    ");
						System.out.format("%52s","--------------------Filmes em Cartaz----------------\n \n");
						
						int quantidadeFilme = cartaz.listarFilmes();						
						
						System.out.println("____________________________________________________");
						
						{//Input que recebe o filme que vai ser deletado.
							String[] opcoes = new String[0];
							String mensagem = "-Escolhar um dos filmes citadas acima para ser deletado. \n-E digite o id do Filme:";
							idFilmeEscolhido = input.inputInt(opcoes, quantidadeFilme, mensagem);
							cartaz.setFilmeEscolhido(idFilmeEscolhido);
							System.out.println("ID do filme escolhido para ser deletado: " + idFilmeEscolhido);
						}
						
						Filme filmeEscolhidoToDelete = cartaz.getFilmeEscolhido();
						int idFilmeDelete = filmeEscolhidoToDelete.getIdFilme();
						
						
						PreparedStatement statement2 = conn.prepareStatement("Delete from Filme where idfilme = ?");
						statement2.setInt(1, idFilmeDelete);
						statement2.executeUpdate();
						
						statement2.close();
						
						cartaz.removerFilme(idFilmeDelete);

					}
					
					
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}
}
