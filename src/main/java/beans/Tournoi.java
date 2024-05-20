package beans;

public class Tournoi {

	private String id;
	private String nom;
	private String code;

	public Tournoi(String id, String nom, String code) {
		super();
		this.id = id;
		this.nom = nom;
		this.code = code;

	}

	public Tournoi(String nom, String code) {
		super();
		this.id = "";
		this.nom = nom;
		this.code = code;

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Tournoi [id=" + id + ", nom=" + nom + ", code=" + code + "]";
	}

}
