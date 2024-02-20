<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>페이지 오류입니다.</title>
</head>
<body>
	<jsp:include page="../header.jsp"/>
	<div class="container my-5">
		<div class="text-center">
			<h1 class="alert-danger p-3 m-3">요청하신 페이지를 찾을 수 없습니다.</h1>
			<p><a href="/productlist.do" class="btn btn-secondary">&laquo; 상품 목록 바로가기</a></p>
		</div>
	</div>
	<jsp:include page="../footer.jsp"/>
</body>
</html>