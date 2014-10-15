<%@page session="true"%>

<!--index.jsp-->
<%
	String user = request.getParameter("user");

	session.setMaxInactiveInterval(24 * 60 * 60);
	session.setAttribute("user", user);
	
	response.sendRedirect("success.jsp");
%>