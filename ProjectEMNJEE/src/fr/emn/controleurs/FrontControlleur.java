package fr.emn.controleurs;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class FrontControlleur
 */
@WebServlet("/")
public class FrontControlleur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontControlleur() {
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
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//int matricule = 0;
		//String sMatricule = request.getParameter("matricule");
		//if(sMatricule != null) {
		//	try {
		//		matricule = Integer.parseInt(sMatricule);
		//	}catch (NumberFormatException e) {
		//		matricule = 0;
		//	}
		//}
		System.out.println(request.getRequestURI());
		RequestDispatcher rd;
		//if(matricule >= 100 && matricule <= 200) {
			//Employee trouve
			//EmployeBean emp = new EmployeBean(matricule, 0, "Toto "+ matricule, "Georges "+ matricule);
			//request.setAttribute("employee", emp);
			//rd = request.getRequestDispatcher("/jsp/EmployeView.jsp");
			//rd.forward(request, response);
		//} else {
			rd = request.getRequestDispatcher("/jsp/logPage.jsp");
			rd.forward(request, response);
		//}
		
		
	}
}
