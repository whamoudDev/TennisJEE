package servlets;

import java.io.IOException;

import beans.User;
import dao.DaoFactory;
import dao.UserDaoImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sessionUtils.HashClass;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDaoImpl userDao;
	private User user;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {

		user = null;
		userDao = new UserDaoImpl(DaoFactory.getInstance());

	}

	public boolean verifLogin(HttpServletRequest request, HttpServletResponse response) {
		if (request.getParameter("txtLogin") != null && request.getParameter("txtPassword") != null) {
			user = userDao.isValidLogin(request.getParameter("txtLogin"),
					HashClass.sha1(request.getParameter("txtPassword")));

			return user != null;
		} else {
			return false;
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		if (verifLogin(request, response)) {

			// request.getSession().setAttribute("loginOk", true);
			request.getSession().setAttribute("userDao", userDao);
			request.getSession().setAttribute("userOk", user);
			request.getSession().setAttribute("nomUser", user.getNom());
			request.getSession().setAttribute("prenomUser", user.getPrenom());
			request.getSession().setAttribute("profilUser", user.getProfil());
			response.sendRedirect("ListJoueur");
		} else {
			request.getSession().setAttribute("userDao", null);
			request.getSession().setAttribute("userOk", null);
			doGet(request, response);
		}
	}

}
