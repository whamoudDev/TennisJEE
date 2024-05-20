package action;

import java.io.IOException;
import java.util.ArrayList;

import Interface.IEpreuveDao;
import Interface.IJoueurDao;
import Interface.IMatchDao;
import Interface.ITournoiDao;
import beans.BeanException;
import beans.Match;
import dao.DaoException;
import dao.EpreuveDaoImpl;
import dao.JoueurDaoImpl;
import dao.MatchDaoImpl;
import dao.TournoiDaoImpl;
import dao.UserDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlets.ListMatch;
import sessionUtils.SessionController;

public class MatchActionController {

	private UserDaoImpl userDao;
	private IMatchDao matchDao;
	private Match selectedMatch;

	public MatchActionController(UserDaoImpl userDao, IMatchDao matchDao) {
		super();
		this.userDao = userDao;
		this.matchDao = matchDao;
	}

	public MatchActionController(IMatchDao matchDao) {
		super();
		this.matchDao = matchDao;
	}

	public UserDaoImpl getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	public IMatchDao getMatchDao() {
		return matchDao;
	}

	public void setMatchDao(IMatchDao matchDao) {
		this.matchDao = matchDao;
	}

	public Match getSelectedMatch() {
		return selectedMatch;
	}

	public void setSelectedMatch(Match selectedMatch) {
		this.selectedMatch = selectedMatch;
	}

	public void actionMenuJoueurs(HttpServletRequest request, HttpServletResponse response)
			throws IOException, BeanException, DaoException {
		// TODO Auto-generated method stub
		if (SessionController.sessionVerifConnexion(request)) {
			IJoueurDao joueurDao = new JoueurDaoImpl(userDao.getDaoFactory());
			request.setAttribute("dataJoueur", joueurDao.lister());
			response.sendRedirect("ListJoueur");
		} else {
			response.sendRedirect("Login");
		}

	}

	public void actionMenuTournois(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		if (SessionController.sessionVerifConnexion(request)) {
			ITournoiDao tournoiDao = new TournoiDaoImpl(userDao.getDaoFactory());
			request.setAttribute("dataTournoi", tournoiDao.lister());
			response.sendRedirect("ListTournoi");
		} else {
			response.sendRedirect("Login");
		}

	}

	public void actionMenuMatchs(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub

		if (SessionController.sessionVerifConnexion(request)) {
			request.setAttribute("dataMatch", matchDao.listerMatchVersion("Vainqueur"));
			response.sendRedirect("ListMatch");
		} else {
			response.sendRedirect("Login");
		}

	}

	public void actionMenuEpreuves(HttpServletRequest request, HttpServletResponse response)
			throws IOException, BeanException {
		// TODO Auto-generated method stub
		if (SessionController.sessionVerifConnexion(request)) {

			IEpreuveDao epreuveDao = new EpreuveDaoImpl(userDao.getDaoFactory());
			request.setAttribute("dataEpreuve", epreuveDao.listerEpreuveData());
			response.sendRedirect("ListEpreuve");
		} else {
			response.sendRedirect("Login");
		}
	}

	public void actionComboListMatchVainqueurFinaliste(ListMatch listMatch, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		String version = request.getParameter("txtChampVersion");

		if (SessionController.sessionVerifConnexion(request)) {
			if (version.equals("Vainqueur")) {

				request.setAttribute("dataMatch", matchDao.listerMatchVersion("Vainqueur"));
				request.setAttribute("version", "Vainqueur");
			} else {

				request.setAttribute("dataMatch", matchDao.listerMatchVersion("Finaliste"));
				request.setAttribute("version", "Finaliste");
			}

			listMatch.getServletContext().getRequestDispatcher("/WEB-INF/listMatch.jsp").forward(request, response);

		} else {
			response.sendRedirect("Login");
		}

	}

	public void actionComboListMatchRecherche(ListMatch listMatch, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String version = request.getParameter("txtChampVersion");
		String colonne = request.getParameter("txtChampRecherche");
		String valeur = request.getParameter("txtRecherche");

		if (SessionController.sessionVerifConnexion(request)) {
			if (version.equals("Vainqueur")) {

				request.setAttribute("dataMatch", matchDao.listerRechercheMatchVersion("Vainqueur", colonne, valeur));
				request.setAttribute("version", "Vainqueur");
			} else {

				request.setAttribute("dataMatch", matchDao.listerRechercheMatchVersion("Finaliste", colonne, valeur));
				request.setAttribute("version", "Finaliste");
			}

			listMatch.getServletContext().getRequestDispatcher("/WEB-INF/listMatch.jsp").forward(request, response);

		} else {
			response.sendRedirect("Login");
		}
	}

}
