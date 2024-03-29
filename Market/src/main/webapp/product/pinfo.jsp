<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 정보</title>
<script>
	let addToCart = function(){
		if(confirm("상품을 추문하시겠습니까?")){ //confirm : 확인, 취소 버튼이 생김
			document.addform.submit();
		}else {
			document.addform.reset();
		}
	}
</script>
</head>
<body>
	<jsp:include page="../header.jsp"/>
		<div class="container my-3">
		<h2>상품 정보</h2>
		<div class="row">
			<div class="col-5">
				<img src="../upload/${product.pimage}" alt="" style="width:100%;">
			</div>
			<div class="col-6">
				<h3>${product.pname}</h3>
				<p>${product.description}</p>
				<p><b>상품 코드 : </b><span class="badge bg-dark">${product.pid}</span></p>
				<p><b>분류 : </b>${product.category}</p>
				<p><b>재고 수 : </b>${product.pstock}</p>
				<p><b>가격 : </b><fmt:formatNumber value="${product.price}"/>원</p>
				<form action="/addcart.do?pid=${product.pid}" method="post" name="addform">
					<!-- 상품 주문버튼을 누르면 폼이 전송되야 함 -->
					<a href="#" onclick="addToCart()" class="btn btn-success">상품 주문</a>	
					<a href="/cart.do" class="btn btn-warning">장바구니</a>	
					<a href="/productlist.do" class="btn btn-secondary">상품목록 &raquo;</a>		
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="../footer.jsp"/>
</body>
</html>