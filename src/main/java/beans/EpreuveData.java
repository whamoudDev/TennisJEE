/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

/**
 *
 * @author WalidDev
 */
public class EpreuveData {
	private Epreuve epreuve;
	private Match match;
	private Joueur vainqueur;
	private Joueur finaliste;

	public EpreuveData(Epreuve epreuve, Match match, Joueur vainqueur, Joueur finaliste) {
		this.epreuve = epreuve;
		this.match = match;
		this.vainqueur = vainqueur;
		this.finaliste = finaliste;
	}

	public Epreuve getEpreuve() {
		return epreuve;
	}

	public void setEpreuve(Epreuve epreuve) {
		this.epreuve = epreuve;
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}

	public Joueur getVainqueur() {
		return vainqueur;
	}

	public void setVainqueur(Joueur vainqueur) {
		this.vainqueur = vainqueur;
	}

	public Joueur getFinaliste() {
		return finaliste;
	}

	public void setFinaliste(Joueur finaliste) {
		this.finaliste = finaliste;
	}

}
