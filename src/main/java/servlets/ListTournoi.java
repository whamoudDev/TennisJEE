package servlets;

import java.io.IOException;
import Interface.ITournoiDao;
import action.TournoiActionController;
import beans.BeanException;
import dao.DaoException;
import dao.TournoiDaoImpl;
import dao.UserDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sessionUtils.SessionController;

/**
 * Servlet implementation class ListTournoi
 */
@WebServlet("/ListTournoi")
public class ListTournoi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TournoiActionController tournoiActionController;
	private UserDaoImpl userDao;
	private ITournoiDao tournoiDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListTournoi() {
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
			tournoiDao = new TournoiDaoImpl(userDao.getDaoFactory());
			tournoiActionController = new TournoiActionController(userDao, tournoiDao);
			request.setAttribute("dataTournoi", tournoiDao.lister());
			this.getServletContext().getRequestDispatcher("/WEB-INF/listTournoi.jsp").forward(request, response);

		} else {

			System.out.println("servlet ListTournoi : login Nok !");
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
		if (request.getParameter("menuTournois") != null) {
			try {
				tournoiActionController.actionMenuJoueurs(request, response);
			} catch (BeanException | DaoException e) {
				// TODO Auto-generated catch block
				request.setAttribute("erreur", e.getMessage());
				this.getServletContext().getRequestDispatcher("/WEB-INF/listTournoi.jsp");
			}
		}

		// menuTournois
		if (request.getParameter("menuTournois") != null) {
			tournoiActionController.actionMenuTournois(request, response);
		}

		// menuMatchs
		if (request.getParameter("menuMatchs") != null) {
			tournoiActionController.actionMenuMatchs(request, response);
		}
		// menuEpreuves
		if (request.getParameter("menuEpreuves") != null) {
			try {
				tournoiActionController.actionMenuEpreuves(request, response);
			} catch (IOException | BeanException e) {
				// TODO Auto-generated catch block
				request.setAttribute("erreur", e.getMessage());
				this.getServletContext().getRequestDispatcher("/WEB-INF/listTournoi.jsp");
			}
		}

		// Deconnexion
		if (request.getParameter("deconnexion") != null) {
			SessionController.sessionDeconnexion(request);
		}

		// Selection d'un tournoi
		if (request.getParameter("selectedTournoi") != null) {
			tournoiActionController.actionSelectionTournoi(this.getServletContext(), request, response);
		}

		// Rechercher tournoi
		if (request.getParameter("rechercherTournoi") != null) {
			tournoiActionController.actionRechercherTournoi(this.getServletContext(), request, response);
		}

		// Ajout d'un tournoi
		if (request.getParameter("addTournoi") != null) {
			tournoiActionController.actionAddTournoi(request, response);
		}

		// Editer un tournoi
		if (request.getParameter("editTournoi") != null) {
			tournoiActionController.actionEditTournoi(request, response);
		}

		// Supprimer un tournoi
		if (request.getParameter("deleteTournoi") != null) {
			tournoiActionController.actionDeleteTournoi(request, response);
		}

	}

}
