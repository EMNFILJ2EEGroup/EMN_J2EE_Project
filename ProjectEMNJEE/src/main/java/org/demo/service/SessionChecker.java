package main.java.org.demo.service;

import javax.servlet.http.HttpSession;

public class SessionChecker {

	
	public SessionChecker() {
		
	}
	
	
	public static boolean check(HttpSession session) {
		String token = (String) session.getAttribute("token");
		if(token != null) {
			if(!token.equals("")) return true; //TODO changer le test
		}
		return false;
	}
	
	
}
