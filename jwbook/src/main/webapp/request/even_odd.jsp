<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	ul li{list-style: none; margin: 10px;}
</style>
</head>
<body>
	<form action="calcProcess.jsp" method="post">
	<h2>홀수/짝수를 판별하는 프로그램</h2>
	<ul>
		<li>
			<label for="num">숫자 </label>
			<input type="text" id="num" name="num">
			<button type="submit">확인</button>
		</li>
		<!-- <li>
			<button type="submit">확인</button>
		</li> -->
	</ul>
</body>
</html>