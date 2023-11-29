<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>0
<jsp:useBean id="abDAO" class="adressbook.AddrBookDAO" scope="application"/>

<%
	String email = request.getParameter("email");
	boolean result = abDAO.checkLogin(email);
	String name = abDAO.getNameByEmail(email); //이메일을 매개변수로 이름(username) 가져오기
	
	if(result){ 
		//인증이 되었으면 세션 발급 (세션이름 = sessinId)
		//이름 세션 발급
		session.setAttribute("sessionId", email);
		session.setAttribute("sessionName", name);
		response.sendRedirect("addrList.jsp"); //주소 목록으로 이동
	}else{
		out.println("<script>");
		out.println("alert('아이디나 비밀번호가 일치하지 않습니다.')");
		out.println("history.go(-1)"); //이전 페이지로 이동 .back()과 같음
		out.println("</script>");
	}

%>