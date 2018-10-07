package Lab_2;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;


public class ControllerServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actStr = request.getParameter("formAction");
		if (actStr == null || !actStr.equals("check")){
			Logger.getLogger(getClass().getName()).warning("going to index.jsp");
			request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		} else {
			Logger.getLogger(getClass().getName()).warning("going to checking servlet");
			request.getServletContext().getRequestDispatcher("/checking").forward(request, response);
		}
	}
}
