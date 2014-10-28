package main.java.org.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import main.java.org.demo.bean.jpa.EventsEntity;
import main.java.org.demo.service.MainService;
import main.java.org.demo.service.ServicesInterface;

/**
 * Servlet implementation class IndexControlleur
 */
@WebServlet("/main")
public class MainControlleur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainControlleur() {
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
		List<EventsEntity> publishedEventsList = serviceLayer.getPublicEventList();
		RequestDispatcher rd;
		
		System.out.println("MainController [GET]");
		
		request.setAttribute("eventsList", publishedEventsList);
		rd = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//NOTHING TO DO
	}
}
