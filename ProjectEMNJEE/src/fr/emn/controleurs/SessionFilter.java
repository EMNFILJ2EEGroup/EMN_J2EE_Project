package fr.emn.controleurs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class SessionFilter
 */
@WebFilter(urlPatterns={"/*"}, initParams={ @WebInitParam(name="auth-free-urls", 
value= "/login,"+
		"/css/login.css") }) 
public class SessionFilter implements Filter {

	private ArrayList<String> urlList;	

	/**
	 * Default constructor. 
	 */
	public SessionFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String url = req.getServletPath();
		boolean allowedRequest = false;

		if(urlList.contains(url)) {
			allowedRequest = true;
		}

		if (!allowedRequest) {
			HttpSession session = req.getSession(false);
			if(session == null) {
				System.out.println("--filter ->redirect. [from="+url+"]");
				res.sendRedirect("/ProjectEMNJEE/login");
			}
			else if (session.getAttribute("token") == null) {
				System.out.println("--filter ->redirect. [from="+url+"]");
				res.sendRedirect("/ProjectEMNJEE/login");
			}
			else {
				chain.doFilter(request, response);
			}
		}
		else {
			chain.doFilter(request, response);
		}

//TODO : cleaner...
		//TODO Si on appelle http://localhost:8080/ProjectEMNJEE/jsp/login.jsp
		// Le css crash...
		//Voir comment faire un appel propre des css / ou  cacher les jsp/css/etc...

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		String urls = fConfig.getInitParameter("auth-free-urls");
		StringTokenizer token = new StringTokenizer(urls, ",");

		urlList = new ArrayList<String>();

		while (token.hasMoreTokens()) {
			urlList.add(token.nextToken());
		}
	}

}
