package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Interface.IMatchDao;
import beans.MatchVersion;

public class MatchDaoImpl implements IMatchDao {

	private ArrayList<MatchVersion> MatchVersionVainqueur;
	private ArrayList<MatchVersion> MatchVersionFinaliste;
	private DaoFactory daoFactory;

	public MatchDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public ArrayList<MatchVersion> listerMatchVersion(String version) {
		ArrayList<MatchVersion> listVersion = new ArrayList<>();

		if (version.equals("Vainqueur")) {
			try {
				Connection conn = daoFactory.getConnexion();
				// Le deuxieme try ferme automatiquement la connexion a la fin et remplace
				// conn.close();
				try (conn) {
					PreparedStatement preparedStatement = conn.prepareStatement(
							"SELECT mt.ID AS IDMatch, jou.ID AS IDJoueur, jou.NOM, jou.PRENOM, jou.SEXE FROM match_tennis mt INNER JOIN joueur jou ON mt.ID_VAINQUEUR=jou.ID ORDER BY mt.ID;");
					ResultSet rs = preparedStatement.executeQuery();

					while (rs.next()) {
						listVersion.add(new MatchVersion(rs.getString("IDMatch"), rs.getString("IDJoueur"),
								rs.getString("NOM"), rs.getString("PRENOM"), rs.getString("SEXE")));
					}

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.setMatchVersionVainqueur(listVersion);
			return listVersion;

		} else {

			try {
				Connection conn = daoFactory.getConnexion();
				// Le deuxieme try ferme automatiquement la connexion a la fin et remplace
				// conn.close();
				try (conn) {
					PreparedStatement preparedStatement = conn.prepareStatement(
							"SELECT mt.ID AS IDMatch, jou.ID AS IDJoueur, jou.NOM, jou.PRENOM, jou.SEXE FROM match_tennis mt INNER JOIN joueur jou ON mt.ID_FINALISTE=jou.ID ORDER BY mt.ID;");
					ResultSet rs = preparedStatement.executeQuery();

					while (rs.next()) {
						listVersion.add(new MatchVersion(rs.getString("IDMatch"), rs.getString("IDJoueur"),
								rs.getString("NOM"), rs.getString("PRENOM"), rs.getString("SEXE")));
					}

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			this.setMatchVersionVainqueur(listVersion);
			return listVersion;

		}
	}

	@Override
	public ArrayList<MatchVersion> listerRechercheMatchVersion(String version, String colonne, String valeur) {
		ArrayList<MatchVersion> listMatch = new ArrayList<>();

		if (version.equals("Vainqueur")) {

			try {
				Connection conn = daoFactory.getConnexion();
				// le deuxieme try ferme automatique la connexion a la fin, remplace
				// conn.close();
				try (conn) {

					if (colonne.equals("IDJoueur")) {
						colonne = "ID_VAINQUEUR";
					}
					PreparedStatement preparedStatement = conn.prepareStatement(
							"SELECT mt.ID AS IDMatch, jou.ID AS IDVainqueur, jou.NOM, jou.PRENOM, jou.SEXE FROM match_tennis mt INNER JOIN joueur jou ON mt.ID_VAINQUEUR=jou.ID WHERE "
									+ colonne + " LIKE '%" + valeur + "%' ORDER BY mt.ID;");
					ResultSet rs = preparedStatement.executeQuery();

					while (rs.next()) {
						listMatch.add(new MatchVersion(rs.getString("IDMatch"), rs.getString("IDVainqueur"),
								rs.getString("NOM"), rs.getString("PRENOM"), rs.getString("SEXE")));
					}

				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return listMatch;

		} else {

			try {
				Connection conn = daoFactory.getConnexion();
				// le deuxieme try ferme automatique la connexion a la fin, remplace
				// conn.close();
				try (conn) {
					if (colonne.equals("IDJoueur")) {
						colonne = "ID_FINALISTE";
					}
					PreparedStatement preparedStatement = conn.prepareStatement(
							"SELECT mt.ID AS IDMatch, jou.ID AS IDFinaliste, jou.NOM, jou.PRENOM, jou.SEXE FROM match_tennis mt INNER JOIN joueur jou ON mt.ID_FINALISTE=jou.ID WHERE "
									+ colonne + " LIKE '%" + valeur + "%' ORDER BY mt.ID;");
					ResultSet rs = preparedStatement.executeQuery();

					while (rs.next()) {
						listMatch.add(new MatchVersion(rs.getString("IDMatch"), rs.getString("IDFinaliste"),
								rs.getString("NOM"), rs.getString("PRENOM"), rs.getString("SEXE")));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			return listMatch;

		}
	}

	public ArrayList<MatchVersion> getMatchVersionVainqueur() {
		return MatchVersionVainqueur;
	}

	public void setMatchVersionVainqueur(ArrayList<MatchVersion> MatchVersionVainqueur) {
		this.MatchVersionVainqueur = MatchVersionVainqueur;
	}

	public ArrayList<MatchVersion> getMatchVersionFinaliste() {
		return MatchVersionFinaliste;
	}

	public void setMatchVersionFinaliste(ArrayList<MatchVersion> MatchVersionFinaliste) {
		this.MatchVersionFinaliste = MatchVersionFinaliste;
	}

	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

}
