package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sessionUtils.SessionController;
import java.io.IOException;
import action.EpreuveActionController;
import beans.BeanException;
import dao.DaoException;
import dao.EpreuveDaoImpl;
import dao.UserDaoImpl;

/**
 * Servlet implementation class ListEpreuve
 */

@WebServlet("/ListEpreuve")
public class ListEpreuve extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao;
	private EpreuveDaoImpl epreuveDao;
	private EpreuveActionController epreuveActionController;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListEpreuve() {
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
			epreuveDao = new EpreuveDaoImpl(userDao.getDaoFactory());
			epreuveActionController = new EpreuveActionController(userDao, epreuveDao);
			try {
				request.setAttribute("dataEpreuve", epreuveDao.listerEpreuveData());
			} catch (BeanException e) {
				// TODO Auto-generated catch block
				request.setAttribute("erreur", e.getMessage());
				this.getServletContext().getRequestDispatcher("/WEB-INF/listEpreuve.jsp");
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/listEpreuve.jsp").forward(request, response);

		} else {

			System.out.println("servlet ListEpreuve : login Nok !");
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
				epreuveActionController.actionMenuJoueurs(request, response);
			} catch (BeanException | DaoException e) {
				// TODO Auto-generated catch block
				request.setAttribute("erreur", e.getMessage());
				this.getServletContext().getRequestDispatcher("/WEB-INF/listEpreuve.jsp");
			}
		}

		// menuTournois
		if (request.getParameter("menuTournois") != null) {
			epreuveActionController.actionMenuTournois(request, response);
		}

		// menuMatchs
		if (request.getParameter("menuMatchs") != null) {
			epreuveActionController.actionMenuMatchs(request, response);
		}
		// menuEpreuves
		if (request.getParameter("menuEpreuves") != null || request.getParameter("listerToutesEpreuves") != null) {
			try {
				epreuveActionController.actionMenuEpreuves(request, response);
			} catch (IOException | BeanException e) {
				// TODO Auto-generated catch block
				request.setAttribute("erreur", e.getMessage());
				this.getServletContext().getRequestDispatcher("/WEB-INF/listEpreuve.jsp");
			}
		}

		// Deconnexion
		if (request.getParameter("deconnexion") != null) {
			SessionController.sessionDeconnexion(request);
		}

		// Filtre
		if (request.getParameter("rechercherEpreuve") != null) {
			try {
				epreuveActionController.actionRechercherEpreuves(this.getServletContext(), request, response);
			} catch (ServletException | IOException | BeanException | DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
