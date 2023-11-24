<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>0
<jsp:useBean id="abDAO" class="adressbook.AddrBookDAO" scope="application"/>

<%
	String email = request.getParameter("email");
	boolean result = abDAO.checkLogin(email);
	
	if(result){ //이메일이 있으면 sessin 발급(세션이름 = sessinId)
		session.setAttribute("sessionId", email);
		response.sendRedirect("addrList.jsp"); //주소 목록으로 이동
	}else{
		out.println("<script>");
		out.println("alert('아이디나 비밀번호가 일치하지 않습니다.')");
		out.println("history.go(-1)"); //이전 페이지로 이동 .back()과 같음
		out.println("</script>");
	}

%>