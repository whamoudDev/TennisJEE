package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {

	String url;
	String username;
	String password;

	public DaoFactory(String url, String username, String password) {
		super();
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static DaoFactory getInstance() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

		} catch (ClassNotFoundException e) {
		}

		return new DaoFactory(
				"jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris",
				"root", "");
	}

	public Connection getConnexion() throws SQLException {

		try {
			Connection conn = DriverManager.getConnection(this.getUrl(), this.getUsername(), this.getPassword());
			System.out.println("success");
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

}
