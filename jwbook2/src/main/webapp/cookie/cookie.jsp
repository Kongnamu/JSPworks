<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>로그인</h1>
	<hr>
	<form action="process.jsp" method="post">
		<p>
			<label>아이디: </label>
			<input type="text" name="id">
		</p>
		<p>
			<label>비밀번호: </label>
			<input type="password" name="pwd">
		</p>
		<p><input type="submit" value="로그인"></p>
	</form>
</body>
</html>