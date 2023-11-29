<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 목록</title>
<style>
	#container{width: 80%; margin: 30px auto; text-align: center;}
</style>
</head>
<jsp:useBean id="product" class="el.Product"/>
<body>
	<div id="container">
		<h2>상품 목록</h2>
		<hr>
		<form action="selProduct.jsp" method="get">
			<select name="select">
			<!-- <option value="사과">사과</option> -->
			<!-- foreach문 사용하기 -->
			<c:forEach items="${product.productList}" var="product">
				<option><c:out value="${product}"/></option>
			
			</c:forEach>
			<%-- <% 
				for(int i = 0; i < product.getProductList().length; i++){
					out.println("<option>" + product.getProductList()[i] + "</option>");
				}
				// 상향 for문 : for(자료형 변수 : 배열이름) { 출력: 변수}
				for(String item : product.getProductList()){
					out.println("<option>" + item + "</option>");
				}
			%> --%>
			
			
			</select>
			<input type="submit" value="선택">
		</form>
	</div>
</body>
</html>