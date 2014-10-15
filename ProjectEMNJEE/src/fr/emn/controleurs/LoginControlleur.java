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
 * Servlet implementation class LoginControlleur
 */
@WebServlet("/login")
public class LoginControlleur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginControlleur() {
        super();
        // TODO Auto-generated constructor stub
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
		System.out.println("LoginController [GET] - getLoginPage");
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/jsp/login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginController [POST] - begin");
		String un = request.getParameter("username");
		String pwd = request.getParameter("passwd");
		
		if(un.equals("test") && pwd.equals("test")) {
			System.out.println("LoginController [POST] - correct");
			request.getSession().setAttribute("token", "plop");
			response.sendRedirect("/ProjectEMNJEE/main");
		} else {
			System.out.println("LoginController [POST] - not correct");
			response.sendRedirect("/ProjectEMNJEE/login");
		}
	}
	
}
