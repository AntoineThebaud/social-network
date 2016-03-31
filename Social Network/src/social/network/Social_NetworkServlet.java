package social.network;

import java.io.IOException;
import javax.servlet.http.*;
import com.googlecode.objectify.ObjectifyService;

@SuppressWarnings("serial")
public class Social_NetworkServlet extends HttpServlet {
	static {
        ObjectifyService.register(Personne.class); // Fait connaître Personne à Objectify
    }
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.sendRedirect("/inscription.jsp");
	}
}
