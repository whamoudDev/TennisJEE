package action;

import java.io.IOException;

import Interface.IEpreuveDao;
import Interface.IJoueurDao;
import Interface.IMatchDao;
import Interface.ITournoiDao;
import beans.BeanException;
import dao.DaoException;
import dao.JoueurDaoImpl;
import dao.MatchDaoImpl;
import dao.TournoiDaoImpl;
import dao.UserDaoImpl;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sessionUtils.SessionController;

public class EpreuveActionController {

	private UserDaoImpl userDao;
	private IEpreuveDao epreuveDao;

	public EpreuveActionController(UserDaoImpl userDao, IEpreuveDao epreuveDao) {
		super();
		this.userDao = userDao;
		this.epreuveDao = epreuveDao;
	}

	public UserDaoImpl getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	public IEpreuveDao getEpreuveDao() {
		return epreuveDao;
	}

	public void setEpreuveDao(IEpreuveDao epreuveDao) {
		this.epreuveDao = epreuveDao;
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
			request.setAttribute("dataEpreuve", epreuveDao.listerEpreuveData());
			response.sendRedirect("ListEpreuve");
		} else {
			response.sendRedirect("Login");
		}
	}

	public void actionRechercherEpreuves(ServletContext servletContext, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, BeanException, DaoException {

		if (SessionController.sessionVerifConnexion(request)) {

			request.setAttribute("dataEpreuve", epreuveDao.listerEpreuveDataFiltered(
					request.getParameter("txtChampAnnee"), request.getParameter("txtChampType")));

			servletContext.getRequestDispatcher("/WEB-INF/listEpreuve.jsp").forward(request, response);
		}

	}

}
