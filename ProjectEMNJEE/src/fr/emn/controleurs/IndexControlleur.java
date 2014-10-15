package fr.emn.controleurs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IndexControlleur
 */
@WebServlet("/main")
public class IndexControlleur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexControlleur() {
		super();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String token = (String) request.getSession().getAttribute("token");
		if(token == null) response.sendRedirect("/ProjectEMNJEE/login");
		else {
			if(!token.equals("plop")) {
				System.out.println("IndexController [GET] - not connected");
				response.sendRedirect("/ProjectEMNJEE/login");
			} else {
				System.out.println("IndexController [GET] - connected, ok");
				RequestDispatcher rd;
				rd = request.getRequestDispatcher("/jsp/index.jsp");
				rd.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
