<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>실습 예제</title>
</head>
<body>
	<h2>홀수/짝수를 판별하는 프로그램</h2>
	<%
		int num = 3;
		String result = "";
		
			if(num % 3 ==0){
				result = "홀수";
			}else{
				result = "짝수";
			}
		
	%>
	
	<p>결과는 <%= result %> 입니다.</p>
</body>
</html>