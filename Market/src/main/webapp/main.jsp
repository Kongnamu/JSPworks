<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>KH Market</title>
</head>
<body>
	<%-- <%@ include file = "header.jsp" %> --%>
	<jsp:include page="header.jsp"/>
	<div class="container mt-3">
		<h1 class="text-center">웹 마켓에 오신 것을 환영합니다.</h1>
		<div>
			<img src="resources/images/pic.jpg" alt="한소희" class="rounded-3">
		</div>
	</div>
	<jsp:include page="footer.jsp"/>
	<%-- <%@ include file = "footer.jsp" %> --%>
</body>
</html>