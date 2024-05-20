package sessionUtils;

import jakarta.servlet.http.HttpServletRequest;

public class SessionController {

	public static boolean sessionVerifConnexion(HttpServletRequest request) {
		return request.getSession().getAttribute("userOk") != null;
	}

	public static void sessionDeconnexion(HttpServletRequest request) {
		request.getSession().invalidate();
	}
}
