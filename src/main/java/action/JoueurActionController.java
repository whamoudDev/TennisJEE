package action;

import java.io.IOException;

import Interface.IEpreuveDao;
import Interface.IJoueurDao;
import Interface.IMatchDao;
import Interface.ITournoiDao;
import beans.BeanException;
import beans.Joueur;
import dao.DaoException;
import dao.EpreuveDaoImpl;
import dao.MatchDaoImpl;
import dao.TournoiDaoImpl;
import dao.UserDaoImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sessionUtils.SessionController;

public class JoueurActionController {

	private UserDaoImpl userDao;
	private IJoueurDao joueurDao;
	private Joueur selectedJoueur;

	public JoueurActionController(UserDaoImpl userDao, IJoueurDao joueurDao) {
		super();
		this.userDao = userDao;
		this.joueurDao = joueurDao;

	}

	public UserDaoImpl getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	public IJoueurDao getJoueurDao() {
		return joueurDao;
	}

	public void setJoueurDao(IJoueurDao joueurDao) {
		this.joueurDao = joueurDao;
	}

	public void actionMenuJoueurs(HttpServletRequest request, HttpServletResponse response)
			throws IOException, BeanException, DaoException {

		if (SessionController.sessionVerifConnexion(request)) {

			request.setAttribute("dataJoueur", joueurDao.lister());
			response.sendRedirect("ListJoueur");
		} else {
			response.sendRedirect("Login");
		}

	}

	public void actionMenuTournois(HttpServletRequest request, HttpServletResponse response) throws IOException {

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

	public void actionSelectionJoueur(ServletContext servletContext, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, BeanException, DaoException {

		if (SessionController.sessionVerifConnexion(request)) {

			for (Joueur joueurTemp : joueurDao.getAllJoueur()) {

				if (joueurTemp.getId().equals(request.getParameter("joueurId"))) {
					selectedJoueur = joueurTemp;
				}
			}

			if (selectedJoueur != null) {
				request.setAttribute("lastSelectedJoueur", selectedJoueur);
				request.setAttribute("dataJoueur", joueurDao.lister());
			}

			servletContext.getRequestDispatcher("/WEB-INF/listJoueur.jsp").forward(request, response);
		}
	}

	public void actionRechercherJoueur(ServletContext servletContext, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, BeanException, DaoException {

		if (SessionController.sessionVerifConnexion(request)) {

			if (!request.getParameter("txtRecherche").equals(""))
				request.setAttribute("dataJoueur", joueurDao.listerRecherche(request.getParameter("txtChampRecherche"),
						request.getParameter("txtRecherche")));
			else
				request.setAttribute("dataJoueur", joueurDao.lister());

			servletContext.getRequestDispatcher("/WEB-INF/listJoueur.jsp").forward(request, response);
		}

	}

	public void actionAddJoueur(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, BeanException {

		if (SessionController.sessionVerifConnexion(request)) {

			if (joueurDao.ajouter(new Joueur(request.getParameter("txtNom"), request.getParameter("txtPrenom"),
					request.getParameter("txtSexe"))))
				System.out.println("Joueur ajouté");
			else
				System.out.println("Ajout annulé");

			response.sendRedirect("ListJoueur");

		}

	}

	public void actionEditJoueur(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (SessionController.sessionVerifConnexion(request)) {

			if (selectedJoueur != null
					&& joueurDao.modifier(selectedJoueur.getId(), new Joueur(request.getParameter("txtNom"),
							request.getParameter("txtPrenom"), request.getParameter("txtSexe")))) {

				System.out.println("Joueur modifié");
				selectedJoueur = null;
			} else {
				System.out.println("Modification annulé");
			}
			response.sendRedirect("ListJoueur");

		}

	}

	public void actionDeleteJoueur(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (SessionController.sessionVerifConnexion(request)) {

			if (selectedJoueur != null && joueurDao.supprimer(selectedJoueur.getId())) {

				System.out.println("Joueur supprimé");
				selectedJoueur = null;
			} else {
				System.out.println("Suppression annulé");
			}
			response.sendRedirect("ListJoueur");

		}

	}

}
