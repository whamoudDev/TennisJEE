package Interface;

import java.util.ArrayList;

import beans.BeanException;
import beans.Joueur;
import dao.DaoException;

public interface IJoueurDao {

	public ArrayList<Joueur> getAllJoueur();

	public void setAllJoueur(ArrayList<Joueur> allJoueur);

	public ArrayList<Joueur> lister() throws BeanException, DaoException;

	public ArrayList<Joueur> listerRecherche(String colonne, String valeur) throws BeanException;

	public boolean ajouter(Joueur joueur) throws BeanException;

	public boolean modifier(String id, Joueur joueur);

	public boolean supprimer(String id);

}