package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Interface.IJoueurDao;
import beans.BeanException;
import beans.Joueur;

public class JoueurDaoImpl implements IJoueurDao {

	private ArrayList<Joueur> allJoueur;
	private DaoFactory daoFactory;

	public JoueurDaoImpl() {
		super();

	}

	public JoueurDaoImpl(DaoFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	@Override
	public ArrayList<Joueur> getAllJoueur() {
		return allJoueur;
	}

	@Override
	public void setAllJoueur(ArrayList<Joueur> allJoueur) {
		this.allJoueur = allJoueur;
	}

	@Override
	public ArrayList<Joueur> lister() throws BeanException, DaoException {
		ArrayList<Joueur> listJoueur = new ArrayList<>();

		try {
			Connection conn = daoFactory.getConnexion();
			// Le deuxieme try ferme automatique la connexion a fin, remplace conn.close();
			try (conn) {
				PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM joueur; ");
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					listJoueur.add(new Joueur(rs.getString("ID"), rs.getString("NOM"), rs.getString("PRENOM"),
							rs.getString("SEXE")));
				}

			}
		} catch (SQLException e) {

			throw new DaoException("Impossible de communiquer avec la bdd");
		}
		this.setAllJoueur(listJoueur);
		return listJoueur;
	}

	@Override
	public ArrayList<Joueur> listerRecherche(String colonne, String valeur) throws BeanException {
		// TODO Auto-generated method stub

		ArrayList<Joueur> listJoueur = new ArrayList<>();
		try {
			Connection conn = daoFactory.getConnexion();
			// le deuxieme try ferme automatique la connexion a fin, remplace conn.close();
			try (conn) {
				PreparedStatement preparedStatement = conn
						.prepareStatement("SELECT * FROM joueur WHERE " + colonne + " LIKE '%" + valeur + "%'  ; ");
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					listJoueur.add(new Joueur(rs.getString("ID"), rs.getString("NOM"), rs.getString("PRENOM"),
							rs.getString("SEXE")));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listJoueur;
	}

	@Override
	public boolean ajouter(Joueur joueur) throws BeanException {
		// TODO Auto-generated method stub

		try {
			Connection conn = daoFactory.getConnexion();
			// le deuxieme try ferme automatique la connexion a fin, remplace conn.close();
			try (conn) {
				if (!joueur.getNom().equals("") && !joueur.getPrenom().equals("")
						&& (joueur.getSexe().substring(0, 1).equals("H")
								|| joueur.getSexe().substring(0, 1).equals("F"))) {

					PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM joueur WHERE NOM='"
							+ joueur.getNom() + "' AND  PRENOM= '" + joueur.getPrenom() + "'; ");
					ResultSet rs = preparedStatement.executeQuery();
					int doublon = 0;
					while (rs.next()) {
						doublon++;
					}

					if (doublon > 0) {
						joueur.setPrenom(joueur.getPrenom() + doublon);
					}

					PreparedStatement preparedStatement1 = conn
							.prepareStatement("INSERT INTO joueur (NOM, PRENOM, SEXE) VALUES (?,?,?); ");
					preparedStatement1.setString(1, joueur.getNom());
					preparedStatement1.setString(2, joueur.getPrenom());
					preparedStatement1.setString(3, joueur.getSexe());
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
	public boolean modifier(String id, Joueur joueur) {
		// TODO Auto-generated method stub
		try {
			Connection conn = daoFactory.getConnexion();
			try (conn) {
				if (!joueur.getNom().equals("") && !joueur.getPrenom().equals("")
						&& (joueur.getSexe().substring(0, 1).equals("H")
								|| joueur.getSexe().substring(0, 1).equals("F"))) {

					PreparedStatement preparedStatement = conn
							.prepareStatement("UPDATE joueur SET NOM= ? , PRENOM = ? , SEXE = ? WHERE ID = ? ");
					preparedStatement.setString(1, joueur.getNom());
					preparedStatement.setString(2, joueur.getPrenom());
					preparedStatement.setString(3, joueur.getSexe());
					preparedStatement.setString(4, id);
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
						" DELETE sco FROM joueur jou INNER JOIN match_tennis mat ON jou.ID=mat.ID_VAINQUEUR OR jou.ID= mat.ID_FINALISTE INNER JOIN score_vainqueur sco ON mat.ID=sco.ID_MATCH WHERE jou.ID= ? ");
				preparedStatement1.setString(1, id);
				preparedStatement1.executeUpdate();

				PreparedStatement preparedStatement2 = conn.prepareStatement(
						" DELETE mat FROM joueur jou INNER JOIN match_tennis mat ON jou.ID=mat.ID_VAINQUEUR OR jou.ID= mat.ID_FINALISTE WHERE jou.ID= ? ");
				preparedStatement2.setString(1, id);
				preparedStatement2.executeUpdate();

				PreparedStatement preparedStatement3 = conn.prepareStatement(" DELETE FROM joueur WHERE ID= ? ");
				preparedStatement3.setString(1, id);
				int rowCount = preparedStatement3.executeUpdate();

				return rowCount > 0;

			}
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}
	}

}
