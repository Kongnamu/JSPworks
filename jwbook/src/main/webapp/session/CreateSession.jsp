<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %> <!-- 없어도됨 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>세션 생성</title>
</head>
<body>
	<h2>세션 생성</h2>
	<!-- session : 내장 객체임 -->
	<p>세션 : <%=session %> </p>
	<!-- JSESSIONID --> 
	<p>세션아이디: <%=session.getId() %></p>
</body>
</html>