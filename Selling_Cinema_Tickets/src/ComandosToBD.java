import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ComandosToBD {
	
	//Essa class contem os comandos do BD que sao usados em mais de um lugar no programa
	
	public Filme[] selectFilmes(Connection conn) {
		try {
			String url = "jdbc:sqlite:TrabalhoFinalSIBD.db";
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
			return filmes;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
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
