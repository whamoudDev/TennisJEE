package servlets;

import java.io.IOException;
import Interface.IJoueurDao;
import action.JoueurActionController;
import beans.BeanException;
import dao.DaoException;
import dao.JoueurDaoImpl;
import dao.UserDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sessionUtils.SessionController;

/**
 * Servlet implementation class ListJoueur
 */
@WebServlet("/ListJoueur")
public class ListJoueur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao;
	private IJoueurDao joueurDao;
	private JoueurActionController joueurActionController;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListJoueur() {
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
			joueurDao = new JoueurDaoImpl(userDao.getDaoFactory());
			joueurActionController = new JoueurActionController(userDao, joueurDao);
			try {
				request.setAttribute("dataJoueur", joueurDao.lister());
			} catch (BeanException | DaoException e) {
				// TODO Auto-generated catch block
				request.setAttribute("erreur", e.getMessage());
				this.getServletContext().getRequestDispatcher("/WEB-INF/listJoueur.jsp");
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/listJoueur.jsp").forward(request, response);

		} else {

			System.out.println("servlet ListJoueur : login Nok !");
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
				joueurActionController.actionMenuJoueurs(request, response);
			} catch (BeanException | DaoException e) {
				// TODO Auto-generated catch block
				request.setAttribute("erreur", e.getMessage());
				this.getServletContext().getRequestDispatcher("/WEB-INF/listJoueur.jsp");
			}
		}

		// menuTournois
		if (request.getParameter("menuTournois") != null) {
			joueurActionController.actionMenuTournois(request, response);
		}

		// menuMatchs
		if (request.getParameter("menuMatchs") != null) {
			joueurActionController.actionMenuMatchs(request, response);
		}
		// menuEpreuves
		if (request.getParameter("menuEpreuves") != null) {
			try {
				joueurActionController.actionMenuEpreuves(request, response);
			} catch (IOException | BeanException e) {
				// TODO Auto-generated catch block
				request.setAttribute("erreur", e.getMessage());
				this.getServletContext().getRequestDispatcher("/WEB-INF/listJoueur.jsp");
			}
		}

		// Deconnexion
		if (request.getParameter("deconnexion") != null) {
			SessionController.sessionDeconnexion(request);
		}

		// Selection d'un joueur
		if (request.getParameter("selectedJoueur") != null) {
			try {
				joueurActionController.actionSelectionJoueur(this.getServletContext(), request, response);
			} catch (BeanException | DaoException e) {
				// TODO Auto-generated catch block
				request.setAttribute("erreur", e.getMessage());
				this.getServletContext().getRequestDispatcher("/WEB-INF/listJoueur.jsp");
			}
		}

		// Rechercher joueur
		if (request.getParameter("rechercherJoueur") != null) {
			try {
				joueurActionController.actionRechercherJoueur(this.getServletContext(), request, response);
			} catch (BeanException | DaoException e) {
				// TODO Auto-generated catch block
				request.setAttribute("erreur", e.getMessage());
				this.getServletContext().getRequestDispatcher("/WEB-INF/listJoueur.jsp");
			}
		}

		// Ajout d'un joueur
		if (request.getParameter("addJoueur") != null) {
			try {
				joueurActionController.actionAddJoueur(request, response);
			} catch (ServletException | IOException | BeanException e) {
				// TODO Auto-generated catch block
				request.setAttribute("erreur", e.getMessage());
				this.getServletContext().getRequestDispatcher("/WEB-INF/listJoueur.jsp");
			}
		}

		// Editer un joueur
		if (request.getParameter("editJoueur") != null) {
			joueurActionController.actionEditJoueur(request, response);
		}

		// Supprimer un joueur
		if (request.getParameter("deleteJoueur") != null) {
			joueurActionController.actionDeleteJoueur(request, response);
		}

	}

}
