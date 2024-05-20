/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

/**
 *
 * @author WalidDev
 */
public class Epreuve {

	private String id;
	private String annee;
	private String type;
	private String idTournoi;

	public Epreuve(String id, String annee, String type) {
		this.id = id;
		this.annee = annee;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIdTournoi() {
		return idTournoi;
	}

	public void setIdTournoi(String idTournoi) {
		this.idTournoi = idTournoi;
	}

}
