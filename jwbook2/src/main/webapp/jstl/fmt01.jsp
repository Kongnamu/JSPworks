<%-- <%@page import="java.util.Date"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jstl format 태그</title>
</head>
<body>
	<h2>날짜를 다양한 형식으로 표기하기</h2>
	<%-- <% Date now = new Date(); %>
	<%=now %> --%>
	<jsp:useBean id="now" class="java.util.Date"/>
	
	<p>현재 날짜 및 시간 : ${now}
	<p><fmt:formatDate value="${now}" type="date"/>
	<p><fmt:formatDate value="${now}" type="time"/>
	<p><fmt:formatDate value="${now}" pattern="yyyy-MM-dd hh:mm:ss a"/>
	
	<h2>숫자 표기</h2>
	<p><fmt:formatNumber value="12300"/>
	<p><fmt:formatNumber value="12300" type="number"/>
	<!-- 통화 : currency -->
	<p><fmt:formatNumber value="12300" type="currency"/>
	<p><fmt:formatNumber value="12300" type="currency" currencySymbol="$"/>
	<p><fmt:formatNumber value="12300" type="percent"/>
	<!-- 형식지정 : 소수자리수 -->
	<p><fmt:formatNumber value="12300.56" pattern="#,##0.0"/>
</body>
</html>