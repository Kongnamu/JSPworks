<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//데이터를 잠시 저장하는 객체 : request
		String id = request.getParameter("id");
		String pw = request.getParameter("pwd");
	%>
	
	<p>아이디: <%=id %></p>
	<p>패스워드: <%=pw %></p>
</body>
</html>