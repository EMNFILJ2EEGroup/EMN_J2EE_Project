package main.java.org.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

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

import org.apache.log4j.Logger;

/**
 * Servlet Filter implementation class SessionFilter 
 */
@WebFilter(urlPatterns={"/*"}, initParams={ 
		@WebInitParam(name="auth-free-urls", 
				value= "/login,/Error404.html,/main,/main/info"),
				@WebInitParam(name="auth-free-patterns", 
				value= ".*(\\.css)$,"+
						".*(\\.js)$,"+ 
						".*(\\.woff)$," + 
						".*(\\.ttf)$")}) 
public class SessionFilter implements Filter {
	
	private static Logger logger = Logger.getLogger(SessionFilter.class);

	private ArrayList<String> urlList;	
	private ArrayList<String> patternList;

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
	 * Appellé que sur les URLS qui sont protégées.
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String url = req.getServletPath();
		logger.info("--filter ->"+url);
		boolean allowedRequest;
		boolean redirect = false;

		allowedRequest = this.matchUrl(url);
		if(!allowedRequest)
			allowedRequest = this.matchPattern(url);



		if (!allowedRequest) { //You need a valid session
			HttpSession session = req.getSession(false);
			if(session == null)
				redirect = true;
			else
				if(session.getAttribute("uid") == null ) 
					redirect = true;
		}
		
		if(redirect)
			res.sendRedirect(req.getContextPath() + "/login");
		else
			chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		String urls = fConfig.getInitParameter("auth-free-urls");
		String patterns = fConfig.getInitParameter("auth-free-patterns");
		StringTokenizer tokenURL = new StringTokenizer(urls, ",");
		StringTokenizer tokenPAT = new StringTokenizer(patterns, ",");

		urlList = new ArrayList<String>();
		patternList = new ArrayList<String>();

		while (tokenURL.hasMoreTokens()) {
			urlList.add(tokenURL.nextToken());
		}
		while (tokenPAT.hasMoreTokens()) {
			patternList.add(tokenPAT.nextToken());
		}
	}

	public boolean matchUrl(String requestedUrl) {
		boolean res = (urlList.contains(requestedUrl))? true : false;
		return res;
	}

	public boolean matchPattern(String requestedUrl) {
		boolean found = false;
		for(int i=0; i < patternList.size(); ++i) {
			found = found || Pattern.compile(patternList.get(i)).matcher(requestedUrl).matches();

			if(found == true) break;
		}
		return found;
	}

}
