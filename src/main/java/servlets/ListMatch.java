package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sessionUtils.SessionController;

import java.io.IOException;

import action.JoueurActionController;
import action.MatchActionController;
import action.TournoiActionController;
import beans.BeanException;
import dao.DaoException;
import dao.MatchDaoImpl;
import dao.TournoiDaoImpl;
import dao.UserDaoImpl;

/**
 * Servlet implementation class ListMatch
 */
@WebServlet("/ListMatch")
public class ListMatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao;
	private MatchActionController matchActionController;
	private MatchDaoImpl matchDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListMatch() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		if (SessionController.sessionVerifConnexion(request)) {

			userDao = (UserDaoImpl) request.getSession().getAttribute("userDao");
			matchDao = new MatchDaoImpl(userDao.getDaoFactory());
			matchActionController = new MatchActionController(userDao, matchDao);

			if (request.getParameter("version") != null && request.getParameter("version").equals("Finaliste")) {
				request.setAttribute("dataMatch", matchDao.listerMatchVersion("Finaliste"));
				request.setAttribute("version", "Finaliste");

			} else {
				request.setAttribute("dataMatch", matchDao.listerMatchVersion("Vainqueur"));
				request.setAttribute("version", "Vainqueur");

			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/listMatch.jsp").forward(request, response);

		} else {

			System.out.println("servlet ListMatch : login Nok !");
			response.sendRedirect("Login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// menuJoueurs
		if (request.getParameter("menuJoueurs") != null) {
			try {
				matchActionController.actionMenuJoueurs(request, response);
			} catch (BeanException | DaoException e) {
				// TODO Auto-generated catch block
				request.setAttribute("erreur", e.getMessage());
				this.getServletContext().getRequestDispatcher("/WEB-INF/listMatch.jsp");
			}
		}

		// menuTournois
		if (request.getParameter("menuTournois") != null) {
			matchActionController.actionMenuTournois(request, response);
		}

		// menuMatchs
		if (request.getParameter("menuMatchs") != null) {
			matchActionController.actionMenuMatchs(request, response);
		}
		// menuEpreuves
		if (request.getParameter("menuEpreuves") != null) {
			try {
				matchActionController.actionMenuEpreuves(request, response);
			} catch (IOException | BeanException e) {
				// TODO Auto-generated catch block
				request.setAttribute("erreur", e.getMessage());
				this.getServletContext().getRequestDispatcher("/WEB-INF/listMatch.jsp");
			}
		}

		// Deconnexion
		if (request.getParameter("deconnexion") != null) {
			SessionController.sessionDeconnexion(request);
		}

		// Lister
		if (request.getParameter("lister") != null) {
			matchActionController.actionComboListMatchVainqueurFinaliste(this, request, response);
		}

		// Rechercher
		if (request.getParameter("rechercherMatch") != null) {
			matchActionController.actionComboListMatchRecherche(this, request, response);
		}
	}

}
