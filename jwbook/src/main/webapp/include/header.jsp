<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>header</title>
</head>
<body>
	<!-- 선언문 태그 -->
	<%!
		//전역 변수
		int pageCount = 0;
	
		//함수 정의
		void addCount(){
			pageCount++;
		}
	%>
	
	<%
		//함수 호출
		addCount();
	%>
	<p>이 사이트의 방문은 <%=pageCount %>번째 입니다.</p>
</body>
</html>