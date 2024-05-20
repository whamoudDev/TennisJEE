package Interface;

import java.util.ArrayList;
import beans.Tournoi;

public interface ITournoiDao {

	public ArrayList<Tournoi> getAllTournoi();

	public void setAllTournoi(ArrayList<Tournoi> allTournoi);

	public ArrayList<Tournoi> lister();

	public ArrayList<Tournoi> listerRecherche(String colonne, String valeur);

	public boolean ajouter(Tournoi tournoi);

	public boolean modifier(String id, Tournoi tournoi);

	public boolean supprimer(String id);

}