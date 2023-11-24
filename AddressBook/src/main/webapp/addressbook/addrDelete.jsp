<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="abDAO" class="addressbook.AddrBookDAO" scope="application"/>
<% 
	String username = request.getParameter("username");
	abDAO.deleteAddrBook(username);
		
	//삭제후 이동할 페이지 설정 (redirect)
	response.sendRedirect("addrList.jsp");
%>