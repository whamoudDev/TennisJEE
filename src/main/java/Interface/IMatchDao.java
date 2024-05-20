package Interface;

import java.util.ArrayList;
import beans.Match;
import beans.MatchVersion;

public interface IMatchDao {

	public ArrayList<MatchVersion> listerMatchVersion(String version);

	public ArrayList<MatchVersion> listerRechercheMatchVersion(String version, String colonne, String valeur);

}
