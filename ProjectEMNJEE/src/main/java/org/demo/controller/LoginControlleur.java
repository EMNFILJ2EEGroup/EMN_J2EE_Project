package main.java.org.demo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.org.demo.bean.jpa.OrganizersEntity;
import main.java.org.demo.persistence.PersistenceServiceProvider;
import main.java.org.demo.persistence.services.OrganizersPersistence;
import main.java.org.demo.service.MainService;
import main.java.org.demo.service.ServicesInterface;

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
		ServicesInterface serviceLayer = new MainService();
		//TODO
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		rd.forward(request, response);
		request.getSession().removeAttribute("toast");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LoginController [POST] - begin");
		String un = request.getParameter("username");
		String pwd = request.getParameter("passwd");
		String btn = request.getParameter("button");
		
		if(btn.equals("Subscribe")) 
		{
			System.out.println("LoginController [POST] - subscribe");
			request.getSession().removeAttribute("toast");
			if(checkUserAlreadyExist(un)){
				request.getSession().setAttribute("toast", "Nom d'utilisateur déjà existant");
				response.sendRedirect(request.getContextPath() + "/login");
			}
			else {
				// TODO : Create user in BD
				
				OrganizersEntity usr = new OrganizersEntity();
				usr.setEmail(un);
				usr.setPassword(pwd);
				OrganizersPersistence provider = PersistenceServiceProvider.getService(OrganizersPersistence.class);
				provider.save(usr);
				
				request.getSession().setAttribute("token", generateToken(un, pwd));
				response.sendRedirect(request.getContextPath() + "/main");
			}
		} 
		else 
		{
			if(checkLogin(un,pwd)) {
				System.out.println("LoginController [POST] - correct");
				request.getSession().removeAttribute("toast");
				request.getSession().setAttribute("token", generateToken(un, pwd));
				response.sendRedirect(request.getContextPath() + "/main");
			} else {
				System.out.println("LoginController [POST] - not correct");
				request.getSession().setAttribute("toast", "Nom d'utilisateur ou mot de passe invalides");
				response.sendRedirect(request.getContextPath() + "/login");
			}
		}
	}

	private String generateToken(String user, String mdp) {
		return "plop"; //TODO
	}

	private boolean checkUserAlreadyExist(String user) {
		return false; //TODO
	}

	private boolean checkLogin(String user, String mdp) { //TODO
		return (user.equals("admin@a") && mdp.equals("test")) ? true : false;
	}

}
