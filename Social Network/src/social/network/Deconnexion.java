package social.network;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class Deconnexion extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//Suppression des variables de sessions
		HttpSession session = req.getSession();
		session.invalidate();
		//Redirection sur la page principale.
		resp.sendRedirect("/index.jsp");
	}
}
