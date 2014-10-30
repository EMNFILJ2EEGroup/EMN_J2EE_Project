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
		ServicesInterface serviceLayer = new MainService();
		RequestDispatcher rd;

		//Redirect people already logged
		if(serviceLayer.checkValidSession((String)request.getSession().getAttribute("uid"))) {
			response.sendRedirect(request.getContextPath() + "/main");
			return;
		}

		rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		rd.forward(request, response);
		request.getSession().removeAttribute("toastDanger");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServicesInterface serviceLayer = new MainService();
		
		//Redirect people already logged
		if(serviceLayer.checkValidSession((String)request.getAttribute("uid"))) {
			response.sendRedirect(request.getContextPath() + "/main");
			return;
		}
		
		String un = request.getParameter("username");
		String pwd = request.getParameter("passwd");
		String btn = request.getParameter("button");
		Integer uid = null;

		if(btn.equals("Subscribe")) 
		{
			request.getSession().removeAttribute("toastDanger");
			uid = serviceLayer.validateSubscribe(un, pwd);
			if(uid != null) {
				request.getSession().setAttribute("uid", uid);
				request.getSession().setAttribute("uname", un);
				response.sendRedirect(request.getContextPath() + "/main");
			} else {
				request.getSession().setAttribute("toastDanger", "Nom d'utilisateur déjà existant");
				response.sendRedirect(request.getContextPath() + "/login");
			}
		} 
		else 
		{
			uid = serviceLayer.checkLogin(un, pwd);
			if(uid != null) {
				request.getSession().removeAttribute("toast");
				request.getSession().setAttribute("uid", uid);
				request.getSession().setAttribute("uname", un);
				response.sendRedirect(request.getContextPath() + "/main");
			} else {
				request.getSession().setAttribute("toastDanger", "Nom d'utilisateur ou mot de passe invalides");
				response.sendRedirect(request.getContextPath() + "/login");
			}
		}
	}

}
