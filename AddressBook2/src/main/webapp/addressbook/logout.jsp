<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//모든 세션 삭제 : 로그아웃
	session.invalidate();
	
	//로그아웃 후에 index 페이지로 이동
	response.sendRedirect("../index.jsp");
%>