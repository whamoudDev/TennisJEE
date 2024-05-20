package beans;

public class User {

	private int id;
	private String login;
	private String password;
	private String nom;
	private String prenom;
	private int profil;

	public User(int id, String login, String password, int profil) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.profil = profil;
	}

	public User(int id, String login, String password, String nom, String prenom, int profil) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.nom = nom;
		this.prenom = prenom;
		this.profil = profil;
	}

	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getProfil() {
		return profil;
	}

	public void setProfil(int profil) {
		this.profil = profil;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + ", profil=" + profil + "]";
	}

}
