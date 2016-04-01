package social.network;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Deconnexion extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		//Suppression des variables de sessions
		
		//Redirection sur la page principale.
		resp.sendRedirect("/index.jsp");
	}
}
