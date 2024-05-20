package beans;

public class MatchVersion {

	private String idMatch;
	private String idJoueur;
	private String nom;
	private String prenom;
	private String sexe;

	public MatchVersion(String idMatch, String idJoueur, String nom, String prenom, String sexe) {
		this.idMatch = idMatch;
		this.idJoueur = idJoueur;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
	}

	public String getIdMatch() {
		return idMatch;
	}

	public void setIdMatch(String idMatch) {
		this.idMatch = idMatch;
	}

	public String getIdJoueur() {
		return idJoueur;
	}

	public void setIdJoueur(String idJoueur) {
		this.idJoueur = idJoueur;
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

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	@Override
	public String toString() {
		return "MatchVersion{" + "idMatch=" + idMatch + ", idJoueur=" + idJoueur + ", nom=" + nom + ", prenom=" + prenom
				+ ", sexe=" + sexe + '}';
	}

}
