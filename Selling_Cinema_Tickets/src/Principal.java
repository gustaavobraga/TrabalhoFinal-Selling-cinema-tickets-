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
		ComandosToBD comandosToBD = new ComandosToBD();
		
		
		Connection conn = null;
		Filme[] filmes = comandosToBD.selectFilmes(conn);
		if (filmes != null) {
			try {
				String url = "jdbc:sqlite:/home/gustavobraga/Downloads/TrabalhoFinal/TrabalhoFinalSIBD.db";
				conn = DriverManager.getConnection(url);
				
				//ADD os filmes ao cartaz
				for(Filme i: filmes) {
					cartaz.setFilmes(i);
				}
							
					
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
						//A variavel opcoes contem os nomes que devem ser impressos para o usuario
						String[] opcoes = {"Cliente","Administrador","Cadastrar Administrador"};
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
							
							
							int sizeSessoes=cartaz.getSizeSessaoDoFilmeEscolhido();
							
							//IF Nº 3
							if (resposta == 1 & sizeSessoes > 0) {
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
										
										{//Puxa a lista de poltronas que estao ocupadas da sessao escolhida
											Sessao sessaoEscolhida = cartaz.getSessaoEscolhida();
											int idSessaoEscolhida = sessaoEscolhida.getIdSessao();
											
											//Obs: So sera cadastro na tabela poltrona as poltronas que estao com status igual a true
											//Contar o numero de poltronas com status iguais a true da sessao escolhida
											PreparedStatement statement = conn.prepareStatement("Select count(*) From Poltrona Where idSessao = (?)");
											statement.setInt(1, idSessaoEscolhida);
											ResultSet rs = statement.executeQuery();
											rs.next();
											int numDePoltronas = rs.getInt(1);
											
											if (numDePoltronas > 0) {
												Poltrona[] poltronas = new Poltrona[numDePoltronas];
												
												//Puxa as poltroas que estao com status igual a true
												statement = conn.prepareStatement("Select * From Poltrona Where idSessao = (?)");
												statement.setInt(1, idSessaoEscolhida);
												rs = statement.executeQuery();
												
												int index = 0;
												while(rs.next()) {
													int idPoltrona = rs.getInt("idPoltrona");
													String numPoltrona = rs.getString("numPoltrona");
													//Como o valor da coluna statusPoltrona vai ser sempre igual a 1 (true)
													//entao nao vamos puxar esse valor do BD
													//int statusPoltrona = rs.getInt("statusPoltrona");
													
													poltronas[index] = new Poltrona();
													poltronas[index].adicionarPoltrona(idPoltrona, numPoltrona);
													index += 1;
												}
												
												sessaoEscolhida.setStatusPoltrona(poltronas);
											}
										}
										
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
															
															//Salvando as poltronas que foram compradas no BD
															Map <Integer, Poltrona> dicPoltronasEscolhidas = cartaz.getDicPoltronasEscolhidas();
															for (Integer key: dicPoltronasEscolhidas.keySet()) {
																
																int idPoltrona = dicPoltronasEscolhidas.get(key).getIdPoltrona();
																String numPoltrona = dicPoltronasEscolhidas.get(key).getNumPoltrona();
																int idSessao = cartaz.getSessaoEscolhida().getIdSessao();
																
																PreparedStatement statement = conn.prepareStatement("insert into Poltrona  values(?,?,1,?)");
																statement.setInt(1, idPoltrona);
																statement.setString(2, numPoltrona);
																statement.setInt(3, idSessao);
																statement.executeUpdate();
																statement.close();
																
															}
															
															
															
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
						String nomeDeUser;
						String senha;
						
						PreparedStatement statement3;
						ResultSet rs;
						loopADD:
						while (true) {
							{//Input do nomeDeUser do administrador
								String mensagem = "Nome do usuario: ";
								nomeDeUser = input.inputStr(mensagem);
							}
							{//Input do senha do administrador
								String mensagem = "Senha: ";
								senha = input.inputStr(mensagem);
							}
							statement3 = conn.prepareStatement("Select count(*) From Administrador Where nomeDeUser = (?) AND senha = (?)");
							statement3.setString(1, nomeDeUser);
							statement3.setString(2, senha);
							rs = statement3.executeQuery();
							rs.next();
							
							if (rs.getInt(1) == 1) {
								break loopADD;
							}
							System.out.println("Usuario ou Senha invalida");
						}
						rs.close();
						statement3.close();
						
						
						System.out.println("____________________________________________________");
						System.out.println("                                                    ");
						System.out.format("%46s", "Voce esta acessando como administrador\n");
						System.out.println("____________________________________________________");
						
						{//Primeiro input do ADD 
							//A variavel opcoes contem os nomes que devem ser impressos para o usuario
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
							
							//Puxa a lista de filmes atualizada do BD
							filmes = comandosToBD.selectFilmes(conn);
							
							//Apagar os filmes que estao no cartaz
							cartaz.removerFilme();
							
							//ADD os novos filmes ao cartaz
							for(Filme i: filmes) {
								cartaz.setFilmes(i);
							}
							
						
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
							}
							
							Filme filmeEscolhidoToDelete = cartaz.getFilmeEscolhido();
							int idFilmeDelete = filmeEscolhidoToDelete.getIdFilme();
							
							
							PreparedStatement statement2 = conn.prepareStatement("Delete from Filme where idfilme = ?");
							statement2.setInt(1, idFilmeDelete);
							statement2.executeUpdate();
							
							statement2.close();
							System.out.println("Filme deletado com sucesso.");
							
							cartaz.removerFilme(idFilmeDelete);
	
						} else if (resposta == 3) { //Cadastrar Sessoes
							System.out.println("____________________________________________________");
							System.out.println("                                                    ");
							System.out.format("%35s", "Cadastrar Sessoes\n");
							System.out.println("____________________________________________________");
							System.out.println("                                                    ");
							System.out.format("%52s","--------------------Filmes em Cartaz----------------\n \n");
							
							int quantidadeFilme = cartaz.listarFilmes();						
							
							System.out.println("____________________________________________________");
							
							{//Input que recebe o filme que vai ser add uma nova sessao.
								String[] opcoes = new String[0];
								String mensagem = "-Escolhar um filme acima para adicionar uma nova sessao. \n-E digite o id do Filme:";
								idFilmeEscolhido = input.inputInt(opcoes, quantidadeFilme, mensagem);
								cartaz.setFilmeEscolhido(idFilmeEscolhido);
							}
							Filme filmeEscolhidoToAddSessao = cartaz.getFilmeEscolhido();
							int idFilme = filmeEscolhidoToAddSessao.getIdFilme();
							
							String dataSessao;
							String localSessao;
							
							{//Input da data da sessao
								String mensagem = "Digite a data da sessao (0:00h DD/MM): ";
								dataSessao = input.inputStr(mensagem);
							}
							{//Input da data da sessao
								String mensagem = "Digite o endereco da sala da sessao: ";
								localSessao = input.inputStr(mensagem);
							}
							
							PreparedStatement statement = conn.prepareStatement("insert into Sessao (data, local, idfilme) values(?,?,?)");
							statement.setString(1, dataSessao);
							statement.setString(2, localSessao);
							statement.setInt(3, idFilme);
							statement.executeUpdate();
							statement.close();
							
							//Puxa a lista de filmes do BD atualizada com a nova sessao
							filmes = comandosToBD.selectFilmes(conn);
							
							//Apagar os filmes que estao no cartaz
							cartaz.removerFilme();
							
							//ADD os novos filmes ao cartaz
							for(Filme i: filmes) {
								cartaz.setFilmes(i);
							}
							
						}else if (resposta == 4) {//Deletar Sessao
							int idSessaoDelete;
							
							System.out.println("____________________________________________________");
							System.out.println("                                                    ");
							System.out.format("%35s", "Deletar Sessao\n");
							System.out.println("____________________________________________________");
							System.out.println("                                                    ");
							System.out.format("%52s","--------------------Filmes em Cartaz----------------\n \n");
							
							int quantidadeFilme = cartaz.listarFilmes();						
							
							System.out.println("____________________________________________________");
							
							{//Input que recebe o filme que vai ter a sessao deletada.
								String[] opcoes = new String[0];
								String mensagem = "-Escolhar um filme acima para remover uma sessao. \n-E digite o id do Filme:";
								idFilmeEscolhido = input.inputInt(opcoes, quantidadeFilme, mensagem);
								cartaz.setFilmeEscolhido(idFilmeEscolhido);
							}
							
							System.out.println("____________________________________________________");
							System.out.println("                                                    ");
							System.out.format("%52s","------------------Lista de Sessoes------------------\n \n");
							
							int sizeSessoes = cartaz.listarSessoesDoFilmeEscolhido();
							
							{//Input que recebe o filme que vai ter a sessao deletada.
								String[] opcoes = new String[0];
								String mensagem = "-Escolhar uma sessao acima para ser deletada. \n-E digite o id da sessao:";
								idSessaoDelete = input.inputInt(opcoes, sizeSessoes, mensagem);
								cartaz.setSessaoEscolhida(idSessaoDelete);
							}
							
							Sessao sessaoDelete = cartaz.getSessaoEscolhida();
							idSessaoDelete = sessaoDelete.getIdSessao();
							
							PreparedStatement statement = conn.prepareStatement("Delete from Sessao where idsessao = (?)");
							statement.setInt(1, idSessaoDelete);
							statement.executeUpdate();
							statement.close();
							
							//Puxa a lista de filmes do BD atualizada com a nova sessao
							filmes = comandosToBD.selectFilmes(conn);
							
							//Apagar os filmes que estao no cartaz
							cartaz.removerFilme();
							
							//ADD os novos filmes ao cartaz
							for(Filme i: filmes) {
								cartaz.setFilmes(i);
							}	
						}
						
					} else if (resposta == 3) {
						String nome;
						String cpf;
						String nomeDeUser;
						String senha;
						
						System.out.println("____________________________________________________");
						System.out.println("                                                    ");
						System.out.format("%38s", "Cadastrar Administrador\n");
						System.out.println("____________________________________________________");
						
						
						{//Input do nome do administrador
							String mensagem = "Nome do administrador: ";
							nome = input.inputStr(mensagem);
						}
						{//Input do cpf do administrador
							String mensagem = "CPF: ";
							cpf = input.inputStr(mensagem);
						}
						{//Input do nomeDeUser do administrador
							String mensagem = "Nome do usuario: ";
							nomeDeUser = input.inputStr(mensagem);
						}
						{//Input do senha do administrador
							String mensagem = "Senha: ";
							senha = input.inputStr(mensagem);
						}
						
						PreparedStatement statement = conn.prepareStatement("insert into Administrador values(?,?,?,?)");
						statement.setString(1, nome);
						statement.setString(2, cpf);
						statement.setString(3, nomeDeUser);
						statement.setString(4, senha);
						statement.executeUpdate();
						statement.close();
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
}
