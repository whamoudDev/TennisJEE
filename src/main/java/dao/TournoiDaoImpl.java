package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Interface.ITournoiDao;
import beans.Tournoi;

public class TournoiDaoImpl implements ITournoiDao {

	private ArrayList<Tournoi> allTournoi;
	private DaoFactory daoFactory;

	public TournoiDaoImpl() {
		super();

	}

	public TournoiDaoImpl(DaoFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public ArrayList<Tournoi> getAllTournoi() {
		return allTournoi;
	}

	@Override
	public void setAllTournoi(ArrayList<Tournoi> allTournoi) {
		this.allTournoi = allTournoi;
	}

	@Override
	public ArrayList<Tournoi> lister() {
		ArrayList<Tournoi> listTournoi = new ArrayList<>();

		try {
			Connection conn = daoFactory.getConnexion();
			// Le deuxieme try ferme automatique la connexion a fin, remplace conn.close();
			try (conn) {
				PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM tournoi; ");
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					listTournoi.add(new Tournoi(rs.getString("ID"), rs.getString("NOM"), rs.getString("CODE")));
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.setAllTournoi(listTournoi);
		return listTournoi;
	}

	@Override
	public ArrayList<Tournoi> listerRecherche(String colonne, String valeur) {
		// TODO Auto-generated method stub

		ArrayList<Tournoi> listTournoi = new ArrayList<>();
		try {
			Connection conn = daoFactory.getConnexion();
			// le deuxieme try ferme automatique la connexion a fin, remplace conn.close();
			try (conn) {
				PreparedStatement preparedStatement = conn
						.prepareStatement("SELECT * FROM tournoi WHERE " + colonne + " LIKE '%" + valeur + "%'  ; ");
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					listTournoi.add(new Tournoi(rs.getString("ID"), rs.getString("NOM"), rs.getString("CODE")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listTournoi;
	}

	@Override
	public boolean ajouter(Tournoi tournoi) {
		// TODO Auto-generated method stub

		try {
			Connection conn = daoFactory.getConnexion();
			// le deuxieme try ferme automatique la connexion a fin, remplace conn.close();
			try (conn) {
				if (!tournoi.getNom().equals("") && !tournoi.getCode().equals("")) {

					PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Tournoi WHERE NOM='"
							+ tournoi.getNom() + "' AND  CODE= '" + tournoi.getCode() + "'; ");
					ResultSet rs = preparedStatement.executeQuery();
					int doublon = 0;
					while (rs.next()) {
						doublon++;
					}

					if (doublon > 0) {
						tournoi.setNom(tournoi.getNom() + doublon);
					}

					PreparedStatement preparedStatement1 = conn
							.prepareStatement("INSERT INTO tournoi (NOM, CODE) VALUES (?,?); ");
					preparedStatement1.setString(1, tournoi.getNom());
					preparedStatement1.setString(2, tournoi.getCode());
					int rowCount = preparedStatement1.executeUpdate();

					return rowCount > 0;

				} else {
					return false;
				}

			} catch (SQLException e) {

				e.printStackTrace();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean modifier(String id, Tournoi tournoi) {
		// TODO Auto-generated method stub
		try {
			Connection conn = daoFactory.getConnexion();
			try (conn) {
				if (!tournoi.getNom().equals("") && !tournoi.getCode().equals("")) {

					PreparedStatement preparedStatement = conn
							.prepareStatement("UPDATE tournoi SET NOM= ? , CODE = ? WHERE ID = ? ");
					preparedStatement.setString(1, tournoi.getNom());
					preparedStatement.setString(2, tournoi.getCode());
					preparedStatement.setString(3, id);
					int rowCount = preparedStatement.executeUpdate();

					return rowCount > 0;

				} else {
					return false;
				}

			}
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean supprimer(String id) {
		// TODO Auto-generated method stub
		try {
			Connection conn = daoFactory.getConnexion();
			try (conn) {

				PreparedStatement preparedStatement1 = conn.prepareStatement(
						" DELETE sco FROM tournoi tour INNER JOIN epreuve ep ON tour.ID=ep.ID_TOURNOI INNER JOIN match_tennis mat ON ep.ID=mat.ID_EPREUVE INNER JOIN score_vainqueur sco ON mat.ID=sco.ID_MATCH WHERE tour.ID= ? ");
				preparedStatement1.setString(1, id);
				preparedStatement1.executeUpdate();

				PreparedStatement preparedStatement2 = conn.prepareStatement(
						" DELETE mat FROM tournoi tour INNER JOIN epreuve ep ON tour.ID=ep.ID_TOURNOI INNER JOIN match_tennis mat ON ep.ID=mat.ID_EPREUVE WHERE tour.ID= ? ");
				preparedStatement2.setString(1, id);
				preparedStatement2.executeUpdate();

				PreparedStatement preparedStatement3 = conn.prepareStatement(
						" DELETE ep FROM tournoi tour INNER JOIN epreuve ep ON tour.ID=ep.ID_TOURNOI WHERE tour.ID= ? ");
				preparedStatement3.setString(1, id);
				preparedStatement3.executeUpdate();

				PreparedStatement preparedStatement4 = conn.prepareStatement(" DELETE FROM tournoi WHERE ID= ? ");
				preparedStatement4.setString(1, id);
				int rowCount = preparedStatement4.executeUpdate();

				return rowCount > 0;

			}
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}
	}

}
