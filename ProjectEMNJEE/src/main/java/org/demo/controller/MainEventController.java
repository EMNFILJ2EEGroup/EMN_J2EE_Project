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
 * Servlet implementation class MainEventController
 */
@WebServlet("/main/info")
public class MainEventController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainEventController() {
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
		String rawEventID = request.getParameter("event");
		List<EventsEntity> publishedEventsList = serviceLayer.getPublicEventList();
		RequestDispatcher rd;
		int eventID;

		request.setAttribute("eventsList", publishedEventsList);
		if (serviceLayer.checkEventIdValidity(rawEventID)) {
			eventID = Integer.parseInt(rawEventID);
			EventsEntity event = serviceLayer.getEvent(eventID);
			request.setAttribute("focusedEvent", event);
		}
		else {
			response.sendRedirect(request.getContextPath() + "/Error404.html");
			request.getSession().removeAttribute("toast");
			return;
		}
		rd = request.getRequestDispatcher("/WEB-INF/jsp/mainEvent.jsp");
		rd.forward(request, response);
		request.getSession().removeAttribute("toast");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServicesInterface serviceLayer = new MainService();
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String fname = request.getParameter("fname");
		String company = request.getParameter("company");
		String rawEventID = request.getParameter("event");
		int eventID;
		
		if (serviceLayer.checkEventIdValidity(rawEventID)) {
			eventID = Integer.parseInt(rawEventID);
			if(serviceLayer.checkEventSubscribe(eventID, email, name, fname, company)) {
				request.getSession().setAttribute("toast", "Vous avez correctement été ajouté à la liste des invités de l'évenement.");
			}
			else {
				request.getSession().setAttribute("toast", "Les informations saisis ne sont pas correctes.");
			}
			response.sendRedirect(request.getContextPath() + "/main/info?event=" + eventID);
		} else {
			response.sendRedirect(request.getContextPath() + "/Error404.html");
		}
	}

}
