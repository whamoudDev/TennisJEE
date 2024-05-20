/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

/**
 *
 * @author WalidDev
 */
public class Match {
	private String idMatch;
	private String idEpreuve;
	private String idVainqueur;
	private String idFinaliste;

	public Match(String idMatch) {
		this.idMatch = idMatch;
	}

	public Match(String idMatch, String idEpreuve) {
		this.idMatch = idMatch;
		this.idEpreuve = idEpreuve;
	}

	public Match(String idMatch, String idEpreuve, String idJoueur, String isVainqueurOrFinaliste) {
		this.idMatch = idMatch;
		this.idEpreuve = idEpreuve;
		if (isVainqueurOrFinaliste.equals("Vainqueur"))
			this.idVainqueur = idJoueur;
		else
			this.idFinaliste = idJoueur;
	}

	public String getIdMatch() {
		return idMatch;
	}

	public void setIdMatch(String idMatch) {
		this.idMatch = idMatch;
	}

	public String getIdEpreuve() {
		return idEpreuve;
	}

	public void setIdEpreuve(String idEpreuve) {
		this.idEpreuve = idEpreuve;
	}

	public String getIdVainqueur() {
		return idVainqueur;
	}

	public void setIdVainqueur(String idVainqueur) {
		this.idVainqueur = idVainqueur;
	}

	public String getIdFinaliste() {
		return idFinaliste;
	}

	public void setIdFinaliste(String idFinaliste) {
		this.idFinaliste = idFinaliste;
	}

}
