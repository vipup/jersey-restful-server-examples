package hello.world;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FatherServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4824646813941634836L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Father father1 = new Father();
        Father father2 = new Father();
        father1.incrementCount();
        request.setAttribute("father2", father2); // Will be available in JSP as ${father2}
        request.getRequestDispatcher("/WEB-INF/father.jsp").forward(request, response);
    }
}