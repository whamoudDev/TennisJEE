/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import Interface.IEpreuveDao;
import beans.BeanException;
import beans.Epreuve;
import beans.EpreuveData;
import beans.Joueur;
import beans.Match;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author WalidDev
 */
public class EpreuveDaoImpl implements IEpreuveDao {

	private ArrayList<EpreuveData> listEpreuveData;
	private DaoFactory daoFactory;

	public EpreuveDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public ArrayList<EpreuveData> listerEpreuveData() throws BeanException {
		ArrayList<EpreuveData> listEpreuve = new ArrayList<>();

		try {
			Connection conn = daoFactory.getConnexion();
			// Le deuxieme try ferme automatiquement la connexion a  la fin et remplace
			// conn.close();
			try (conn) {
				PreparedStatement preparedStatement = conn.prepareStatement(
						"SELECT ep.ID AS IdEpreuve, ep.ANNEE AS Annee, ep.TYPE_EPREUVE AS Type, mat.ID AS IdMatch, jouv.NOM AS NomVainqueur, jouv.PRENOM AS PrenomVainqueur, jouf.NOM AS NomFinaliste, jouf.PRENOM AS PrenomFinaliste "
								+ "FROM epreuve ep " + "INNER JOIN match_tennis mat ON ep.ID=mat.ID_EPREUVE "
								+ "INNER JOIN joueur jouv ON mat.ID_VAINQUEUR=jouv.ID "
								+ "INNER JOIN joueur jouf ON mat.ID_FINALISTE=jouf.ID " + "ORDER BY ep.ID;");
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					listEpreuve.add(new EpreuveData(
							new Epreuve(rs.getString("IdEpreuve"), rs.getString("Annee"), rs.getString("Type")),
							new Match(rs.getString("IdMatch")),
							new Joueur(rs.getString("NomVainqueur"), rs.getString("PrenomVainqueur")),
							new Joueur(rs.getString("NomFinaliste"), rs.getString("PrenomFinaliste"))));
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.setListEpreuveData(listEpreuve);
		return listEpreuve;
	}

	@Override
	public ArrayList<EpreuveData> listerEpreuveDataFiltered(String annee, String type) throws BeanException {

		ArrayList<EpreuveData> listEpreuve = new ArrayList<>();

		try {
			Connection conn = daoFactory.getConnexion();
			// Le deuxieme try ferme automatiquement la connexion a  la fin et remplace
			// conn.close();
			try (conn) {
				PreparedStatement preparedStatement = conn.prepareStatement(
						"SELECT ep.ID AS IdEpreuve, ep.ANNEE AS Annee, ep.TYPE_EPREUVE AS Type, mat.ID AS IdMatch, jouv.NOM AS NomVainqueur, jouv.PRENOM AS PrenomVainqueur, jouf.NOM AS NomFinaliste, jouf.PRENOM AS PrenomFinaliste "
								+ "FROM epreuve ep " + "INNER JOIN match_tennis mat ON ep.ID=mat.ID_EPREUVE "
								+ "INNER JOIN joueur jouv ON mat.ID_VAINQUEUR=jouv.ID "
								+ "INNER JOIN joueur jouf ON mat.ID_FINALISTE=jouf.ID " + "WHERE ep.ANNEE= '" + annee
								+ "' AND ep.TYPE_EPREUVE='" + type + "';");
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					listEpreuve.add(new EpreuveData(
							new Epreuve(rs.getString("IdEpreuve"), rs.getString("Annee"), rs.getString("Type")),
							new Match(rs.getString("IdMatch")),
							new Joueur(rs.getString("NomVainqueur"), rs.getString("PrenomVainqueur")),
							new Joueur(rs.getString("NomFinaliste"), rs.getString("PrenomFinaliste"))));
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.setListEpreuveData(listEpreuve);
		return listEpreuve;
	}

	public ArrayList<EpreuveData> getListEpreuveData() {
		return listEpreuveData;
	}

	public void setListEpreuveData(ArrayList<EpreuveData> listEpreuveData) {
		this.listEpreuveData = listEpreuveData;
	}

	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

}
