package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import beans.User;

public class UserDaoImpl {

	DaoFactory daoFactory;

	public UserDaoImpl(DaoFactory daoFactory) {
		super();
		this.daoFactory = daoFactory;
	}

	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public User isValidLogin(String login, String password) {

		Connection connexion = null;
		PreparedStatement statement = null;

		try {
			connexion = daoFactory.getConnexion();
			statement = connexion.prepareStatement("SELECT * FROM connexion WHERE login = ? AND password = ? ; ");
			statement.setString(1, login);
			statement.setString(2, password);

			ResultSet rs = statement.executeQuery();

			if (rs.next()) {

				return new User(Integer.parseInt(rs.getString("id")), rs.getString("login"), rs.getString("password"),
						rs.getString("nom"), rs.getString("prenom"), Integer.parseInt(rs.getString("profil")));
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
