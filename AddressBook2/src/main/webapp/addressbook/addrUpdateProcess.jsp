<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="addrBook" class="adressbook.AddrBook" />
<!-- setBnum() -->
<jsp:setProperty property="bnum" name="addrBook"/>
<jsp:setProperty property="username" name="addrBook"/>
<jsp:setProperty property="tel" name="addrBook"/>
<jsp:setProperty property="email" name="addrBook"/>
<jsp:setProperty property="gender" name="addrBook"/>
<jsp:useBean id="abDAO" class="adressbook.AddrBookDAO" scope="application"/>

<%
	//수정 처리 메서드 호출
	abDAO.updateAddrBook(addrBook);

	out.println("<script>");
	out.println("alert('수정이 완료되었습니다.')");
	out.println("location.href='addrList.jsp' ");
	out.println("</script>");
%>