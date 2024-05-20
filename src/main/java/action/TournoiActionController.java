package action;

import java.io.IOException;

import Interface.IEpreuveDao;
import Interface.IJoueurDao;
import Interface.IMatchDao;
import Interface.ITournoiDao;
import beans.BeanException;
import beans.Tournoi;
import dao.DaoException;
import dao.EpreuveDaoImpl;
import dao.JoueurDaoImpl;
import dao.MatchDaoImpl;
import dao.UserDaoImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sessionUtils.SessionController;

public class TournoiActionController {

	private UserDaoImpl userDao;
	private ITournoiDao tournoiDao;
	private Tournoi selectedTournoi;

	public TournoiActionController(UserDaoImpl userDao, ITournoiDao tournoiDao) {
		super();
		this.userDao = userDao;
		this.tournoiDao = tournoiDao;

	}

	public UserDaoImpl getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	public ITournoiDao getTournoiDao() {
		return tournoiDao;
	}

	public void setTournoiDao(ITournoiDao tournoiDao) {
		this.tournoiDao = tournoiDao;
	}

	public void actionMenuJoueurs(HttpServletRequest request, HttpServletResponse response)
			throws IOException, BeanException, DaoException {

		if (SessionController.sessionVerifConnexion(request)) {
			IJoueurDao joueurDao = new JoueurDaoImpl(userDao.getDaoFactory());
			request.setAttribute("dataJoueur", joueurDao.lister());
			response.sendRedirect("ListJoueur");
		} else {
			response.sendRedirect("Login");
		}

	}

	public void actionMenuTournois(HttpServletRequest request, HttpServletResponse response) throws IOException {

		if (SessionController.sessionVerifConnexion(request)) {
			request.setAttribute("dataTournoi", tournoiDao.lister());
			response.sendRedirect("ListTournoi");
		} else {
			response.sendRedirect("Login");
		}

	}

	public void actionMenuMatchs(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		if (SessionController.sessionVerifConnexion(request)) {

			IMatchDao matchDao = new MatchDaoImpl(userDao.getDaoFactory());
			request.setAttribute("dataTournoi", matchDao.listerMatchVersion("Vainqueur"));
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

	public void actionSelectionTournoi(ServletContext servletContext, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (SessionController.sessionVerifConnexion(request)) {

			for (Tournoi tournoiTemp : tournoiDao.getAllTournoi()) {

				if (tournoiTemp.getId().equals(request.getParameter("tournoiId"))) {
					selectedTournoi = tournoiTemp;
				}
			}

			if (selectedTournoi != null) {
				request.setAttribute("lastSelectedTournoi", selectedTournoi);
				request.setAttribute("dataTournoi", tournoiDao.lister());
			}

			servletContext.getRequestDispatcher("/WEB-INF/listTournoi.jsp").forward(request, response);
		}
	}

	public void actionRechercherTournoi(ServletContext servletContext, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (SessionController.sessionVerifConnexion(request)) {

			if (!request.getParameter("txtRecherche").equals(""))
				request.setAttribute("dataTournoi", tournoiDao.listerRecherche(
						request.getParameter("txtChampRecherche"), request.getParameter("txtRecherche")));
			else
				request.setAttribute("dataTournoi", tournoiDao.lister());

			servletContext.getRequestDispatcher("/WEB-INF/listTournoi.jsp").forward(request, response);
		}

	}

	public void actionAddTournoi(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (SessionController.sessionVerifConnexion(request)) {

			if (tournoiDao.ajouter(new Tournoi(request.getParameter("txtNom"), request.getParameter("txtCode"))))
				System.out.println("Tournoi ajouté");
			else
				System.out.println("Ajout annulé");

			response.sendRedirect("ListTournoi");

		}

	}

	public void actionEditTournoi(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (SessionController.sessionVerifConnexion(request)) {

			if (selectedTournoi != null && tournoiDao.modifier(selectedTournoi.getId(),
					new Tournoi(request.getParameter("txtNom"), request.getParameter("txtCode")))) {

				System.out.println("Tournoi modifié");
				selectedTournoi = null;
			} else {
				System.out.println("Modification annulé");
			}
			response.sendRedirect("ListTournoi");

		}

	}

	public void actionDeleteTournoi(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (SessionController.sessionVerifConnexion(request)) {
			if (selectedTournoi != null && tournoiDao.supprimer(selectedTournoi.getId())) {

				System.out.println("Tournoi supprimé");
				selectedTournoi = null;
			} else {
				System.out.println("Suppression annulé");
			}
			response.sendRedirect("ListTournoi");

		}

	}

}
