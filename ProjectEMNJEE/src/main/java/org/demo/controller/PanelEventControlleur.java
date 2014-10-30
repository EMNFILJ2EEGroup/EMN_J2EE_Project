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
import main.java.org.demo.bean.jpa.ParticipationsEntity;
import main.java.org.demo.service.MainService;
import main.java.org.demo.service.ServicesInterface;

/**
 * Servlet implementation class PanelEventControlleur
 */
@WebServlet("/panel/info")
public class PanelEventControlleur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PanelEventControlleur() {
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
		int eventID;
		int uid = (Integer)request.getSession().getAttribute("uid");
		List<EventsEntity> myeventsList = serviceLayer.getUserEvents(uid);
		RequestDispatcher rd;
		
		
		if (serviceLayer.checkEventIdValidity(rawEventID)) { // Le paramètre est valide.
			request.setAttribute("myEventsList", myeventsList);
			eventID = Integer.parseInt(rawEventID);
			EventsEntity event = serviceLayer.getEvent(eventID);
			
			if(event != null) { // L'event existe.
				if(serviceLayer.isOwner(eventID, uid)) {
					request.setAttribute("focusedEvent", event);
					List<ParticipationsEntity> participants = serviceLayer.getParticipationList(eventID);
					request.setAttribute("eventParticipants", participants);
					request.setAttribute("focusedEvent", event);
				} else {
					request.getSession().setAttribute("toastDanger", "Tentative d'accès à un évènement dont vous n'êtes pas le propriétaire...");
					response.sendRedirect(request.getContextPath() + "/panel"); 
					return;
				}
			} else  {
				forwardTo404(request, response); return;
			}	
		} else {
			forwardTo404(request, response); return;
		}
		rd = request.getRequestDispatcher("/WEB-INF/jsp/panelEvent.jsp");
		rd.forward(request, response);
		request.getSession().removeAttribute("toastSuccess");
		request.getSession().removeAttribute("toastWarning");
		request.getSession().removeAttribute("toastDanger");;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServicesInterface serviceLayer = new MainService();
		
		String nom = request.getParameter("nomEvent");
		String addr = request.getParameter("adresseEvent");
		String begD = request.getParameter("dateDebut");
		String begH = request.getParameter("heureDebut");
		String endD = request.getParameter("dateFin");
		String endH = request.getParameter("heureFin");
		String eventId = request.getParameter("relatedEvent");
		String btn = request.getParameter("button");
		int published = 0;
		Integer uid = (Integer)request.getSession().getAttribute("uid");

		if(btn.equalsIgnoreCase("Enregistrer et publier")) published = 1;
		
		if(serviceLayer.checkEventIdValidity(eventId)) {
			if(serviceLayer.isOwner(Integer.parseInt(eventId), uid)) {
				boolean result = serviceLayer.validateUpdateEvent(Integer.parseInt(eventId),  nom, addr, begD, endD, begH, endH, published);
				if(result) {
					request.getSession().setAttribute("toastSuccess", "Votre évènement a bien été mis à jour !");
					response.sendRedirect(request.getContextPath() + "/panel/info?event="+ eventId);
				} else {
					request.getSession().setAttribute("toastWarning", "Votre évènement n'a pas été mis à jour, vérifiez votre saisie...");
					response.sendRedirect(request.getContextPath() + "/panel/info?event=" + eventId);
				}
			} else {
				request.getSession().setAttribute("toastDanger", "Tentative d'accès à un évènement dont vous n'êtes pas le propriétaire...");
				response.sendRedirect(request.getContextPath() + "/panel");
			}
		} else {
			forwardTo404(request, response); return;
		}
		
		
		
	}

	private void forwardTo404(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/Error404.html");
		request.getSession().removeAttribute("toastSuccess");
		request.getSession().removeAttribute("toastWarning");
		request.getSession().removeAttribute("toastDanger");
	}
	
}
