package main.java.org.demo.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class PanelNewControlleur
 */
@WebServlet("/panel/new")
public class PanelNewControlleur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PanelNewControlleur() {
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
		List<EventsEntity> myeventsList = serviceLayer.getUserEvents((Integer)request.getSession().getAttribute("uid"));
		RequestDispatcher rd;
		
		request.setAttribute("myEventsList", myeventsList);
		rd = request.getRequestDispatcher("/WEB-INF/jsp/panelNew.jsp");
		rd.forward(request, response);
		request.getSession().removeAttribute("toast");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServicesInterface serviceLayer = new MainService();
		request.getSession().removeAttribute("toast");
		
		String nom = request.getParameter("nomEvent");
		String addr = request.getParameter("adresseEvent");
		String begD = request.getParameter("dateDebut");
		String begH = request.getParameter("heureDebut");
		String endD = request.getParameter("dateFin");
		String endH = request.getParameter("heureFin");
		String btn = request.getParameter("button");
		int published = 0;
		Integer uid = (Integer)request.getSession().getAttribute("uid");
		
		if(btn.equalsIgnoreCase("Créer et publier")) published = 1;
		
		boolean result = serviceLayer.validateNewEvent(uid, nom, addr, begD, endD, begH, endH, published);
		if(result) {
			request.getSession().setAttribute("toast", "Votre évènement a bien été crée !");
			response.sendRedirect(request.getContextPath() + "/panel");
		} else {
			request.getSession().setAttribute("toast", "Votre évènement n'a pas été crée, certains champs sont incorrectes...");
			response.sendRedirect(request.getContextPath() + "/panel/new");
		}
		
	}

}
