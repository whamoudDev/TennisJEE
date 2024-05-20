/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

/**
 *
 * @author WalidDev
 */
public class Joueur {

    private String id;
    private String nom;
    private String prenom;
    private String sexe;

    public Joueur(String id, String nom, String prenom, String sexe) throws BeanException {
        super();
        this.id = id;
        setNom(nom);
        setPrenom(prenom);
        this.sexe = sexe;
    }

    public Joueur(String nom, String prenom) throws BeanException {
    	setNom(nom);
    	setPrenom(prenom);
    }

    public Joueur(String nom, String prenom, String sexe) {
        super();
        this.id = "";
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
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

    public void setNom(String nom) throws BeanException {
    	if(nom.length()>20)
    		throw new BeanException("Le nom est trop grand (Max 20 caractères) !");
    	else
    		this.nom = nom;
        
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) throws BeanException{
    	if(prenom.length()>30)
    		throw new BeanException("Le prenom est trop grand (Max 20 caractères) !");
    	else
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
        return "Joueur [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + "]";
    }

}
